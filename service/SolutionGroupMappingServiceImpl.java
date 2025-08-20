package com.example.demo.service.impl;

import com.example.demo.adapter.out.mongodb.SolutionGroupMappingRepository;
import com.example.demo.domain.model.SolutionGroupMapping;
import com.example.demo.service.SolutionGroupMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionGroupMappingServiceImpl implements SolutionGroupMappingService {

    private final SolutionGroupMappingRepository mappingRepository;

    @Override
    public SolutionGroupMapping addMapping(SolutionGroupMapping mapping) {
        return mappingRepository.save(mapping);
    }

    @Override
    public List<SolutionGroupMapping> getMappingsBySolutionId(String solutionID) {
        return mappingRepository.findBySolutionID(solutionID);
    }

    @Override
    public void deleteMapping(String solutionID, String groupsID) {
        mappingRepository.deleteBySolutionIDAndGroupsID(solutionID, groupsID);
    }

    @Override
    public SolutionGroupMapping updateMappingStatus(String solutionID, String groupsID, String status) {
        SolutionGroupMapping mapping = mappingRepository.findBySolutionIDAndGroupsID(solutionID, groupsID)
                .orElseThrow(() -> new RuntimeException("Mapping not found for solutionID: " + solutionID + " and groupID: " + groupsID));
        mapping.setStatus(status);
        return mappingRepository.save(mapping);
    }

    @Override
    public boolean isSolutionFullyApproved(String solutionID) {
        List<SolutionGroupMapping> mappings = mappingRepository.findBySolutionID(solutionID);
        return mappings.stream().allMatch(mapping -> "APPROVED".equals(mapping.getStatus()));
    }
}
