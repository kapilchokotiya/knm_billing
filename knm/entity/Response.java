package com.knm.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Response {
    private String message;
    private Object data;
}

