import java.util.ArrayList;

/// Piece that functions as a queen
public class Queen extends Piece {

	public Queen(ChessBoard cb,boolean w, int x, int y) {
		super(cb,w,330,"Queen",x,y);
	
	}
	
	public Queen(ChessBoard cb, Piece piece) {
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

	public ArrayList<Square> getMoves(boolean color) {
		//Essentially a combination of bishop/rook
		ArrayList<Integer[]> ret = getDiagMovement();
		ret.addAll(getRookMovement());	
		return posToSquares(ret,color);
	}


}
