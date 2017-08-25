package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Author;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Author> listar(){
		return autoresRepository.findAll();
	}
	
	public Author salvar(Author autor) {
		if (autor.getId() != null){
			Author a = autoresRepository.findOne(autor.getId());
			
			if (a != null){
				throw new AutorExistenteException("O autor já existe.");
			}
		}
		
		return autoresRepository.save(autor);
	}
	
	public Author buscar(Long id){
		Author autor = autoresRepository.findOne(id);
		
		if (autor == null){
			throw new AutorNaoEncontradoException("O autor não pôde ser encontrado.");
		}
		
		return autor;
	}

}
