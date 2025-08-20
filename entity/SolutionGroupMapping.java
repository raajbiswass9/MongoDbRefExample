package com.example.demo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "solution_group_mappings")
public class SolutionGroupMapping {

    @Id
    private String id;

    // Reference to Solution ID
    private String solutionID;

    // Reference to Group ID
    private String groupsID;

    // Status can be APPROVED, PENDING, etc.
    private String status;
}
