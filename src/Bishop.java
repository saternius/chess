import java.awt.Color;
import java.util.ArrayList;


public class Bishop extends Piece {

	public Bishop(boolean w) {
		super(w,660);
	}
	
	public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
		
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		boolean high = false;

		//diag top-right
		int k=1;
		while(xPos+k<8 && yPos+k<8 && ChessBoard.noPeices(xPos+k, yPos+k)){
			Integer[] pos = {xPos+k,yPos+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(xPos+k<8 && yPos+k<8 && !ChessBoard.noPeices(xPos+k, yPos+k) && white!=ChessBoard.board[xPos+k][yPos+k].peice.white){
			Integer[] pos = {xPos+k,yPos+k};
			ret.add(pos);
		}
		
		//diag bottom-right
		k=1;
		while(xPos+k<8 && yPos-k>-1 && ChessBoard.noPeices(xPos+k, yPos-k)){
			Integer[] pos = {xPos+k,yPos-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(xPos+k<8 && yPos-k>-1 && !ChessBoard.noPeices(xPos+k, yPos-k) && white!=ChessBoard.board[xPos+k][yPos-k].peice.white){
			Integer[] pos = {xPos+k,yPos-k};
			ret.add(pos);
		}
		
		//diag bottom-left
		k=1;
		while(xPos-k>-1 && yPos-k>-1 && ChessBoard.noPeices(xPos-k, yPos-k)){
			Integer[] pos = {xPos-k,yPos-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(xPos-k>-1 && yPos-k>-1 && !ChessBoard.noPeices(xPos-k, yPos-k) && white!=ChessBoard.board[xPos-k][yPos-k].peice.white){
			Integer[] pos = {xPos-k,yPos-k};
			ret.add(pos);
		}
		
		//diag top-left
		k=1;
		while(xPos-k>-1 && yPos+k<8 && ChessBoard.noPeices(xPos-k, yPos+k)){
			Integer[] pos = {xPos-k,yPos+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat
		if(xPos-k>-1 && yPos+k<8 && !ChessBoard.noPeices(xPos-k, yPos+k) && white!=ChessBoard.board[xPos-k][yPos+k].peice.white){
			Integer[] pos = {xPos-k,yPos+k};
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
