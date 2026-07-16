package com.ismaeldevtech.employee_batch_processor.reader;

import com.ismaeldevtech.employee_batch_processor.model.Employee;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeItemReader {
    public FlatFileItemReader<Employee> reader(){
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("employees.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("firstName", "lastName", "email", "department", "salary");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(Employee.class);
            }});
        }});
        return reader;
    }
}
