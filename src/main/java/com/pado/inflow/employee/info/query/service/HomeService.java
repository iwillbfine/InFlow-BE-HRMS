package com.pado.inflow.employee.info.query.service;

import com.pado.inflow.employee.info.query.dto.HomeInfoDTO;
import com.pado.inflow.employee.info.query.repository.HomeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("queryHomeService")
public class HomeService {

    private final HomeInfoMapper homeInfoMapper;

    @Autowired
    public HomeService(HomeInfoMapper homeInfoMapper) {
        this.homeInfoMapper = homeInfoMapper;
    }

    public List<HomeInfoDTO> getHomeInfo(Long employeeId, int month) {
        return homeInfoMapper.getEvents(employeeId, month);
    }

}
