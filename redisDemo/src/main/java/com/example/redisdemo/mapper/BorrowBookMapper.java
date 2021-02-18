package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.BorrowBook;

import java.util.List;

public interface BorrowBookMapper {
   List<BorrowBook> selectBorrowBookByUser(Long bbId );
   List<BorrowBook> selectBorrowBookByUserV2(Long bbId);
   List<BorrowBook> selectBorrowBookReturned();
   List<BorrowBook> selectBorrowBookNotReturned();
   int insertBorrowBook(BorrowBook borrowBook);
   int updateBorrowBook(BorrowBook borrowBook);
   int deleteBorrowBook(Long bbId);
   BorrowBook selectBorrowBookById(Long bbId);
}
