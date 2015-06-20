package arquiteturadefinida.logicajogo;

public enum Direcao_Inimigo implements Direcao {

	ESQUERDA(0, -1),
	CIMA(-1, 0),
	DIREITA(0, 1),
	BAIXO(1, 0),
	;


	private final int incrementoLinha;
	private final int incrementoColuna;
	
	private Direcao_Inimigo( int incrementoLinha, int incrementoColuna) {
		this.incrementoLinha = incrementoLinha;
		this.incrementoColuna = incrementoColuna;
	}
	

	public int getIncrementoLinha() {
		return incrementoLinha;
	}

	public int getIncrementoColuna() {
		return incrementoColuna;
	}





	

}
