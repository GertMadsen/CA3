
package com.schwertz.carrentalservice.interfaces.web.rest.shared;

import com.schwertz.carrentalservice.interfaces.web.rest.dtos.PayloadDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom REST request and response exception handler. By custom we mean, not
 * relative to MVC framework used.
 *
 * @author mani
 */
@ControllerAdvice("com.schwertz.carrentalservice.interfaces.web.rest.controllers")
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(
	  CustomExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status,
	  WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
	  MissingServletRequestPartException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    List<String> errors = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      errors.add(error.getDefaultMessage());
    }
    return new ResponseEntity(new PayloadDto.Builder().errors(errors).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
	  HttpMessageNotWritableException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
	  HttpMessageNotReadableException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
	  HttpHeaders headers, HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
	  MissingPathVariableException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
	  HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
	  HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
	  ServletRequestBindingException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	  HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
	  HttpStatus status, WebRequest request) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    status);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
	  AsyncRequestTimeoutException arte, HttpHeaders hh, HttpStatus hs,
	  WebRequest wr) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    arte.getMessage())).build(),
	    hs);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException be,
	  HttpHeaders hh, HttpStatus hs, WebRequest wr) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    be.getMessage())).build(),
	    hs);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
	  ConversionNotSupportedException cnse, HttpHeaders hh, HttpStatus hs,
	  WebRequest wr) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    cnse.getMessage())).build(),
	    hs);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
	  Object o, HttpHeaders hh, HttpStatus hs, WebRequest wr) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    ex.getMessage())).build(),
	    hs);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(
	  EntityNotFoundException exception) {

    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    exception.getMessage())).build(), HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle's only JSR 303 contraint violocation exceptions.
   *
   * @param exception
   * @return
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(
	  ConstraintViolationException exception) {

    List<String> errors = new ArrayList<>();
    for (ConstraintViolation error : exception.getConstraintViolations()) {
      errors.add(error.getMessage());
    }
    return new ResponseEntity(new PayloadDto.Builder().errors(errors).build(),
	    HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles every other exceptions.
   *
   * @param exception
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleOtherException(
	  Exception exception) {

    LOGGER.info(exception.getMessage());
    return new ResponseEntity(new PayloadDto.Builder().errors(Arrays.asList(
	    exception.getMessage())).build(), HttpStatus.BAD_REQUEST);
  }
}
