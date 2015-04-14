package logica;

import javax.swing.ImageIcon;

public enum Elemento {

	AGUA______,
	MACA______,
	PERSONAGEM,
	GRAMA_____,
	PASSAGEM__,
	;

	private ImageIcon icone;
	private Posicao posicao;
	


	public void setIcone(ImageIcon icone) {
		this.icone = icone;
	}
	
	public ImageIcon getIcone(){
		return icone;
	}
	
	public Posicao getPosicao(){
		return posicao;
	}
	
	public void setPosicao(Posicao posicao){
		this.posicao = posicao;
	}
}
