package com.syngenta.annotation;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.syngenta.annotation.pojo.TagQueryRequest;
import com.syngenta.annotation.utils.MongoDBUtils;
import com.syngenta.annotation.pojo.TagDocument;


public class TagQuery implements RequestHandler<TagQueryRequest,List<TagDocument>>{
	private MongoDBUtils mongoUtil = new MongoDBUtils();
	private String collectionName ="Tag";
	
	
	public List<TagDocument> handleRequest(TagQueryRequest request, Context context) {
		ArrayList<TagDocument> existingTags = new ArrayList<TagDocument>();
		MongoDatabase db = mongoUtil.getDbConnection();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> tagDocuments = collection.find();
		for (Document tagDoc : tagDocuments){
			TagDocument doc = new TagDocument();
			doc.setId(tagDoc.get("_id").toString());
			doc.setDescription(tagDoc.getString("description"));
			existingTags.add(doc);
		}
		return existingTags;
	}
	

}
