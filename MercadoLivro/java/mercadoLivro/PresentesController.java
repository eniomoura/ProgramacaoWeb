package mercadoLivro;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/presentes")
public class PresentesController extends HttpServlet{	
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
			ArrayList<ItemCarrinho> carrinho= new ArrayList<>();
			String carrinhoCode;
			String item=valor(req, "item", "");
			String op=valor(req, "op", "");
			HttpSession session = req.getSession();
			carrinhoCode=(String) session.getAttribute("carrinhoCode");
			if(op.equals("checkout")){
				carrinho = (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");
				for(ItemCarrinho i : carrinho){
					CarrinhoDAO.alterar(i.getNome(),
										i.getImagePath(),
										i.getTipo(),
										i.getPreco(),
										i.getEstoque()-i.getQuantidade(),
										i.getID());
				}
				req.setAttribute("notif", "<div class=\"panel panel-success col-xl-1\">Pedido realizado. Cheque seu e-mail para opções de pagamento e entrega.</div>");
				session.setAttribute("carrinho", new ArrayList<>());
				session.setAttribute("carrinhoCode", "");
			}else{
				req.setAttribute("notif", "");				
			}
			if(session.getAttribute("logged")==null || session.getAttribute("logged")!="true"){
				session.setAttribute("logged", "false");
				session.setAttribute("buttonCode", "<a href=login class=\"btn btn-register btn-xs\"> LOGIN </a>");
			}
			if(item!=""){
				boolean naoHaEstoque=false;
				if(session.getAttribute("logged").equals("true")){
					ItemCarrinho novoItem = CarrinhoDAO.getItemByID(Integer.parseInt(item));
						if(session.getAttribute("carrinho")==null){
							novoItem.adicionarItem();
							if(novoItem.getEstoque()>=0){
								carrinho.add(novoItem);
							}else{
								naoHaEstoque=true;
							}
							session.setAttribute("carrinho", carrinho);
						}else{
							carrinho = (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");
							for(ItemCarrinho i : carrinho){
								if(i.getID()==novoItem.getID()){
									novoItem.setQuantidade(1);
									if(i.getQuantidade()<i.getEstoque()){
										i.adicionarItem();
									}else{
										naoHaEstoque=true;
									}
								}
							}
							if(novoItem.getQuantidade()==0){
								novoItem.adicionarItem();
								if(novoItem.getEstoque()>=0){
									carrinho.add(novoItem);
								}else{
									naoHaEstoque=true;
								}
							}
							session.setAttribute("carrinho", carrinho);
						}
						carrinhoCode=gerarCarrinhoCode(session);
						if(naoHaEstoque)carrinhoCode=carrinhoCode+"Não há estoque suficiente desse item.";
				}else{
					carrinhoCode="É preciso efetuar login para adicionar itens ao carrinho!";
				}
			}else if(session.getAttribute("logged").equals("true")){
				carrinhoCode=gerarCarrinhoCode(session);
			}else{
				carrinhoCode="";
			}
			session.setAttribute("carrinhoCode", carrinhoCode);
			req.setAttribute("itemCode", geradorListaPresentes(CarrinhoDAO.listar()));
			req.getRequestDispatcher("presentes.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}
	
	public String geradorListaPresentes(List<ItemCarrinho> items){
		String itemCode="";
		for(ItemCarrinho item : items){
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			if(item.getTipo().equals("presente")){
				itemCode = itemCode.concat("<div class=\"card col-md-3 col-xs-12\">\n" +
"	            	              			 <div class=\"thumbnail\">\n" +
"	                	             			 <a><img src=\"image/itemIcons/"+ item.getImagePath() + "\" width=\"250\" height=\"250\"" +
"	                    	          					 \nalt="+ item.getNome() +"></a>\n" +
"	                	    	          		 <div class=\"caption\">\n" +
"	                      			           		 <h3><a>"+ item.getNome() +"</a></h3>\n" +
"	                              	 	  			<span class=\"col-ms-3\" style=\"font-size:10px;\">"+ "&nbsp&nbspEm estoque: " + item.getEstoque() +"</span>\n" +
"	 		                    		         </div>\n" +
"	                            	  			 <p></p>\n" +
"	                            	  			 <div class=\"money\" style=\"display:inline-block;\">\n" +
"	                            	     		 	<sup>R$</sup>\n" +
"	                              	 	  			<span class=\"val\" style=\"font-size:27px;\">"+ df.format(item.getPreco()) +"</span>\n" +

"	                              				 </div>\n" +
"	                              				 <form><button type=\"submit\" class=\"btn-shop btn btn-warning btn-block\" name=\"item\" value=\""+ item.getID() +"\"> Adicionar ao carrinho</button></form>\n" +
"	                              				 <p></p>\n" +
"					              	 	      </div>\n" +
"	                        			   </div>" +
"											");
			}
		}
		return itemCode;
	}
	public String gerarCarrinhoCode(HttpSession session){
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");
		String carrinhoCode="<ul class=\"list-group\"><li class=\"list-group-item\" style=\"text-align:center\">Carrinho de Compras</li>";
		float valorTotal=0;
		if(session.getAttribute("logged").equals("true")&&carrinho!=null){
			for(ItemCarrinho item : carrinho){
				carrinhoCode=carrinhoCode+"<li class=\"list-group-item\">"+ item.getNome() + "<a href=\"teste\" style=\"color:red\" class=\"pull-right\">[-]</a>" +"<br><span class=\"badge\"><sup>R$</sup> "+ df.format(item.getPreco()) +"</span><span class=\"badge\">"+ item.getQuantidade() +"x</span><br></li>";
				valorTotal+=item.getPreco()*item.getQuantidade();
			}
			carrinhoCode=carrinhoCode+"<li class=\"list-group-item\">Valor total: <sup>R$</sup> "+ df.format(valorTotal) +"<span class=\"badge\"></span></li></ul>";
		}else{
			carrinhoCode="</ul>";
		}
		return carrinhoCode;
	}
}