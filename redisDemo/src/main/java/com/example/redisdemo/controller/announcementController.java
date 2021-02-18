package com.example.redisdemo.controller;

import com.example.redisdemo.annotation.ResponseResult;
import com.example.redisdemo.domain.Announcement;
import com.example.redisdemo.exception.DataNoFoundException;
import com.example.redisdemo.exception.MethodFailureRunException;
import com.example.redisdemo.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class announcementController {
    @Autowired
    private AnnouncementService announcementService ;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseResult
    public List<Announcement> getAnnouncement()
    {
        return  announcementService.selectAnnouncementAll();
    }
    @PostMapping
    @ResponseResult
    public void insertAnnouncement(@RequestBody Announcement announcement) throws MethodFailureRunException {
        int row = announcementService.insertAnnouncement(announcement);
        if(row<=0){ throw new MethodFailureRunException("数据插入失败！");}
    }
    @PutMapping
    @ResponseResult
    public void updateAnnouncement(@RequestBody Announcement announcement) throws DataNoFoundException, MethodFailureRunException {
        int row = announcementService.updateAnnouncement(announcement);
        if(row<=0){ throw new MethodFailureRunException("数据修改失败！");}
    }
    @DeleteMapping("/{aId}")
    @ResponseResult
    public void deleteAnnouncement(@PathVariable Long aId) throws DataNoFoundException, MethodFailureRunException {
        int row = announcementService.deleteAnnouncement(aId);
        if(row<=0){ throw new MethodFailureRunException("数据删除失败！");}
    }








}
