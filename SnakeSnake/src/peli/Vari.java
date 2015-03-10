package peli;
/**
 * M‰‰ritt‰‰ peliss‰ mahdollisesti k‰ytett‰v‰t muotojen v‰rit ja tarjoaa
 * metodit niiden RGB-arvojen saamiseen
 *
 */
public enum Vari {
	
	VALKOINEN(255, 255, 255), VIHREA(0, 255, 0), SININEN(0, 0, 255);
	
	private int red;
	private int green;
	private int blue;
	
	private Vari(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public int getRed(){
		return red;
	}
	public int getGreen(){
		return green;
	}
	public int getBlue(){
		return blue;
	}
	
}
