package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.BorrowCard;

import java.util.List;

public interface BorrowCardMapper {
    List<BorrowCard> selectBorrowCard(BorrowCard borrowCard );
    int updateBorrowCard(BorrowCard borrowCard );
    int insertBorrowCard(BorrowCard borrowCard );
    int deleteBorrowCard(Long bcId);
    BorrowCard selectBorrowCardById(Long bcId);
    List<BorrowCard> selectBorrowCardAll();
}
