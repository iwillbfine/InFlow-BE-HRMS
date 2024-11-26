package com.pado.inflow.employee.attach.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.CareerDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.dto.LanguageTestDTO;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.Career;
import com.pado.inflow.employee.attach.command.domain.aggregate.entity.LanguageTest;
import com.pado.inflow.employee.attach.command.domain.repository.CareerRepository;
import com.pado.inflow.employee.attach.command.domain.repository.LanguageTestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("LTCommandService")
public class LanguageTestServiceImpl implements LanguageTestService {

    private final LanguageTestRepository languageTestRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LanguageTestServiceImpl(LanguageTestRepository languageTestRepository,
                                   ModelMapper modelMapper) {
        this.languageTestRepository = languageTestRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원의 어학 정보 등록
    @Override
    public List<LanguageTestDTO> addLangTests(List<LanguageTestDTO> langTests) {
        return Optional.ofNullable(
                        languageTestRepository.saveAll(
                                        langTests.stream()
                                                .map(dto -> modelMapper.map(dto, LanguageTest.class))
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, LanguageTestDTO.class))
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 어학 정보 수정
    @Override
    public List<LanguageTestDTO> modifyLangTests(List<LanguageTestDTO> langTests) {
        return Optional.ofNullable(
                        languageTestRepository.saveAllAndFlush(
                                        langTests.stream()
                                                .map(dto -> modelMapper.map(dto, LanguageTest.class)) // DTO → 엔티티 변환
                                                .collect(Collectors.toList())
                                ).stream()
                                .map(entity -> modelMapper.map(entity, LanguageTestDTO.class)) // 엔티티 → DTO 변환
                                .collect(Collectors.toList())
                )
                .filter(list -> !list.isEmpty()) // 리스트가 비어있는지 검증
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원의 어학 정보 삭제
    @Override
    public Boolean deleteLangTests(List<Long> langTests) {
        return Optional.ofNullable(langTests)
                .map(lang -> {
                    languageTestRepository.deleteAllById(lang);
                    return true;
                })
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
