package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateVacationItemProcessor implements ItemProcessor<Vacation, Vacation> {

    @Override
    public Vacation process(Vacation vacation) {
        // 상태를 업데이트하는 로직
        if (vacation.getExpiredAt().isBefore(LocalDateTime.now()) &&
                vacation.getExpirationStatus() == ExpirationStatus.N) {
            vacation.setExpirationStatus(ExpirationStatus.Y);
        }
        return vacation;
    }

}
