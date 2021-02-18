package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.Message;

import java.util.List;

public interface MessageMapper {
    List<Message> selectMessage();

    int insertMessage(Message message);
}
