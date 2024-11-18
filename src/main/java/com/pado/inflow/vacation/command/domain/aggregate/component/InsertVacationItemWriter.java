package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.repository.VacationRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class InsertVacationItemWriter implements ItemWriter<List<Vacation>> {

    private final VacationRepository vacationRepository;

    @Autowired
    public InsertVacationItemWriter(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public void write(Chunk<? extends List<Vacation>> items) {
        for (List<Vacation> vacations : items) {
            vacationRepository.saveAll(vacations); // 여러 종류의 휴가 목록을 한 번에 저장
        }
    }

}
