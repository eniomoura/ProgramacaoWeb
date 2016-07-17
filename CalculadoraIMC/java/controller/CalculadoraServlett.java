package controller;

import model.Calculadora;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(value="/calculadora.php")
public class CalculadoraServlett extends HttpServlet{
	
	private String convertString(
			HttpServletRequest req,
			String param,
			String padrao) {

		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}
	
	private float toFloat(
			HttpServletRequest req,
			String param,
			String padrao) {
		return Float.parseFloat( convertString(req,param,padrao));
	}

	private boolean toBool(
			HttpServletRequest req,
			String param,
			String padrao) {
		
		return convertString(req, param, padrao).equals("Masculino");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		float fMassa = toFloat(req, "mPeso", "0");
		float fAltura  = toFloat(req, "mAltura", "0");
		boolean  isMale = toBool(req, "combow", "0");
		
		//Passando parâmetro para o JSP.
		req.setAttribute(
				"resultado",
				Calculadora.getIMCDesctiption(fMassa, fAltura, isMale));

		req.getRequestDispatcher("CalculadoraView.jsp").forward(req, resp);
	}
	
}
