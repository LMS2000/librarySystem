package com.example.redisdemo.service;

import com.example.redisdemo.domain.Announcement;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> selectAnnouncementAll();
    int insertAnnouncement(Announcement announcement) throws MethodFailureRunException;
    int deleteAnnouncement(Long aId) throws DataNoFoundException;
    int updateAnnouncement(Announcement announcement) throws DataNoFoundException, MethodFailureRunException;
}
