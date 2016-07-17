<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="mercadoLivro.ItemCarrinho"%>
<html>

<head>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<title>Mercado Livro - Admin</title>
</head>

<body>
<div class=container align=center>
  <h1>
    <a href="admin">Administração de Estoque</a>
  </h1>
  <a href="livros">Voltar ao Site</a>
  <p></p>
  <form>
    <table>
      <tr><td><p></p></td></tr>
      <tr class=row>
        <td class="col-sm-1">Nome:</td>
        <td class="col-sm-1"><input name="nome" value="${nome}"></td>
      </tr>
      <tr><td><p></p></td></tr>
      <tr class=row>
        <td class="col-sm-1">Tipo:</td>
        <td class="col-sm-1"><select name="tipo">
        	<option value="livro">Livro</option>
        	<option value="caderno">Caderno</option>
        	<option value="presente">Presente</option>
        </select></td>
      </tr>
      <tr><td><p></p></td></tr>
      <tr class=row>
        <td class="col-sm-1">Preço:</td>
        <td class="col-sm-1"><input name="preco" value="${tipo}"></td>
      </tr>
      <tr><td><p></p></td></tr>
      <tr class=row>
        <td class="col-sm-1">Estoque:</td>
        <td class="col-sm-1"><input name="estoque" value="${tipo}"></td>
      <tr><td><p></p></td></tr>
      <tr class=row>
        <td class="col-sm-1">Imagem:</td>
        <!--td class="col-sm-1"><input type="file" name="imagePath" value="${imagePath}"></td-->
        <td class="col-sm-1"><select name="imagePath">
        	<option>${imagesCode}</option>
        </select></td>
      </tr>
    </table>
    <p><br></p>
    <button name="operacao" value="incluir" class="btn btn-space">Incluir</button>
    <button name="operacao" value="excluir" class="btn btn-space">Excluir</button>
    <button name="operacao" value="alterar" class="btn btn-space">Alterar</button>
  </form>
  <b>${msg}</b>
  <br>
  <p><hr></p>
  <h4>Lista de Items:</h4>
  <table class=table-condensed>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Preço</th>
      <th>Estoque</th>
      <th>Tipo</th>
      <th></th>
    </tr>
	<%
   	 	List<ItemCarrinho> items = (List<ItemCarrinho>) request.getAttribute("items");
    	if (items != null && !items.isEmpty()) {
    	for (ItemCarrinho i : items) {
    %>
    <tr>
      <td><%=i.getID()%></td>
      <td><%=i.getNome()%></td>
      <td><%=i.getPreco()%></td>
      <td><%=i.getEstoque()%></td>
      <td><%=i.getTipo()%></td>
      <td><a href="admin?operacao=excluir&id=<%=i.getID()%>">Excluir</a></td>
    </tr>
    <%
      }
    }
    %>
  </table>
  </div>
</body>

</html>