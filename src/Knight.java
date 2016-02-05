import java.awt.Color;
import java.util.ArrayList;


public class Knight extends Piece {

	public Knight(boolean w) {
		super(w,990,"Knight");
	}
	
	@Override
	public ArrayList<Square> posMove(int xPos, int yPos, boolean color) {
	
		//get all 8 positions the knight could possibly go to.
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		Integer[] pos1 = {xPos-1,yPos-2};
		Integer[] pos2 = {xPos+1,yPos-2};	
		Integer[] pos3 = {xPos-2,yPos-1};	
		Integer[] pos4 = {xPos+2,yPos-1};	
		Integer[] pos5 = {xPos+2,yPos+1};	
		Integer[] pos6 = {xPos-2,yPos+1};	
		Integer[] pos7 = {xPos+1,yPos+2};	
		Integer[] pos8 = {xPos-1,yPos+2};	
		Integer[][] poses = {pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8};
		
		//if a position is out of bounds remove it
		for(int i=0; i<poses.length; i++){
			int xP = poses[i][0];
			int yP = poses[i][1];
			if(xP>-1 && xP<8 && yP>-1 && yP<8){
				ret.add(poses[i]);
			}
		}

		//if your piece is not in the way then mark the square for being legal. Turn all legal squares blue
		ArrayList<Square> posMoves = new ArrayList<Square>();
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if(ChessBoard.board[posX][posY].peice == null || white != ChessBoard.board[posX][posY].peice.white){
				if(color){
					ChessBoard.board[posX][posY].color = Color.BLUE;
				}
				posMoves.add(ChessBoard.board[posX][posY]);
			}
		}
		return posMoves;
	}

}
