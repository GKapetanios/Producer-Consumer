package com.marvel.magneto.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {
    /**
     * Method that subscribes to a RabbitMQ queue and consumes task ids that are inserted in that queue
     *
     * @param taskId The id of the task that is consumed each time
     */
    @RabbitListener(queues = "${queue.name}")
    public void consume(String taskId) {
        System.out.println("--------------------------------------------------");
        System.out.println("Task consumed");
        System.out.println("Task id: " + taskId);
        System.out.println("--------------------------------------------------");
    }
}
