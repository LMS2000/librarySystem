package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.Book;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class bookController {
    @Autowired
    private BookService bookService ;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<Book>  getBookList()
    {
        return bookService.selectBookAll();
    }
    @PostMapping
    @ResponseResult
    public void insertBook(Book book) throws DataNoFoundException, MethodFailureRunException {
        int i = bookService.insertBook(book);
        if(i<=0){throw new MethodFailureRunException("数据插入失败！");}
    }
    @PutMapping
    @ResponseResult
    public void updateBook(Book book) throws DataNoFoundException, MethodFailureRunException {
        int i = bookService.updateBook(book);
        if(i<=0){throw new MethodFailureRunException("数据修改失败！");}
    }
    @DeleteMapping("/{bId}")
    @ResponseResult
    public void deleteBook(Long bId) throws DataNoFoundException, MethodFailureRunException {
        int i = bookService.deleteBook(bId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }



}
