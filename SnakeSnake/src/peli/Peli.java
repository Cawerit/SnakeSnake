package peli;

/**
 * Alustaa ikkunan, sekä pelilaudan ja aloittaa pelin
 * 
 * @author Oskari Noppa, Aleksi Kyllönen, Miska Kallio
 *
 */
public class Peli {
	
	/**
	 * Määrittää, kuinka monen pisteen (syödyt omenat) ero pelaajien välillä on oltava, 
	 * jotta toinen voittaa kierroksen vain syötyjen omenoiden perusteella.
	 */
	public static int VOITTO_PISTEET = 4;
	
	public static void main(String[] args) {
				
		Lauta pelilauta = new Lauta();
		new Ikkuna(pelilauta);
		pelilauta.aloitaPeli();
	}
	
}