package com.wikimedia;



public class KafkaTopic {

    String topic = "default";

    public KafkaTopic(String topicvalue){
        this.topic = topicvalue;
    }
    public KafkaTopic(){

    }

    public String getter(){
        return this.topic;
    }

}
