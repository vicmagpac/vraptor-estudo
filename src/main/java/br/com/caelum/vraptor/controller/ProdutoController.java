package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Log;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ProdutoController {
	
	private final Result result;
	private final ProdutoDao dao;
	private final Validator validator;
	private final Mailer mailer;
	
	@Inject
	public ProdutoController(Result result, ProdutoDao dao, Validator validator, Mailer mailer) {
		this.result = result;
		this.dao = dao;
		this.validator = validator;
		this.mailer = mailer;
	}
	
	/**
	 *  para evitar que alguém chame esse construtor por engano em testes e até mesmo
	 *  para deixar explicito que é apenas para o uso do CDI
	 */
	@Deprecated
	ProdutoController() {
		this(null, null, null, null);
	}
		
	@Get("/")
	public void inicio() {
		
	}
	
	@Get
	@Log
	public void lista() {
		this.result.include("produtoList", this.dao.lista());
	}
	
	@Get
	public void listaXML() {
		this.result.use(Results.xml()).from(this.dao.lista()).serialize();
	}
	
	@Get
	public void listaJSON() {
		this.result.use(Results.json()).from(this.dao.lista()).serialize();
	}
	
	@Get
	@Log
	public void formulario() {
		
	}
	
	@Post
	public void adiciona(@Valid Produto produto) {
		
		// se a validação não passar, retorna para o formulario
		this.validator.onErrorUsePageOf(this).formulario();
		
		dao.adiciona(produto);
		this.result.include("mensagem", "Produto adicionado com sucesso!");
		this.result.redirectTo(this).lista();
		
	}
	
	@Get
	public void remove(Produto produto) {
		dao.remove(produto);
		
		this.result.include("mensagem", "Produto excluído com sucesso!");
		this.result.redirectTo(this).lista();
	}
	
	@Get
	public void enviaPedidoDeNovosItens(Produto produto) throws EmailException {
		Email email = new SimpleEmail();
		email.setSubject("[vraptor-produtos] Precisamos de mais estoque");
		email.addTo("victor.magalhaesp@gmail.com");
		email.setMsg("Precisamos de mais itens do produto" + produto.getNome());
		
		this.mailer.send(email);
		this.result.redirectTo(this).lista();
	}
}
