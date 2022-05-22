package com.whut.bandou.service;
 
import com.whut.bandou.bean.Book;
import com.whut.bandou.bean.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Page<Book> listBook(Pageable pageable);

    Page<Book> listBook(String queryOption ,String queryInfo, Pageable pageable);

    Book findByISBN(String ISBN);

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    Book findById(Long id);

}
