package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.BorrowBook;
import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/borrowBook")
public class borrowBookController {
    @Autowired
    private BorrowBookService borrowBookService;

    @RequestMapping(value = "/returned",method = RequestMethod.GET)
    @ResponseResult
    public List<BorrowBook> getBorrowBookReturnedList()
    {
        return borrowBookService.selectBorrowBookReturned();
    }
    @RequestMapping(value = "/notReturned", method = RequestMethod.GET)
    @ResponseResult
    public List<BorrowBook> getBorrowBookNotReturnedList()
    {
        return borrowBookService.selectBorrowBookNotReturned();
    }
    @RequestMapping(value = "/userReturned", method =RequestMethod.GET)
    @ResponseResult
    public List<BorrowBook> getBorrowReturnByUserList(HttpServletRequest request) throws Exception {
        String token =request.getHeader("Authorization");
        String jwt=token.replace("Bearer","");
        return borrowBookService.selectBorrowBookByUserReturned(jwt);
    }
    @RequestMapping(value = "/userNotReturned", method =RequestMethod.GET)
    @ResponseResult
    public List<BorrowBook> getBorrowNotReturnByUserList(HttpServletRequest request) throws Exception {
        String token =request.getHeader("Authorization");
        String jwt=token.replace("Bearer","");
        return borrowBookService.selectBorrowBookByUserNotReturned(jwt);
    }
    @PostMapping
    @ResponseResult
    public void insertBorrowBook(BorrowBook borrowBook ) throws DataNoFoundException, MethodFailureRunException {
        int i = borrowBookService.insertBorrowBook(borrowBook);
        if(i<=0){ throw new MethodFailureRunException("数据插入失败!");}
    }
    @PutMapping
    @ResponseResult
    public  void updateBorrowBook(@RequestBody BorrowBook borrowBook) throws DataNoFoundException, MethodFailureRunException {
        int i = borrowBookService.updateBorrowBook(borrowBook);
        if(i<=0){throw new MethodFailureRunException("数据修改失败！");}
    }
    @DeleteMapping("/{bbId}")
    public void deleteBorrowBook(@PathVariable Long bbId) throws DataNoFoundException, MethodFailureRunException {
        int i = borrowBookService.deleteBorrowBook(bbId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }




}
