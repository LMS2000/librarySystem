package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.Message;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class messageController {
    @Autowired
    private MessageService messageService ;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<Message> getMessageList()
    {
        return messageService.selectMessageList();
    }
    @PostMapping()
    @ResponseResult
    public void insertMessage(Message message) throws MethodFailureRunException {
        int i = messageService.insertMessage(message);
        if(i<=0){throw new MethodFailureRunException("插入数据失败！");}
    }


}
