package main;

import static logica.Elemento.*;
import logica.Elemento;
import apresentacao.TelaJogo;

public class MainVersao4 {

	public static void main(String[] args) {
		final Elemento[][] disposicaoInicial1 = {
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______},
				{GRAMA_____, GRAMA_____, GRAMA_____, MACA______, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, GRAMA_____, GRAMA_____, GRAMA_____},
				{PASSAGEM__, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, GRAMA_____, GRAMA_____, GRAMA_____},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, GRAMA_____, GRAMA_____, GRAMA_____},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, MACA______},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, GRAMA_____},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, AGUA______, AGUA______, GRAMA_____, GRAMA_____, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, AGUA______, GRAMA_____},
				{GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, MACA______, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____},
				{GRAMA_____, PERSONAGEM, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____, GRAMA_____},
		};
		new TelaJogo(disposicaoInicial1,"/agua.png","/maca.png","/personagem.png","/grama.png","/passagem.png");
	}
}
