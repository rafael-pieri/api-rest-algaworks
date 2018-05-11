package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.model.book.Book;
import com.algaworks.socialbooks.dto.BookDTO;
import com.algaworks.socialbooks.repository.BooksRepository;
import com.algaworks.socialbooks.repository.CommentsRepository;
import com.algaworks.socialbooks.exceptions.BookNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public Collection<BookDTO> list() {
        ArrayList<BookDTO> bookDTO = new ArrayList<>();

        BeanUtils.copyProperties(booksRepository.findAll(), bookDTO);

        return bookDTO;
    }

    public BookDTO findById(Long id) {
        Optional<Book> book = booksRepository.findOne(id);

        if (!book.isPresent()) {
            throw new BookNotFoundException("The book could not be found.");
        }

        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(book.get(), bookDTO);

        return bookDTO;
    }

    public BookDTO save(Book book) {
        BookDTO bookDTO = new BookDTO();

        BeanUtils.copyProperties(booksRepository.save(book).orElseThrow(RuntimeException::new), bookDTO);

        return bookDTO;
    }

    public void delete(Long id) {
        if(!booksRepository.findOne(id).isPresent()){
            throw new BookNotFoundException("The book could not be found.");
        }

        booksRepository.delete(id);
    }

    public void update(Long id, Book book) {
        Optional<Book> one = booksRepository.findOne(id);

        if(!one.isPresent()){
            throw new BookNotFoundException("The book could not be found.");
        }

        booksRepository.save(one.get());
    }
}