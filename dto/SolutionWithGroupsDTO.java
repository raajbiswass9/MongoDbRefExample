package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SolutionWithGroupsDTO {
    private String solutionId;
    private String solutionName;
    private List<GroupReviewStatusDTO> groupReviewStatus;

    @Data
    @Builder
    public static class GroupReviewStatusDTO {
        private String groupName;
        private String status;
    }
}
