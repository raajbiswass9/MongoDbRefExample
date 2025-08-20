package com.example.demo.service.impl;

import com.example.demo.adapter.out.mongodb.GroupRepository;
import com.example.demo.adapter.out.mongodb.SolutionGroupMappingRepository;
import com.example.demo.adapter.out.mongodb.SolutionRepository;
import com.example.demo.domain.model.Group;
import com.example.demo.domain.model.Solution;
import com.example.demo.domain.model.SolutionGroupMapping;
import com.example.demo.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {

    private final SolutionRepository solutionRepository;
    private final GroupRepository groupRepository;
    private final SolutionGroupMappingRepository mappingRepository;

    @Override
    public Solution addSolution(Solution solution) {
        // Save solution
        Solution saved = solutionRepository.save(solution);

        // Create entries in SolutionGroupMapping for all groups
        solution.getGroups().forEach(groupName -> {
            Group group = groupRepository.findByName(groupName)
                    .orElseThrow(() -> new RuntimeException("Group not found: " + groupName));
            SolutionGroupMapping mapping = SolutionGroupMapping.builder()
                    .solution(saved)
                    .group(group)
                    .status("PENDING")
                    .build();
            mappingRepository.save(mapping);
        });

        return saved;
    }

    @Override
    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    @Override
    public List<Solution> getSolutionsByGroupNames(List<String> groupNames) {
        return solutionRepository.findByGroupsIn(groupNames);
    }

    @Override
    @Transactional
    public void removeGroupFromSolution(String solutionId, String groupName) {
        Solution solution = solutionRepository.findById(solutionId)
                .orElseThrow(() -> new RuntimeException("Solution not found"));

        solution.setGroups(solution.getGroups().stream()
                .filter(name -> !name.equals(groupName))
                .toList());

        solutionRepository.save(solution);

        Group group = groupRepository.findByName(groupName)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        mappingRepository.deleteBySolutionAndGroup(solution, group);
    }

    @Override
    @Transactional
    public void updateSolutionGroupsStatus(String solutionId, Set<String> groupNames, String newStatus) {
        Solution solution = solutionRepository.findById(solutionId)
                .orElseThrow(() -> new RuntimeException("Solution not found"));

        List<Group> groups = groupRepository.findByNameIn(groupNames);

        if (groups.isEmpty()) {
            throw new RuntimeException("No valid groups found for the provided names");
        }

        List<SolutionGroupMapping> mappings = mappingRepository.findBySolutionAndGroupIn(solution, groups);
        mappings.forEach(mapping -> mapping.setStatus(newStatus));
        mappingRepository.saveAll(mappings);
    }
}
