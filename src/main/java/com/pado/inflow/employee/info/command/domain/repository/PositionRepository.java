package com.pado.inflow.employee.info.command.domain.repository;


import com.pado.inflow.employee.info.command.domain.aggregate.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PositionRepository extends JpaRepository<Position, String> {
    @Query("SELECT CASE WHEN p.positionCode = 'P005' THEN true ELSE false END FROM Position p WHERE p.positionCode = :positionCode")
    boolean isManagerPosition(String positionCode);
}
