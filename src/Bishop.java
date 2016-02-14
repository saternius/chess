import java.util.ArrayList;

/// Piece that functions as a bishop
public class Bishop extends Piece {
	public Bishop(ChessBoard cb, boolean w, int x, int y) {
		super(cb,w,660,"Bishop",x,y);
	}
	
	public Bishop(ChessBoard cb, Piece piece) {
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
		ArrayList<Integer[]> ret = getDiagMovement();
		return posToSquares(ret,color);
	}

}
