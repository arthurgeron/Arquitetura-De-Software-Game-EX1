package logica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FabricaIcones {
	
	private List<String> listaCaminhoImagens = new ArrayList<String>();
	private List<ImageIcon> listaIcones = new ArrayList<ImageIcon>();

	
	public String getCaminhoImagem(ImageIcon icon) {
		return listaCaminhoImagens.get(listaIcones.indexOf(icon));
	}
	
	public void addCaminhoImagem(String caminhoImagem){
		if(!listaCaminhoImagens.contains(caminhoImagem)){
			try {
				this.listaIcones.add( new ImageIcon(ImageIO.read(getClass().getResourceAsStream(caminhoImagem))));
				this.listaCaminhoImagens.add(caminhoImagem);
			} catch (IOException e) {
				e.printStackTrace();
				this.listaCaminhoImagens.remove(caminhoImagem);
			}
		}
	}
	
	public ImageIcon getIcone(String caminhoImagem){
		return listaIcones.get(listaCaminhoImagens.indexOf(caminhoImagem));
	}
	
	
}
