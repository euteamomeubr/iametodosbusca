package br.pucpr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe respons�vel por logs em modo Verboso. <br />
 * Tipo de Pattern - <strong>SINGLETON</strong>
 * 
 * @author Heverton
 * 
 */
public class Verbose {
	private boolean verbo = false;
	private static Verbose instancia = null;
	private SimpleDateFormat sdf = null;

	private Verbose(boolean verbose) {
		this.verbo = verbose;
		sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
	}

	/**
	 * M�todo do singleton para obter a classe para log em modo verboso.
	 * 
	 * @return instancia do modo verboso.
	 */
	public static Verbose getInstancia() {
		if (instancia == null) {
			boolean isVerboso = Boolean.parseBoolean(System.getProperty(
					"verbose", "false"));

			System.out.println("O MODO VERBOSO EST� "
					+ (isVerboso ? "ATIVADO" : "DESATIVADO") + ".");

			if (!isVerboso)
				System.out
						.println("Para ativar o modo verboso utilize o argumento -Dverbose=true");

			instancia = new Verbose(isVerboso);
		}
		return instancia;
	}

	/**
	 * Loga a mensagem em modo de informa��o. <br />
	 * Deve ser s�ncrono pois s� h� um console.
	 */
	public synchronized void info(String msg) {
		//TODO Desfazer o NOT
		if (!verbo)
			System.out.println("[" + sdf.format(new Date()) + "] - " + msg);
	}

}
