package com.mocktool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterMockDTO {
    private String request;
    private String environment;
    private String service;
}
