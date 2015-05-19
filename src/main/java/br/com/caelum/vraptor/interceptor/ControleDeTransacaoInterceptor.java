package br.com.caelum.vraptor.interceptor;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;

@Intercepts(after={AutorizadorInterceptor.class, LogInterceptor.class})
public class ControleDeTransacaoInterceptor {
	private final EntityManager em;
	
	@Inject 
	public ControleDeTransacaoInterceptor(EntityManager em) {
		this.em = em;
	}
	
	@Deprecated
	public ControleDeTransacaoInterceptor() {
		this(null);
	}
	
	@AroundCall
	public void intercepts(SimpleInterceptorStack stack) {
		this.em.getTransaction().begin();
		stack.next();
		this.em.getTransaction().commit();
	}
}
