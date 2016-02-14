import java.util.ArrayList;


/// Piece that functions as a pawn
public class Pawn extends Piece {
	public Pawn(ChessBoard cb,boolean w, int x,int y) {
		super(cb,w,1650,"Pawn",x,y);
	}
	

	public Pawn(ChessBoard cb, Piece piece) {
		super(cb,piece);
	}


	@Override
	public ArrayList<Square> threatMap() {
		ArrayList<Square> threats = new ArrayList<Square>();
		
		if(white){			
			//Check if the pawn can eat another piece.
			if(inBounds(x-1,y-1)){
				threats.add(cb.board[x-1][y-1]);
			}
			if(inBounds(x+1,y-1)){
				threats.add(cb.board[x+1][y-1]);
			}
			
		}else{
			//essentially the same as before but is shifted directions.
			if(inBounds(x-1,y+1)){
				threats.add(cb.board[x-1][y+1]);
			}
			if(inBounds(x+1,y+1)){
				threats.add(cb.board[x+1][y+1]);
			}
		}
		return threats;
	
	}
	
	@Override
	public ArrayList<Square> posMove() {
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();
		
		if(white){
			Integer[] inFront = {x,y-1}; //the spot in front of the pawn	
			//check if the pawn can move twice on first go.
			if(y==6 && cb.noPeices(x,y-2) && cb.noPeices(x,y-1)){
				Integer[] inFront2 = {x,y-2};
			    ret.add(inFront2);	
			}
			if(cb.noPeices(x,y-1)){
				ret.add(inFront);
			}
			
			//Check if the pawn can eat another piece.
			if(inBounds(x-1,y-1) ){
				if(!cb.noPeices(x-1,y-1)){
					Integer[] eats = {x-1,y-1};
					ret.add(eats);
				}
			}
			if(inBounds(x+1,y-1)){
				if(!cb.noPeices(x+1,y-1)){
					Integer[] eats = {x+1,y-1};
					ret.add(eats);
				}
			}
			
		}else{
			//essentially the same as before but is shifted directions.
			Integer[] inFront = {x,y+1};
			if(y==1  && cb.noPeices(x,y+2) && cb.noPeices(x,y+1)){
				Integer[] inFront2 = {x,y+2};
			    ret.add(inFront);
			    ret.add(inFront2);
				
			}
			if(cb.noPeices(x,y+1)){
				ret.add(inFront);
			}
			
			//Check for eating
			if(inBounds(x-1,y+1)){
				if(!cb.noPeices(x-1,y+1)){
					Integer[] eats = {x-1,y+1};
					ret.add(eats);
				}
			}
			if(inBounds(x+1,y+1)){
				if(!cb.noPeices(x+1,y+1)){
					Integer[] eats = {x+1,y+1};
					ret.add(eats);
				}
			}
		}
		return posToSquares(ret,true);
	}

}
