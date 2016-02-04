import java.awt.Color;
import java.util.ArrayList;


public class Rook extends Piece {

	public Rook(boolean w) {
		super(w,990+330);
	}


public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
		
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>(); 
		boolean high = false;

		//right
		int k=1;
		while(xPos+k<8 && ChessBoard.noPeices(xPos+k, yPos)){
			Integer[] pos = {xPos+k,yPos};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(xPos+k<8 && !ChessBoard.noPeices(xPos+k, yPos) && white!=ChessBoard.board[xPos+k][yPos].peice.white){
			Integer[] pos = {xPos+k,yPos};
			ret.add(pos);
		}
		
		//left
		k=1;
		while(xPos-k>-1 && ChessBoard.noPeices(xPos-k, yPos)){
			Integer[] pos = {xPos-k,yPos};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if((xPos-k)>-1 && !ChessBoard.noPeices(xPos-k, yPos) && white!=ChessBoard.board[xPos-k][yPos].peice.white){
			Integer[] pos = {xPos-k,yPos};
			ret.add(pos);
		}
		
		//Up
		k=1;
		while(yPos-k>-1 && ChessBoard.noPeices(xPos, yPos-k)){
			Integer[] pos = {xPos,yPos-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(yPos-k>-1 && !ChessBoard.noPeices(xPos, yPos-k) && white!=ChessBoard.board[xPos][yPos-k].peice.white){
			Integer[] pos = {xPos,yPos-k};
			ret.add(pos);
		}
		
		//Down
		k=1;
		while(yPos+k<8 && ChessBoard.noPeices(xPos, yPos+k)){
			Integer[] pos = {xPos,yPos+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(yPos+k<8 && !ChessBoard.noPeices(xPos, yPos+k) && white!=ChessBoard.board[xPos][yPos+k].peice.white){
			Integer[] pos = {xPos,yPos+k};
			ret.add(pos);
		}	
		ArrayList<Square> posMoves = new ArrayList<Square>();
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if(ChessBoard.board[posX][posY].peice == null || white != ChessBoard.board[posX][posY].peice.white){
				if(color){
					ChessBoard.board[posX][posY].color = Color.BLUE;
				}
				high = true;
				posMoves.add(ChessBoard.board[posX][posY]);
			}
		}
		return posMoves;
	}

	
}
