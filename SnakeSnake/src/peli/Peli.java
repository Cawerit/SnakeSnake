package peli;

/**
 * Alustaa ikkunan, sekä pelilaudan ja aloittaa pelin
 * 
 * @author Oskari Noppa, Aleksi Kyllönen, Miska Kallio
 *
 */
public class Peli {
	
	public static int VOITTO_PISTEET = 5;
	
	public static void main(String[] args) {
				
		Lauta pelilauta = new Lauta();
		new Ikkuna(pelilauta);
		pelilauta.aloitaPeli();
	}
	
}