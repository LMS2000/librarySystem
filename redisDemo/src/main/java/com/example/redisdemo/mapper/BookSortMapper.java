package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.BookSort;

import java.util.List;

public interface BookSortMapper {
    List<BookSort> selectBookSort(BookSort bookSort);
    int updateBookSort(BookSort bookSort );
    int  insertBookSort(BookSort bookSort );
    int deleteBookSort(Long bsId);
    BookSort selectBookSortById(Long bsId);
    List<BookSort> selectBookSortAll();
}
