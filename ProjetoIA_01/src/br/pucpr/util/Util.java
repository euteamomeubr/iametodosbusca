package br.pucpr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	private static final String PATTERN_IP = 
		"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
	
	private static int contadorNoVisitados = 0;
	/**
	 * Fun��o que v�lida se o IP est� no padr�o
	 * @param ip
	 * @return true se OK.
	 */
	public static boolean isIpValido(String ip){
		boolean retorno = false;
		
		Pattern pat = Pattern.compile(PATTERN_IP);
		Matcher mat = pat.matcher(ip);
		
		if(mat.matches())
			retorno = true;
		
		return retorno;
	}

	/**
	 * Incrementa a quantidade de n�s que foram visitados pela busca
	 */
	public static void incrementaQtdeNoVisitado(){
		contadorNoVisitados++;
	}
	
	/**
	 * Retorna a quantidade de n�s visitados.
	 * @return quantidade de n�s visitados.
	 */
	public static int getNumeroNosVisitados(){
		return contadorNoVisitados;
	}
}
