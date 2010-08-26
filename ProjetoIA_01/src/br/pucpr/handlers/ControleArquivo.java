package br.pucpr.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.pucpr.excecoes.Exce��oDeArquivo;
import br.pucpr.util.Verbose;

public class ControleArquivo {

	private BufferedReader file;
	private String path;
	private static final Verbose logger = Verbose.getInstancia();

	/**
	 * Construtor que recebe o caminho do Arquivo.
	 * 
	 * @param path
	 * @throws Exce��oDeArquivo
	 */
	public ControleArquivo(String path) throws Exce��oDeArquivo {
		super();
		this.path = path;
		logger.debug("Iniciando o arquivo em : " + path);
		inicializarArquivo();
	}

	/**
	 * Localiza e inicia a abertura do arquivo.
	 * 
	 * @throws Exce��oDeArquivo
	 */
	private void inicializarArquivo() throws Exce��oDeArquivo {
		File f = new File(path);
		if (!f.exists() || !f.canRead())
			throw new Exce��oDeArquivo("O Arquivo [" + f.getAbsolutePath()
					+ "] n�o foi encontrado" + " ou n�o pode ser lido.");

		try {
			FileReader fr = new FileReader(f);
			file = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			throw new Exce��oDeArquivo("O Arquivo [" + f.getAbsolutePath()
					+ "] n�o foi encontrado" + "ou n�o pode ser lido.", e);
		}
	}

	/**
	 * M�todo que indica se o arquivo est� pronto para ser lido.
	 * 
	 * @return <strong>true</strong> - se posso ler o arquivo <br>
	 *         <strong>false</strong> - se n�o posso ler
	 */
	public boolean possoLer() {
		boolean retorno = false;
		try {
			retorno = file.ready();
		} catch (IOException e) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * M�todo que le o arquivo e te retorno a linha atual, j� com o trim
	 * aplicado. <br>
	 * J� faz o parser de modo a ignorar as linhas de coment�rios. <br>
	 * Linhas de coment�rios come�am com <strong>#</strong>
	 * 
	 * @return linha - pronta para ser usada.
	 */
	public String leiaLinha() {
		String retorno = new String("");

		try {
			do {
				if (file.ready()) {
					retorno = file.readLine().trim();
				} else {
					retorno = null;
					break;
				}
			} while (retorno.isEmpty() || retorno.startsWith("#"));
		} catch (IOException e) {
			logger.debug("ERRO - " + e.getMessage());
		}

		return retorno;
	}

}
