package com.zettamine.accounts.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponseDto {
    private  String apiPath;
    private HttpStatus errorCode;
    private  String errorMessage;
    private LocalDateTime errorTime;

}
