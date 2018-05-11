package com.algaworks.socialbooks.controller;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.dto.BookDTO;
import com.algaworks.socialbooks.services.AuthorsService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.util.Collection;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "authors")
@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    @ApiOperation(value = "Gets list of authors", response = AuthorDTO.class,
        responseContainer = "List", tags = {"Authors"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = AuthorDTO.class)})
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Collection<AuthorDTO> list() {
        return authorsService.list();
    }

    @ApiOperation(value = "Gets author by id", response = AuthorDTO.class, tags = {"Authors"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = AuthorDTO.class)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO findById(@PathVariable("id") UUID id) {
        return authorsService.findById(id);
    }

    @ApiOperation(value = "Creates a new author", response = AuthorCreateDTO.class, tags = {"Authors"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation", response = AuthorCreateDTO.class)})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorCreateDTO save(@Valid @RequestBody AuthorPostObjectDTO authorPostObjectDTO) {
        return authorsService.save(authorPostObjectDTO);
    }
}