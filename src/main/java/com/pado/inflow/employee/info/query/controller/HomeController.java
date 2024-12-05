package com.pado.inflow.employee.info.query.controller;

import com.pado.inflow.common.ResponseDTO;
import com.pado.inflow.employee.info.query.dto.HomeInfoDTO;
import com.pado.inflow.employee.info.query.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController("queryHomeController")
@RequestMapping("/api/home")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseDTO<?> getHomeInfo(@RequestParam("employeeId") Long employeeId,
                                      @RequestParam("month") int month) {

        List<HomeInfoDTO> events = homeService.getHomeInfo(employeeId, month);
        return ResponseDTO.ok(events);
    }

}
