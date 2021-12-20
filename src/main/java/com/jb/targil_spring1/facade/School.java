package com.jb.targil_spring1.facade;

import com.jb.targil_spring1.beans.Lecturer;
import com.jb.targil_spring1.beans.Student;
import com.jb.targil_spring1.exceptions.SchoolException;
import com.jb.targil_spring1.repositories.LecturerRepo;
import com.jb.targil_spring1.repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class School {
    private final LecturerRepo lecturerRepo;
    private final StudentRepo studentRepo;

    public void addLecturer(Lecturer lecturer){
        lecturerRepo.save(lecturer);
    }

    public void addStudent(Student student){studentRepo.save(student);}

    public Lecturer findLecturerById(long id){
        return lecturerRepo.findLecturerById(id);
    }

    public List<Student> getStudentByLecturerId(long id){
        return findLecturerById(id).getStudents();
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public List<Integer> getAllGrades(){
        //like a boss
        return getAllStudents().stream().map(Student::getGrade).collect(Collectors.toList());

        //Senior2
        //return getAllStudents().stream().map(item->item.getGrade()).collect(Collectors.toList());

        /* junior
        List<Integer> grades = new ArrayList<>();
        for (Student item:getAllStudents()){
            grades.add(item.getGrade());
        }
        return grades;
        */
    }

    public List<Integer> getGradesByDate(Date myDate){
        return studentRepo.findByEndDate(myDate) // List<Student> by date
                .parallelStream().map(Student::getGrade) // get only student grades
                .collect(Collectors.toList());   // create the required list (getGrade(int)->List<Integer>)
    }

    public List<Integer> getGradesBetweenDates(Date startDate, Date endDate){
        return studentRepo.findByEndDateBetween(startDate,endDate)
                .stream().map(Student::getGrade)
                .collect(Collectors.toList());
    }

    public List<Lecturer> getAllLecturer(){
        return lecturerRepo.findAll();
    }

    public void deletedStudent(long id){
        studentRepo.deleteById(id);
    }

    public void updateLecturer(Lecturer lecturer) throws SchoolException {
        if(!lecturerRepo.existsById(lecturer.getId())){
            throw new SchoolException("Lecturer not found");
        }
        Lecturer updateLecturer = lecturerRepo.findLecturerById(lecturer.getId());
        updateLecturer.setName(lecturer.getName());
        updateLecturer.setSalary(lecturer.getSalary());
        lecturerRepo.saveAndFlush(updateLecturer);
    }
}
