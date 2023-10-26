package com.tp.employeeconnect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "emp")
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   private LocalDate dateOfBirth;

   private double salary;

   @ManyToOne
   @JsonBackReference
   private Department department;

    public Employee(String name, LocalDate dateOfBirth, double salary, Department department) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
    }
}
