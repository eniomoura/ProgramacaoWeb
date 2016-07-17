package database;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class SegurancaFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		//Obtém referências ao request e ao response.
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		//Obtém a sessão corrente. Caso não exista, retorna "null".
		HttpSession session = request.getSession(false);
		//Endereço de login.
		String loginUri = request.getContextPath() + "/login";

		//Se sessão existe e existe um usuário associado a ela == usuário logado
		boolean loggedIn = session != null && session.getAttribute("usuario") != null;
		//Se o usuário está na página de login
		boolean loginRequest = request.getRequestURI().equals(loginUri);
		//Se o usuário está fazendo cadastro
		boolean cadUsuarioRequest = request.getRequestURI().equals(request.getContextPath()+"/cadastro");
		
		//Se estiver logado ou se for a página de login.
		if (loggedIn || loginRequest || cadUsuarioRequest) {
			//Segue adiante.
			chain.doFilter(request, response);
		} else {
			//Redireciona para o login.
			response.sendRedirect(loginUri);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}