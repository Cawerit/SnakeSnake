package peli;
import java.awt.event.KeyEvent;
/**
 * M‰‰ritt‰‰ mahdolliset suunnat pelilaudalla ja tarjoaa metodit
 * koordinaatistossa liikkumiseen suunnan mukaisesti.
 *
 */
public enum Suunta {
	VASEN(-1, 0, KeyEvent.VK_A, KeyEvent.VK_LEFT), 
	OIKEA(1, 0, KeyEvent.VK_D, KeyEvent.VK_RIGHT), 
	YLOS(0,-1, KeyEvent.VK_W, KeyEvent.VK_UP), 
	ALAS(0,1, KeyEvent.VK_S, KeyEvent.VK_DOWN);
	
	private int xIncrease;
	private int yIncrease;
	private int p1KeyCode;
	private int p2KeyCode;
	
	private Suunta(int xIncrease, int yIncrease, int p1KeyCode, int p2KeyCode){
		this.xIncrease = xIncrease;
		this.yIncrease = yIncrease;
		this.p1KeyCode = p1KeyCode;
		this.p2KeyCode = p2KeyCode;
	}
	/**
	 * @return Luku, joka kuvaa montako askelta X-akselilla on otettava siirty‰kseen kyseiseen suuntaan
	 */
	public int getXIncrease(){ return xIncrease; }
	/**
	 * @return Luku, joka kuvaa montako askelta Y-akselilla on otettava siirty‰kseen kyseiseen suuntaan
	 */
	public int getYIncrease(){ return yIncrease; }
	/**
	 * @return N‰pp‰inkoodi jolla pelaaja 1 viittaa kyseiseen suuntaan
	 */
	public int getP1KeyCode(){ return p1KeyCode; }
	/**
	 * @return N‰pp‰inkoodi jolla pelaaja 2 viittaa kyseiseen suuntaan
	 */
	public int getP2KeyCode(){ return p2KeyCode; }
	/**
	 * Vertaa annettua suuntaa k‰sitelt‰v‰‰n ja palauttaa tiedon siit‰, onko suunta k‰sitelt‰v‰n "vastakohta".
	 * Vastakohta "parit" ovat:
	 * Suunta.VASEN ja Suunta.OIKEA
	 * Suunta.YLOS ja Suunta.ALAS
	 * 
	 * Esimerkiksi:
	 * <br><code>
	 * Suunta.VASEN.isOpposite( Suunta.OIKEA ); // => true
	 * Suunta.VASEN.isOpposite( Suunta.ALAS ); // => false
	 * Suunta.OIKEA.isOpposite( Suunta.VASEN ); // => true
	 * </code>
	 * 
	 * @param toinen Suunta, johon k‰sitelt‰v‰‰ suuntaa verrataan
	 * @return Tieto siit‰, onko toinen suunta k‰sitelt‰v‰n "vastakohta"
	 */
	public boolean isOpposite(Suunta toinen){
		//Palauttaa tiedon siit‰, onko verrattava suunta t‰m‰n vastakohta
		return (xIncrease == toinen.getXIncrease()*-1 && yIncrease == toinen.getYIncrease()*-1);
	}
	
}
