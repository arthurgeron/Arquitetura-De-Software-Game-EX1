package arquiteturadefinida.apresentacao;

import static arquiteturadefinida.logicajogo.Elemento.*;
import arquiteturadefinida.logicajogo.Elemento;
import arquiteturadefinida.logicajogo.Tabuleiro;

public class MainVersao5 {

	public static void main(String[] args) {
		final Elemento[][] disposicaoInicial1 = {
				{GRAMA, INIMIGO, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA},
				{GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, AGUA},
				{GRAMA, GRAMA, GRAMA, MACA, GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, GRAMA, GRAMA, GRAMA},
				{PORTAL, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, GRAMA, GRAMA, GRAMA},
				{GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, GRAMA, GRAMA, GRAMA},
				{GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, MACA},
				{GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, GRAMA},
				{GRAMA, GRAMA, GRAMA, GRAMA, AGUA, AGUA, GRAMA, GRAMA, AGUA, AGUA, AGUA, AGUA, AGUA, AGUA, GRAMA},
				{GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, MACA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA},
				{GRAMA, PERSONAGEM, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA, GRAMA},
		};

		Tabuleiro tabuleiro = new Tabuleiro(disposicaoInicial1);
		tabuleiro.setSaida(new TelaJogo(tabuleiro, new FabricaIcones()));
		tabuleiro.iniciarJogo();
	}

}
