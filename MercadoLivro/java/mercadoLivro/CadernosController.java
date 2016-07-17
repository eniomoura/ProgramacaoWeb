package mercadoLivro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cadernos")
public class CadernosController extends HttpServlet{	
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
			String msg=valor(req, "", "");
			HttpSession session = req.getSession();
			if(req.getSession().getAttribute("logged")!="true"){ //substituir por filter
				session.setAttribute("logged", "false");
				session.setAttribute("buttonCode", "<a href=login class=\"btn btn-register btn-xs\"> LOGIN </a>");
			}
			req.setAttribute("itemCode", geradorListaCadernos(CarrinhoDAO.listar()));
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("cadernos.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
	
	public String geradorListaCadernos(List<ItemCarrinho> items){
		String itemCode="";
		for(ItemCarrinho item : items){
			if(item.getTipo().equals("caderno")){
				itemCode = itemCode.concat("<div class=\"card col-md-3 col-xs-12\">\n" +
"	            	              			 <div class=\"thumbnail\">\n" +
"	                	             			 <a><img src=\"image/itemIcons/"+ item.getImagePath() + "\"" +
"	                    	          					 \nalt="+ item.getNome() +"></a>\n" +
"	                	    	          		 <div class=\"caption\">\n" +
"	                      			           		 <h3><a>"+ item.getNome() +"</a></h3>\n" +
"	 		                    		         </div>\n" +
"	                            	  			 <p></p>\n" +
"	                            	  			 <div class=\"money\" style=\"display:inline-block;\">\n" +
"	                            	     		 	<sup>R$</sup>\n" +
"	                              	 	  			<span class=\"val\" style=\"font-size:27px;\">"+ item.getPreco() +"</span>\n" +
"	                              				 </div>\n" +
"	                              				 <button type=\"button\" class=\"btn-shop btn btn-warning btn-block\"> Adicionar ao carrinho</button>\n" +
"	                              				 <p></p>\n" +
"					              	 	      </div>\n" +
"	                        			   </div>" +
"											");
			}
		}
		return itemCode;
	}
}