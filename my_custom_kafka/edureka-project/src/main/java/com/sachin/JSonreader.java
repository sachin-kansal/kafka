package com.sachin;

import java.io.File;
import java.util.Scanner;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSonreader {
    public static void main(String[] args)
    {
        // file reading
        File j_file = new File(".\\edureka-project\\src\\main\\resources\\products.json");
        try {
            Scanner j_file_reader = new Scanner(j_file);
            while (j_file_reader.hasNextLine()){
            String s_arr =  j_file_reader.nextLine();
                // cleaning the json object while getting out of json array
                if (s_arr.endsWith("]")) s_arr=s_arr.substring(0,s_arr.length()-1);
                if (s_arr.endsWith("},"))s_arr=s_arr.substring(0,s_arr.length()-1);
                if (s_arr.startsWith("["))s_arr=s_arr.replaceFirst("\\[", "");
                
            
                ObjectMapper objectMapper = new ObjectMapper();
                try{
                    json_obj product = objectMapper.readValue(s_arr, json_obj.class);
                System.out.print(product);
                }catch(JsonMappingException e){
                        System.out.print(e);
                        break;
                }catch(JsonProcessingException e){
                        System.out.println(e);
                        break;
                }
            }
            
            j_file_reader.close();
        }
        catch (Exception e)
        {
         e.printStackTrace();   
        }

    }
}
