# Producer Consumer Implementation

This project has as scope to provide a solution for the Producer Consumer problem using the Spring Boot Framework and a micro-services architecture. The implementation is done with the use of three services. The **Xavier** service which is the producer component, the **RabbitMQ** service which is the queue/storage component and the **Magneto** service which is the consumer component.

## Services

 - **Xavier:** This service is responsible for creating producers which will in their own turn create tasks and add them in the queue. This service is responsible for synchronizing the actions of the producers and ensuring that the producers will not try and create new tasks if the queue has no available space. This service also provides a RESTful API which exposes two endpoints, one for creating a new producers and one for listing the existing producers. Finally this service communicates with the RabbbitMQ service when a new task is created so as to store the task in the queue.
 - **RabbitMQ:** This service is completely unaware of what the other services do and just acts like the middle-man between them. This service's purpose is to store the tasks that are produced by the producers and keep them there to be consumed by the cosumers. Also this service provides metrics for how many producers and consumers are connected to it as well as runtime metrics regarding the rate that the tasks are going in and out of the queue. 
 - **Magneto:** This service is responsible for consuming tasks that are stored in the queue. This service monitors the queue and only tries to consume tasks from it if the queue is not empty.

## Xavier API Documentation

`GET /producers`

Description: This API call provides the list of all the existing producers

Response code: 200

Response type: JSON

Example response: `[{"id": "692f457b-679c-48aa-ba33-b493cd0c7a77", "name": "Wolverine"}, {"id": "243b78be-8e32-485e-a056-0de66a40fc77", "name": "Cyclops"}]`

`POST /producers`

Description: This API call creates a new producer

Example body: `[{"name": "Wolverine"}]`

Response code: 200

Response type: JSON

Example response: `[{"id": "692f457b-679c-48aa-ba33-b493cd0c7a77", "name": "Wolverine"}]`

## How to Deploy/Run the Project

**RabbitMQ Service**
 1. Download the RabbitMQ docker image by executing the following command in a command line
`docker pull rabbitmq`
 2. Launch the RabbitMQ service with its management console by executing the following command in a command line
`docker run -d -p 15672:15672 -p 5672:5672 --name {name of the container} rabbitmq:3-management`
 3. Navigate through a web browser to "localhost/15672" and log in the RabbitMQ console with the deafult credentials. (Username and Password set as guest)
 4. Go to the exchanges tab and add a new exchange with the name "tasks.exchange"
 5. Go to the queues tab and add a new queue with the name "Tasks"
 6. Add a new binding for the new queue on the new exchange with the routing key value "key"

**Xavier Service**

 1. Navigate to the root directory of the project
 2. Execute the following command to generate the jar file
 `mvnw install`
 3. Execute the following command to run the project `mvnw spring-boot:run`
 4. Use the API call (See Documentation Section) to create producers which will start producing tasks.

**Magneto Service**

 1. Navigate to the root directory of the project
 2. Execute the following command to generate the jar file
 `mvnw install`
 3. Execute the following command to run the project `mvnw spring-boot:run`

**Monitoring the Project**

You can use the Xavier's service console log to see actions of the producers.

You can use the Magneto's service console log to see the actions of the consumer.

You can use the RabbitMQ management console to view the connected producers and consumers as well as the tasks that go in and out of the queue.

## Enhancements

 1. Eliminate the check of whether the queue has available space from the producer service and let the consumer service notify it that there is available space in the queue.
 2. Modify the consumer service so as to also be able to spawn more than one instances of consumer instead of just creating multiple instances of that service.
 3. Dockerize the Xavier and Magneto service to work in conjuction with the RabbitMQ service.
