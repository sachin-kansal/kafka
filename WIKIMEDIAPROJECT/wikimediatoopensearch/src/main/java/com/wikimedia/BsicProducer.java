package com.wikimedia;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

public class BsicProducer {
    //private static final org.slf4j.Logger log =  LoggerFactory.getLogger(BsicProducer.class.getSimpleName());

    public static void main(String[] args) {
   // log.info("logger working");
    //System.out.println("Hello world!");
    KafkaTopic topic1 = new KafkaTopic("topic1");
    KafkaTopic topic2 = new KafkaTopic();
    System.out.println(topic1.getter() + "  "+ topic2.getter());

    CommonCallback producercallback = new CommonCallback();

    Properties producer1Properties = new Properties();
    //producer1Properties.putAll();
    producer1Properties.put("bootstrap.servers", "127.0.0.1:9093");
    producer1Properties.setProperty("key.serializer", StringSerializer.class.getName());
    producer1Properties.setProperty("value.serializer", StringSerializer.class.getName());
    producer1Properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());

    Producer <String,String> myproducer = new KafkaProducer<>(producer1Properties);

    ProducerRecord <String,String> message1 = new ProducerRecord<>(topic1.getter(), "k1", "record2");
    myproducer.send(message1,producercallback.myCallback());

    myproducer.close();
    }
}