package com.example.demo.adapter.out.mongodb;

import com.example.demo.domain.model.SolutionGroupMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolutionGroupMappingRepository extends MongoRepository<SolutionGroupMapping, String> {

    // Find all mappings for a particular solution
    List<SolutionGroupMapping> findBySolutionID(String solutionID);

    // Find all mappings with a particular status
    List<SolutionGroupMapping> findByStatus(String status);

    // Find mapping for a particular solution and group
    Optional<SolutionGroupMapping> findBySolutionIDAndGroupsID(String solutionID, String groupsID);

    // Find all mappings for a solution where status matches
    List<SolutionGroupMapping> findBySolutionIDAndStatus(String solutionID, String status);

    // Delete mapping for a solution and group
    void deleteBySolutionIDAndGroupsID(String solutionID, String groupsID);
}
