package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.annotation.Public;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.controller.LoginController;
import br.com.caelum.vraptor.controller.UsuarioLogado;

@Intercepts
public class AutorizadorInterceptor {
	
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private final ControllerMethod controllerMethod;
	
	@Inject
	public AutorizadorInterceptor(UsuarioLogado usuarioLogado, Result result, ControllerMethod controllerMethod) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.controllerMethod = controllerMethod;
	}
	
	@Deprecated
	public AutorizadorInterceptor() {
		this(null, null, null);
	}
	
	@Accepts
	public boolean accepts() {
		return !this.controllerMethod.containsAnnotation(Public.class);
	}
	
	@AroundCall
	public void intercepta(SimpleInterceptorStack stack) {
		// antes de executar o código
		if (this.usuarioLogado.getUsuario() == null) {
			this.result.redirectTo(LoginController.class).formulario();
			return;
		}
		
		stack.next(); // executa o código
		
		// depois de executar o codigo
	}
	
}
