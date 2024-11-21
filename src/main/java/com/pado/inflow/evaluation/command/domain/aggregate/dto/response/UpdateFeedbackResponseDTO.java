package com.pado.inflow.evaluation.command.domain.aggregate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFeedbackResponseDTO {

    @JsonProperty("feedback_id")
    private Long feedbackId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("evaluation_id")
    private Long evaluationId;

}
