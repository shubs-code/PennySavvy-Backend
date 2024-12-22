package com.project.spring.datajpa.model;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;
    private float amount;
    private String type;
    private String description;
    private Date date;
    private Date created;

}
