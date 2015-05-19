package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.annotation.Log;
import br.com.caelum.vraptor.controller.ControllerMethod;

@Intercepts
public class LogInterceptor {
	
	private final ControllerMethod controllerMethod;
	
	@Inject
	public LogInterceptor(ControllerMethod controllerMethod) {
		this.controllerMethod = controllerMethod;
	}
	
	public LogInterceptor() {
		this(null);
	}
	
	
	@Accepts
	public boolean accepts() {
		return this.controllerMethod.containsAnnotation(Log.class);
	}
	
	
	@BeforeCall
	public void before() {
		String nomeDoMetodo = this.controllerMethod.getMethod().getName();
		System.out.println("Executando o método: " + nomeDoMetodo);
	}
}
