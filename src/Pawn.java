import java.awt.Color;
import java.util.ArrayList;



public class Pawn extends Peice {
	public Pawn(boolean w) {
		super(w,1650);
	}
	
	@Override
	public boolean posMove(int xPos, int yPos) {
	
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		boolean high = false;
		if(white){
			Integer[] inFront = {xPos,yPos-1};
			if(yPos==6){
				Integer[] inFront2 = {xPos,yPos-2};
			    ret.add(inFront);
			    ret.add(inFront2);
				
			}else{
				ret.add(inFront);
			
			}
		}else{
			Integer[] inFront = {xPos,yPos+1};
			if(yPos==1){
				Integer[] inFront2 = {xPos,yPos+2};
			    ret.add(inFront);
			    ret.add(inFront2);
				
			}else{
				ret.add(inFront);
			
			}
		}
		
		System.out.println(ret);
		for(int i=0;i<ret.size();i++){
			Integer[] posMove = ret.get(i);
			int posX = posMove[0];
			int posY = posMove[1];
			//System.out.println("posx: "+posX+"  posY:"+posY+ "white: "+ChessBoard.whitePeices[(posY*8+posX)]+ "black: "+ChessBoard.blackPeices[(posY*8+posX)]);
			if(ChessBoard.board[posX][posY].peice == null){
				ChessBoard.board[posX][posY].color = Color.BLUE;
				high = true;
			}
		}
		return high;
	}

}
