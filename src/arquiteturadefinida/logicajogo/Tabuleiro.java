package arquiteturadefinida.logicajogo;

public class Tabuleiro {

	private Elemento[][] matriz;
	private SaidaJogo saida;
	private Posicao posicaoDoPortalOculto;

	public Tabuleiro(Elemento[][] matriz) {
		this.matriz = matriz;
	}

	public void setSaida(SaidaJogo saida) {
		this.saida = saida;
	}

	public void iniciarJogo() {
		ocultarPortal();
		saida.iniciarJogo();
	}

	public int getNumeroLinhas() {
		return matriz.length;
	}

	public int getNumeroColunas() {
		return matriz[0].length;
	}

	public Elemento elementoEm(Posicao posicao) {
		try {
			return matriz[posicao.getLinha()][posicao.getColuna()];
		}
		catch(Exception exception)
		{
			//System.out.println("Exception at elementoEm: "+ exception);
			return Elemento.GRAMA;
		}
	}

	public void fazerMovimento(Direcao d, Elemento entidade) {
		Posicao posicaoAntiga = acharPosicaoDe(entidade);
		Posicao posicaoNova = posicaoAntiga.somar(d);

		if (posicaoEhInvalida(posicaoNova)) return;
		Elemento elementoAlcancado = elementoEm(posicaoNova);
		//Esta verificação evitará que o inimigo ande sobre a água ou pegue maças
		if(!(entidade.equals(Elemento.INIMIGO) && (elementoAlcancado.equals(Elemento.AGUA) || elementoAlcancado.equals(Elemento.MACA) || elementoAlcancado.equals(Elemento.PORTAL)))) {
			alterarElemento(posicaoAntiga, Elemento.GRAMA);
			alterarElemento(posicaoNova, entidade);
			
			switch (elementoAlcancado) {
			case AGUA:
				saida.perderJogo();
				break;
	
			case MACA:
				if (quantidadeMacasRestantes() == 0) reexibirPortal();
				break;
	
			case PORTAL:
				saida.passarDeFase();
				break;
	
			case PERSONAGEM:
				saida.perderJogo();
				break;
			case INIMIGO:
				saida.perderJogo();
				break;
			default:
				break;
			}
		}

	}

	private void ocultarPortal() {
		posicaoDoPortalOculto = acharPosicaoDe(Elemento.PORTAL);
		alterarElemento(posicaoDoPortalOculto, Elemento.GRAMA);
	}

	private void reexibirPortal() {
		alterarElemento(posicaoDoPortalOculto, Elemento.PORTAL);
	}

	private void alterarElemento(Posicao posicao, Elemento e) {
		matriz[posicao.getLinha()][posicao.getColuna()] = e;
		saida.alterarElemento(posicao, e);
	}

	private int quantidadeMacasRestantes() {
		int ret = 0;

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == Elemento.MACA) ++ret;
			}
		}

		return ret;
	}

	public Posicao acharPosicaoDe(Elemento elemento) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == elemento) {
					return new Posicao(i, j);
				}
			}
		}

		return null;
	}

	public boolean posicaoEhInvalida(Posicao p) {
		return p.getLinha() < 0 || p.getLinha() >= getNumeroLinhas()
				|| p.getColuna() < 0 || p.getColuna() >= getNumeroColunas();
	}

}
