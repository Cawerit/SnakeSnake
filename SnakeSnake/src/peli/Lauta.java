package peli;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lauta extends JPanel{
	
	public static final int[] RUUDUT = new int[]{30, 30};
	public static final int RUUDUN_KOKO = 20;
	public static final int FPS = 1;
	
	private Snake[] pelaajat;
	private Koordinaatti omena;
	private Timer loop;
		
	public Lauta(Snake pelaaja1, Snake pelaaja2){
		
		pelaajat = new Snake[]{ pelaaja1, pelaaja2 };
		omena = new Koordinaatti(5, 5);
				
		//Asetetaan laudalle oikea koko
		setPreferredSize(new Dimension(
				RUUDUN_KOKO * RUUDUT[0],
				RUUDUN_KOKO * RUUDUT[1]) );
		
		/**
		 * ActionListener-olio suorittaja vastaa pelin varsinaisesta suorituksesta.
		 *
		 */
		ActionListener suorittaja = new ActionListener(){
			
			/**
			 * Metodia kutsutaan n. Peli.FPS kertaa sekunnissa. Se tarkistaa pelin tilan
			 * ja kutsuu piirtometodia.
			 */
			@Override
			public void actionPerformed(ActionEvent event){
				boolean omenaSyoty = false;
				
				//Liikutetaan pelaajaa 1, mik‰li pelaaja on omenan p‰‰ll‰, pelaaja kasvaa samalla
				if(tarkistaTormays(omena, pelaaja1.getPaa())){
					omenaSyoty = true;
					pelaaja1.kasva();
				}
				else pelaaja1.liiku();
				
				if(tarkistaTormays(omena, pelaaja2.getPaa())){
					omenaSyoty = true;
					pelaaja2.kasva();
				}
				else pelaaja2.liiku();
				
				if(tarkistaTormays(pelaaja1.getPaa(), pelaaja2.getPaa()))
					loop.stop();
				repaint();
				
			}
			
		};
		
		loop = new Timer(60/FPS, suorittaja);
		loop.start();
		
	}
	
	@Override
	public void paintComponent(Graphics grafiikka){
		super.paintComponent(grafiikka);
		for(int i=0, n=pelaajat.length; i<n; i++){
			for(Koordinaatti k : pelaajat[i].getKoordinaatit()){
				grafiikka.drawRect(k.getX() * RUUDUN_KOKO, k.getY() * RUUDUN_KOKO, RUUDUN_KOKO, RUUDUN_KOKO);
			}
		}
	}
	
	private static boolean tarkistaTormays(Koordinaatti a, Koordinaatti b){
		return ( a.getX() == b.getX() && a.getY() == b.getY() );
	}
	
	
}
