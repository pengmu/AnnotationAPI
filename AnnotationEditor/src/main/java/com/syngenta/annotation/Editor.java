package com.syngenta.annotation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syngenta.annotation.pojo.EditorRequest;
import com.syngenta.annotation.utils.HTMLResourceFileUtil;
import java.io.FileNotFoundException;



public class Editor implements RequestHandler<EditorRequest,String> {
	String namePlaceHolder = "\\{Author\\}";
	String projectPlaceHolder ="\\{Project\\}";
	String csnPlaceHolder = "\\{CSN\\}";
	
	String htmlResponse = "";
	String editorHtml = "html/Editor.html";
	HTMLResourceFileUtil htmlUtil = new HTMLResourceFileUtil();
	
	@Override
	public String handleRequest(EditorRequest request, Context context){
		String userName = request.getUserName();
		String projectId = request.getProjectId();
		String csn = request.getCsn();
		LambdaLogger logger = context.getLogger();
		
		//logger.log("received request for csn: "+ request.getCsn()+" for project :"+request.getProjectId()+" from user: "+request.getUserName());
		if(htmlResponse==""){
			try {
				htmlResponse = htmlUtil.readHtmlFromResource(editorHtml);
			} catch (FileNotFoundException e) {
				logger.log(e.getMessage());
			}
			
		}
		String result = htmlResponse;
		result = (userName!=null&&userName!="")? result.replaceAll(namePlaceHolder,"value=\""+request.getUserName()+'"'):result;
		result = (projectId!=null&&projectId!="")? result.replaceAll(projectPlaceHolder,"value=\""+request.getProjectId()+'"'):result;
		result = (csn!=null&&csn!="")? result.replaceAll(csnPlaceHolder,"value=\""+request.getCsn()+'"'):result;
		
		return result;
	}



}
