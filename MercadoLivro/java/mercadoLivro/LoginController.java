package mercadoLivro;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L; //servlet serial
	
	private String valor(HttpServletRequest req, String param, String padrao) {
		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg="";
			String operacao=valor(req, "operacao", "");
			HttpSession session = req.getSession();
			if(operacao.equals("cadastrar")){
				String email=valor(req, "email", "");
				String nome=valor(req, "nome", "");
				String senha=valor(req, "senha", "");
				String senhaReafirm=valor(req, "senhaReafirm", "");
				String encoding=java.nio.charset.StandardCharsets.UTF_8.toString();
				URLEncoder.encode(email, encoding);
				URLEncoder.encode(nome, encoding);
				URLEncoder.encode(senha, encoding);
				URLEncoder.encode(senhaReafirm, encoding);
				if(!senha.equals(senhaReafirm)){
					msg="A senha digitada não coincide com a confirmação.";
				}else if(email==""||nome==""||senha==""){
					msg="Todos os campos são obrigatórios.";
				}else if(UsuariosDAO.consultarEmail(email)!=null){
					msg="Um usuário com este e-mail já está cadastrado.";
				}else if(UsuariosDAO.consultarNome(nome)!=null){
					msg="Já existe um usuário com este nome.";
				}else{
					UsuariosDAO.inclui(email, nome, senha, "usuarioNormal");
					msg="Usuário cadastrado com sucesso!";
				}
			}else if (operacao.equals("login")){
				String email=valor(req, "email", "");
				String senha=valor(req, "senha", "");
				if(email==""){
					msg="Digite seu e-mail.";
				}else if(senha==""){
					msg="Digite sua senha.";
				}else{
					Usuario usuarioLogado = UsuariosDAO.consultarEmail(email);
					if(usuarioLogado!=null&&usuarioLogado.getSenha().equals(senha)){
						session.setAttribute("usuarioLogado", usuarioLogado);
						session.setAttribute("buttonCode", generateNavBarCode(usuarioLogado));
						session.setAttribute("logged", "true");
						resp.sendRedirect("livros");
					}else{
						msg="Email ou senha não conferem.";
					}
				}
			}else if(operacao.equals("")){
				msg="";
			}else{
				throw new UnsupportedOperationException();
			}
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
	
	public String generateNavBarCode(Usuario usuario){
		String buttonCode="";
		buttonCode = buttonCode.concat("<a href=logout class=\"btn btn-register btn-xs\"> LOGOUT </a><li>\n");
		if(usuario.getUserType().equals("admin")){
			buttonCode = buttonCode.concat("<li><a href=admin class=\"btn btn-register btn-xs\"> ADMINISTRAÇÃO </a>\n");
		}
		return buttonCode;
	}
}