package com.example.ahmed.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}
	
      // if  a resourseNotfound Exception is thrown this method is called 
    @ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	protected  ResponseEntity<ExceptionDetail> handleResourceNotFoundException
	(ResourceNotFoundException ex, WebRequest request){
		String Uri=((ServletWebRequest)request).getRequest().getRequestURI().toString();
		ExceptionDetail ExceptionDetail=new  ExceptionDetail(ex.getMessage(),request.getDescription(false));
		return new  ResponseEntity<> (ExceptionDetail, HttpStatus.NOT_FOUND);
	}
    
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
     
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
    
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, 
      HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        
        ApiError apiError = 
          new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
          apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, 
      HttpHeaders headers, 
      HttpStatus status, 
      WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
          " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
     
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, 
          ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<Object>(
          apiError, new HttpHeaders(), apiError.getStatus());
    }

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                    .collect(Collectors.toList());
        BindingValidation bindings=new BindingValidation(request.getDescription(false),details);
        return new ResponseEntity<Object>(
        		bindings,HttpStatus.BAD_REQUEST);
          }

	}




