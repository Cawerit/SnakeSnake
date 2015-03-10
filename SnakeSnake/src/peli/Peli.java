package peli;
import javax.swing.JFrame;

public class Peli {
	

	public static void main(String[] args) {
		
		Snake[] pelaajat = new Snake[]{ 
				new Snake( new Koordinaatti(0, 0), 1, Suunta.OIKEA),
				new Snake( new Koordinaatti(5, 0), 1, Suunta.ALAS)
		};
		
		Pala omena = new Pala( new Koordinaatti(3, 3), Pala.Tyyppi.OMENA );
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.pack();
		ikkuna.setVisible(true);
		ikkuna.setResizable(false);

	}
}

class Ikkuna extends JFrame{
	
	public Ikkuna(){
		Lauta lauta = new Lauta();
		add(lauta);
	}
	
}