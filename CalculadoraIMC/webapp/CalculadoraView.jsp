<!DOCTYPE html>
<html>


  <head>
    <title>Calculadora</title>
  </head>


<body>
	<h1 align= "center"> IMC </h1>
	<hr>
	<table  width =100%  >
		<tr ></tr>
		<tr>
		<td align ="center">
		
		<br>
		<form>
			<table width = 25% height = 100%>
			<tr>
			<td>Peso: </td>
			<td><input name = "mPeso"> </td>
			</tr>
			<tr>
			<td>Altura: </td>
			<td><input name="mAltura"></td>
			</tr>
			<tr>
			<td>
			<select name = "combow">
				<option value="Masculino">Masculino</option>
				<option value="Feminino">Feminino</option>
			</select>
			</td>
			<td >
			<button>Calcular</button>
			</td>
			</tr>
			</table>

			<br>
		</form>
		<b>Resultado: ${resultado}</b>
		</td>
		</tr>
		<tr></tr>
	</table>
	
</body>
</html>