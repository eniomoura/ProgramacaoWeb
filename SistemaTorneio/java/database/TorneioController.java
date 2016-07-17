package database;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/torneio")
public class TorneioController extends HttpServlet {
	private String valor(HttpServletRequest req, String param, String padrao) {
		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}

	private int toInt(HttpServletRequest req, String param, String padrao) {
		return Integer.parseInt(valor(req, param, padrao));
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg;
			String op = valor(req, "operacao", "");
			int id = toInt(req, "id", "0");
			int vitorias = toInt(req, "vitorias", "0");
			String nome = valor(req, "nome", "");
			if (op.equals("incluir")) {
				TorneioDAO.inclui(id, vitorias, nome);
				msg = "Inclusão realizada com sucesso.";
			} else if (op.equals("alterar")) {
				TorneioDAO.alterar(id, vitorias, nome);
				msg = "Alteração realizada com sucesso.";
			} else if (op.equals("excluir")) {
				TorneioDAO.excluir(id);
				resp.sendRedirect("torneio");
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("carregar")) {
				req.setAttribute("id", id);
				req.setAttribute("vitorias", vitorias);
				req.setAttribute("nome", nome);
				msg="";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			
			req.setAttribute("msg", msg);

			List<Jogador> jogadores = TorneioDAO.listar();
			req.setAttribute("jogadores", jogadores);
			
			req.getRequestDispatcher("TorneioView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}