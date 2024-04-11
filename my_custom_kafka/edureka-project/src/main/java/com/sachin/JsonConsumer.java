package com.sachin;


import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class JsonConsumer {
    public static void main(String args[]){
        Properties props = new Properties();
        props.put("group.id", "catalogue");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.sachin.JsonPozoDeserialiser");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        try (KafkaConsumer<String,json_obj> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("jtop"));

            while (true){
                ConsumerRecords<String,json_obj> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, json_obj> record : records){
                    json_obj product = record.value();
                    System.out.println("revieved"+product.getbrand());
                }
            }
        }catch(Exception e){
                System.out.println(e);
            }
    }
}
