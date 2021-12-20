package com.jb.targil_spring1.repositories;

import com.jb.targil_spring1.beans.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LecturerRepo extends JpaRepository<Lecturer,Long> {
    Lecturer findLecturerById(Long id);
    List<Lecturer> findByName(String name);


    //                 SQL                            variable  run as native query
    @Query(value = "SELECT * FROM lecturer WHERE id = :userId AND name LIKE :userName", nativeQuery = true)
    Lecturer findById(int userId,String userName);


}
