package com.ismaeldevtech.employee_batch_processor.config;

import com.ismaeldevtech.employee_batch_processor.model.Employee;
import com.ismaeldevtech.employee_batch_processor.processor.EmployeeProcessor;
import com.ismaeldevtech.employee_batch_processor.reader.EmployeeItemReader;
import com.ismaeldevtech.employee_batch_processor.writer.EmployeeWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchJobConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step processEmployeesStep(FlatFileItemReader<Employee> reader,
                                     EmployeeProcessor processor,
                                     JdbcBatchItemWriter<Employee> writer) {
        return new StepBuilder("processEmployeesStep", jobRepository)
                .<Employee, Employee>chunk(1000, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job processEmployeesJob(Step processEmployeesStep) {
        return new JobBuilder("processEmployeesJob", jobRepository)
                .start(processEmployeesStep)
                .build();
    }
}