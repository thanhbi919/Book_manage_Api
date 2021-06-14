package com.example.project_oop.services;

import com.example.project_oop.controller.request.AddBookRequest;
import com.example.project_oop.controller.request.UpdateBookRequest;
import com.example.project_oop.controller.response.BookListResponse;
import com.example.project_oop.entity.BookEntity;
import com.example.project_oop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookListResponse getBookByCategoryID(Integer cateId, String order, String orderBy, Integer pageNum){
        Sort sort = Sort.by(Sort.Direction.ASC,orderBy);
        if(order.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC,orderBy);
        }
        PageRequest pageRequest = PageRequest.of(pageNum,2,sort);

        BookListResponse response = new BookListResponse();
        List<BookEntity> data = bookRepository.findAllByCategoryId(cateId, pageRequest);
        response.setData(data);
        response.setCode(200);
        response.setMessage("Success");
        return response;
    }
    public List<BookEntity> addListBook(List<AddBookRequest> requests){
        List<BookEntity> entities = new ArrayList<>();
        for(AddBookRequest e : requests){
            BookEntity item = new BookEntity();
            item.setTitle(e.getTitle());
            item.setAuthor(e.getAuthor());
            item.setCategoryId(e.getCategoryId());
            entities.add(item);
        }
        entities = bookRepository.saveAll(entities);
        return entities;
    }

    public BookEntity updateBook(UpdateBookRequest request){
        BookEntity entity = bookRepository.findById(request.getId()).orElse(null);
        if(entity == null){
            System.out.println("Not existed ID: " + request.getId());
            return null;
        }
        entity.setTitle(request.getTitle());
        entity.setAuthor(request.getAuthor());
        entity = bookRepository.save(entity);
        return entity;
    }

    public String deleteBook(Integer id){
        BookEntity entity = bookRepository.findById(id).orElse(null);
        if(entity == null){
            System.out.println("Not existed ID: " + id);
            return null;
        }
        bookRepository.delete(entity);
        return "OK";
    }




}
