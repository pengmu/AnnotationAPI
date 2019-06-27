package com.syngenta.annotation;

import java.io.FileNotFoundException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syngenta.annotation.pojo.ViewerRequest;
import com.syngenta.annotation.utils.HTMLResourceFileUtil;

public class Viewer implements RequestHandler<ViewerRequest,String>{
	String htmlResponse = "";
	String viewHtml = "html/View.html";
	HTMLResourceFileUtil htmlUtil = new HTMLResourceFileUtil();
	
	public String handleRequest(ViewerRequest request, Context context) {

		if(htmlResponse==""){
			try {
				htmlResponse = htmlUtil.readHtmlFromResource(viewHtml);
			} catch (FileNotFoundException e) {
				context.getLogger().log(e.getMessage());
			}
			
		}
		
		return htmlResponse;
	}

}
