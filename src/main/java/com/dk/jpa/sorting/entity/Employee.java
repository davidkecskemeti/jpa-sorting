package com.dk.jpa.sorting.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "Employee.findByAgeGreaterThanNamedJPQL",
        query = "SELECT e FROM Employee e WHERE e.age > :age ORDER BY e.firstName ASC")
@NamedNativeQuery(name = "Employee.findAllNamedNativeSQL",
        query = "SELECT * FROM Employee e ORDER BY e.age DESC")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Employee(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }
}
