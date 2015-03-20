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
	private Color vari;
	private Suunta suunta;
	public boolean suuntaAsetettu;
	
	public Snake(Koordinaatti aloituskohta, int pituus, Suunta suunta, Color vari){
		this.suunta = suunta;
		this.vari = vari;
		palat = new ArrayList<Koordinaatti>();
		suuntaAsetettu = false;
		
		//Asetetaan pelaajat alkusijainteihinsa
		for(int i=0; i<pituus; i++){
			Koordinaatti koordinaatti = new Koordinaatti( 
					aloituskohta.getX() + (i * suunta.getXIncrease()),
					aloituskohta.getY() + (i * suunta.getYIncrease()));
			
			palat.add(koordinaatti);
			
		}
	}
	
	/**
	 * @return Pelaajan kaikkien liitoskohtien sijainnit listana
	 */
	public ArrayList<Koordinaatti> getKoordinaatit(){
		return palat;
	}
	
	/**
	 * Asettaa pelaajalle uuden suunnan. Suunta vaikuttaa siihen, minne pelaaja liikkuu seuraavalla pelilaudan (Lauta.java)
	 * p‰ivityskierroksella.
	 * 
	 * Mik‰li annettu suunta on pelaajan nykyisen suunnan "vastakohta", muutoksia nykyiseen suuntaan ei tehd‰,
	 * sill‰ t‰m‰ muutos johtaisi pelaajan kuolemaan (pelaaja ei saa tˆrm‰t‰ itseens‰)
	 * 
	 * Mik‰li annettu suunta on jo kertaalleen asetettu samalla pelilaudan p‰ivityskierroksella, muutoksia nykyiseen
	 * suuntaan ei tehd‰, koska muutos aiheuttaisi odottamatonta k‰ytt‰ytymist‰ tilanteessa, jossa pelaaja tekee liian
	 * nopeita muutoksia suuntaan.
	 * Esimerkiksi:
	 * <br>
	 * <br><code>
	 * 
	 * setSuunta( Suunta.VASEN ); //suunta == Suunta.VASEN
	 * //pelilaudan p‰ivitys ( Lauta.loop )
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.YLOS
	 * 
	 * //pelilaudan p‰ivitys
	 * 
	 * setSuunta( Suunta.OIKEA ); //suunta == Suunta.OIKEA
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.OIKEA koska pelilauta ei ole viel‰ p‰ivittynyt
	 * 
	 * //pelilaudan p‰ivitys
	 * 
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.YLOS
	 * //pelilaudan p‰ivitys
	 * setSuunta( Suunta.ALAS ); //suunta == Suunta.YLOS koska ei voi k‰‰nty‰ "vastakkaiseen" suuntaan
	 * 
	 * </code>
	 * 
	 * @param suunta Pelaajalle asetettava uusi suunta
	 * 
	 */
	public void setSuunta(Suunta suunta){
		if(suuntaAsetettu || this.suunta.isOpposite(suunta)) return;
		this.suunta = suunta;
		suuntaAsetettu = true;
	}
	
	/**
	 * Luo pelaajalle uuden liitoskohdan, "p‰‰n", jonka sijainti laudalla riippuu edellisest‰ p‰‰n sijainnista
	 * ja pelaajan suunnasta. T‰ten pelaaja kasvaa yhden "askeleen" laudalla.
	 */
	private void siirry(){
		Koordinaatti vanhaPaa = getPaa();
		Koordinaatti uusiPaa = new Koordinaatti( 
				vanhaPaa.getX() + suunta.getXIncrease(),
				vanhaPaa.getY() + suunta.getYIncrease() );
		palat.add(uusiPaa);
	}
	/**
	 * Metodi kutsuu metodia siirry() ja poistaa pelaajan viimeisen palan. T‰ten pelaaja liikkuu yhden askeleen laudalla.
	 */
	public void liiku(){//Liikkuminen silloin kun ei tarvitse kasvattaa k‰‰rmett‰
		siirry(); 
		palat.remove(0);
	}
	/**
	 * Metodi kutsuu metodia siirry() eik‰ tee lis‰toimenpiteit‰. T‰ten pelaaja liikkuu yhden askeleen laudalla. Ja kasvaa samalla.
	 */
	public void kasva(){//Liikkuminen silloin kun k‰‰rme kasvaa samalla
		siirry();
	}
	/**
	 * @return Pelaajan palalistan viimeinen pala, eli "p‰‰n" koordinaatit.
	 */
	public Koordinaatti getPaa(){ 
		return palat.get(palat.size()-1); 
	}
	/**
	 * @return Lista kaikista pelaajan paloista, lukuunottamatta "p‰‰t‰"
	 */
	private List<Koordinaatti> getKroppa(){
		return getKoordinaatit().subList(0, palat.size()-1);
	}
	/**
	 * @return Pelaajan v‰ri, jota k‰ytet‰‰n sen piirt‰miseen n‰ytˆlle.
	 */
	public Color getVari(){
		return vari;
	}
	
	/**
	 * Vertaa pelaajan sijaintia toiseen pelaajaan, sek‰ pelilaudan reunoihin ja palauttaa tiedon siit‰,
	 * onko pelaajan tila "kuollut", eli rikkooko pelaajan p‰‰n sijainti pelin rajoituksia.
	 * 
	 * @param toinen Toinen pelaaja, johon k‰sitelt‰v‰‰ pelaajaa verrataan joissain tarkistuksissa
	 * @return Tieto siit‰, onko k‰sitelt‰v‰ pelaaja "kuollut". True, jos on kuollut, false jos ei.
	 */
	public boolean kuollut(Snake toinen){
		Koordinaatti paa = getPaa();
		return toinen.getKoordinaatit().size() - palat.size() >= Peli.VOITTO_PISTEET //Toisen pelaajan pisteet eiv‰t saa ylitt‰‰ k‰sitelt‰v‰n pelaajan pisteit‰
				|| paa.collides(Lauta.RUUDUT[0]+1, Lauta.RUUDUT[1]+1)	//P‰‰ ei saa ylitt‰‰ laudan ulkorajoja
				|| paa.collides(-1, -1) //P‰‰ ei saa ylitt‰‰ laudan sis‰rajoja
				|| paa.collides(toinen.getKoordinaatit()) //P‰‰ ei saa koskea toiseen pelaajaan
				|| paa.collides(getKroppa()); //P‰‰ ei saa osua muihin pelaajan osiin
	}
}
