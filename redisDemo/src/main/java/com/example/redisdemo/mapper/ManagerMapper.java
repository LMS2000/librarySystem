package com.example.redisdemo.mapper;

import com.example.redisdemo.domain.Manager;

import java.util.List;

public interface ManagerMapper {
    List<Manager> selectManager(Manager manager );
    int updateManager(Manager manager );
    int insertManager(Manager manager );
    int deleteManager(Long mId);
    Manager selectManagerById(Long mId);
    List<Manager> selectManagerAll();
}
