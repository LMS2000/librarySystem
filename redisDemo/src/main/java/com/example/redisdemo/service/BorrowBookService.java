package com.example.redisdemo.service;

import com.example.redisdemo.domain.BorrowBook;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface BorrowBookService {
    List<BorrowBook> selectBorrowBookByUserReturned(String token) throws Exception;
    List<BorrowBook> selectBorrowBookByUserNotReturned(String token) throws Exception;
    List<BorrowBook> selectBorrowBookReturned();
    List<BorrowBook> selectBorrowBookNotReturned();
    int updateBorrowBook(BorrowBook  borrowBook) throws DataNoFoundException, MethodFailureRunException;
    int deleteBorrowBook(Long bbId) throws DataNoFoundException, MethodFailureRunException;
    int insertBorrowBook(BorrowBook borrowBook) throws DataNoFoundException, MethodFailureRunException;
}
