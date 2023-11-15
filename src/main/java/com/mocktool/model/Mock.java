package com.mocktool.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String environment;
    private String request;
    private String response;
    private String service;
    private Boolean isActive;
}
