package com.pado.inflow.employee.attach.query.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AllAttachedInfoDTO {
    private Long employeeId;
    private String employeeNumber;
    private List<CareerDTO> careers;
    private List<EducationDTO> educations;
    private List<FamilyMemberDTO> familyMembers;
    private List<LanguageTestDTO> languageTests;
    private List<QualificationDTO> qualifications;
    private List<DisciplineRewardDTO> disciplineRewards;
}
