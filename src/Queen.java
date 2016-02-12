import java.util.ArrayList;

/// Piece that functions as a queen
public class Queen extends Piece {

	public Queen(boolean w, int x, int y) {
		super(w,330,"Queen",x,y);
	
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

	public ArrayList<Square> getMoves(boolean color) {
		//Essentially a combination of bishop/rook
		ArrayList<Integer[]> ret = getDiagMovement();
		ret.addAll(getRookMovement());	
		return posToSquares(ret,color);
	}


}
