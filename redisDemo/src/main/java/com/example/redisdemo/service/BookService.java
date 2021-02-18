package com.example.redisdemo.service;

import com.example.redisdemo.domain.Book;
import com.example.redisdemo.domain.Library;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface BookService {
    List<Book> selectBookAll();

    int insertBook(Book book) throws DataNoFoundException, MethodFailureRunException;


    int updateBook(Book book) throws DataNoFoundException, MethodFailureRunException;

    int deleteBook(Long bId) throws DataNoFoundException, MethodFailureRunException;
}

