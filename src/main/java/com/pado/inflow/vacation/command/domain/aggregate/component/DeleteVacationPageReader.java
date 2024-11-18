package com.pado.inflow.vacation.command.domain.aggregate.component;

import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import com.pado.inflow.vacation.command.domain.aggregate.type.ExpirationStatus;
import com.pado.inflow.vacation.command.domain.repository.VacationRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteVacationPageReader implements ItemReader<Vacation> {

    private final VacationRepository vacationRepository;
    private List<Vacation> currentVacations;

    @Autowired
    public DeleteVacationPageReader(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @Override
    public Vacation read() {
        // 현재 페이지의 데이터가 비어있다면 새 페이지를 로드
        if (currentVacations == null || currentVacations.isEmpty()) {
            // 현재 시간
            // 페이지 크기 100
            Page<Vacation> currentVacationPage = vacationRepository.findByExpirationStatus(
                    ExpirationStatus.Y, // 현재 시간
                    PageRequest.of(0, 10) // 페이지 크기 100
            );

            // 더 이상 데이터가 없다면 null 반환
            if (!currentVacationPage.hasContent()) {
                return null;
            }

            // 리스트를 수정 가능한 리스트로 복사
            currentVacations = new ArrayList<>(currentVacationPage.getContent());
        }

        // 데이터를 모두 소비했다면 null 반환
        if (currentVacations.isEmpty()) {
            return null;
        }

        // 첫 번째 데이터를 반환
        return currentVacations.remove(0);
    }

}
