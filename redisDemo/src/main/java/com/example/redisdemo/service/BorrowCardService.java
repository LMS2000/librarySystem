package com.example.redisdemo.service;

import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface BorrowCardService {
       List<BorrowCard> selectBorrowCard();
       int insertBorrowCard(BorrowCard borrowCard) throws MethodFailureRunException;
       int deleteBorrowCard(Long bcId) throws DataNoFoundException, MethodFailureRunException;
       int updateBorrowCard(BorrowCard borrowCard ) throws DataNoFoundException, MethodFailureRunException;
}
