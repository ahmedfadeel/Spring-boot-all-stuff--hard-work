package com.example.ahmed.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionDetail {
	
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
 private Date time;

 private String message;
 private String path;

 
 public  ExceptionDetail( String msg,String uri ) {
	 this.time=new Date();
	 this.message=msg;
	 this.path=uri;
    
 }
 
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
 
 
}
