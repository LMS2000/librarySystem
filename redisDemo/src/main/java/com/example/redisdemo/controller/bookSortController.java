package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.BookSort;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.BookSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookSort")
public class bookSortController {

    @Autowired
    private BookSortService bookSortService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<BookSort> getBookSortList()
    {
     return bookSortService.selectBookSortAll();
    }
    @PostMapping
    @ResponseResult
    public  void insertBookSort(BookSort bookSort) throws MethodFailureRunException {
        int i = bookSortService.insertBookSort(bookSort);
        if(i<=0){throw new MethodFailureRunException("数据插入失败！");}
    }
    @PutMapping
    @ResponseResult
    public  void  updateBookSort(BookSort bookSort) throws DataNoFoundException, MethodFailureRunException {
        int i = bookSortService.updateBookSort(bookSort);
        if(i<=0){throw new MethodFailureRunException("数据修改失败！");}
    }
    @DeleteMapping("/{bsId}")
    @ResponseResult
    public void deleteBookSort(@PathVariable Long bsId) throws DataNoFoundException, MethodFailureRunException {
        int i = bookSortService.deleteBookSort(bsId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }



}
