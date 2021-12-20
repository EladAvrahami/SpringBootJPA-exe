package com.jb.targil_spring1.repositories;

import com.jb.targil_spring1.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByEndDate(Date date);
    List<Student> findByEndDateBetween(Date startDate, Date endDate);//חשוב מאוד לעבוד עם Date של SQL
}


