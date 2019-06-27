package com.syngenta.annotation;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.syngenta.annotation.pojo.AnnotationDocument;
import com.syngenta.annotation.pojo.QueryRequest;
import com.syngenta.annotation.utils.MongoDBUtils;

public class Query implements RequestHandler<QueryRequest,List<AnnotationDocument>>{
	private MongoDBUtils mongoDBUtil = new MongoDBUtils();
	private String collectionName ="Annotation";
	

	@SuppressWarnings("unchecked")
	public List<AnnotationDocument> handleRequest(QueryRequest request, Context context) {
	    int limit = request.getLimit();
		MongoDatabase db = mongoDBUtil.getDbConnection();
		ArrayList<AnnotationDocument> docList = new ArrayList<AnnotationDocument>();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		//long totalCount = collection.countDocuments();
		FindIterable<Document> documents = collection.find().limit(limit);		

		for(Document mongoDoc : documents) {
		        AnnotationDocument doc = new AnnotationDocument();
		        doc.setannotationText(mongoDoc.getString("annotation"));
		        doc.setAuthor(mongoDoc.getString("author"));
		        doc.setObjectID(mongoDoc.getObjectId("_id").toString());
		        doc.setprojectId(mongoDoc.getString("project_id"));
		        doc.setCsn(mongoDoc.getString("csn"));
		        doc.setannotationText(mongoDoc.getString("annotation_text"));
		        doc.setCreationDate(mongoDoc.getString("creation_date"));
		        List<String> tags = (List<String>)mongoDoc.get("tags");
		        doc.setTags(tags);
		        docList.add(doc);
		 }
		
		return docList;
	}
	

}
