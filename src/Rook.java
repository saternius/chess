import java.util.ArrayList;

/// Piece that functions as a rook
public class Rook extends Piece {

	public Rook(ChessBoard cb,boolean w,int x, int y) {
		super(cb,w,990+330,"Rook",x,y);
	}
	
	public Rook(ChessBoard cb, Piece piece) {
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
	
	public ArrayList<Square>getMoves(boolean color){
		ArrayList<Integer[]> ret = getRookMovement();
		return posToSquares(ret,color);
	}

	
}
