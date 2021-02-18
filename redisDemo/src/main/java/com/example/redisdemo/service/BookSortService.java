package com.example.redisdemo.service;

import com.example.redisdemo.domain.BookSort;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface BookSortService {
    List<BookSort> selectBookSortAll();
    int deleteBookSort(Long bsId) throws DataNoFoundException;
    int updateBookSort(BookSort bookSort) throws DataNoFoundException, MethodFailureRunException;
    int insertBookSort(BookSort bookSort) throws MethodFailureRunException;
}
