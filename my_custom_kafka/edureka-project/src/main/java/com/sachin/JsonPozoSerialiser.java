package com.sachin;
/*
 * Json Object are in pozo objects and are now biengserilised for producer
 */


import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonPozoSerialiser implements Serializer<json_obj>{
    private final ObjectMapper objectmapper= new ObjectMapper();

    @Override
    public byte[] serialize(String topic, json_obj data) {
        try{
        return objectmapper.writeValueAsBytes(data);
        }
        catch (Exception e){
        throw new UnsupportedOperationException("Unimplemented method 'serialize'", e);
        }
    }
}