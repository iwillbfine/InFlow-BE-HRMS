package com.pado.inflow.vacation.command.config;

import com.pado.inflow.vacation.command.domain.aggregate.component.VacationItemProcessor;
import com.pado.inflow.vacation.command.domain.aggregate.component.VacationItemWriter;
import com.pado.inflow.vacation.command.domain.aggregate.component.VacationPageReader;
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
public class VacationUpdateTaskConfig {

    @Bean
    public Job vacationUpdateJob(JobRepository jobRepository, Step vacationUpdateStep) {
        return new JobBuilder("vacationUpdateJob", jobRepository)
                .start(vacationUpdateStep) // 첫 번째 스텝 실행
                .build();
    }

    @Bean
    public Step vacationUpdateStep(JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager,
                                   VacationPageReader vacationPageReader,
                                   VacationItemProcessor vacationItemProcessor,
                                   VacationItemWriter vacationItemWriter) {
        return new StepBuilder("vacationUpdateStep", jobRepository)
                .<Vacation, Vacation>chunk(100, transactionManager)
                .allowStartIfComplete(true)
                .reader(vacationPageReader)
                .processor(vacationItemProcessor)
                .writer(vacationItemWriter)
                .build();
    }

}
