package com.github.awvalenti.arquiteturadesoftware.rpg1;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Entidade {
	private ImageIcon icone = new ImageIcon(); // ícone da identidade
	private String nome;
	private Boolean ehJogador;
	private int posicaoXNoMapa = 0 ;
	private int posicaoYNoMapa = 0 ;
	public Entidade(String localDoIcone, String nome, Boolean ehJogador)
	{
		try{
			this.icone = new ImageIcon(ImageIO.read(Main.class.getResourceAsStream(localDoIcone)));
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		this.nome = nome;
		this.ehJogador = ehJogador;
	}
	public Entidade(String localDoIcone, String nome, Boolean ehJogador,int posicaoX,int posicaoY)
	{
		try{
			this.icone = new ImageIcon(ImageIO.read(Main.class.getResourceAsStream(localDoIcone)));
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		this.nome = nome;
		this.ehJogador = ehJogador;
		this.posicaoXNoMapa = posicaoX;
		this.posicaoYNoMapa = posicaoY;
	}
	
	public Entidade(ImageIcon icone, String nome, Boolean ehJogador)
	{
		this.icone = icone;		
		this.nome = nome;
		this.ehJogador = ehJogador;
	}
	public Entidade(ImageIcon icone, String nome, Boolean ehJogador,int posicaoX,int posicaoY)
	{
		this.icone = icone;		
		this.nome = nome;
		this.ehJogador = ehJogador;
		this.posicaoXNoMapa = posicaoX;
		this.posicaoYNoMapa = posicaoY;
	}
	public ImageIcon getIcone()
	{
		return icone;
	}
	public void setIcone(ImageIcon icone)
	{
		this.icone = icone;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public Boolean ehJogador()
	{
		return ehJogador;
	}
	public void setEhJogador(Boolean ehJogador)
	{
		this.ehJogador =  ehJogador;
	}
	public int getPosicaoXNoMapa()
	{
		return posicaoXNoMapa;
	}
	public void setPosicaoXNoMapa(int posicaoXNoMapa)
	{
		this.posicaoXNoMapa = posicaoXNoMapa;
	}
	public int getPosicaoYNoMapa()
	{
		return posicaoYNoMapa;
	}
	public void setPosicaoYNoMapa(int posicaoYNoMapa)
	{
		this.posicaoYNoMapa = posicaoYNoMapa;
	}
	
}
