package br.pucpr;

import java.util.ArrayList;
import java.util.List;

import br.pucpr.busca.BuscaLarguraCega;
import br.pucpr.excecoes.Exce��oDeArquivo;
import br.pucpr.excecoes.N�N�oEncontradoExce��o;
import br.pucpr.handlers.ControleParser;
import br.pucpr.model.Node;
import br.pucpr.model.TipoBusca;
import br.pucpr.util.Util;
import br.pucpr.util.Verbose;

/**
 * Classe principal, que ser� o ponto de Start da Aplica��o.
 * 
 * @author Heverton Ivan de Sene
 * 
 */
public class MetodosBusca {

	private static final Verbose logger;

	static {
		// System.setProperty("verbose", "false");
		logger = Verbose.getInstancia();
	}

	public static void main(String[] args) {

		logger.debug("Iniciando a aplica��o.");

		try {
			if (args.length != 3)
				throw new IllegalArgumentException(
						"N�mero inv�lido de Argumentos");

			String origem = args[0].trim();
			String destino = args[1].trim();
			TipoBusca tipo = TipoBusca.getTipoBusca(args[2].trim());

			if (tipo.equals(TipoBusca.UNKNOW)) {
				throw new IllegalArgumentException(
						"Tipo de Busca Desconhecida.");
			}

			if (!Util.isIpValido(origem)) {
				throw new IllegalArgumentException("Ip de ORIGEM inv�lido. = "
						+ origem);
			}

			if (!Util.isIpValido(destino)) {
				throw new IllegalArgumentException("Ip de Destino inv�lido. = "
						+ destino);
			}

			ControleParser cp = new ControleParser("resources/topologia.txt");
			cp.constr�iGrafo();
			cp.mostraGrafo();
			List<Node> grafo = cp.getGrafo();

			Node inicio = cp.getNodeInfoNoGrafo(origem);
			Node fim = cp.getNodeInfoNoGrafo(destino);

			BuscaLarguraCega busca = new BuscaLarguraCega(grafo);
			Node encontrado = busca.buscarLargura(inicio, fim);

			// Preenche o caminho de volta.
			List<Node> caminho = new ArrayList<Node>();
			do {
				caminho.add(0, encontrado);
			} while ((encontrado = encontrado.getPai()) != null);

			int i = 0;
			for (Node node : caminho) {
				logger.info("[" + (++i) + "] >> " + node);
			}

			logger.info("Foram visitados [" + Util.getNumeroNosVisitados()
					+ "] n�s at� encontrar o caminho.");

		} catch (Exce��oDeArquivo e) {
			e.printStackTrace();
		} catch (N�N�oEncontradoExce��o e) {
			e.printStackTrace();
		} catch (IllegalArgumentException iae) {
			StringBuilder sb = new StringBuilder();
			sb.append("\nArgumento entrados de forma inv�lida.\n");
			sb.append("Problema [" + iae.getMessage() + "]\n");
			sb.append("Favor utilizar a seguinte sintaxe:\n");
			sb.append("buscar IP_ORIGEM IP_DESTINO TIPO_BUSCA\n");
			sb.append("TIPO_BUSCA pode ser ( CEGA | A* )");
			logger.info(sb.toString());
		}

		logger.debug("Fim a aplica��o.");
	}

}
