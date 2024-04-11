package com.sachin;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer {
    public static void main(String[] args){
        Properties  cons_props = new Properties();
        cons_props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        cons_props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        cons_props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        cons_props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumerapi");
        cons_props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        cons_props.setProperty(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, "6000");


        KafkaConsumer <String,String> consumer = new KafkaConsumer<>(cons_props);
        consumer.subscribe(Collections.singletonList("sachin"));

        try{while (true){
            ConsumerRecords <String,String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord <String,String> record: records){
                System.out.println(record.topic()+" "+record.key()+" "+record.value());
            }
        }     
        }finally{
        consumer.close();
        }
    }
}
