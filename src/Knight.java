import java.awt.Color;
import java.util.ArrayList;


public class Knight extends Peice {

	public Knight(boolean w) {
		super(w,990);
	}
	
	@Override
	public boolean posMove(int xPos, int yPos) {
	
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		boolean high = false;
	
		Integer[] pos1 = {xPos-1,yPos-2};
		Integer[] pos2 = {xPos+1,yPos-2};	
		Integer[] pos3 = {xPos-2,yPos-1};	
		Integer[] pos4 = {xPos+2,yPos-1};	
		Integer[] pos5 = {xPos+2,yPos+1};	
		Integer[] pos6 = {xPos-2,yPos+1};	
		Integer[] pos7 = {xPos+1,yPos+2};	
		Integer[] pos8 = {xPos-1,yPos+2};	
		Integer[][] poses = {pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8};
		
		for(int i=0; i<poses.length; i++){
			int xP = poses[i][0];
			int yP = poses[i][1];
			if(xP>-1 && xP<8 && yP>-1 && yP<8){
				ret.add(poses[i]);
			}
		}

	
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			if(ChessBoard.board[posX][posY].peice == null || white != ChessBoard.board[posX][posY].peice.white){
				ChessBoard.board[posX][posY].color = Color.BLUE;
				high = true;
			}
		}
		return high;
	}

}
