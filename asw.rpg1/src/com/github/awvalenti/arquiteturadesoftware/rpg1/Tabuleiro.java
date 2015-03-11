package com.github.awvalenti.arquiteturadesoftware.rpg1;

import java.util.ArrayList;

public class Tabuleiro {
	private String[][] posicoes;
	public Tabuleiro(int tamanhoX, int tamanhoY, ArrayList<Entidade> entidades)
	{
		posicoes = new String[tamanhoX][tamanhoY];//Tabuleiro
		for(int i = 0; i< posicoes.length ; i++)//preenche as posições com grama por padrão
		{
			for(int j = 0; j < posicoes[tamanhoX-1].length ; j++ )
			{
				posicoes[i][j] = "grama";
			}
		}
		for(Entidade entidade : entidades) // Preenche o tabuleiro com as entidades
		{
			try
			{
				posicoes[entidade.getPosicaoXNoMapa()][entidade.getPosicaoYNoMapa()] = entidade.getNome();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
	}
	public String[][] getTabuleiro()
	{
		return posicoes;
	}
	public void setTabuleiro(String[][] posicoes)
	{
		this.posicoes = posicoes;
	}
}
