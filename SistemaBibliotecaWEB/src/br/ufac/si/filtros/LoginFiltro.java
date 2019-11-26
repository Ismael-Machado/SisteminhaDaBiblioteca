package br.ufac.si.filtros;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFiltro implements Filter {

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession httpSession = httpRequest.getSession(false);
		
		String loginURI = httpRequest.getContextPath()+ "/paginas/login.xhtml?faces-redirect=true";
		
		boolean estaLogado = (httpSession != null && httpSession.getAttribute("usuarioLogado") != null);
		
		boolean acessandoLogin = httpRequest.getRequestURL().equals(loginURI);
		
		if(estaLogado || acessandoLogin) {
			chain.doFilter(request, response);
		}else {
			httpResponse.sendRedirect(loginURI);
		}

	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

}
