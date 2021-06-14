package com.example.project_oop.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequest {
    private String title;
    private String author;
    private Integer categoryId;
}

