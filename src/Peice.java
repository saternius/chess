import java.awt.Graphics;


public class Peice {
	int value;
	boolean white;
	int xS;
	public Peice(boolean w, int xSft){
		white = w;
		xS = xSft;
	}
	
	public void draw(Graphics stage, int x, int y){
		int yShift = 0;
		if(!white){
			yShift = 350;
		}
		stage.drawImage(gameApplet.peicesPng, x, y, gameApplet.boardDim/8+x, gameApplet.boardDim/8+y, 0+xS, 0+yShift, 350+xS, 350+yShift, null);
	
	}

	public boolean posMove(int xPos, int yPos) {
		return false;
		
	}

}
