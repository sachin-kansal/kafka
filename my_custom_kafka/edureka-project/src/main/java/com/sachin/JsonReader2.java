
/*
 * incomplete with issues
 */

package com.sachin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonReader2 {
    public json_obj[] JsonRead(String path){

        // String filepath = ".\\edureka-project\\src\\main\\resources\\products.json";
        try{
        byte[] jsonData = Files.readAllBytes(Paths.get(path));
        InputStream inputStream = new ByteArrayInputStream(jsonData);
        ObjectMapper objectMapper = new ObjectMapper();
        json_obj[] products = objectMapper.readValue(inputStream, json_obj[].class);
            System.out.print(products[0]);
        return products;
        
    }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
}
}
