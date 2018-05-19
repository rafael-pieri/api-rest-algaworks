package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.services.AuthorService;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Collection<AuthorDTO> findAll() {
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
}