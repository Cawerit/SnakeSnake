package peli;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

public class Lauta extends JPanel implements ActionListener{
	
	public static final int[] RUUDUT = new int[]{30, 30};
	public static final int RUUDUN_KOKO = 20;
	public static final double FPS = 0.7;
	
	private static final Color OMENAN_VARI = Color.green;
	
	private Snake[] pelaajat;
	private int[] pisteet;
	private Koordinaatti omena;
	private Timer loop;
		
	public Lauta(){
		
		pelaajat = new Snake[2];
		pisteet = new int[]{0,0};
		loop = new Timer((int)(60/FPS), this);
		
		//Asetetaan laudalle oikea koko
		setPreferredSize(new Dimension(
				RUUDUN_KOKO * RUUDUT[0],
				RUUDUN_KOKO * RUUDUT[1]) );
		setFocusable(true);
		addKeyListener(new Painallukset());
				
	}
	
	public void aloitaPeli(){
		
		pelaajat[0] = new Snake( new Koordinaatti(0, 10), 5, Suunta.OIKEA, Color.red);
		pelaajat[1] = new Snake( new Koordinaatti(RUUDUT[0], 10), 5, Suunta.VASEN, Color.blue);

		arvoOmena();
		
		loop.start();
		
	}

	private void lopetaPeli(Snake voittaja){
		loop.stop();
		if(voittaja == null)
			System.out.print("Tasapeli! ");
		else{
			int voittajaIndex = (voittaja == pelaajat[0]) ? 0 : 1;
			pisteet[voittajaIndex]++;
			System.out.print("Pelaaja " + (voittajaIndex+1) + " voitti t‰m‰n er‰n! ");
		}
		System.out.println("Tilanne on " + pisteet[0] + " - " + pisteet[1]);
		
		aloitaPeli();
		
	}
	
	/**
	 * Metodia kutsutaan Lauta.FPS kertaa sekunnissa. Se tarkistaa pelin tilan
	 * ja kutsuu piirtometodia.
	 */
	@Override
	public void actionPerformed(ActionEvent event){
		Snake pelaaja1 = pelaajat[0];
		Snake pelaaja2 = pelaajat[1];
		
		//Liikutetaan pelaajaa 1, mik‰li pelaaja on omenan p‰‰ll‰, pelaaja kasvaa samalla
		if(omena.collides(pelaaja1.getPaa())){
			arvoOmena();
			pelaaja1.kasva();
		}
		else pelaaja1.liiku();
		
		if(omena.collides(pelaaja2.getPaa())){
			arvoOmena();
			pelaaja2.kasva();
		}
		else pelaaja2.liiku();
		
		Koordinaatti p1Paa = pelaaja1.getPaa();
		Koordinaatti p2Paa = pelaaja2.getPaa();
		boolean p1Kuollut = (p1Paa.collides(RUUDUT) || p1Paa.collides(0, 0) || p1Paa.collides(pelaaja2.getKoordinaatit()));
		boolean p2Kuollut = (p2Paa.collides(RUUDUT) || p2Paa.collides(0, 0) || p2Paa.collides(pelaaja1.getKoordinaatit()));
		
		if(p1Kuollut)
			lopetaPeli( p2Kuollut ? null : pelaaja2 );
		else if(p2Kuollut)
			lopetaPeli( pelaaja1 );
			
		repaint();
		
	}
	
	
	@Override
	public void paintComponent(Graphics grafiikka){
		super.paintComponent(grafiikka);
		
		grafiikka.setColor( OMENAN_VARI );
		grafiikka.fillOval(omena.getX() * RUUDUN_KOKO, omena.getY() * RUUDUN_KOKO, RUUDUN_KOKO, RUUDUN_KOKO);
		
		for(int i=0, n=pelaajat.length; i<n; i++){
			
			grafiikka.setColor(pelaajat[i].getVari());
			for(Koordinaatti k : pelaajat[i].getKoordinaatit()){
				grafiikka.fillRect(k.getX() * RUUDUN_KOKO, k.getY() * RUUDUN_KOKO, RUUDUN_KOKO, RUUDUN_KOKO);
			}
			
		}
	}	
	
	private class Painallukset extends KeyAdapter{
		
		
		@Override
		public void keyPressed(KeyEvent event){
			
			int nappi = event.getKeyCode();
			
			
			for(Suunta s : Suunta.values()){
				if(nappi == s.getP1KeyCode()){
					if(!pelaajat[0].setSuunta(s)) lopetaPeli(pelaajat[1]);
					break;
				}
				if(nappi == s.getP2KeyCode()){
					if(!pelaajat[1].setSuunta(s)) lopetaPeli(pelaajat[0]);
					break;
				}
			}
			
		}
		
	}
	
	private void arvoOmena(){
		ArrayList<Koordinaatti> yhdistetty = new ArrayList<Koordinaatti>( pelaajat[0].getKoordinaatit() );
		yhdistetty.addAll( pelaajat[1].getKoordinaatit() );
		omena = Koordinaatti.random(0, RUUDUT[0], 0, RUUDUT[1], yhdistetty);
	}
	
}
