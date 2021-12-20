package com.jb.targil_spring1.controllers;


import com.jb.targil_spring1.beans.Lecturer;
import com.jb.targil_spring1.beans.Student;
import com.jb.targil_spring1.beans.UserDetails;
import com.jb.targil_spring1.exceptions.LoginException;
import com.jb.targil_spring1.exceptions.SchoolException;
import com.jb.targil_spring1.facade.School;
import com.jb.targil_spring1.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")  //http://localhost:8080/school
@RequiredArgsConstructor
//enable CrossOrigion, allow to get request from web browser on another port (security issue)
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class SchoolController {
    private final School school;
    private final JWTutil myUtil;

    @GetMapping("login/{userEmail}")
    public ResponseEntity<?> getLoginToken(@PathVariable String userEmail){
        UserDetails admin = new UserDetails(userEmail,"12345","Admin");
        //JWTutil myJWT = new JWTutil();
        return new ResponseEntity<>(myUtil.generateToken(admin), HttpStatus.ACCEPTED);
    }

    @GetMapping("lecturer/all")  //http://localhost:8080/lecturer/all
    public ResponseEntity<?> getAllLecturer(@RequestHeader (name="Authorization") String token) throws LoginException {
        //check if token is valid
        if (myUtil.validateToken(token,new UserDetails("admin@admin.com","12345","admin"))){
            return new ResponseEntity<>(school.getAllLecturer(), HttpStatus.OK);
        } else {
            throw new LoginException("Invalid user !");
        }

    }

    @PostMapping("lecturer/add") //http://localhost:8080/lecturer/lecturer/add
    public ResponseEntity<?> addLecturer(@RequestBody Lecturer lecturer){
        school.addLecturer(lecturer);
        System.out.println(lecturer);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("student/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student)
    {
        school.addStudent(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("student/all")
    public ResponseEntity<?> getAllStudents(){
        return new ResponseEntity<>(school.getAllStudents(),HttpStatus.OK);
    }

    //delete
    @DeleteMapping("student/{id}") //to put variable into url String , we will use { }
    public ResponseEntity<?> deleteStudent(@PathVariable long id){
            school.deletedStudent(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //update
    @PutMapping("lecturer/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateLecturer(@RequestBody Lecturer lecturer) throws SchoolException {
        try {
            school.updateLecturer(lecturer);
        } catch (SchoolException e) {
            throw new SchoolException(lecturer.getName()+" is not in the system");
        }
    }


}
