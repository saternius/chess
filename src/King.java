import java.awt.Color;
import java.util.ArrayList;


public class King extends Piece {
	
	public King(boolean w) {
		super(w,0,"King");
		// TODO Auto-generated constructor stub
	}
	
public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
			
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		
		//right
		int k=1;
		if(xPos+k<8 && ChessBoard.noPeices(xPos+k, yPos)){
			Integer[] pos = {xPos+k,yPos};
			ret.add(pos);
		}
		
		//check if can eat
		if(xPos+k<8 && !ChessBoard.noPeices(xPos+k, yPos) && white!=ChessBoard.board[xPos+k][yPos].peice.white){
			Integer[] pos = {xPos+k,yPos};
			ret.add(pos);
		}
		
		if(xPos-k>-1 && ChessBoard.noPeices(xPos-k, yPos)){
			Integer[] pos = {xPos-k,yPos};
			ret.add(pos);
		}
		
		//check if can eat
		if((xPos-k)>-1 && !ChessBoard.noPeices(xPos-k, yPos) && white!=ChessBoard.board[xPos-k][yPos].peice.white){
			Integer[] pos = {xPos-k,yPos};
			ret.add(pos);
		}

		if(yPos-k>-1 && ChessBoard.noPeices(xPos, yPos-k)){
			Integer[] pos = {xPos,yPos-k};
			ret.add(pos);
		}
		
		//check if can eat
		if(yPos-k>-1 && !ChessBoard.noPeices(xPos, yPos-k) && white!=ChessBoard.board[xPos][yPos-k].peice.white){
			Integer[] pos = {xPos,yPos-k};
			ret.add(pos);
		}
		
		if(yPos+k<8 && ChessBoard.noPeices(xPos, yPos+k)){
			Integer[] pos = {xPos,yPos+k};
			ret.add(pos);
		}
		
		//check if can eat
		if(yPos+k<8 && !ChessBoard.noPeices(xPos, yPos+k) && white!=ChessBoard.board[xPos][yPos+k].peice.white){
			Integer[] pos = {xPos,yPos+k};
			ret.add(pos);
		}
		
		
		

		if(xPos+k<8 && yPos+k<8 && ChessBoard.noPeices(xPos+k, yPos+k)){
			Integer[] pos = {xPos+k,yPos+k};
			ret.add(pos);
		}
		
		//check if can eat
		if(xPos+k<8 && yPos+k<8 && !ChessBoard.noPeices(xPos+k, yPos+k) && white!=ChessBoard.board[xPos+k][yPos+k].peice.white){
			Integer[] pos = {xPos+k,yPos+k};
			ret.add(pos);
		}
		

		if(xPos+k<8 && yPos-k>-1 && ChessBoard.noPeices(xPos+k, yPos-k)){
			Integer[] pos = {xPos+k,yPos-k};
			ret.add(pos);
		}
		
		//check if can eat
		if(xPos+k<8 && yPos-k>-1 && !ChessBoard.noPeices(xPos+k, yPos-k) && white!=ChessBoard.board[xPos+k][yPos-k].peice.white){
			Integer[] pos = {xPos+k,yPos-k};
			ret.add(pos);
		}
		

		if(xPos-k>-1 && yPos-k>-1 && ChessBoard.noPeices(xPos-k, yPos-k)){
			Integer[] pos = {xPos-k,yPos-k};
			ret.add(pos);
		}
		
		//check if can eat
		if(xPos-k>-1 && yPos-k>-1 && !ChessBoard.noPeices(xPos-k, yPos-k) && white!=ChessBoard.board[xPos-k][yPos-k].peice.white){
			Integer[] pos = {xPos-k,yPos-k};
			ret.add(pos);
		}
		
		if(xPos-k>-1 && yPos+k<8 && ChessBoard.noPeices(xPos-k, yPos+k)){
			Integer[] pos = {xPos-k,yPos+k};
			ret.add(pos);
		}
		
		//check if can eat
		if(xPos-k>-1 && yPos+k<8 && !ChessBoard.noPeices(xPos-k, yPos+k) && white!=ChessBoard.board[xPos-k][yPos+k].peice.white){
			Integer[] pos = {xPos-k,yPos+k};
			ret.add(pos);
		}
		
		//Check if move is not blocked by own pieces and flip all legal moves blue.
		ArrayList<Square> posMoves = new ArrayList<Square>();
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if((ChessBoard.board[posX][posY].peice == null || white != ChessBoard.board[posX][posY].peice.white) && ChessBoard.attacks.indexOf(ChessBoard.board[posX][posY])==-1){
				if(color){
					ChessBoard.board[posX][posY].color = Color.BLUE;
				}
				
				posMoves.add(ChessBoard.board[posX][posY]);
			}
		}
		

		//For all moves in posMoves, check if by moving would the king result in a check. If so the move is removed, and the square's color is restored.
		if(color){
			int ogX = x;
			int ogY = y;
			
			ChessBoard.board[x][y].peice = null;
			for(int i=0; i<posMoves.size();i++){
				
				x = posMoves.get(i).x;
				y = posMoves.get(i).y;
				Piece rep = ChessBoard.board[x][y].peice;
				ChessBoard.board[x][y].peice = this;
				System.out.println("Checking on x: "+x+" y:"+y);
				if(ChessBoard.isChecked()){
					System.out.println("Removing x: "+x+" y:"+y);
					posMoves.remove(i);
					ChessBoard.board[x][y].color = ChessBoard.board[x][y].ogColor;
				}
				ChessBoard.board[x][y].peice = rep;
			}
			x = ogX;
			y = ogY;
			ChessBoard.board[x][y].peice = this;
		}
		
		return posMoves;
	}


}
