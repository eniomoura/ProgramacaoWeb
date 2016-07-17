<!DOCTYPE html>
<html>

  <head>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<title>Login - Torneio</title>
  </head>

  <body>
  	<div class=container align=center>
  	<h1><a href="login">Login</a></h1>
    <form class=form-horizontal>
      <table>
        <tr class="row">
          <td class="col-sm-1"><b>Usuário</b></td>
          <td class="col-sm-1"><input name="usuario"></td>
        </tr>
        <tr class="row">
          <td class="col-sm-1"><b>Senha</b></td>
          <td class="col-sm-1"><input type="password" name="senha"></td>
        </tr>
      </table>
      <p></p>
      <button name="operacao" value="entrar" class="btn btn-space">Entrar</button>
      <button name="operacao" value="cadastro" class="btn btn-space">Cadastro</button>
      <button name="operacao" value="dbeditor" style="display:none">Editar DB</button> <!-- DEBUG -->
    </form>
    <br>
    <b>${msg}</b>
    </div>
  </body>

</html>