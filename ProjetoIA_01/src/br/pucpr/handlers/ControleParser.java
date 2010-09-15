package br.pucpr.handlers;

import java.util.ArrayList;
import java.util.List;

import br.pucpr.excecoes.Exce��oDeArquivo;
import br.pucpr.excecoes.N�N�oEncontradoExce��o;
import br.pucpr.model.Edge;
import br.pucpr.model.Node;
import br.pucpr.model.Ponto;
import br.pucpr.util.Verbose;

public class ControleParser {
	private ControleArquivo file;
	private List<Node> nodes;
	private static final Verbose logger = Verbose.getInstancia();

	/**
	 * Contrutor do Controlador de Parser, que recebe o arquivo que ser�
	 * parseado.
	 * 
	 * @param path
	 * @throws Exce��oDeArquivo
	 */
	public ControleParser(String path) throws Exce��oDeArquivo {
		super();
		file = new ControleArquivo(path);
		nodes = new ArrayList<Node>();
	}

	public void constr�iGrafo() {
		// Busca os N�s.
		while (file.possoLer()) {
			String linha = file.leiaLinha();
			if (linha != null) {
				if (!linha.startsWith("/")) {
					logger.debug(linha);

					String[] info = linha.split(";");
					String ip = info[0];
					String nome = info[1];
					int X = Integer.parseInt(info[2]);
					int Y = Integer.parseInt(info[3]);
					int Fator = Integer.parseInt(info[4]);
					Ponto P = new Ponto(X,Y);
					Node n� = new Node(ip, nome, P, Fator);
					nodes.add(n�);
				} else {
					// Significa que acabou a parte de carga de nodes
					break;
				}
			}
		}

		// Busca as Arestas
		while (file.possoLer()) {
			String linha = file.leiaLinha();
			if (linha != null) {
				if (!linha.startsWith("/")) {
					logger.debug(linha);
					String[] info = linha.split(";");
					String ipOrigem = info[0];

					int index = nodes.indexOf(new Node(ipOrigem));
					Node n�Origem = nodes.get(index);
					List<Edge> arestasOrigem = n�Origem.getArestas();

					info = info[1].split("-");

					for (String ipDestino : info) {
						// TODO Arrumar o peso
						Edge aresta = new Edge(ipDestino);
						arestasOrigem.add(aresta);
					}

				} else {
					// Significa que acabou a parte de carga de nodes
					break;
				}
			}
		}
	}

	public final List<Node> getGrafo() {
		return nodes;
	}

	/**
	 * Busca as informa��es do NODE passado o ID.
	 * 
	 * @param ip
	 *            - string que representa o IP desejado.
	 * @return o n� preenchido
	 * @throws N�N�oEncontradoExce��o
	 */
	public final Node getNodeInfoNoGrafo(String ip)
			throws N�N�oEncontradoExce��o {
		Node n� = null;
		try {
			int index = nodes.indexOf(new Node(ip));

			if (index >= 0)
				n� = nodes.get(index);
		} catch (Exception e) {
			throw new N�N�oEncontradoExce��o("O N� n�o foi encontrado.\n", e);
		}

		if (n� == null)
			throw new N�N�oEncontradoExce��o("O N� n�o foi encontrado.");

		return n�;
	}

	public void mostraGrafo() {
		for (Node n� : nodes) {
			logger.debug("---------------------------------\nORIGEM => "
					+ n�.toString());
			List<Edge> arestas = n�.getArestas();
			for (Edge ar : arestas) {
				Node noDestino = new Node(ar.getIpDestino());
				int index = nodes.indexOf(noDestino);
				noDestino = nodes.get(index);
			}

		}
	}
}
