package arquiteturadefinida.logicajogo;

public enum Direcao_TecladoWASD implements Direcao {

	ESQUERDA(37, 0, -1),
	CIMA(65, -1, 0),
	DIREITA(68, 0, 1),
	BAIXO(83, 1, 0),
	WASD(0,0,0),
	;

	private final int codigoTecla;
	private final int incrementoLinha;
	private final int incrementoColuna;
	
	private Direcao_TecladoWASD(int codigoTecla, int incrementoLinha, int incrementoColuna) {
		this.codigoTecla = codigoTecla;
		this.incrementoLinha = incrementoLinha;
		this.incrementoColuna = incrementoColuna;
	}
	

	public int getIncrementoLinha() {
		return incrementoLinha;
	}

	public int getIncrementoColuna() {
		return incrementoColuna;
	}

	public static Direcao_TecladoWASD comCodigo(int codigoTecla) {
		for (Direcao_TecladoWASD d : values()) {
			if (d.codigoTecla == codigoTecla ) return d;
		}

		return null;
	}



}
