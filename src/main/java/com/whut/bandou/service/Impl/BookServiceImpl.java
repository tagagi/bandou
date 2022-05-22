package com.whut.bandou.service.Impl;

import com.whut.bandou.bean.Book; 
import com.whut.bandou.bean.Comment;
import com.whut.bandou.dao.BookDao;
import com.whut.bandou.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Page<Book> listBook(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    @Override
    public Page<Book> listBook(String queryOption, String queryInfo, Pageable pageable) {
        if (queryOption.equals("书名")) {
            return bookDao.findByBookname(queryInfo, pageable);
        } else if (queryOption.equals("作者")) {
            return bookDao.findByWriters(queryInfo, pageable);
        } else {
            return bookDao.findByISBNs(queryInfo, pageable);
        }
    }

    @Override
    public Book findByISBN(String ISBN) {
        return bookDao.findByISBN(ISBN);
    }

    @Override
    public Book saveBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book book1 = bookDao.findById(id).orElse(null);
        if (book1 == null) {
            System.out.println("未获得更新对象");
            return null;
        }
        book.setDescription(book1.getDescription());
        BeanUtils.copyProperties(book, book1);
        return bookDao.save(book1);
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id).orElse(null);
    }

}
