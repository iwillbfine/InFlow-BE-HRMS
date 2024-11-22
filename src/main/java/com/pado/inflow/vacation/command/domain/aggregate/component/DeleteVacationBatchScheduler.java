package com.pado.inflow.vacation.command.domain.aggregate.component;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteVacationBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job vacationDeleteJob;

    @Autowired
    public DeleteVacationBatchScheduler(JobLauncher jobLauncher,
                                        @Qualifier("vacationDeleteJob") Job vacationDeleteJob) {
        this.jobLauncher = jobLauncher;
        this.vacationDeleteJob = vacationDeleteJob;
    }

    @Scheduled(cron = "0 30 0 1 * *") // 매월 1일 00시 30분
    public void runBatchJob() throws Exception {
        // 고유한 JobParameters 생성
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 파라미터 추가
                .toJobParameters();

        // 배치 작업 실행
        jobLauncher.run(vacationDeleteJob, jobParameters);
    }

}
