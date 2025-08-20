package com.example.demo.adapter.out.mongodb;

import com.example.demo.domain.model.Solution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends MongoRepository<Solution, String> {
    
    // Find solutions by name
    List<Solution> findByName(String name);
    
    // Find all solutions containing at least one group from the list
    List<Solution> findByGroupsIn(List<String> groupNames);
}
