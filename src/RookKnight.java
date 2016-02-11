import java.util.ArrayList;

///Piece that has the movement of a Rook and a Knight
public class RookKnight extends Piece {

	public RookKnight(boolean w, int x, int y) {
		super(w, 1650+330, "RookKnight", x, y);
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
		ArrayList<Integer[]> ret = getRookMovement();
		ret.addAll(getClockMovement());	
		return posToSquares(ret,color);
	}

	
}
