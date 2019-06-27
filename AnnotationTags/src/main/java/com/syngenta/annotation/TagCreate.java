package com.syngenta.annotation;


import org.bson.Document;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syngenta.annotation.pojo.TagDocument;
import com.syngenta.annotation.utils.MongoDBUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class TagCreate implements RequestHandler<TagDocument,String> {
	private MongoDBUtils mongoDBUtil = new MongoDBUtils();
	private String collectionName ="Tag";
	public String handleRequest(TagDocument input, Context context) {
		MongoDatabase db = mongoDBUtil.getDbConnection();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		//List<Document> docs = generateInitialDocuments(context);
		Document doc = generateDocument(input,context);
		collection.insertOne(doc);
		
		return doc.get("_id").toString();
	}
	
	private Document generateDocument(TagDocument input, Context context) {
		Document doc = new Document();
		doc.append("_id", input.getId());
		doc.append("description", input.getDescription());
		return doc;
	}

	/**
	private List<Document> generateInitialDocuments(Context context){
		ArrayList<Document> docs = new ArrayList<Document>();
		ClassLoader classLoader = getClass().getClassLoader();
		File tagsFile = new File(classLoader.getResource("tags/tags.csv").getFile());
		try {
			Scanner scanner = new Scanner(tagsFile);
			
			while(scanner.hasNextLine()){
				String tag = scanner.nextLine().trim();
				if(tag.startsWith("#")){
					tag = tag.substring(1);
				}
				Document document = new Document();
				document.append("_id", tag);
				document.append("description", "Description for #"+tag);
				docs.add(document);
				
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			
			context.getLogger().log(e.getMessage());
		}
		
		
		return docs;
	}
	**/
	
	
	

}
