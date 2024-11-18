package com.pado.inflow.vacation.command.domain.repository;

import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    // 만료일이 현재 날짜 이전인 데이터를 찾는 쿼리
    Page<Vacation> findByExpiredAtBefore(LocalDateTime currentDateTime, Pageable pageable);

}
