package peli;
import java.util.ArrayList;
import java.util.List;
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
	public boolean suuntaAsetettu;
	
	public Snake(Koordinaatti aloituskohta, int pituus, Suunta suunta, Color vari){
		this.suunta = suunta;
		this.vari = vari;
		palat = new ArrayList<Koordinaatti>();
		suuntaAsetettu = false;
		
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
	public void setSuunta(Suunta suunta){
		if(suuntaAsetettu || this.suunta.isOpposite(suunta)) return;
		this.suunta = suunta;
		suuntaAsetettu = true;
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
	
	private List<Koordinaatti> getKroppa(){
		return getKoordinaatit().subList(0, palat.size()-1);
	}
	
	public Color getVari(){
		return vari;
	}
	
	public boolean kuollut(Snake toinen){
		Koordinaatti paa = getPaa();
		return paa.collides(Lauta.RUUDUT[0]+1, Lauta.RUUDUT[1]+1) 
				|| toinen.getKoordinaatit().size() - palat.size() >= Peli.VOITTO_PISTEET
				|| paa.collides(-1, -1) 
				|| paa.collides(toinen.getKoordinaatit())
				|| paa.collides(getKroppa());
	}
}
