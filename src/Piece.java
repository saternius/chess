import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

///Handles all logic and operations performed by a peice. 
public class Piece {
	boolean white;
	int x;
	int y;
	String type;
	/*!
	 * Initializes the piece,
	 * @param[in] w 	true if the piece is white.
	 * @param[in] xSft	Shift in the piece sprite.
	 * @param[in] type	The number of bytes to copy
	 * @param[in] x		x Coordinate.
	 * @param[in] y		y Coordinate.
	 */
	public Piece(boolean w, int xSft, String type, int x, int y){
		this.x = x;
		this.y = y;
		white = w;
		xS = xSft;
		this.type = type;
	}
	
	/// Returns an array of squares it can move to, and toggles all those squares blue.
	public ArrayList<Square> posMove() {
		return new ArrayList<Square>();
	}
	///Returns an array of squares in which the piece is threatening (it can eat).
	public ArrayList<Square> threatMap() {
		return new ArrayList<Square>();
	}
	
	///Returns true if the piece is pinned. (If you move it their king goes in check)
	public boolean isPinned(){
		if(ChessBoard.isChecked()){
			return false;
		}
		
		ChessBoard.board[x][y].peice = null;
		ChessBoard.updateThreats();
		boolean ret = ChessBoard.isChecked();
		ChessBoard.board[x][y].peice = this;
		ChessBoard.updateThreats();
		return ret;
	}
	
	
	//---------------------------------MOVEMENTS--------------------------------------//
	///returns all 8 positions the knight could possibly go to.
	public ArrayList<Integer[]> getClockMovement(){
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  
		Integer[] pos1 = {x-1,y-2};
		Integer[] pos2 = {x+1,y-2};	
		Integer[] pos3 = {x-2,y-1};	
		Integer[] pos4 = {x+2,y-1};	
		Integer[] pos5 = {x+2,y+1};	
		Integer[] pos6 = {x-2,y+1};	
		Integer[] pos7 = {x+1,y+2};	
		Integer[] pos8 = {x-1,y+2};	
		Integer[][] poses = {pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8};
		for(int i=0; i<poses.length; i++){
			int xP = poses[i][0];
			int yP = poses[i][1];
			if(xP>-1 && xP<8 && yP>-1 && yP<8){
				ret.add(poses[i]);
			}
		}
		return ret;
	}
	
	///Returns diagonal movement positions
	public ArrayList<Integer[]> getDiagMovement(){
		ArrayList<Integer[]> ret = new ArrayList<Integer[]>();  

		//diag top-right
		int k=1;
		while(x+k<8 && y+k<8 && ChessBoard.noPeices(x+k, y+k)){
			Integer[] pos = {x+k,y+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x+k<8 && y+k<8 && !ChessBoard.noPeices(x+k, y+k) && white!=ChessBoard.board[x+k][y+k].peice.white){
			Integer[] pos = {x+k,y+k};
			ret.add(pos);
		}
		
		//diag bottom-right
		k=1;
		while(x+k<8 && y-k>-1 && ChessBoard.noPeices(x+k, y-k)){
			Integer[] pos = {x+k,y-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x+k<8 && y-k>-1 && !ChessBoard.noPeices(x+k, y-k) && white!=ChessBoard.board[x+k][y-k].peice.white){
			Integer[] pos = {x+k,y-k};
			ret.add(pos);
		}
		
		//diag bottom-left
		k=1;
		while(x-k>-1 && y-k>-1 && ChessBoard.noPeices(x-k, y-k)){
			Integer[] pos = {x-k,y-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x-k>-1 && y-k>-1 && !ChessBoard.noPeices(x-k, y-k) && white!=ChessBoard.board[x-k][y-k].peice.white){
			Integer[] pos = {x-k,y-k};
			ret.add(pos);
		}
		
		//diag top-left
		k=1;
		while(x-k>-1 && y+k<8 && ChessBoard.noPeices(x-k, y+k)){
			Integer[] pos = {x-k,y+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x-k>-1 && y+k<8 && !ChessBoard.noPeices(x-k, y+k) && white!=ChessBoard.board[x-k][y+k].peice.white){
			Integer[] pos = {x-k,y+k};
			ret.add(pos);
		}
		return ret;
	}
	
		///Returns Rook Movements
		public ArrayList<Integer[]> getRookMovement(){
			ArrayList<Integer[]> ret = new ArrayList<Integer[]>(); 
			//right
			int k=1;
			while(x+k<8 && ChessBoard.noPeices(x+k, y)){
				Integer[] pos = {x+k,y};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(x+k<8 && !ChessBoard.noPeices(x+k, y) && white!=ChessBoard.board[x+k][y].peice.white){
				Integer[] pos = {x+k,y};
				ret.add(pos);
			}
			
			//left
			k=1;
			while(x-k>-1 && ChessBoard.noPeices(x-k, y)){
				Integer[] pos = {x-k,y};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if((x-k)>-1 && !ChessBoard.noPeices(x-k, y) && white!=ChessBoard.board[x-k][y].peice.white){
				Integer[] pos = {x-k,y};
				ret.add(pos);
			}
			
			//Up
			k=1;
			while(y-k>-1 && ChessBoard.noPeices(x, y-k)){
				Integer[] pos = {x,y-k};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(y-k>-1 && !ChessBoard.noPeices(x, y-k) && white!=ChessBoard.board[x][y-k].peice.white){
				Integer[] pos = {x,y-k};
				ret.add(pos);
			}
			
			//Down
			k=1;
			while(y+k<8 && ChessBoard.noPeices(x, y+k)){
				Integer[] pos = {x,y+k};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(y+k<8 && !ChessBoard.noPeices(x, y+k) && white!=ChessBoard.board[x][y+k].peice.white){
				Integer[] pos = {x,y+k};
				ret.add(pos);
			}	
			return ret;
		}
		
	///Converts the integer array to square array, based of the square's coordinates.
	/*!
	 * @param[in] ret 	list of x and y positions
	 * @param[in] color	whether to color the squares or not
	 */
	public ArrayList<Square> posToSquares(ArrayList<Integer[]> ret, boolean color){
		
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
	
	//---------------------------------GRAPHICS--------------------------------------//
	int xS;
	///Draws the piece.
	/*!
	 * Initializes the piece,
	 * @param[in] stage Graphics object to draw to.
	 * @param[in] x		x Coordinate.
	 * @param[in] y		y Coordinate.
	 */
	public void draw(Graphics stage, int x, int y){
		int yShift = 0;
		if(!white){
			yShift = 350;
		}
		stage.drawImage(gameApplet.peicesPng, x, y, gameApplet.boardDim/8+x, gameApplet.boardDim/8+y, 0+xS, 0+yShift, 350+xS, 350+yShift, null);
	
	}
}
