<!DOCTYPE html>
<%@page import="database.Jogador"%>
<%@page import="java.util.List"%>
<html>

<head>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<title>Torneio</title>
</head>

<body>
<div class=container align=center>
  <h1>
    <a href="torneio">Sistema de Torneio</a>
  </h1>
  <a href="logout">Logout (atualmente logado como ${usuario})</a>
  <p></p>
  <form>
    <table>
      <tr class=row>
        <td class="col-sm-1">ID:</td>
        <td class="col-sm-1"><input name="id" value="${id}"></td>
      </tr>
      <tr class=row>
        <td class="col-sm-1">Nome:</td>
        <td class="col-sm-1"><input name="nome" value="${nome}"></td>
      </tr>
      <tr class=row>
        <td class="col-sm-1">Vitórias:</td>
        <td class="col-sm-1"><input name="vitorias" value="${vitorias}"></td>
      </tr>
    </table>
    <p></p>
    <button name="operacao" value="incluir" class="btn btn-space">Incluir</button>
    <button name="operacao" value="excluir" class="btn btn-space">Excluir</button>
    <button name="operacao" value="alterar" class="btn btn-space">Alterar</button>
  </form>
  <b>${msg}</b>
  <br>
  <p></p>
  <h4>Lista de Jogadores:</h4>
  <table class=table-condensed>
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Vitórias</th>
      <th></th>
      <th></th>
    </tr>
    <%
      //Obtém a lista de alunos criada pelo controlador ------Editar: obter a lista de jogadores
  	List<Jogador> jogadores = (List<Jogador>) request.getAttribute("jogadores");
    if (jogadores != null && !jogadores.isEmpty()) {
      for (Jogador i : jogadores) {
    %>
    <tr>
      <td><%=i.getID()%></td>
      <td><%=i.getNome()%></td>
      <td><%=i.getVitorias()%></td>
      <td><a href="torneio?operacao=excluir&id=<%=i.getID()%>">Excluir</a></td>
      <td><a href="torneio?operacao=carregar&id=<%=i.getID()%>&nome=<%=i.getNome()%>&vitorias=<%=i.getVitorias()%>">Alterar</a></td>
    </tr>
    <%
      }
    }
    %>
  </table>
  </div>
</body>

</html>