package com.whut.bandou.dao;

import com.whut.bandou.bean.Book; 
import com.whut.bandou.bean.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> , JpaSpecificationExecutor<Book> {
    @Query("select b from Book b where b.ISBN = ?1")
    Book findByISBN(String ISBN);


    @Query("select b from Book b where b.bookname like ?1")
    Page<Book> findByBookname(String queryInfo, Pageable pageable);

    @Query("select b from Book b where b.writer like ?1")
    Page<Book> findByWriters(String queryInfo, Pageable pageable);

    @Query("select b from Book b where b.ISBN like ?1")
    Page<Book> findByISBNs(String queryInfo, Pageable pageable);
}
