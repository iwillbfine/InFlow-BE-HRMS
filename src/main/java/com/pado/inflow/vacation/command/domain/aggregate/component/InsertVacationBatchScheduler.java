package com.pado.inflow.vacation.command.domain.aggregate.component;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class InsertVacationBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job vacationInsertJob;

    @Autowired
    public InsertVacationBatchScheduler(JobLauncher jobLauncher,
                                        Job vacationInsertJob) {
        this.jobLauncher = jobLauncher;
        this.vacationInsertJob = vacationInsertJob;
    }

    @Scheduled(cron = "0 15 0 * * *") // 매일 00시 15분
    public void runBatchJob() throws Exception {
        // 고유한 JobParameters 생성
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 파라미터 추가
                .toJobParameters();

        // 배치 작업 실행
        jobLauncher.run(vacationInsertJob, jobParameters);
    }

}
