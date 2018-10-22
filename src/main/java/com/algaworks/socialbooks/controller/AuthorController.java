package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.dto.author.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.author.AuthorDTO;
import com.algaworks.socialbooks.dto.author.AuthorPostObjectDTO;
import com.algaworks.socialbooks.dto.author.AuthorPutObjectDTO;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO findById(@PathVariable("id") UUID id) {
        return authorService.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces =
            {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorCreateDTO save(@Valid @RequestBody AuthorPostObjectDTO authorPostObjectDTO) {
        return authorService.save(authorPostObjectDTO);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public AuthorCreateDTO update(@PathVariable("id") UUID id, @RequestBody AuthorPutObjectDTO authorPutObjectDTO) {
        return authorService.update(id, authorPutObjectDTO);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        authorService.delete(id);
    }
}