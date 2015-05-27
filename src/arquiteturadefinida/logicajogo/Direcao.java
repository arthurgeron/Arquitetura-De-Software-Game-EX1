package arquiteturadefinida.logicajogo;

public enum Direcao {

	ESQUERDA(37, 65, 0, -1),
	CIMA(38, 87, -1, 0),
	DIREITA(39, 68, 0, 1),
	BAIXO(40, 83, 1, 0),
	;

	private final int codigoTecla;
	private final int codigoTecla2;
	private final int incrementoLinha;
	private final int incrementoColuna;
	
	private Direcao(int codigoTecla, int codigoTecla2, int incrementoLinha, int incrementoColuna) {
		this.codigoTecla = codigoTecla;
		this.incrementoLinha = incrementoLinha;
		this.incrementoColuna = incrementoColuna;
		this.codigoTecla2 = codigoTecla2;
	}
	

	public int getIncrementoLinha() {
		return incrementoLinha;
	}

	public int getIncrementoColuna() {
		return incrementoColuna;
	}

	public static Direcao comCodigo(int codigoTecla) {
		for (Direcao d : values()) {
			if (d.codigoTecla == codigoTecla || d.codigoTecla2 == codigoTecla ) return d;
		}

		return null;
	}

}
