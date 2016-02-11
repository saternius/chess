import java.awt.Color;
import java.util.ArrayList;

/// Piece that functions as a rook
public class Rook extends Piece {

	public Rook(boolean w,int x, int y) {
		super(w,990+330,"Rook",x,y);
	}

	@Override
	public ArrayList<Square> threatMap() {
		return getMoves(false);
	}
	
	@Override
	public ArrayList<Square> posMove() {
		if(isPinned()){
			return new ArrayList<Square>();
		}
		return getMoves(true);
	}
	
	public ArrayList<Square>getMoves(boolean color){
		ArrayList<Integer[]> ret = getRookMovement();
		return posToSquares(ret,color);
	}

	
}
