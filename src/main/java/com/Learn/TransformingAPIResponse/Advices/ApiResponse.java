package com.Learn.TransformingAPIResponse.Advices;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ApiResponse<T>{

    private LocalDateTime timeStamp;

    private  T data;

    private ApiError error;

    public  ApiResponse()
    {
        this.timeStamp= LocalDateTime.now();
    }

    public ApiResponse(T data)
    {
        this();
        this.data=data;
    }

    public  ApiResponse(ApiError error)
    {
        this();
        this.error=error;
    }
}
