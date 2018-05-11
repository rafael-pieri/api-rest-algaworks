package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.dto.AuthorCreateDTO;
import com.algaworks.socialbooks.dto.AuthorDTO;
import com.algaworks.socialbooks.dto.AuthorPostObjectDTO;
import com.algaworks.socialbooks.exceptions.AuthorNotFoundException;
import com.algaworks.socialbooks.model.author.Author;
import com.algaworks.socialbooks.model.author.AuthorHistory;
import com.algaworks.socialbooks.repository.AuthorsHistoryRepository;
import com.algaworks.socialbooks.repository.AuthorsRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorsHistoryService {

//    @Autowired
//    private AuthorsRepository authorsRepository;

    @Autowired
    private AuthorsHistoryRepository authorsHistoryRepository;

//    public Collection<AuthorDTO> list() {
//        ArrayList<AuthorDTO> authorDTOS = new ArrayList<>();
//
//        BeanUtils.copyProperties(authorsRepository.findAll(), authorDTOS);
//
//        return authorDTOS;
//    }
//
//    public AuthorDTO findById(UUID id) {
//        Optional<Author> author = authorsRepository.findOne(id);
//
//        if (author.isPresent()) {
//            AuthorDTO authorDTO = new AuthorDTO();
//            BeanUtils.copyProperties(author.get(), authorDTO);
//            return authorDTO;
//        }
//
//        throw new AuthorNotFoundException("The author could not be found.");
//    }

    public void save(Author author) {
        AuthorHistory authorHistory = new AuthorHistory.AuthorHistoryBuilder()
            .withAuthorId(author.getId())
            .withName(author.getName())
            .withBirth(author.getBirth())
            .withNationality(author.getNationality())
            .build();

        authorsHistoryRepository.save(authorHistory);
    }
}