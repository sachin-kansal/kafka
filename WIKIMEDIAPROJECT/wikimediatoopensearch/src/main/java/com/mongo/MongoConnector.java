package com.mongo;

import com.mongo.*;
import com.mongodb.*;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;

import org.bson.*;

public class MongoConnector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoClient mongoclient = MongoClients.create("mongodb://localhost:27017/");
		ListDatabasesIterable<Document> a = mongoclient.listDatabases();
		a.nameOnly(true);
		a.filter(null);
		MongoCursor<Document> b = a.cursor();
		while (b.hasNext()) {
		System.out.print(b.next());
		}
	}

}

