package com.wikimedia;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

public class WikiProducer{

        public static void main(String[] args) throws URISyntaxException, InterruptedException {
   // log.info("logger working");
    //System.out.println("Hello world!");
    KafkaTopic topic1 = new KafkaTopic("wiki.ingester");
    Properties producer1Properties = new Properties();
    //producer1Properties.putAll();
    producer1Properties.put("bootstrap.servers", "127.0.0.1:9093,127.0.0.2:9093");
    producer1Properties.setProperty("key.serializer", StringSerializer.class.getName());
    producer1Properties.setProperty("value.serializer", StringSerializer.class.getName());
    producer1Properties.setProperty("partitioner.class", RoundRobinPartitioner.class.getName());
    // 0-> fire and forget , 1-> leader confirmation, -1 or all -> all insync replicas
    // min.insync.replica --> no. of min.insync replica in brokers
    producer1Properties.setProperty("acks","all");
    //
    producer1Properties.setProperty("producer.retries", "5");
    producer1Properties.setProperty("enable.idempotence", "True");
    producer1Properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "wiki.producer");

    Producer <String,String> myproducer = new KafkaProducer<>(producer1Properties);

    EventHandler eventhandeler = new MyEventHandeler(myproducer, topic1.getter());
    URI url= new URI("https://stream.wikimedia.org/v2/stream/recentchange");
    EventSource.Builder builder = new EventSource.Builder(eventhandeler,url);
    EventSource eventSource = builder.build();

    eventSource.start();
    TimeUnit.MINUTES.sleep(10);

}
}
