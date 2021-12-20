package com.jb.targil_spring1.CLR;

import com.jb.targil_spring1.beans.Lecturer;
import com.jb.targil_spring1.beans.Student;
import com.jb.targil_spring1.beans.UserType;
import com.jb.targil_spring1.repositories.LecturerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class CreateData implements CommandLineRunner {
    private final LecturerRepo lecturerRepo;
    @Override
    public void run(String... args) throws Exception {
        Student student1 = Student.builder()
                .grade(100)
                .endDate(Date.valueOf("2021-06-20"))
                .build();
        Student student2 = Student.builder()
                .grade(98)
                .endDate(Date.valueOf("2021-06-18"))
                .build();
        Student student3 = Student.builder()
                .grade(90)
                .endDate(Date.valueOf("2021-06-23"))
                .build();
        Student student4 = Student.builder()
                .grade(60)
                .endDate(Date.valueOf("2021-06-01"))
                .build();

        Lecturer lecturer1 = Lecturer.builder()
                .name("Aviv")
                .salary(95_000)
                .students(Arrays.asList(student1,student2))
                .userType(UserType.COMPANY)
                .userTypeId(UserType.COMPANY)
                .build();

        Lecturer lecturer2 = Lecturer.builder()
                .name("Dikla")
                .salary(12_000)
                .student(student3)
                .student(student4)
                .userType(UserType.ADMINISTRATOR)
                .userTypeId(UserType.ADMINISTRATOR)
                .build();

        lecturerRepo.saveAll(Arrays.asList(lecturer1,lecturer2));

        System.out.println("Me finished !!!!");
    }
}
