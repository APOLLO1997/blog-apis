package com.shikhar.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.shikhar.blog.payloads.ApiResponse;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourcNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message =ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message =error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ApiResponse> numberFormatExceptionHandler(NumberFormatException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
}
