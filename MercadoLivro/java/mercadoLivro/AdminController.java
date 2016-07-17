package mercadoLivro;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			String msg;
			String op = valor(req, "operacao", "");
			String nome = valor(req, "nome", "");
			String imagePath = valor(req, "imagePath", "");
			String tipo = valor(req, "tipo", "");
			String preco = valor(req, "preco", "");
			String estoque = valor(req, "estoque", "");
			String id = valor(req, "id", "");
			if (op.equals("incluir")) {
				CarrinhoDAO.inclui(nome, imagePath, tipo, Double.parseDouble(preco), Integer.parseInt(estoque));
				msg = "Inclusão realizada com sucesso.";
			} else if (op.equals("alterar")) {
				CarrinhoDAO.alterar(nome, imagePath, tipo, Double.parseDouble(preco), Integer.parseInt(estoque), Integer.parseInt(id));
				msg = "Alteração realizada com sucesso.";
			} else if (op.equals("excluir")) {
				CarrinhoDAO.excluir(Integer.parseInt(id));
				resp.sendRedirect("admin");
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			
			req.setAttribute("msg", msg);
			req.setAttribute("imagesCode", gerarImagesCode(req));
			List<ItemCarrinho> items = CarrinhoDAO.listar();
			req.setAttribute("items", items);
			
			req.getRequestDispatcher("admin.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
	
	public String gerarImagesCode(HttpServletRequest request){
		String imagesCode="";
		File dir = new File("C:/Users/Enio/workspace/mercadoLivro/src/main/webapp/image/itemIcons");
		File[] list = dir.listFiles();
		imagesCode=(imagesCode + list[0].getName() + "</option>\n");
		for(int i=1;i<list.length;i++){
			imagesCode=(imagesCode + "<option>" + list[i].getName() + "</option>\n");
			if(i==list.length-1){
				imagesCode=(imagesCode + "<option>" + list[i].getName() + "\n");
			}
		}
		return imagesCode;
	}
}