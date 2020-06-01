package com.marvel.xavier.services;

import com.marvel.xavier.models.Producer;
import com.marvel.xavier.models.Task;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope("singleton")
public class ProducerServiceImpl implements ProducerService {
    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;
    private final TaskExecutor taskExecutor;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${max.queue.size}")
    private int maxQueueSize = 100;
    @Value("${queue.name}")
    private String queueName;
    private List<Producer> producers = new LinkedList<>();

    public ProducerServiceImpl(TaskExecutor taskExecutor, AmqpTemplate amqpTemplate, AmqpAdmin amqpAdmin) {
        this.taskExecutor = taskExecutor;
        this.amqpTemplate = amqpTemplate;
        this.amqpAdmin = amqpAdmin;
    }

    /**
     * Method that returns all the existing producers
     *
     * @return All the existing producers
     */
    @Override
    public List<Producer> getProducers() {
        return this.producers;
    }

    /**
     * Method that creates a new producer, adds the created producer in the producers list
     * and prompts the created producer to start producing tasks
     *
     * @param producerName The name of the created producer
     * @return The created producer
     */
    @Override
    public Producer createProducer(String producerName) {
        Producer producer = new Producer(producerName);

        System.out.println("--------------------------------------------------");
        System.out.println("Producer created");
        System.out.println("Producer id: " + producer.getId());
        System.out.println("Producer name: " + producer.getName());
        System.out.println("--------------------------------------------------");

        producers.add(producer);

        taskExecutor.execute(() -> {
            produceTasks(producer);
        });

        return producer;
    }

    /**
     * Method that produces new tasks.
     * A producer is passed as parameter in this method.
     * The method initially checks whether the queue has available space for new tasks.
     * If no avaialbles space exists the producer is put to sleep for 3 seconds and then checks again.
     * If there is avaialble space the producer produces a new task and sends the task to the RabbitMQ queue.
     *
     * @param producer The producer which will produce tasks
     */
    private void produceTasks(Producer producer) {
        while (true) {
            try {
                while (this.isQueueFull()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Queue is full.");
                    System.out.println("Producer name: " + producer.getName() + " going to sleep.");
                    System.out.println("--------------------------------------------------");
                    Thread.sleep(3000);
                }

                Task task = producer.produceTask();

                amqpTemplate.convertAndSend(this.exchange, this.routingKey, task.getId());

                System.out.println("--------------------------------------------------");
                System.out.println("Task created");
                System.out.println("Task id: " + task.getId());
                System.out.println("Producer name: " + producer.getName());
                System.out.println("--------------------------------------------------");
            } catch (Exception e) {

            }
        }
    }

    /**
     * Method that checks if the queue in which the tasks are stored in is full
     *
     * @return Boolean value which denotes if the queue is full or not
     */
    private boolean isQueueFull() {
        int queueSize = (int) amqpAdmin.getQueueProperties(queueName).get("QUEUE_MESSAGE_COUNT");

        return queueSize >= maxQueueSize;
    }
}
