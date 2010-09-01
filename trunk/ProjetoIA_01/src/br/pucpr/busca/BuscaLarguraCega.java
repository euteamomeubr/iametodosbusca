package br.pucpr.busca;

import java.util.ArrayList;
import java.util.List;

import br.pucpr.excecoes.N�N�oEncontradoExce��o;
import br.pucpr.model.Edge;
import br.pucpr.model.Node;
import br.pucpr.util.Util;
import br.pucpr.util.Verbose;

/**
 * M�todo de Busca em Largura - CEGA
 * 
 * @author Heverton Ivan de Sene
 * 
 */
public class BuscaLarguraCega {

	private List<Node> grafo = null;
	private List<Node> adjacentes = null;
	private static final Verbose logger = Verbose.getInstancia();

	/**
	 * Construtor que passa o grafo a ser buscado.
	 * 
	 * @param grafo
	 */
	public BuscaLarguraCega(List<Node> grafo) {
		super();
		this.grafo = grafo;
		this.adjacentes = new ArrayList<Node>();
	}

	/**
	 * Fun��o respons�vel por limpar todas as informa��es de n�. <br />
	 * Isto significa dizer que nenhum n� ainda foi visitado.
	 */
	private void zeraGrafo() {
		for (Node n� : grafo) {
			n�.setVisitado(false);
		}
	}

	/**
	 * Busca as informa��es do NODE passando o ID.
	 * 
	 * @param ip
	 *            - string que representa o IP desejado.
	 * @return o n� preenchido
	 * @throws N�N�oEncontradoExce��o
	 */
	private final Node getNodeInfoNoGrafo(String ip)
			throws N�N�oEncontradoExce��o {
		Node n� = null;
		try {
			int index = grafo.indexOf(new Node(ip));

			if (index >= 0)
				n� = grafo.get(index);
		} catch (Exception e) {
			throw new N�N�oEncontradoExce��o("O N� n�o foi encontrado.\n", e);
		}

		if (n� == null)
			throw new N�N�oEncontradoExce��o("O N� n�o foi encontrado.");

		return n�;
	}

	/**
	 * Retorna o n� do caminho.
	 * 
	 * @param inicio
	 * @param fim
	 * @throws N�N�oEncontradoExce��o
	 */
	public Node buscarLargura(Node inicio, Node fim)
			throws N�N�oEncontradoExce��o {
		logger.debug("Come�ar o m�todo de busca em Largura.");
		long beginTime = System.currentTimeMillis();

		if (fim.equals(inicio)) {
			logger.debug("voc� j� se encontra onde gostaria. ==> " + inicio);
			return inicio;
		}

		Node n� = inicio;
		logger.debug("Inicio em => " + n�);
		zeraGrafo();

		List<Edge> ligacoes = n�.getArestas();
		n�.setVisitado(true);

		adicionaEntradaNoFimDaLista(ligacoes, n�);

		while (!adjacentes.isEmpty()) {
			n� = adjacentes.remove(0);

			if (!n�.isVisitado()) {

				logger.debug("Visitando ." + n� + " >> "
						+ Util.getNumeroNosVisitados());

				n�.setVisitado(true);
				Util.incrementaQtdeNoVisitado();
				if (fim.equals(n�)) {
					logger.debug("N� encontrado.");
					break;
				}

				ligacoes = n�.getArestas();
				adicionaEntradaNoFimDaLista(ligacoes, n�);
			}
		}

		long endTime = System.currentTimeMillis();
		logger.debug("O m�todo de busca em Largura demorou ["
				+ (endTime - beginTime) + "] milisegundos.");
		return n�;
	}

	/**
	 * Adiciona as entradas da Aresta em uma lista.
	 * 
	 * @param ligacoes
	 * @param pai
	 *            - N� pai que est� requisitando a inser��o na fila
	 * @throws N�N�oEncontradoExce��o
	 */
	private void adicionaEntradaNoFimDaLista(List<Edge> ligacoes, Node pai)
			throws N�N�oEncontradoExce��o {

		for (Edge edge : ligacoes) {
			Node n� = getNodeInfoNoGrafo(edge.getIpDestino());

			if (!n�.isVisitado()) {
				if (n�.getPai() == null)
					n�.setPai(pai);

				adjacentes.add(n�);
			}

		}
	}

}
