import java.util.ArrayList;

/// Piece that functions as a knight
public class Knight extends Piece {

	public Knight(ChessBoard cb,boolean w, int x, int y) {
		super(cb, w,990,"Knight",x,y);
	}
	
	public Knight(ChessBoard cb, Piece piece) {
		super(cb,piece);
	}
	
	@Override
	public ArrayList<Square> threatMap() {
		return getMoves(false);
	}
	
	@Override
	public ArrayList<Square> posMove() {
		return getMoves(true);
	}
	
	public ArrayList<Square> getMoves(boolean color){
		ArrayList<Integer[]> ret = getClockMovement();
		return posToSquares(ret,color);
	}

}
