package com.dk.jpa.sorting.runner;

import com.dk.jpa.sorting.entity.Employee;
import com.dk.jpa.sorting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeCommandLineRunner implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // create new employees
        List<Employee> list = Arrays.asList(
                new Employee("John", "Doe", 45, 8000),
                new Employee("Mike", "Hassan", 55, 6500),
                new Employee("Emma", "Doe", 35, 4580),
                new Employee("Ali", "Obray", 21, 3200),
                new Employee("Beanca", "Lee", 21, 3200)
        );
        employeeRepository.saveAll(list);

        // find all users
//            Iterable<Employee> emps = employeeRepository.findAll(Sort.by("age", "salary").descending());

        // find users by last name
//            Sort sort = Sort.by("salary").descending().and(Sort.by("firstName"));
//            List<Employee> employees = employeeRepository.findByLastName("Doe", sort);

        Sort sort = Sort.by("salary").descending().and(Sort.by("firstName"))
                .and(Sort.by("age").descending()).and(Sort.by("lastName").ascending());
        List<Employee> employees = employeeRepository.findBySalaryRange(100, 10000, sort);

        // omit sorting
        Iterable<Employee> emps = employeeRepository.findAll(Sort.unsorted());

        // print employees
        employees.forEach(emp -> {
            System.out.println(emp.toString());
        });

    }
}
