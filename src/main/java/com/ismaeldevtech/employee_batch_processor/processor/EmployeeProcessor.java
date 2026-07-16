package com.ismaeldevtech.employee_batch_processor.processor;

import com.ismaeldevtech.employee_batch_processor.model.Employee;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

    private static final double SALARY_THRESHOLD = 30000.0;

    @Override
    public Employee process(Employee employee) throws Exception {
        if (employee.getSalary() < SALARY_THRESHOLD) {
            return null;
        }
        employee.setFirstName(employee.getFirstName().toUpperCase());
        employee.setLastName(employee.getLastName().toUpperCase());
        return employee;
    }
}