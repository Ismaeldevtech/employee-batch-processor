package com.ismaeldevtech.employee_batch_processor.writer;

import com.ismaeldevtech.employee_batch_processor.model.Employee;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class EmployeeWriter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<Employee> writer() {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(
                        new BeanPropertyItemSqlParameterSourceProvider<>()
                )
                .sql("INSERT INTO employees (first_name, last_name, email, department, salary) " +
                        "VALUES (:firstName, :lastName, :email, :department, :salary)")
                .dataSource(dataSource)
                .build();
    }
}
