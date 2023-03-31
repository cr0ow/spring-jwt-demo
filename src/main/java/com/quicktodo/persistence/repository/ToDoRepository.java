package com.quicktodo.persistence.repository;

import com.quicktodo.persistence.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByOwnerUserId(long ownerId);

}
