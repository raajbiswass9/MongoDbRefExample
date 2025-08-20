package com.example.demo.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groups")
@Builder
public class Group {
    @Id
    private String id;
    private String name;
}
