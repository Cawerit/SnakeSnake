package peli;
import java.util.ArrayList;

/**
 * Luokka kuvaa yksittäistä käärmettä
 *
 */
public class Snake {

	private ArrayList<Koordinaatti> palat;
	private ArrayList<Pala> omenat;
	private Vari vari;
	private Suunta suunta;
	
	public Snake(Koordinaatti aloituskohta, int pituus, Suunta suunta){
		this.suunta = suunta;
		palat = new ArrayList<Koordinaatti>();
		for(int i=0; i<pituus; i++){
			
			Koordinaatti koordinaatti = new Koordinaatti( 
					aloituskohta.getX() + i * suunta.getXIncrease(),
					aloituskohta.getY() + i * suunta.getYIncrease());
			
			palat.add(koordinaatti);
			
		}
	}
}
