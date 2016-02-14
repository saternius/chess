import java.util.ArrayList;

///Piece that has the movement of a Rook and a Knight
public class RookKnight extends Piece {

	public RookKnight(ChessBoard cb,boolean w, int x, int y) {
		super(cb,w, 1650+330, "RookKnight", x, y);
	}
	
	public RookKnight(ChessBoard cb, Piece piece) {
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
		ArrayList<Integer[]> ret = getRookMovement();
		ret.addAll(getClockMovement());	
		return posToSquares(ret,color);
	}

	
}
