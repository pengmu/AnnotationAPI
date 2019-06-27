package com.syngenta.annotation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.syngenta.annotation.pojo.AnnotationDocument;
import com.syngenta.annotation.utils.MongoDBUtils;

public class AnnotationCreate implements RequestHandler<AnnotationDocument,String>{
	private MongoDBUtils mongoDBUtil = new MongoDBUtils();
	private String collectionName ="Annotation";

	public String handleRequest(AnnotationDocument input,  Context context) {
		MongoDatabase db = mongoDBUtil.getDbConnection();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		Document doc = generateMongoDocument(input);
		collection.insertOne(doc);
		return doc.get("_id").toString();
	}
	
	private Document generateMongoDocument(AnnotationDocument doc){
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		List<String> tags = new ArrayList<String>();
		for (String tag : doc.getTags()){
			tags.add(tag);
		}
		Document document = new Document();
		document.append("author",doc.getAuthor());
		document.append("project_id", doc.getprojectId());
		document.append("csn", doc.getCsn());
		document.append("annotation_text", doc.getannotationText());
		document.append("tags", tags);
		document.append("creation_date", timeStamp);
		return document;
	}
	
}
