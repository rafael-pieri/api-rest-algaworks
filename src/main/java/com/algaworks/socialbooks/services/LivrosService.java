package com.algaworks.socialbooks.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comment;
import com.algaworks.socialbooks.domain.Book;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Book> listar() {
		return livrosRepository.findAll();
	}
	
	public Book buscar(Long id) {
		Book livro = livrosRepository.findOne(id);
		
		if (livro == null){
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		
		return livro;
	}
	
	public Book salvar(Book livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livrosRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Book livro){
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Book livro){
		buscar(livro.getId());
	}
	
	public Comment salvarComentario(Long livroId, Comment comentario){
		Book livro = buscar(livroId);
		
		comentario.setBook(livro);
		comentario.setDate(new Date());
		
		return comentariosRepository.save(comentario);
	}
	
	public List<Comment> listarComentarios(Long livroId){
		Book livro = buscar(livroId);
		return livro.getComments();
	}

}
