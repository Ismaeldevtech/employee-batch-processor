package com.ismaeldevtech.employee_batch_processor.listener;

import com.ismaeldevtech.employee_batch_processor.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class EmployeeSkipListener implements SkipListener<Employee, Employee> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeSkipListener.class);
    private static final String REJECTED_FILE = "rejected-employees.csv";

    private void writeToRejectedFile(String reason, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(REJECTED_FILE, true))) {
            writer.println(LocalDateTime.now() + "," + reason + "," + data);
        } catch (IOException e) {
            log.error("Error escribiendo en fichero de rechazados: {}", e.getMessage());
        }
    }

    @Override
    public void onSkipInRead(Throwable t) {
        log.warn("Registro saltado en lectura: {}", t.getMessage());
        writeToRejectedFile("READ_ERROR", t.getMessage());
    }

    @Override
    public void onSkipInProcess(Employee employee, Throwable t) {
        log.warn("Empleado saltado en procesamiento: {} {}",
                employee.getFirstName(), employee.getLastName());
        writeToRejectedFile("PROCESS_ERROR",
                employee.getFirstName() + "," + employee.getLastName() +
                        "," + employee.getEmail() + "," + employee.getSalary());
    }

    @Override
    public void onSkipInWrite(Employee employee, Throwable t) {
        log.warn("Empleado saltado en escritura: {} {}",
                employee.getFirstName(), employee.getLastName());
        writeToRejectedFile("WRITE_ERROR",
                employee.getFirstName() + "," + employee.getLastName() +
                        "," + employee.getEmail() + "," + employee.getSalary());
    }
}