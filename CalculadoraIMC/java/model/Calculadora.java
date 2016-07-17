package model;

public class Calculadora {

	private static float calculaIMC(float massa, float altura) {
		float imc = massa / (altura * altura);
		return imc;
	}

	public static String getIMCDesctiption(float massa , float altura, boolean isMale) {
		float imc = calculaIMC (massa , altura);
		String[] mensagens = { "Abaixo do peso", "No peso normal", "Marginalmente acima do peso", 
				"Acima do peso ideal", "Obeso" };

		float[] limitesMasculinos = { 20.7f, 26.4f, 27.8f, 31.1f};
		float[] limitesFemininos  = { 19.1f, 25.8f, 27.3f, 32.3f};

		float[] limiteEscolhido;

		limiteEscolhido = isMale ? limitesMasculinos : limitesFemininos;

		String resposta = "IMC = " + imc + " ";

		boolean flag = false;
		for (int i = 0; i < limiteEscolhido.length; i++) {
			if (imc < limiteEscolhido[i]) {
				resposta += mensagens[i];
				flag = true;
				break;
			}
		}
		if (!flag)
			resposta += mensagens[mensagens.length-1];
		return resposta;
	}

}
