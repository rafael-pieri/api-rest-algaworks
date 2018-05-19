package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.dto.BookDTO;
import com.algaworks.socialbooks.repository.BookRepository;
import com.algaworks.socialbooks.repository.CommentRepository;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Collection<BookDTO> list() {
        ArrayList<BookDTO> bookDTO = new ArrayList<>();

        BeanUtils.copyProperties(bookRepository.findAll(), bookDTO);

        return bookDTO;
    }

    public BookDTO findById(Long id) {
        Optional<Book> book = bookRepository.findOne(id);

        if (!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(book.get(), bookDTO);

        return bookDTO;
    }

    public BookDTO save(Book book) {
        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(bookRepository.save(book).orElseThrow(RuntimeException::new), bookDTO);

        return bookDTO;
    }

    public void delete(Long id) {
        if(!bookRepository.findOne(id).isPresent()){
            throw new BookNotFoundException("The book could not be found.");
        }

        bookRepository.delete(id);
    }

    public void update(Long id, Book book) {
        Optional<Book> one = bookRepository.findOne(id);

        if(!one.isPresent()){
            throw new BookNotFoundException("The book could not be found.");
        }

        bookRepository.save(one.get());
    }
}