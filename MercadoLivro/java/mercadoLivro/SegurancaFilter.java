package mercadoLivro;

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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		
		String loginUri = request.getContextPath() + "/login";
		String livrosUri = request.getContextPath() + "/livros";
		String cadernosUri = request.getContextPath() + "/cadernos";
		String presentesUri = request.getContextPath() + "/presentes";
		
		boolean loggedIn = session != null && session.getAttribute("usuarioLogado")!= null;
		boolean loginRequest = request.getRequestURI().equals(loginUri);
		boolean livrosRequest = request.getRequestURI().equals(livrosUri);
		boolean cadernosRequest = request.getRequestURI().equals(cadernosUri);
		boolean presentesRequest = request.getRequestURI().equals(presentesUri);
		boolean stylesheetRequest = request.getRequestURI().contains("css");
		boolean imageRequest = request.getRequestURI().contains("/image/");
		
		if (loggedIn || loginRequest || livrosRequest || cadernosRequest || presentesRequest || stylesheetRequest ||imageRequest) {
			chain.doFilter(request, response);
		} else {
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