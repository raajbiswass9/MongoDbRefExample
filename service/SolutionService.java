package com.example.demo.service;

import com.example.demo.domain.model.Solution;

import java.util.List;

public interface SolutionService {

    Solution addSolution(Solution solution);

    List<Solution> getAllSolutions();

    List<Solution> getSolutionsByGroupNames(List<String> groupNames);

    void removeGroupFromSolution(String solutionId, String groupName);

    void updateSolutionGroupsStatus(String solutionId, 
                                    java.util.Set<String> groupNames, 
                                    String newStatus);
}
