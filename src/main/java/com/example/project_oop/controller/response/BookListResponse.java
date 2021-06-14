package com.example.project_oop.controller.response;

import com.example.project_oop.entity.BookEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookListResponse {
    private Integer code;
    private String message;
    private List<BookEntity> data;

}

