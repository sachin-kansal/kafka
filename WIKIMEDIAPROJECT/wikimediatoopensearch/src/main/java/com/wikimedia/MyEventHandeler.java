package com.wikimedia;

import java.time.Duration;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;



public class MyEventHandeler implements EventHandler{

    Producer<String, String> producer;
    String topic;

    public MyEventHandeler(Producer<String,String> producer, String topic){
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public void onClosed() {
        // TODO Auto-generated method stub
        producer.close(Duration.ofMillis(30000));

    }

    @Override
    public void onComment(String arg0) throws Exception {
        // TODO Auto-generated method stub

    }

@Override
    public void onError(Throwable arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(arg0);
    }

@Override
    public void onMessage(String event, MessageEvent message)  {
        producer.send(new ProducerRecord<>(topic, message.getData()));
    }
@Override
    public void onOpen()  {
        // TODO Auto-generated method stub

    }

}
