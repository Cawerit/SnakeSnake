package peli;

public class Pala{
	
	public static final int KOKO = 5;
	
	public static enum Tyyppi{
		OMENA(Vari.VALKOINEN), PELAAJA1(Vari.VIHREA), PELAAJA2(Vari.VIHREA);
		
		private Vari vari;
		
		private Tyyppi(Vari vari){
			this.vari=vari;
		}
	}
	
	private Koordinaatti sijainti;
	private Tyyppi tyyppi;
	
	public Pala(Koordinaatti sijainti, Tyyppi tyyppi){
		this.sijainti = sijainti;
		this.tyyppi = tyyppi;
	}
	
}
