package com.example.demo.service;

import com.example.demo.domain.model.Group;

import java.util.List;

public interface GroupService {

    Group addGroup(Group group);

    List<Group> getAllGroups();

    Group getGroupByName(String name);

    void deleteGroup(String groupId);
}
