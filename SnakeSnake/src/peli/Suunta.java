package peli;
/**
 * M‰‰ritt‰‰ mahdolliset suunnat pelilaudalla ja tarjoaa metodit
 * koordinaatistossa liikkumiseen suunnan mukaisesti.
 * 
 *
 */
public enum Suunta {
	VASEN(-1, 0), OIKEA(1, 0), YLOS(0,-1), ALAS(0,1);
	
	private int xIncrease;
	private int yIncrease;
	
	private Suunta(int xIncrease, int yIncrease){
		this.xIncrease = xIncrease;
		this.yIncrease = yIncrease;
	}
	
	public int getXIncrease(){ return xIncrease; }
	public int getYIncrease(){ return yIncrease; }
	
}
