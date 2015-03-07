package peli;
/**
 * M‰‰ritt‰‰ peliss‰ mahdollisesti k‰ytett‰v‰t muotojen v‰rit ja tarjoaa
 * metodit niiden RGB-arvojen saamiseen
 *
 */
public enum Vari {
	
	VALKOINEN(255, 255, 255);
	
	private int red;
	private int green;
	private int blue;
	
	private Vari(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
}
