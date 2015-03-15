package peli;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;

public class Koordinaatti {

	private int X;
	private int Y;
	
	public Koordinaatti(int X, int Y){
		this.X = X;
		this.Y = Y;
	}
	
	public int getX(){ return X; }
	public int getY(){ return Y; }
	
	public String toString(){
		return "[" + X + ", " + Y + "]";
	}
	
	public static Koordinaatti random(int minX, int maxX, int minY, int maxY, Collection<Koordinaatti> exclude){
		Random r = new Random();
		int x, y;
		boolean ok = false;
		do{
			x = r.nextInt(maxX-minX+1)+minX;//Random luku väliltä minX-maxX (molemmat inclusive)
			y = r.nextInt(maxY-minY+1)+minY;//Random luku väliltä minY-maxY (molemmat inclusive)
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
	
	public boolean collides(int... XY){
		return ( getX() == XY[0] || getY() == XY[1] );
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
