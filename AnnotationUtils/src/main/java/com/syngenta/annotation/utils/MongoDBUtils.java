package com.syngenta.annotation.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtils {
	private String mongoClusterURI ="mongodb://annotation_user:annotation_user"
			+ "@annotationrepositpory-shard-00-00-symgt.mongodb.net:27017,"
			+ "annotationrepositpory-shard-00-01-symgt.mongodb.net:27017,"
			+ "annotationrepositpory-shard-00-02-symgt.mongodb.net:27017"
			+ "/test?ssl=true&replicaSet=AnnotationRepositpory-shard-0&authSource=admin&retryWrites=true ;";
	private String dbName="AnnotationRepository";
	private MongoClient mongoClient = null;
	public MongoDatabase getDbConnection() {
		MongoClientOptions.Builder destDboptions = MongoClientOptions.builder();
		if(mongoClient==null){
			mongoClient = new MongoClient(new MongoClientURI(mongoClusterURI, destDboptions));
		}
		MongoDatabase db = mongoClient.getDatabase(dbName);
		return db;
	
	}
}
