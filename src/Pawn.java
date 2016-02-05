import java.awt.Color;
import java.util.ArrayList;



public class Pawn extends Piece {
	public Pawn(boolean w) {
		super(w,1650,"Pawn");
	}
	
	@Override
	public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
		//There are two arrays because pawns don't attack in the direction they move.
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();
		ArrayList<Square> threats = new ArrayList<Square>();
		
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
				threats.add(ChessBoard.board[xPos-1][yPos-1]);
				if(!ChessBoard.noPeices(xPos-1,yPos-1)){
					Integer[] eats = {xPos-1,yPos-1};
					ret.add(eats);
				}
			}
			if(xPos+1<8 && yPos-1>-1){
				threats.add(ChessBoard.board[xPos+1][yPos-1]);
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
				threats.add(ChessBoard.board[xPos-1][yPos+1]);
				if(!ChessBoard.noPeices(xPos-1,yPos+1)){
					Integer[] eats = {xPos-1,yPos+1};
					ret.add(eats);
				}
			}
			if(xPos+1<8 && yPos+1<8){
				threats.add(ChessBoard.board[xPos+1][yPos+1]);
				if(!ChessBoard.noPeices(xPos+1,yPos+1)){
					Integer[] eats = {xPos+1,yPos+1};
					ret.add(eats);
				}
				
			}
			
		}
		
		//check if own pieces are in the way. Turn all legal squares blue
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if(ChessBoard.noPeices(posX,posY) || white != ChessBoard.board[posX][posY].peice.white){
				if(color){
					ChessBoard.board[posX][posY].color = Color.BLUE;
				}
			}
		}
		return threats;
	}

}
