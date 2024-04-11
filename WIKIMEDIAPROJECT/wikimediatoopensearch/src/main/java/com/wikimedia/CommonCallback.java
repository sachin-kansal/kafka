package com.wikimedia;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CommonCallback {

    public Callback myCallback() {
    Callback callback = new Callback() {

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if(exception == null){
                System.out.println(metadata.topic());
                System.out.println(metadata.partition());
            }
        }

    };
    return callback;
}
}
