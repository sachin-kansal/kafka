package com.sachin;
import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
public class producer {     
    public static void main(String[] args){
        System.out.println("start");
        String topicname= "sachin";
       // String key= "k1";
    // properties of producer application
    Properties props = new Properties();
    //props.put();
    props.setProperty(ProducerConfig.CLIENT_ID_CONFIG,"Producer1");
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    props.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG,"30000");
    try(KafkaProducer <String,String> producer1 = new KafkaProducer<>(props)){
    ProducerRecord <String,String> record = new ProducerRecord<>(topicname,"hi brother");
    producer1.send(record, new Callback() {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception e){
            if (e==null){
                System.out.println(metadata.topic()+ metadata.partition() + metadata.serializedValueSize());
            }
            else{
                System.out.println(e);
            }
        }
    });
    producer1.flush();    
    }catch(Exception e){
        e.printStackTrace();
    }
    System.out.println("Producer execution completed");
} 
}
