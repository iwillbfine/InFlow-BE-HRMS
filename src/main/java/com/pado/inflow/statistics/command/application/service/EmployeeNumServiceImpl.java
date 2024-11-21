package com.pado.inflow.statistics.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import com.pado.inflow.employee.info.query.service.EmployeeQueryService;
import com.pado.inflow.statistics.command.domain.aggregate.dto.EmployeeNumDTO;
import com.pado.inflow.statistics.command.domain.aggregate.dto.EmployeeNumInitDTO;
import com.pado.inflow.statistics.command.domain.aggregate.entity.EmployeeNum;
import com.pado.inflow.statistics.command.domain.repository.EmployeeNumRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("ENCommandService")
public class EmployeeNumServiceImpl implements EmployeeNumService {

    private final EmployeeNumRepository employeeNumRepository;
    private final EmployeeQueryService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeNumServiceImpl(EmployeeNumRepository employeeNumRepository,
                                  EmployeeQueryService employeeService,
                                  ModelMapper modelMapper) {
        this.employeeNumRepository = employeeNumRepository;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    // 사원 수 통계 초기화
    @Override
    public List<EmployeeNum> initEmployeeNum() {
        return employeeNumStatistics();
    }

    // 매달 사원 수 통계 업데이트
    @Override
    @Scheduled(cron = "59 59 23 L * ?", zone = "Asia/Seoul")
    public void updateEmployeeNum() {
        employeeNumStatistics();
    }

    // 사원 수 통계 생성
    @Transactional
    protected List<EmployeeNum> employeeNumStatistics() {
        List<EmployeeNumInitDTO> data = employeeData();
        List<EmployeeNum> statistics = calculate(data);

        employeeNumRepository.deleteAll();
        return Optional.ofNullable(employeeNumRepository.saveAll(statistics))
                .filter(e -> !e.isEmpty())
                .orElseThrow(() -> new CommonException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    // 사원 데이터
    private List<EmployeeNumInitDTO> employeeData() {
        List<EmployeeNumInitDTO> list = new ArrayList<>();

        employeeService.getAllEmployees().forEach(emp -> {
            EmployeeNumInitDTO tmp = new EmployeeNumInitDTO();

            tmp.setEmployeeId(emp.getEmployeeId());
            tmp.setInYear(emp.getJoinDate().getYear());
            tmp.setInMonth(emp.getJoinDate().getMonthValue());
            tmp.setLeft(String.valueOf(emp.getResignationStatus()));
            if (emp.getResignationDate() != null) {
                tmp.setLeftYear(emp.getResignationDate().getYear());
                tmp.setLeftMonth(emp.getResignationDate().getMonthValue());
            }
            list.add(tmp);
        });
        return list;
    }

    // 사원 통계 계산
    private List<EmployeeNum> calculate(List<EmployeeNumInitDTO> list) {
        List<Integer> years = list.stream()
                .flatMap(emp -> emp.getLeftYear() != null ?
                        List.of(emp.getInYear(), emp.getLeftYear()).stream() :
                        List.of(emp.getInYear()).stream())
                .distinct()
                .sorted()
                .toList();

        List<EmployeeNum> result = new ArrayList<>();

        for (int y : years) {
            for (int m = 1; m <= 12; m++) {
                long tot = 0, in = 0, out = 0;

                for (EmployeeNumInitDTO em : list) {
                    if (em.getInYear() == y && em.getInMonth() == m) in++;
                    if ("Y".equals(em.getLeft()) && em.getLeftYear() == y && em.getLeftMonth() == m) out++;
                    if (((em.getInYear() < y) || (em.getInYear() == y && em.getInMonth() <= m)) &&
                            ((em.getLeftYear() == null) || (em.getLeftYear() > y) ||
                                    (em.getLeftYear() == y && em.getLeftMonth() > m))) {
                        tot++;
                    }
                }

                EmployeeNumDTO dto = new EmployeeNumDTO(
                        y, m, m <= 6 ? "상반기" : "하반기", tot, in, out, LocalDate.now());
                result.add(modelMapper.map(dto, EmployeeNum.class));
            }
        }
        return result;
    }
}
