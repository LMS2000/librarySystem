package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.Announcement;

import java.util.List;

public interface AnnouncementMapper {

    /*
    *
    * */
    List<Announcement> selectAnnouncement(Announcement announcement);

    int updateAnnouncement(Announcement announcement);
    int deleteAnnouncement(Long aId);
    int insertAnnouncement(Announcement announcement);

    Announcement selectAnnouncementById(Long aId);

    List<Announcement> selectAnnouncementAll();
}
