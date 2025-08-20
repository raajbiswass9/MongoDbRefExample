package com.example.demo.adapter.out.mongodb;

import com.example.demo.domain.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    Optional<Group> findByName(String name);

    List<Group> findByNameIn(List<String> names);
}
