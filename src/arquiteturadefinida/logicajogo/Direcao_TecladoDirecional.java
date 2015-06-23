package arquiteturadefinida.logicajogo;

public enum Direcao_TecladoDirecional implements Direcao {

	ESQUERDA(37, 0, -1),
	CIMA(38, -1, 0),
	DIREITA(39, 0, 1),
	BAIXO(40, 1, 0),
	DIRECIONAL(0,0,0),
	;

	private final int codigoTecla;
	private final int incrementoLinha;
	private final int incrementoColuna;
	
	private Direcao_TecladoDirecional(int codigoTecla, int incrementoLinha, int incrementoColuna) {
		this.codigoTecla = codigoTecla;
		this.incrementoLinha = incrementoLinha;
		this.incrementoColuna = incrementoColuna;
	}
	

	public int getIncrementoLinha() {
		return incrementoLinha;
	}

	public Direcao_TecladoDirecional getDirecao(){
		return this;
	}
	public int getIncrementoColuna() {
		return incrementoColuna;
	}

	public static Direcao_TecladoDirecional comCodigo(int codigoTecla) {
		for (Direcao_TecladoDirecional d : values()) {
			if (d.codigoTecla == codigoTecla ) return d;
		}

		return null;
	}

}
