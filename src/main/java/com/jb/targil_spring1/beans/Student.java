package com.jb.targil_spring1.beans;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//spring JPA
@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private int grade;
    private Date endDate;


}
