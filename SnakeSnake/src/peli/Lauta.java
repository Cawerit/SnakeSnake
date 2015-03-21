package peli;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

public class Lauta extends JPanel implements ActionListener{
	
	/**
	 * M‰‰ritt‰‰ pelialueen koon koordinaattien m‰‰r‰n‰. T‰t‰ muuttamalla voi suurentaa pelialueen kokoa.
	 */
	public static final int[] RUUDUT = new int[]{45, 45};
	
	
	/**
	 * M‰‰ritt‰‰, kuinka monta pikseli‰ jokainen koordinaatti on kooltaan piirrettyn‰ ruudulle.
	 * T‰t‰ muuttamalla voi muuttaa sit‰, kuinka suurena pelialue piirret‰‰n.
	 */
	public static final int RUUDUN_KOKO = 15;
	
	
	/**
	 * M‰‰ritt‰‰, kuinka monta kertaa sekunnissa pelin tila p‰ivitet‰‰n ja kuva uudelleenpiirret‰‰n ruudulle.
	 * T‰t‰ muuttamalla voi muuttaa sit‰, kuinka nopeasti peli kulkee.
	 */
	public static final double FPS = 10;
	
	
	private static final Color OMENAN_VARI = Color.green;
	
	private Snake[] pelaajat;
	
	private Koordinaatti omena;
	private Timer loop;//Ajastin
	private Font fontti;
	private Color fonttiVari;
	
	private String[] viesti;
	private int[] pisteet;
	
	/**
	 * M‰‰ritt‰‰, mink‰ kokoisia pelaajat ovat pelin alkaessa.
	 */
	private static final int aloitusKoko = 10;
		
	/**
	 * Luokka vastaa pelin k‰ynnist‰misest‰, logiikasta ja sen piirt‰misest‰ Ikkuna-luokan muodostamaan
	 * JFrame-ikkunaan.
	 */
	public Lauta(){

		pelaajat = new Snake[2];
		pisteet = new int[]{0,0};
		loop = new Timer((int)(1000/FPS), this);
		fontti = new Font("Arial", 1, 30);
		fonttiVari = Color.white;
		viesti = new String[2];
		
		//Asetetaan laudalle oikea koko
		setPreferredSize(new Dimension(
				RUUDUN_KOKO * RUUDUT[0],
				RUUDUN_KOKO * RUUDUT[1]) );
		setFocusable(true);
		setBackground(Color.black);
		addKeyListener(new Painallukset());
				
	}
	
	/**
	 * Metodi luo 2 pelaajaa, asettaa kirjoitettavan viestin pelitilanteeksi ja k‰ynnist‰‰ ajastimen (loop).
	 * Se siis aloittaa yksitt‰isen pelikierroksen.
	 * 
	 */
	public void aloitaPeli(){
		
		//Luodaan pelaajat. Pelaajien parametrej‰ muuttamalla voi vaikuttaa pelin aloitustilanteeseen
		//ja pelaajien v‰riin
		
		pelaajat[0] = new Snake( 
				new Koordinaatti(0, RUUDUT[1]/2), //Pelaajan 1 aloituskohta on vasemmassa reunassa kent‰n puolessa v‰liss‰
				aloitusKoko,
				Suunta.OIKEA,				 	  //Pelaaja1 l‰htee liikkeelle oikealle, kohti ikkunan vasenta laitaa
				Color.red						  //Pelaajan 1 v‰ri on punainen
				);
		
		pelaajat[1] = new Snake(
				new Koordinaatti(RUUDUT[0], RUUDUT[1]/2), //Pelaajan 2 aloituskohta on oikeassa reunassa kent‰n puolessa v‰liss‰
				aloitusKoko,
				Suunta.VASEN,
				Color.blue
				);
		
		viesti[0] = null; //M‰‰ritet‰‰n, ett‰ kent‰n yl‰laidassa ei n‰ytet‰ viesti‰,
		naytaTilanne();	  //M‰‰ritet‰‰n, ett‰ kent‰n alalaidassa n‰ytet‰‰n pelikierroksen pistetilanne

		arvoOmena();	  //Arvotaan omenalle uusi sijainti
		
		loop.start();     //K‰ynnistet‰‰n pelin loop (ajastin, joka kutsuu actionPerformed-metodia)
		
	}
	
	/**
	 * Metodi lopettaa yksitt‰isen pelikierroksen pys‰ytt‰m‰ll‰ ajastimen (loop), m‰‰ritt‰m‰ll‰ voittajan perusteella
	 * n‰ytˆlle kirjoitettavan viestin.
	 * 
	 * @param voittaja Kierroksen voittaja, mik‰ kirjoitetaan ruudulle. Jos null, pelin ilmoitetaan p‰‰ttyneen tasapeliin.
	 */
	private void lopetaPeli(Snake voittaja){
		
		loop.stop();
		
		if(voittaja == null){
			viesti[0] = "Tasapeli!";
			fonttiVari = Color.white;
		}
		else{
			int voittajaIndex = (voittaja == pelaajat[0]) ? 0 : 1;
			pisteet[voittajaIndex]++;
			viesti[0] = "Pelaaja " + (voittajaIndex+1) + " voitti t‰m‰n er‰n! ";
			fonttiVari = voittaja.getVari();
		}
		viesti[1] = "Tilanne on " + pisteet[0] + " - " + pisteet[1];
		
		repaint();
		
	}
	
