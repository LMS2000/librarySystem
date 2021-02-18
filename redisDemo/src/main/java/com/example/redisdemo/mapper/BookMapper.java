package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.Book;
import com.example.redisdemo.domain.Library;

import java.util.List;

public interface BookMapper {
    List<Book>  selectBook(Book book );
    int insertBook(Book book );
    int updateBook(Book book );
    int deleteBook(Long bId);
    List<Book> selectBookAll();
    Book  selectBookById(Long bId);
    Library  selectLibraryById(Long lId);
}
