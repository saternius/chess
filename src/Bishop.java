import java.awt.Color;
import java.util.ArrayList;

/// Piece that functions as a bishop
public class Bishop extends Piece {
	public Bishop(boolean w, int x, int y) {
		super(w,660,"Bishop",x,y);
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
	
	public ArrayList<Square> getMoves(boolean color){
		ArrayList<Integer[]> ret = getDiagMovement();
		return posToSquares(ret,color);
	}

}
