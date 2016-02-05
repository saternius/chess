import java.awt.Graphics;
import java.util.ArrayList;


public class Piece {
	boolean white;
	int x;
	int y;
	String type;
	public ArrayList<Piece> attackers = new ArrayList<Piece>();  
	public Piece(boolean w, int xSft, String type){
		white = w;
		xS = xSft;
		this.type = type;
	}
	
	// Toggles every square that a piece can go to blue and returns an array of squares in which it is threatening.
	public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
		return null;
	}
	
	
	
	
	//---------------Anything below is beyond the first Assignment-----------------
	int xS;
	public void draw(Graphics stage, int x, int y){
		int yShift = 0;
		if(!white){
			yShift = 350;
		}
		stage.drawImage(gameApplet.peicesPng, x, y, gameApplet.boardDim/8+x, gameApplet.boardDim/8+y, 0+xS, 0+yShift, 350+xS, 350+yShift, null);
	
	}
}
