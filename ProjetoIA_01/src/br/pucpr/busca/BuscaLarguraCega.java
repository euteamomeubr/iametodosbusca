package br.pucpr.busca;

import java.util.List;

import br.pucpr.model.Node;
import br.pucpr.util.Verbose;

/**
 * M�todo de Busca em Largura - CEGA
 * 
 * @author Heverton Ivan de Sene
 * 
 */
public class BuscaLarguraCega {

	private List<Node> grafo = null;
	private static final Verbose logger = Verbose.getInstancia();

	/**
	 * Construtor que passa o grafo a ser buscado.
	 * 
	 * @param grafo
	 */
	public BuscaLarguraCega(List<Node> grafo) {
		super();
		this.grafo = grafo;
	}

	public void buscarLargura(Node inicio, Node fim) {
		logger.info("Come�ar o m�todo de busca em Largura.");
		long beginTime = System.currentTimeMillis();

		// TODO Fazer o m�todo de busca aqui.

		long endTime = System.currentTimeMillis();
		logger.info("O m�todo de busca em Largura demorou ["
				+ (endTime - beginTime) + "] milisegundos.");
	}

}
