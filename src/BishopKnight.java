import java.util.ArrayList;

///This piece has the movement of both a Bishop and a Knight.
public class BishopKnight extends Piece{
	
	public BishopKnight(boolean w, int x, int y) {
		super(w, 1650+660, "BishopKnight", x, y);
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
		ret.addAll(getClockMovement());	
		return posToSquares(ret,color);
	}
	
}
