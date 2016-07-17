<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<title>Cadastro - Torneio</title>
</head>

<body>
	<div class=container align=center>
		<table>
			<h1>
				<a href="cadastro">Cadastro</a>
			</h1>
			<form>
				<table>
					<tr class=row>
						<td class="col-sm-1"><b>Usuário</b></td>
						<td class="col-sm-1"><input name="usuario"></td>
					</tr>
					<tr class=row>
						<td class="col-sm-1"><b>Senha:</b></td>
						<td class="col-sm-1"><input type="password" name="senha"></td>
					</tr>
				</table>
				<p></p>
				<button name="operacao" value="enviar" class="btn btn-space">Enviar</button>
				<button name="operacao" value="voltar" class="btn btn-space">Voltar
					ao Login</button>
			</form>
			<p></p>
			<b>${msg}</b>
		</table>
	</div>
</body>

</html>