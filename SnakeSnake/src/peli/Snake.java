package peli;
import java.util.ArrayList;

/**
 * Luokka kuvaa yksitt‰ist‰ k‰‰rmett‰
 *
 */
public class Snake {

	private ArrayList<Pala> palat;
	private Vari vari;
	
	public Snake(Koordinaatti aloituskohta, int pituus, Suunta suunta){
		palat = new ArrayList<Pala>();
		System.out.println("lol");
		for(int i=0; i<pituus; i++){
			
			Koordinaatti koordinaatti = new Koordinaatti( 
					aloituskohta.getX() + i * suunta.getXIncrease(),
					aloituskohta.getY() + i * suunta.getYIncrease());
			
			palat.add(new Pala(koordinaatti) );
			
		}
	}
}
