package com.syngenta.annotation.pojo;
import java.util.List;

public class AnnotationDocument {
	String objectID;
	String author;
	String projectId;
	String csn;
	String annotationText;
	List<String> tags;
	String creationDate;
	
	public String getObjectID() {
		return objectID;
	}
	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getprojectId() {
		return projectId;
	}
	public void setprojectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCsn() {
		return csn;
	}
	public void setCsn(String csn) {
		this.csn = csn;
	}
	public String getannotationText() {
		return annotationText;
	}
	public void setannotationText(String annotationText) {
		this.annotationText = annotationText;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
}
