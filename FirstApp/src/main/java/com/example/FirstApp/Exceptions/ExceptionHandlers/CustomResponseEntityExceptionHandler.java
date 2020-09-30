package com.example.FirstApp.Exceptions.ExceptionHandlers;

import com.example.FirstApp.Exceptions.ApiError;
import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import javax.validation.Path.Node;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        return buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED,builder.toString(),ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        String error = "JSON request is not readable - incompatible types"; // srediti to da se bas detaljno zna, npr koje polje je pogresno napisano
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Validation problem: "; // moras detaljnije sa vise podataka kasnije to ApiError
        error += ex.getBindingResult().getFieldError().getDefaultMessage();
        error += " (rejected value {"+ex.getBindingResult().getFieldError().getField()+" = "+ex.getBindingResult().getFieldError().getRejectedValue()+"})";
        return  buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,ex));//422 a bad request je 400 jer nemam 422 ponudeno, kako cu ? warning 100%
    }

    /*@Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>("nisam bio pozvan iako nije bilo odgovarajuce metode iznad ",HttpStatus.BAD_REQUEST);
    }*/


    // #### CUSTOM EXCEPTION HANDLERS   ###

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex)
    {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object>  onConstraintValidationException(ConstraintViolationException ex)
    {
        //MethodArgumentNotValidException exp = ex;
        String error = "Validation problem: "; // moras detaljnije sa vise podataka kasnije to ApiError
        String fieldName = null;

        for(ConstraintViolation violation: ex.getConstraintViolations())
        {
            // get the last node of the violation stupid way :S
            ConstraintViolation<?> v = ex.getConstraintViolations().iterator().next();
            for (Node node : v.getPropertyPath()) {
                fieldName = node.getName();
            }
            //######

            //error += "ex.getBindingResult().getFieldError().getDefaultMessage()";
            error += v.getMessageTemplate();
            error += " (rejected value {"+fieldName+" = "+v.getInvalidValue()+"})";

        }
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,ex));
    }

    @ExceptionHandler({ TransactionSystemException.class }) // because service layer made a mistake, then return transactional exception back.
    public ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        if (cause instanceof ConstraintViolationException) {
            constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
        }
        return buildResponseEntity(new ApiError(HttpStatus.EXPECTATION_FAILED,"LOWER LAYER MISTAKE: "+constraintViolations.toString(),ex));
    }

    @ExceptionHandler({ IllegalArgumentException.class }) // because service layer made a mistake, then return transactional exception back.
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,"Output Not Implemented",ex));
    }


    //
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
