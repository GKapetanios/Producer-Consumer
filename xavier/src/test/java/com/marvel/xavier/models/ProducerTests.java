package com.marvel.xavier.models;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerTests {
    @Test
    void testTaskCreation() {
        Producer fakeProducer = new Producer("Wolverine");
        Task fakeTask = fakeProducer.produceTask();

        Assert.assertEquals(fakeProducer.getId(), fakeTask.getProducerId());
    }
}
