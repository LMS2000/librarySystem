package com.example.redisdemo.service;

import com.example.redisdemo.domain.Message;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface MessageService {
    List<Message>  selectMessageList();

    int  insertMessage(Message message) throws MethodFailureRunException;
}
