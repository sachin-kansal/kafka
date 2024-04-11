package com.sachin;


import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;

import org.apache.kafka.clients.admin.TopicListing;

public class topiclister {
    public static void main(String[] args){
        Properties prop = new Properties();
        prop.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        
        try(AdminClient adminClient = AdminClient.create(prop)){
            ListTopicsOptions options = new ListTopicsOptions()
                    .listInternal(true);

            Map<String, TopicListing> topicListingMap = adminClient.listTopics(options).namesToListings().get();
            for (Map.Entry<String, TopicListing> entry : topicListingMap.entrySet()) {
                System.out.println("Topic: " + entry.getKey() + ", TopicListing: " + entry.getValue());
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        }
    }
