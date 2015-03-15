package peli;
import java.awt.event.KeyEvent;
/**
 * M‰‰ritt‰‰ mahdolliset suunnat pelilaudalla ja tarjoaa metodit
 * koordinaatistossa liikkumiseen suunnan mukaisesti.
 * 
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
	
	public int getXIncrease(){ return xIncrease; }
	public int getYIncrease(){ return yIncrease; }
	public int getP1KeyCode(){ return p1KeyCode; }
	public int getP2KeyCode(){ return p2KeyCode; }
	
	public boolean isOpposite(Suunta toinen){
		//Palauttaa tiedon siit‰, onko verrattava suunta t‰m‰n vastakohta
		return (xIncrease == toinen.getXIncrease()*-1 && yIncrease == toinen.getYIncrease()*-1);
	}
	
}
