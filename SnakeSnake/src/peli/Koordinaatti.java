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
	 * Staattinen luokkametodi, joka luo uuden Koordinaatin satunnaiseen sijaintiin m‰‰ritetyll‰ alueella
	 * 
	 * @param minX M‰‰ritt‰‰ alarajan toivotun alueen x-koordinaatille (inclusive)
	 * @param maxX M‰‰ritt‰‰ yl‰rajan toivotun alueen x-koordinaatille (inclusive)
	 * @param minY M‰‰ritt‰‰ alarajan toivotun alueen y-koordinaatille (inclusive)
	 * @param maxY M‰‰ritt‰‰ yl‰rajan toivotun alueen y-koordinaatille (inclusive)
	 * @param exclude M‰‰ritt‰‰ joukon koordinaatteja, jotka eiv‰t ole hyv‰ksytt‰vi‰ palautettavalle arvolle
	 * 
	 * @return Satunnaisesti valittu koordinaatti toivotulta alueelta
	 */
	public static Koordinaatti random(int minX, int maxX, int minY, int maxY, Collection<Koordinaatti> exclude){
		Random r = new Random();
		int x, y;
		boolean ok = false;
		do{
			x = r.nextInt(maxX-minX+1)+minX;//Random luku v‰lilt‰ minX-maxX (molemmat inclusive)
			y = r.nextInt(maxY-minY+1)+minY;//Random luku v‰lilt‰ minY-maxY (molemmat inclusive)
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
	 * Tarkistaa, onko t‰m‰ koordinaatti p‰‰llekk‰in annetun X- tai Y-koordinaattien rivin kanssa
	 * Esimerkiksi: 
	 * <code>
	 * (new Koordinaatti( 0, 0 )).collides( 0, 0 ); // => true
	 * (new Koordinaatti( 0, 0 )).collides( 0, 1 ); // => true
	 * (new Koordinaatti( 0, 0 )).collides( 2, 1 ); // => false
	 * </code>
	 * 
	 * @param XY
	 * @return
	 */
	public boolean collides(int... XY){
		int n = XY.length;
		return ( (n>=1 && getX() == XY[0]) || (n>=2 && getY() == XY[1]) );
	}
	public boolean collides(Koordinaatti b){
		return ( getX() == b.getX() && getY() == b.getY() );
	}
	public boolean collides(Collection<Koordinaatti> b){
		
		for(Koordinaatti k : b){
			if(collides(k)) return true;
		}
		return false;
	}
	
}
