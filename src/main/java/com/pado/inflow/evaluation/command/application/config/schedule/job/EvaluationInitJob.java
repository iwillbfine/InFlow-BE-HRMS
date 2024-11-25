package com.pado.inflow.evaluation.command.application.config.schedule.job;

import com.pado.inflow.evaluation.command.application.config.schedule.service.EvaluationScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class EvaluationInitJob {

    private final EvaluationScheduleService scheduleService;

    public EvaluationInitJob(EvaluationScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Scheduled(cron = "0 0 0 1 6,12 *") // 설정된 시간 : 6월, 12월 // 테스트 완료
    public void initializeEvaluation() {
        log.info("평가 초기화 Job 시작");

            String half = LocalDateTime.now().getMonthValue() == 6 ? "1st" : "2nd";
            scheduleService.initializeEvaluation(
                    LocalDateTime.now().getYear(),
                    half
            );
    }
}