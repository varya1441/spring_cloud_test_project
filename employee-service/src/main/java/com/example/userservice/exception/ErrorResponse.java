package com.example.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorModel> errorMessage;

}

/**
 * This class describe the error object model, which is a simple POJO that contains the rejected filedName, rejectedValue
 * and a messageError.
 */
