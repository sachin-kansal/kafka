package com.wikimedia.myconnectors;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class Mydataschema {

	public static Schema mysfirstschema = SchemaBuilder.struct().name("myschemaname").field("firstfield",Schema.STRING_SCHEMA).build();
}
