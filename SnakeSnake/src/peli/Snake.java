package peli;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Luokka kuvaa yksitt‰ist‰ k‰‰rmett‰ (pelaajaa)
 *
 */
public class Snake {
	
	/**
	 * Lista pelaajan liitoskohtien koordinaateista
	 */
	private ArrayList<Koordinaatti> palat;
	/**
	 * V‰ri, jolla pelaaja piirret‰‰n ruudulle
	 */
	private Color vari;
	/**
	 * Pelaajan senhetkinen suunta, jota k‰ytet‰‰n m‰‰ritt‰m‰‰n mihin pelaajan p‰‰ siirtyy seuraavaksi
	 */
	private Suunta suunta;
	/**
	 * Kertoo, onko pelaajan suunta asetettu kertaalleen t‰ll‰ p‰ivityskierroksella
	 * @see setSuunta(Suunta)
	 */
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
	 * <p>
	 * Asettaa pelaajalle uuden suunnan. Suunta vaikuttaa siihen, minne pelaaja liikkuu seuraavalla pelilaudan
	 * {@link peli.Lauta#actionPerformed(ActionEvent) p‰ivityskierroksella}.
	 * </p>
	 * <p>Mik‰li annettu suunta on pelaajan nykyisen suunnan "vastakohta", muutoksia nykyiseen suuntaan ei tehd‰,
	 * sill‰ t‰m‰ muutos johtaisi pelaajan kuolemaan (pelaaja ei saa tˆrm‰t‰ itseens‰)
	 * </p>
	 * <p>Mik‰li annettu suunta on jo kertaalleen asetettu samalla pelilaudan p‰ivityskierroksella, muutoksia nykyiseen
	 * suuntaan ei tehd‰, koska muutos aiheuttaisi odottamatonta k‰ytt‰ytymist‰ tilanteessa, jossa pelaaja tekee liian
	 * nopeita muutoksia suuntaan.</p>
	 * <br>Esimerkiksi:
	 * <br><code>
	 * 
	 * setSuunta( Suunta.VASEN ); //suunta == Suunta.VASEN	<br>
	 * //pelilaudan p‰ivitys ( Lauta.loop )					<br>
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.YLOS	<br>
	 * <br>
	 * //pelilaudan p‰ivitys								<br>
	 * <br>
	 * setSuunta( Suunta.OIKEA ); //suunta == Suunta.OIKEA	<br>
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.OIKEA koska pelilauta ei ole viel‰ p‰ivittynyt	<br>
	 * <br>
	 * //pelilaudan p‰ivitys								<br>
	 * <br>
	 * setSuunta( Suunta.YLOS ); //suunta == Suunta.YLOS	<br>
	 * //pelilaudan p‰ivitys								<br>
	 * setSuunta( Suunta.ALAS ); //suunta == Suunta.YLOS koska ei voi k‰‰nty‰ "vastakkaiseen" suuntaan	<br>
	 * 
	 * </code>
	 * 
	 * @param suunta Pelaajalle asetettava uusi suunta
	 * @see peli.Suunta
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
	public void kasva(){//Liikkuminen silloin kun k‰‰rme kasvaa samalla
		Koordinaatti vanhaPaa = getPaa();
		Koordinaatti uusiPaa = new Koordinaatti( 
				vanhaPaa.getX() + suunta.getXIncrease(),
				vanhaPaa.getY() + suunta.getYIncrease() );
		palat.add(uusiPaa);
	}
	/**
	 * Metodi kutsuu metodia kasva() ja poistaa pelaajan viimeisen palan. T‰ten pelaaja liikkuu yhden askeleen laudalla.
	 */
	public void liiku(){//Liikkuminen silloin kun ei tarvitse kasvattaa k‰‰rmett‰
		kasva(); 
		palat.remove(0);
	}
	
	/**
	 * @return Pelaajan palalistan viimeinen pala, eli "p‰‰n" koordinaatti.
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
				|| paa.getX() >= Lauta.RUUDUT[0] || paa.getY() >= Lauta.RUUDUT[1]	//P‰‰ ei saa olla ulkorajalla
				|| paa.getX() < 0 || paa.getY() < 0									//P‰‰ ei saa ylitt‰‰ laudan sis‰rajoja
				|| paa.collides(toinen.getKoordinaatit()) 							//P‰‰ ei saa koskea toiseen pelaajaan
				|| paa.collides(getKroppa()); 										//P‰‰ ei saa osua muihin pelaajan osiin
	}
}
