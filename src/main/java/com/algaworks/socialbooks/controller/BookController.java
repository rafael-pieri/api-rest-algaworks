package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.dto.book.BookPostObjectDTO;
import com.algaworks.socialbooks.dto.book.BookDTO;
import com.algaworks.socialbooks.dto.book.BookUpdateDTO;
import com.algaworks.socialbooks.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @CrossOrigin
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookDTO> list() {
        return bookService.list();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO save(@Valid @RequestBody BookPostObjectDTO bookPostObjectDTO) {
        return bookService.save(bookPostObjectDTO);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Long id) {
        final BookDTO book = bookService.findById(id);
        final CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(book);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        bookService.update(id, bookUpdateDTO);
    }
}