package peli;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * 
 * Luokka luo ikkunan ja m‰‰ritt‰‰ sille koon k‰ytt‰j‰n n‰ytˆn koon mukaan.
 *
 */
@SuppressWarnings("serial")
public class Ikkuna extends JFrame{
	
	public Ikkuna(Lauta lauta){
		
		add(lauta);//Aloitetaan lis‰‰m‰ll‰ uusi pelilauta (JPanel)
		setResizable(false);//K‰ytt‰j‰ ei voi muuttaa kokoa (pidet‰‰n homma yksinkertaisena)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Peli loppuu jos ikkuna suljetaan
		
		int uusiKoko;
		try{
			//M‰‰ritet‰‰n pelialueen koko n‰ytˆn koon mukaan
			Dimension alue = Toolkit.getDefaultToolkit().getScreenSize();
			//M‰‰ritet‰‰n koko ruudun pienemm‰n mitan mukaan (pituus/leveys), jotta pidet‰‰n alue neliˆn‰
			//ja otetaan vain n. 2/3 ruudun koosta
			uusiKoko = (int) ( Math.min( alue.getWidth(), alue.getHeight() ) * 0.6 );			
		} catch(Exception e){
			//Mik‰li n‰ytˆn kokoa ei saada luettua, annetaan oletusarvoksi 700x700 px
			uusiKoko = 700;
		}
		//Annetaan pelilaudalle m‰‰ritetty koko
		lauta.setKoko(uusiKoko);
		//Rajataan t‰m‰n ikkunan koko sis‰llytetyn pelilaudan koon mukaan
		pack();
		setVisible(true);//Ikkuna on n‰kyviss‰
	}
	
}
