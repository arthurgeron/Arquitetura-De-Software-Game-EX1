package com.github.awvalenti.arquiteturadesoftware.rpg1;

import java.util.ArrayList;

public class Tabuleiro {
	private String[][] posicoes;
	public Tabuleiro(int tamanhoX, int tamanhoY, ArrayList<Entidade> entidades)
	{
		posicoes = new String[tamanhoX][tamanhoY];
		for(int i = 0; i< posicoes[tamanhoX].length - 1 ; i++)
		{
			for(int j = 0; j < posicoes[tamanhoX][tamanhoY].length() - 1 ; j++ )
			{
				posicoes[i][j] = "grama";
			}
		}
		for(Entidade entidade : entidades)
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
