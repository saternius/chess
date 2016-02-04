import java.awt.Graphics;
import java.util.ArrayList;


public class Piece {
	int value;
	boolean white;
	int xS;
	int x;
	int y;
	public ArrayList<Piece> attackers = new ArrayList<Piece>();  
	public Piece(boolean w, int xSft){
		white = w;
		xS = xSft;
	}
	
	public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
		return null;
	}
	
	
	public void draw(Graphics stage, int x, int y){
		int yShift = 0;
		if(!white){
			yShift = 350;
		}
		stage.drawImage(gameApplet.peicesPng, x, y, gameApplet.boardDim/8+x, gameApplet.boardDim/8+y, 0+xS, 0+yShift, 350+xS, 350+yShift, null);
	
	}


}
