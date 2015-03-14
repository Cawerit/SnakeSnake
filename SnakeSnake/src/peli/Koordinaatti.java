package peli;

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
	
}
