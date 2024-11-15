//package com.pado.inflow.employee.info.query.service;
//
//import com.pado.inflow.common.exception.CommonException;
//import com.pado.inflow.common.exception.ErrorCode;
//import com.pado.inflow.employee.info.enums.EmployeeRole;
//import com.pado.inflow.employee.info.query.dto.EmployeeDTO;
//import com.pado.inflow.employee.info.query.repository.EmployeeMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceImplTest {
//
//    @Mock
//    private EmployeeMapper employeeMapper;
//
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//    private EmployeeDTO employee;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        employee = new EmployeeDTO();
//        employee.setEmployeeId(1L);
//        employee.setEmployeeNumber("202100001");
//        employee.setName("John Doe");
//        employee.setEmployeeRole(EmployeeRole.EMPLOYEE);
//    }
//
//    @Test
//    void testGetAllEmployees() {
//        when(employeeMapper.findAllEmployees()).thenReturn(List.of(employee));
//
//        List<EmployeeDTO> employees = employeeService.getAllEmployees();
//
//        assertNotNull(employees);
//        assertEquals(1, employees.size());
//        assertEquals("John Doe", employees.get(0).getName());
//    }
//
//    @Test
//    void testGetEmployeesByName_Success() {
//        when(employeeMapper.findEmployeesByName("John Doe")).thenReturn(List.of(employee));
//
//        List<EmployeeDTO> employees = employeeService.getEmployeesByName("John Doe");
//
//        assertNotNull(employees);
//        assertEquals(1, employees.size());
//        assertEquals("John Doe", employees.get(0).getName());
//    }
//
//    @Test
//    void testGetEmployeesByName_NotFound() {
//        // lenient를 사용하여 불필요한 스터빙 경고를 무시합니다.
//        lenient().when(employeeMapper.findEmployeesByName("Jane Doe")).thenReturn(Collections.emptyList());
//
//        CommonException exception = assertThrows(CommonException.class, () -> {
//            employeeService.getEmployeesByName("Jane Doe");
//        });
//
//        assertEquals(ErrorCode.NOT_FOUND_EMPLOYEE, exception.getErrorCode());
//    }
//
//    @Test
//    void testGetEmployeeByNumber() {
//        when(employeeMapper.findEmployeeByNumber("202100001")).thenReturn(employee);
//
//        EmployeeDTO result = employeeService.getEmployeeByNumber("202100001");
//
//        assertNotNull(result);
//        assertEquals("John Doe", result.getName());
//    }
//
//    @Test
//    void testGetEmployeeById() {
//        when(employeeMapper.findEmployeeById(1L)).thenReturn(employee);
//
//        EmployeeDTO result = employeeService.getEmployeeById(1L);
//
//        assertNotNull(result);
//        assertEquals("John Doe", result.getName());
//    }
//}
