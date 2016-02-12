import java.util.ArrayList;

/// Piece that functions as a knight
public class Knight extends Piece {

	public Knight(boolean w, int x, int y) {
		super(w,990,"Knight",x,y);
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
		ArrayList<Integer[]> ret = getClockMovement();
		return posToSquares(ret,color);
	}

}
