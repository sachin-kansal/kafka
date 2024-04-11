package com.sachin;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;



public class JsonProducer {
    public static void main (String args[]){
        Properties props = new Properties();
        props.put("bootstrap.servers","localhost:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","com.sachin.JsonPozoSerialiser");
        props.put(ProducerConfig.ACKS_CONFIG, "1");

        try {
            Producer <String,json_obj> jsonproducer = new KafkaProducer<>(props);
        
            JsonReader2 jreader = new JsonReader2();
            json_obj[] products= jreader.JsonRead(".\\edureka-project\\src\\main\\resources\\products.json");
            int i=0;
            while (i<products.length) {
                ProducerRecord <String,json_obj> records = new ProducerRecord<String,json_obj>("jtop",products[i]);
                i++;
                jsonproducer.send(records, new Callback(){
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e){
                    if (e==null){
                        System.out.println(metadata.topic()+"\n"+ metadata.partition() + metadata.serializedValueSize());
                    }
                    else{
                        System.out.println(e);
                    }
                }});
        jsonproducer.flush();        
        }
        jsonproducer.close();
    }catch (Exception e ){
        e.printStackTrace();
        }
    }
}
