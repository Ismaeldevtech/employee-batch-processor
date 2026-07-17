package com.ismaeldevtech.employee_batch_processor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("========================================");
        log.info("JOB INICIADO: {}", jobExecution.getJobInstance().getJobName());
        log.info("Fecha inicio: {}", jobExecution.getStartTime());
        log.info("========================================");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("========================================");
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JOB COMPLETADO CON ÉXITO");
        } else {
            log.error("JOB FALLIDO CON ESTADO: {}", jobExecution.getStatus());
        }
        log.info("Fecha fin: {}", jobExecution.getEndTime());
        log.info("========================================");
    }
}