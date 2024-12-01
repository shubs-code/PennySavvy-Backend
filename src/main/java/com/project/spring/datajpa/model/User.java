package com.project.spring.datajpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="score")
    private float score;

    @Column(name="profileImage")
    private String profileImage;
}