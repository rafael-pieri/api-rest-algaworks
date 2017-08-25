package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Author;
import com.algaworks.socialbooks.repository.AuthorsRepository;
import com.algaworks.socialbooks.services.exceptions.AuthorAlreadyExistsException;
import com.algaworks.socialbooks.services.exceptions.AuthorNotFoundException;

@Service
public class AuthorsService {
	
	@Autowired
	private AuthorsRepository authorsRepository;
	
	public List<Author> list(){
		return authorsRepository.findAll();
	}
	
	public Author save(Author author) {
		if (author.getId() != null){
			Author a = authorsRepository.findOne(author.getId());
			
			if (a != null){
				throw new AuthorAlreadyExistsException("The author already exists.");
			}
		}
		
		return authorsRepository.save(author);
	}
	
	public Author findById(Long id){
		Author author = authorsRepository.findOne(id);
		
		if (author == null){
			throw new AuthorNotFoundException("The author could not be found.");
		}
		
		return author;
	}

}
