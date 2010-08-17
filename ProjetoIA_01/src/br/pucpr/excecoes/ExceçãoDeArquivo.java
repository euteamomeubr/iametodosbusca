package br.pucpr.excecoes;

/**
 * Exce��es do Tipo de Arquivos
 * @author Heverton
 *
 */
public class Exce��oDeArquivo extends Exception {

	private static final long serialVersionUID = 1L;

	public Exce��oDeArquivo() {
		super();
	}

	public Exce��oDeArquivo(String message, Throwable cause) {
		super(message, cause);
	}

	public Exce��oDeArquivo(String message) {
		super(message);
	}

	public Exce��oDeArquivo(Throwable cause) {
		super(cause);
	}

}
