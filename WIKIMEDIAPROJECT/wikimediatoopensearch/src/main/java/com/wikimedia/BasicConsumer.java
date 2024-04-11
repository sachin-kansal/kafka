package com.wikimedia;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

public class BasicConsumer {

    public static void main(String[] args) {


        Properties consumerProperties = new Properties();
        consumerProperties.setProperty("group.id", "group1");
        consumerProperties.setProperty("key.deserializer", StringDeserializer.class.getName());
        consumerProperties.setProperty("value.deserializer", StringDeserializer.class.getName());
        consumerProperties.setProperty("bootstrap.servers", "127.0.0.1:9093,127.0.0.2:9093");

        Consumer <String,String>myConsumer = new KafkaConsumer<>(consumerProperties);

        final Thread maiThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
			public void run(){
                myConsumer.wakeup();
                try{
                    maiThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        ); // shutdown hook added

        myConsumer.subscribe(Arrays.asList("topic1"));
        try{
        while (true){
        ConsumerRecords<String, String> outputs = myConsumer.poll(Duration.ofMillis(3000));
            for (ConsumerRecord<String, String> output: outputs){
                System.out.println(output.value());
            }
        }
        }catch (WakeupException w){
            w.printStackTrace();
        }finally{
            myConsumer.close(Duration.ofMillis(3000));
        }
        myConsumer.close();
    }
}
