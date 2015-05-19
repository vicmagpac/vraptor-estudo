package br.com.caelum.vraptor.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Public;
import br.com.caelum.vraptor.dao.UsuarioDao;
import br.com.caelum.vraptor.model.Usuario;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class LoginController {
	private final UsuarioDao dao;
	private final Result result;
	private final Validator validator;
	private final UsuarioLogado usuarioLogado;
	
	
	@Inject
	public LoginController(UsuarioDao dao, Result result, Validator validator, UsuarioLogado usuarioLogado) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Deprecated
	public LoginController() {
		this(null, null, null, null);
	}
	
	@Get
	@Public
	public void formulario() {}
	
	@Post
	@Public
	public void autentica(Usuario usuario) {
		if (!this.dao.existe(usuario)) {
			this.validator.add(new I18nMessage("login", "login.invalido"));
			this.validator.onErrorUsePageOf(this).formulario();
		}
		
		this.usuarioLogado.setUsuario(usuario);
		this.result.redirectTo(ProdutoController.class).lista();
	}
}
