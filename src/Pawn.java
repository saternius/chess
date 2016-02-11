import java.awt.Color;
import java.util.ArrayList;


/// Piece that functions as a pawn
public class Pawn extends Piece {
	public Pawn(boolean w, int x,int y) {
		super(w,1650,"Pawn",x,y);
	}
	

	@Override
	public ArrayList<Square> threatMap() {
		int xPos = this.x;
		int yPos = this.y;
		ArrayList<Square> threats = new ArrayList<Square>();
		
		if(white){			
			//Check if the pawn can eat another piece.
			if(xPos-1>-1 && yPos-1>-1 ){
				threats.add(ChessBoard.board[xPos-1][yPos-1]);
			}
			if(xPos+1<8 && yPos-1>-1){
				threats.add(ChessBoard.board[xPos+1][yPos-1]);
			}
			
		}else{
			//essentially the same as before but is shifted directions.
			if(xPos-1>-1 && yPos+1<8){
				threats.add(ChessBoard.board[xPos-1][yPos+1]);
			}
			if(xPos+1<8 && yPos+1<8){
				threats.add(ChessBoard.board[xPos+1][yPos+1]);
			}
		}
		return threats;
	
	}
	
	@Override
	public ArrayList<Square> posMove() {
		if(isPinned()){
			return new ArrayList<Square>();
		}
		
		int xPos = this.x;
		int yPos = this.y;
		
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();
		
		if(white){
			Integer[] inFront = {xPos,yPos-1}; //the spot in front of the pawn	
			//check if the pawn can move twice on first go.
			if(yPos==6 && ChessBoard.noPeices(xPos,yPos-2) && ChessBoard.noPeices(xPos,yPos-1)){
				Integer[] inFront2 = {xPos,yPos-2};
			    ret.add(inFront2);	
			}
			if(ChessBoard.noPeices(xPos,yPos-1)){
				ret.add(inFront);
			}
			
			//Check if the pawn can eat another piece.
			if(xPos-1>-1 && yPos-1>-1 ){
				if(!ChessBoard.noPeices(xPos-1,yPos-1)){
					Integer[] eats = {xPos-1,yPos-1};
					ret.add(eats);
				}
			}
			if(xPos+1<8 && yPos-1>-1){
				if(!ChessBoard.noPeices(xPos+1,yPos-2)){
					Integer[] eats = {xPos+1,yPos-1};
					ret.add(eats);
				}
			}
			
		}else{
			//essentially the same as before but is shifted directions.
			Integer[] inFront = {xPos,yPos+1};
			if(yPos==1  && ChessBoard.noPeices(xPos,yPos+2) && ChessBoard.noPeices(xPos,yPos+1)){
				Integer[] inFront2 = {xPos,yPos+2};
			    ret.add(inFront);
			    ret.add(inFront2);
				
			}
			if(ChessBoard.noPeices(xPos,yPos+1)){
				ret.add(inFront);
			}
			
			//Check for eating
			if(xPos-1>-1 && yPos+1<8){
				if(!ChessBoard.noPeices(xPos-1,yPos+1)){
					Integer[] eats = {xPos-1,yPos+1};
					ret.add(eats);
				}
			}
			if(xPos+1<8 && yPos+1<8){
				if(!ChessBoard.noPeices(xPos+1,yPos+1)){
					Integer[] eats = {xPos+1,yPos+1};
					ret.add(eats);
				}
			}
			

			
		}
		//return posToSquares(ret,true);
		ArrayList<Square> squareArray = new ArrayList<Square>();
		//check if own pieces are in the way. Turn all legal squares blue
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if(ChessBoard.noPeices(posX,posY) || white != ChessBoard.board[posX][posY].peice.white){
				ChessBoard.board[posX][posY].color = Color.BLUE;
				squareArray.add(ChessBoard.board[posX][posY]);
			}
		}
		return squareArray;
	}

}
