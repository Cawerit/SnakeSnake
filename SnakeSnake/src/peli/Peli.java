package peli;
import javax.swing.JFrame;

public class Peli {
	
	private Snake[] pelaajat = new Snake[]{ 
			new Snake( new Koordinaatti(0, 0), 1, Suunta.OIKEA),
			new Snake( new Koordinaatti(5, 0), 1, Suunta.ALAS)
	};
	
	private Pala omena = new Pala( Pala.Tyyppi.OMENA );
	

	public static void main(String[] args) {
		
		new Ikkuna();

	}
	
	class Ikkuna extends JFrame{
		
		public Ikkuna(){
			add(new Lauta());
		}
		
	}
}