package com.jb.targil_spring1.CLR;

import com.jb.targil_spring1.beans.Lecturer;
import com.jb.targil_spring1.beans.Student;
import com.jb.targil_spring1.facade.School;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;

//@Component
@RequiredArgsConstructor//במקום לעשות @AutoWired לכל ריפוסיטורי בפיינל
@Order(1)
public class Tester implements CommandLineRunner {
    private final School school;

    @Override
    public void run(String... args) throws Exception {
        //create mock data
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
                .endDate(Date.valueOf("2021-06-20"))
                .build();

        Lecturer lecturer1 = Lecturer.builder()
                .name("Aviv")
                .salary(95_000)
                .students(Arrays.asList(student1,student2))//הוספה של מערך תלמידים ללא שימוש בסינגולר
                .build();

        Lecturer lecturer2 = Lecturer.builder()
                .name("Dikla")
                .salary(12_000)
                .student(student3)//סינגולר מאפשר לי
                // להוסיף תלמידים למורה בצורה יחידנית אחד אחרי השני
                .student(student4)
                .build();

        //save two lecturer with two student to each one
        school.addLecturer(lecturer1);
        school.addLecturer(lecturer2);

        //get lecturer by id
        System.out.println(school.findLecturerById(1));

        //get students by lecturer id
        System.out.println(school.getStudentByLecturerId(2));

        //get all students
        System.out.println(school.getAllStudents());

        //get all grades
        System.out.println(school.getAllGrades());

        //get all grades in date
        System.out.println(school.getGradesByDate(Date.valueOf("2021-06-20")));

        //get all grades between two dates
        System.out.println(school.getGradesBetweenDates(Date.valueOf("2021-06-18"),Date.valueOf("2021-06-20")));
    }
}
