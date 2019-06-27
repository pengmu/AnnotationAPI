package com.syngenta.annotation;

import java.io.FileNotFoundException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syngenta.annotation.pojo.TagQueryRequest;
import com.syngenta.annotation.utils.HTMLResourceFileUtil;

public class TagView implements RequestHandler<TagQueryRequest,String>{
	String htmlResponse = "";
	String tagViewHtml = "html/TagView.html";
	HTMLResourceFileUtil htmlUtil = new HTMLResourceFileUtil();
	
	public String handleRequest(TagQueryRequest input, Context context) {
		LambdaLogger logger = context.getLogger();
		if(htmlResponse==""){
			try {
				htmlResponse = htmlUtil.readHtmlFromResource(tagViewHtml);
			} catch (FileNotFoundException e) {
				logger.log(e.getMessage());
			}
			
		}
		
		return htmlResponse;
	}

}
