package peli;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Luokka kuvaa yksitt‰ist‰ k‰‰rmett‰
 *
 */
public class Snake {

	private ArrayList<Koordinaatti> palat;
	private int omenat = 0;
	private Color vari;
	private Suunta suunta;
	
	public Snake(Koordinaatti aloituskohta, int pituus, Suunta suunta, Color vari){
		this.suunta = suunta;
		this.vari = vari;
		palat = new ArrayList<Koordinaatti>();
		
		for(int i=0; i<pituus; i++){
			
			Koordinaatti koordinaatti = new Koordinaatti( 
					aloituskohta.getX() + (i * suunta.getXIncrease()),
					aloituskohta.getY() + (i * suunta.getYIncrease()));
			
			palat.add(koordinaatti);
			
		}
	}
	
	public ArrayList<Koordinaatti> getKoordinaatit(){
		return palat;
	}
	
	//Asettaa suunnan, palauttaa false jos suunta sama kuin ennen (jolloin k‰‰rmeen voi tappaa)
	public boolean setSuunta(Suunta suunta){
		//palautettava arvo on tieto siit‰, k‰‰ntyikˆ snake juuri ymp‰ri
		boolean palautettava = !this.suunta.isOpposite(suunta);
		this.suunta = suunta;
		return palautettava;
	}
	
	private void siirry(){
		Koordinaatti vanhaPaa = getPaa();
		Koordinaatti uusiPaa = new Koordinaatti( 
				vanhaPaa.getX() + suunta.getXIncrease(),
				vanhaPaa.getY() + suunta.getYIncrease() );
		palat.add(uusiPaa);
	}
	
	public void liiku(){//Liikkuminen silloin kun ei tarvitse kasvattaa k‰‰rmett‰
		siirry(); 
		palat.remove(0);
	}
	public void kasva(){//Liikkuminen silloin kun k‰‰rme kasvaa samalla
		siirry();
		omenat++;
	}
	
	public Koordinaatti getPaa(){ 
		return palat.get(palat.size()-1); 
	}
	
	public Color getVari(){
		return vari;
	}
}
