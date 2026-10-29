package com.Learn.TransformingAPIResponse.Advices;

import com.Learn.TransformingAPIResponse.Exceptions.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundError(ResourceNotFoundException resourceNotFoundException)
    {
        ApiError apiError=ApiError.builder().status(HttpStatus.NOT_FOUND).message(resourceNotFoundException.getMessage()).build();
        //return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        return  BuildErrorResponseEntity(apiError);

    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleOtherExceptions(MethodArgumentNotValidException e)
    {
        List<String> errors= e.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError =ApiError.builder().status(HttpStatus.BAD_REQUEST).message("Please res    olve below error").suberrors(errors).build();
        //return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        return  BuildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolation(ConstraintViolationException e)
    {
        List<String> errors= e.getConstraintViolations().stream().map(error-> error.getMessage()).toList();
        ApiError apiError =ApiError.builder().status(HttpStatus.BAD_REQUEST).message(errors.toString()).build();
        //return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        return  BuildErrorResponseEntity(apiError);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e)
    {
        ApiError apiError =ApiError.builder().status(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        return  BuildErrorResponseEntity(apiError);
       // return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<?>> handleGeneralExceptions(Exception e) {
//
//        List<String> errors = new ArrayList<>();
//
//        Throwable throwable = e;
//
//        while (throwable != null) {
//            errors.add(
//                    throwable.getClass().getSimpleName() +
//                            " : " +
//                            throwable.getMessage()
//            );
//
//            throwable = throwable.getCause();
//        }
//
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .message("An unexpected error occurred")
//                .suberrors(errors)
//                .build();
//
////        return ResponseEntity
////                .status(HttpStatus.INTERNAL_SERVER_ERROR)
////                .body(apiError);
//
//        return  BuildErrorResponseEntity(apiError);
//    }

    private ResponseEntity<ApiResponse<?>> BuildErrorResponseEntity(ApiError apiError) {
        return  new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
