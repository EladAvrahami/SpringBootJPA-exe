package com.jb.targil_spring1.beans;

import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Scope("prototype")
//jpa
@Entity  //ORM - Object Relation Mapping
public class Lecturer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double salary;

    @Singular
    @ManyToMany (cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Student> students;
    @Enumerated(EnumType.STRING)
    UserType userType;

    @Enumerated(EnumType.ORDINAL)
    UserType userTypeId;

}
