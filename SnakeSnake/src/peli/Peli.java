package peli;

public class Peli {
	
	public static void main(String[] args) {
				
		Lauta pelilauta = new Lauta(
				new Snake( new Koordinaatti(0, 10), 3, Suunta.OIKEA),
				new Snake( new Koordinaatti(Lauta.RUUDUT[0], 10), 3, Suunta.VASEN));
		new Ikkuna(pelilauta);
	}
	
}