package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.repository.VacationRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UpdateVacationItemWriter implements ItemWriter<Vacation> {

    private final VacationRepository vacationRepository;

    @Autowired
    public UpdateVacationItemWriter(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public void write(Chunk<? extends Vacation> chunk) {
        // 상태가 업데이트된 vacation 객체를 저장
        vacationRepository.saveAll(chunk.getItems());
    }

}
