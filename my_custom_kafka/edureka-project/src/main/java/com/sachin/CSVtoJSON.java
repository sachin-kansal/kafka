package com.sachin;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * database is provided in csv hence needs to be converted into json 
 * the following piece of code reads the csv file and converts the data into json format 
 * as there are multiple instance the json contains a array of all json object
 * then stores it into the products.json
 */

class CSVtoJSON{
    public static void main(String[] args){

        try{
            File obj = new File("./edureka-project/src/main/resources/products.csv");
            File jfile = new File("./edureka-project/src/main/resources/products.json");
            if (jfile.exists()){
                throw new Exception("Json File already exists");
            }
            else{
                jfile.createNewFile(); // file is created
            FileWriter writer = new FileWriter(jfile, true);  //writer 
            //csv file is bieng read
            Scanner Reader = new Scanner(obj); 
            String header = Reader.nextLine();
            String[] jkeys = header.split(","); 
            writer.write("[");
            while (Reader.hasNextLine()) {
                String[] v_data = Reader.nextLine().split(",");
                Map <String,String> json_obj = new HashMap<>();  // converting csv to map
                for (int i=0; i<=12; i++){
                    json_obj.put(jkeys[i],v_data[i]);
                }
                //map to json
                ObjectMapper mapper = new ObjectMapper();
                String jstring=mapper.writeValueAsString(json_obj);
                writer.write(jstring);
                if (Reader.hasNextLine()){ 
                    writer.write(",\n");
                }else{
                    writer.write("\n");
                }
               }
            Reader.close();
            writer.write(']');
            writer.close();
           }
        }catch (Exception  e){
            System.out.println(e);
        }
    }
}
