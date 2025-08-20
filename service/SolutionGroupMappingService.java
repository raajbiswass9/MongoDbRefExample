package com.example.demo.service;

import com.example.demo.domain.model.SolutionGroupMapping;

import java.util.List;

public interface SolutionGroupMappingService {

    SolutionGroupMapping addMapping(SolutionGroupMapping mapping);

    List<SolutionGroupMapping> getMappingsBySolutionId(String solutionID);

    void deleteMapping(String solutionID, String groupsID);

    SolutionGroupMapping updateMappingStatus(String solutionID, String groupsID, String status);

    boolean isSolutionFullyApproved(String solutionID);
}
