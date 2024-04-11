package com.sachin;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPozoDeserialiser implements Deserializer<json_obj>{

    @Override
    public json_obj deserialize(String topic, byte[] data) {
        // TODO Auto-generated method stub
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(data,json_obj.class);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
        }
        
    }
    
}
