package com.pado.inflow.vacation.command.domain.aggregate.component;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VacationBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job vacationUpdateJob;

    @Autowired
    public VacationBatchScheduler(JobLauncher jobLauncher,
                                  Job vacationUpdateJob) {
        this.jobLauncher = jobLauncher;
        this.vacationUpdateJob = vacationUpdateJob;
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    public void runBatchJob() throws Exception {
        // 고유한 JobParameters 생성
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 파라미터 추가
                .toJobParameters();

        // 배치 작업 실행
        jobLauncher.run(vacationUpdateJob, jobParameters);
    }
}
