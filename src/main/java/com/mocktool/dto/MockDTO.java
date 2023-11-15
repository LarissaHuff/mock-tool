package com.mocktool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockDTO {
    private String request;
    private String response;
    private Long environmentId;
    private String service;
    private Boolean isActive;
}
