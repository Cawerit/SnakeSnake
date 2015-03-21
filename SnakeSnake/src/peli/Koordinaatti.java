package peli;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka kuvaa kohtaa pelilaudalla
 *
 */
public class Koordinaatti {

	private int X;
	private int Y;
	
	public Koordinaatti(int X, int Y){
		this.X = X;
		this.Y = Y;
	}
	
	public int getX(){ return X; }
	public int getY(){ return Y; }
	
	@Override
	public String toString(){
		return "[" + X + ", " + Y + "]";
	}
	
	/**
	 * Staattinen luokkametodi, joka luo uuden Koordinaatin satunnaiseen sijaintiin m��ritetyll� alueella
	 * 
	 * @param minX M��ritt�� alarajan toivotun alueen x-koordinaatille (inclusive)
	 * @param maxX M��ritt�� yl�rajan toivotun alueen x-koordinaatille (inclusive)
	 * @param minY M��ritt�� alarajan toivotun alueen y-koordinaatille (inclusive)
	 * @param maxY M��ritt�� yl�rajan toivotun alueen y-koordinaatille (inclusive)
	 * @param exclude M��ritt�� joukon koordinaatteja, jotka eiv�t ole hyv�ksytt�vi� palautettavalle arvolle
	 * 
	 * @return Satunnaisesti valittu koordinaatti toivotulta alueelta
	 */
	public static Koordinaatti random(int minX, int maxX, int minY, int maxY, Collection<Koordinaatti> exclude){
		Random r = new Random();
		int x, y;
		boolean ok = false;
		do{
			x = r.nextInt(maxX-minX+1)+minX;//Random luku v�lilt� minX-maxX (molemmat inclusive)
			y = r.nextInt(maxY-minY+1)+minY;//Random luku v�lilt� minY-maxY (molemmat inclusive)
			ok = true;
			
			for(Koordinaatti k : exclude){
				if(k.getX() == x && k.getY() == y){
					ok = false;
					break;
				}
			}
			
		} while(!ok);
		return new Koordinaatti(x, y);
	}
	
	/**
	 * Tarkistaa, onko t�m� koordinaatti p��llekk�in annetun X- TAI Y-koordinaattien rivin kanssa
	 * <br>Esimerkiksi: 
	 * <br><code>
	 * (new Koordinaatti( 0, 0 )).collides( 0, 0 ); // true		<br>
	 * (new Koordinaatti( 0, 0 )).collides( 0, 1 ); // true		<br>
	 * (new Koordinaatti( 0, 0 )).collides( 2, 1 ); // false	<br>
	 * </code>
	 * 
	 * @param X x-arvo, johon t�m�n koordinaatin x-arvoa verrataan
	 * @param Y y-arvo, johon t�m�n koordinaatin y-arvoa verrataan
	 * @return Tieto siit�, onko nykyinen koordinaatti annetulla alueella
	 * 
	 */
	public boolean collides(int X, int Y){
		return (getX() == X || getY() == Y );
	}
	/**
	 * Tarkistaa, onko t�m� koordinaatti p��llekk�in annetun koordinaatin kanssa
	 * <br>Esimerkiksi: 
	 * <br><code>
	 * (new Koordinaatti( 0, 0 )).collides( new Koordinaatti( 0, 0 ) ); // true 	<br>
	 * (new Koordinaatti( 0, 0 )).collides( new Koordinaatti( 0, 1 ) ); // false 	<br>
	 * (new Koordinaatti( 0, 0 )).collides( new Koordinaatti( 2, 1 ) ); // false	<br>
	 * </code>
	 * 
	 * @param toinen Toinen koordinaatti, johon t�m�n koordinaatin arvoja verrataan
	 * @return Tieto siit�, onko nykyinen koordinaatti p��llekk�in annetun koordinaatin kanssa
	 * 
	 */
	public boolean collides(Koordinaatti toinen){
		return ( getX() == toinen.getX() && getY() == toinen.getY() );
	}
	/**
	 * Tarkistaa, onko t�m� koordinaatti samassa kohtaa kuin jokin annetuista koordinaateista.
	 * 
	 * @param toiset Lista toisista koordinaateista, joihin t�m�n koordinaatin arvoja verrataan
	 * @return Tieto siit�, p�teek� jollekin annetuista koordinaateista this.getX() == toinen.getX() && this.getY() == toinen.getY()
	 * 
	 */
	public boolean collides(Collection<Koordinaatti> toiset){
		for(Koordinaatti k : toiset){
			if(collides(k)) return true;
		}
		return false;
	}
	
}
