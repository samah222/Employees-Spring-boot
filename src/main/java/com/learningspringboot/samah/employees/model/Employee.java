package com.learningspringboot.samah.employees.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(min = 2, max=50, message = "name should be between 2 and 50 characters")
    @Column(name = "name", nullable = false )
    private String name;

    @Min(1000)
    private double salary;

    @Email
    //@Column(unique = true)
    private String email;

    @Pattern(regexp ="^\\+[0-9]+$")
    private String phone;

    private String jobTitle;

    @NotBlank(message = "Department name is mandatory field")
    private String department;
    
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false, updatable = false)
    private Date updatedAt;
}
