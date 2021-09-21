package com.example.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository
    extends JpaRepository<Book,Long> {

    @Query(value="select * from Book p where p.name like %:keyword% or p.author like %:keyword% ",nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword")String keyword);

    }

