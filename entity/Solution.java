package com.example.demo.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "solutions")
@Builder
public class Solution {
    @Id
    private String id;
    private String name;
    private List<String> groups;
}
