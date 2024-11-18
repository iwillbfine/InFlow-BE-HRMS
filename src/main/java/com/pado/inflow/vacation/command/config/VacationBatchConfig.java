package com.pado.inflow.vacation.command.config;

import com.pado.inflow.vacation.command.domain.aggregate.component.*;
import com.pado.inflow.vacation.command.domain.aggregate.entity.Vacation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class VacationBatchConfig {

    // 휴가 만료 처리
    @Bean
    public Job vacationUpdateJob(JobRepository jobRepository, Step vacationUpdateStep) {
        return new JobBuilder("vacationUpdateJob", jobRepository)
                .start(vacationUpdateStep) // 첫 번째 스텝 실행
                .build();
    }

    @Bean
    public Step vacationUpdateStep(JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager,
                                   UpdateVacationPageReader vacationPageReader,
                                   UpdateVacationItemProcessor vacationItemProcessor,
                                   UpdateVacationItemWriter vacationItemWriter) {
        return new StepBuilder("vacationUpdateStep", jobRepository)
                .<Vacation, Vacation>chunk(100, transactionManager)
                .allowStartIfComplete(true)
                .reader(vacationPageReader)
                .processor(vacationItemProcessor)
                .writer(vacationItemWriter)
                .build();
    }

    // 만료된 휴가 삭제 처리
    @Bean
    public Job vacationDeleteJob(JobRepository jobRepository, Step vacationDeleteStep) {
        return new JobBuilder("vacationDeleteJob", jobRepository)
                .start(vacationDeleteStep)
                .build();
    }

    @Bean
    public Step vacationDeleteStep(JobRepository jobRepository,
                                          PlatformTransactionManager transactionManager,
                                          DeleteVacationPageReader deleteVacationPageReader,
                                          DeleteVacationItemWriter deleteVacationItemWriter) {
        return new StepBuilder("vacationDeleteStep", jobRepository)
                .<Vacation, Vacation>chunk(100, transactionManager)
                .reader(deleteVacationPageReader)
                .writer(deleteVacationItemWriter) // 삭제 로직을 처리할 Writer
                .build();
    }

}
