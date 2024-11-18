package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.employee.info.command.domain.aggregate.entity.Employee;
import com.pado.inflow.employee.info.command.domain.repository.EmployeeRepository;
import com.pado.inflow.employee.info.enums.ResignationStatus;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeePageReader implements ItemReader<Employee> {

    private final EmployeeRepository employeeRepository;
    private int currentPage = 0;
    private List<Employee> currentEmployees;

    @Autowired
    public EmployeePageReader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee read() {
        // 현재 페이지의 데이터가 비어있다면 새 페이지를 로드
        if (currentEmployees == null || currentEmployees.isEmpty()) {
            Page<Employee> currentEmployeePage = employeeRepository.findByResignationStatus(
                    ResignationStatus.N,
                    PageRequest.of(currentPage++, 100)
            );

            // 더 이상 데이터가 없다면 null 반환
            if (!currentEmployeePage.hasContent()) {
                return null;
            }

            // 리스트를 수정 가능한 리스트로 복사
            currentEmployees = new ArrayList<>(currentEmployeePage.getContent());
        }

        // 첫 번째 데이터를 반환하고, 해당 데이터는 리스트에서 제거
        return currentEmployees.isEmpty() ? null : currentEmployees.remove(0);
    }

}
