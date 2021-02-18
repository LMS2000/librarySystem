package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.BorrowCard;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.BorrowCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class borrowCardController {
    @Autowired
    private BorrowCardService borrowCardService ;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<BorrowCard> getBorrowCardList()
    {
        return borrowCardService.selectBorrowCard();
    }
    @PostMapping
    @ResponseResult
    public void insertBorrowCard(BorrowCard borrowCard) throws MethodFailureRunException {
        int i = borrowCardService.insertBorrowCard(borrowCard);
        if(i<=0){throw new MethodFailureRunException("数据插入失败！");}
    }
    @PutMapping
    @ResponseResult
    public void updateBorrowCard(BorrowCard borrowCard) throws DataNoFoundException, MethodFailureRunException {
        int i = borrowCardService.updateBorrowCard(borrowCard);
        if(i<=0){throw new MethodFailureRunException("数据修改失败!");}
    }
    @DeleteMapping("/{bcId}")
    @ResponseResult
    public void deleteBorrowCard(@PathVariable Long bcId) throws DataNoFoundException, MethodFailureRunException {
        int i = borrowCardService.deleteBorrowCard(bcId);
        if(i<=0){throw new MethodFailureRunException("数据删除失败！");}
    }
}
