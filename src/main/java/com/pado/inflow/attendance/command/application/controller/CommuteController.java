package com.pado.inflow.attendance.command.application.controller;

import com.pado.inflow.attendance.command.application.service.CommuteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commandCommuteController")
@RequestMapping("/api/commutes")
public class CommuteController {
    
    private final CommuteService commuteService;
    
    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }
    
    @Po
    
}