	/**
	 * Metodia kutsutaan Lauta.FPS kertaa sekunnissa. Se tarkistaa pelin tilan
	 * ja kutsuu piirtometodia.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent event){
		Snake pelaaja1 = pelaajat[0];
		Snake pelaaja2 = pelaajat[1];
		
		pelaaja1.suuntaAsetettu = false;//Pelaajat voivat taas vaihtaa suuntaa
		pelaaja2.suuntaAsetettu = false;
		
		//Liikutetaan pelaajaa 1, mik‰li pelaaja on omenan p‰‰ll‰, pelaaja kasvaa samalla
		if(omena.collides(pelaaja1.getPaa())){
			arvoOmena();
			pelaaja1.kasva();
			naytaTilanne(); //p‰ivitet‰‰n alalaidassa n‰kyv‰ viesti
		}
		else pelaaja1.liiku();
		
		if(omena.collides(pelaaja2.getPaa())){
			arvoOmena();
			pelaaja2.kasva();
			naytaTilanne();
		}
		else pelaaja2.liiku();
		
		if(pelaaja1.kuollut(pelaaja2))										//Jos pelaaja 1 on kuollut, voittaja on
			lopetaPeli( pelaaja2.kuollut(pelaaja1) ? null : pelaaja2 );     //pelaaja2, tai null, jos myˆs pelaaja 2 on kuollut
		
		else if(pelaaja2.kuollut(pelaaja1))		//Tai jos vain pelaaja 2 on kuollut, pelaaja 1 voittaa
			lopetaPeli( pelaaja1 );
	
		repaint();
		
	}
	
	/**
	 * Muuttaa luokan sis‰ist‰ muuttujaa viesti niin, ett‰ ruudun alalaitaan kirjoitettava teksti (viesti[1])
	 * on nykyisen pelikierroksen tilanne (syˆtyjen omenien m‰‰r‰)
	 * 
	 */
	private void naytaTilanne(){
		fonttiVari = Color.white;
		//Pelaajan pisteet on sen pituuden kasvu pelin aloituksen j‰lkeen
		viesti[1] = (pelaajat[0].getKoordinaatit().size()-aloitusKoko) + " - " + (pelaajat[1].getKoordinaatit().size()-aloitusKoko);
	}
	
	/**
	 * Kutsuu ylikirjoitettua JPanelin paintComponent-metodia ja piirt‰‰ lis‰ksi kaikki pelaajat ruudulle, sek‰ omenan
	 * ja viesti-muuttujaan tallennetut viestit.
	 * 
	 */
	@Override
	public void paintComponent(Graphics grafiikka){
		super.paintComponent(grafiikka);
		
		// Piirret‰‰n omena ruudulle
		grafiikka.setColor( OMENAN_VARI );
		grafiikka.fillOval(omena.getX() * RUUDUN_KOKO, omena.getY() * RUUDUN_KOKO, RUUDUN_KOKO, RUUDUN_KOKO);
		
		//Piirret‰‰n pelaajat ruudulle
		for(int i=0, n=pelaajat.length; i<n; i++){
			
			grafiikka.setColor(pelaajat[i].getVari());
			//Jokaisen pelaajan liitoskohdan kohdalle piirret‰‰n neliˆ
			for(Koordinaatti k : pelaajat[i].getKoordinaatit()){
				grafiikka.fillRect(k.getX() * RUUDUN_KOKO, k.getY() * RUUDUN_KOKO, RUUDUN_KOKO, RUUDUN_KOKO);
			}
			
		}
		
		grafiikka.setFont(fontti);
		grafiikka.setColor(fonttiVari);
		if(viesti[0] != null) grafiikka.drawString(viesti[0], 40, 40);
		if(viesti[1] != null) grafiikka.drawString(viesti[1], 40, RUUDUT[0]*RUUDUN_KOKO);
		
	}	
	
	/**
	 * Luokka vastaa pelin kontrolleista (napinpainalluksista)
	 *
	 */
	private class Painallukset extends KeyAdapter{
		
		/**
		 * Metodi seuraa painettuja n‰pp‰imi‰ ja tekee niiden mukaan tarvittavat toimenpiteet
		 */
		@Override
		public void keyPressed(KeyEvent event){
			
			int nappi = event.getKeyCode();
			
			//K‰yd‰‰n l‰pi kaikki Suunta-enumin suunnat ja verrataan napinpainallusta niihin tallennettuihin
			//pelaajien n‰pp‰imiin
			for(Suunta s : Suunta.values()){
				if(nappi == s.getP1KeyCode()){
					pelaajat[0].setSuunta(s);
					return;
				}
				if(nappi == s.getP2KeyCode()){
					pelaajat[1].setSuunta(s);
					return;
				}
			}
			//Jos peli on keskeytetty, v‰lilyˆnti jatkaa peli‰
			if(nappi == KeyEvent.VK_SPACE && !loop.isRunning())
				aloitaPeli();
			
		}
		
	}
	
	/**
	 * Arpoo omena-muuttujaan uuden koordinaatin. 
	 * Koordinaatin X-arvoksi hyv‰ksyt‰‰n kokonaisluvut v‰lilt‰ (0, RUUDUT[0])
	 * ja Y-arvoksi hyv‰ksyt‰‰n kokonaisluvut v‰lilt‰ (0, RUUDUT[1]),
	 * poislukien molempien pelaajien koordinaatit (omena ei voi ilmesty‰ pelaajan kohdalle)
	 * 
	 */
	private void arvoOmena(){
		ArrayList<Koordinaatti> yhdistetty = new ArrayList<Koordinaatti>( pelaajat[0].getKoordinaatit() );
		yhdistetty.addAll( pelaajat[1].getKoordinaatit() );
		omena = Koordinaatti.random(0, RUUDUT[0], 0, RUUDUT[1], yhdistetty);
	}
	
}
