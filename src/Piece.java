import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

///Handles all logic and operations performed by a piece. 
public class Piece {
	boolean white; ///< true if white
	int x;///< x position
	int y;///< y position
	String type;///< name of piece
	ChessBoard cb;
	/*!
	 * Initializes the piece,
	 * @param[in] w 	true if the piece is white.
	 * @param[in] xSft	Shift in the piece sprite.
	 * @param[in] type	The number of bytes to copy
	 * @param[in] x		x Coordinate.
	 * @param[in] y		y Coordinate.
	 */
	public Piece(ChessBoard cb ,boolean w, int xSft, String type, int x, int y){
		this.cb = cb;
		this.x = x;
		this.y = y;
		white = w;
		xS = xSft;
		this.type = type;
	}
	
	public Piece(ChessBoard cb, Piece p) {
		this.cb = cb;
		this.x = p.x;
		this.y = p.y;
		white = p.white;
		xS = p.xS;
		this.type = p.type;
	}

	/// Returns an array of squares it can move to, and toggles all those squares blue.
	public ArrayList<Square> posMove() {
		return new ArrayList<Square>();
	}
	///Returns an array of squares in which the piece is threatening (it can eat).
	public ArrayList<Square> threatMap() {
		return new ArrayList<Square>();
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
			if(cb.noPeices(posX,posY) || white != cb.board[posX][posY].peice.white){
				posMoves.add(cb.board[posX][posY]);
			}
		}
		
		if(color){
			posMoves = checkForChecksandPinned(posMoves);
			for(int i=0; i<posMoves.size();i++){
				posMoves.get(i).color = Color.BLUE;
			}
		}
		return posMoves;
	}
	
	
	
	///Removes all the squares which are pinned/do not resolve a check
	public ArrayList<Square> checkForChecksandPinned(ArrayList<Square> pos){


			for(int i=0; i<pos.size();i++){
				//Make hypothetical
				ChessBoard cb = new ChessBoard(this.cb);
				cb.updateThreats();
				
				int px =  pos.get(i).x;
				int py =  pos.get(i).y;

				Piece captPeice = cb.board[px][py].peice;
				cb.board[px][py].peice = cb.board[x][y].peice;
				cb.board[x][y].peice = null;
				cb.updateThreats();
				//Does it resolve check? if not remove it
				if(cb.isChecked()){
					pos.set(i, null);
				}
			}
		pos.removeAll(Collections.singleton(null));
		return pos;
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
		while(x+k<8 && y+k<8 && cb.noPeices(x+k, y+k)){
			Integer[] pos = {x+k,y+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x+k<8 && y+k<8 && !cb.noPeices(x+k, y+k) && white!=cb.board[x+k][y+k].peice.white){
			Integer[] pos = {x+k,y+k};
			ret.add(pos);
		}
		
		//diag bottom-right
		k=1;
		while(x+k<8 && y-k>-1 && cb.noPeices(x+k, y-k)){
			Integer[] pos = {x+k,y-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x+k<8 && y-k>-1 && !cb.noPeices(x+k, y-k) && white!=cb.board[x+k][y-k].peice.white){
			Integer[] pos = {x+k,y-k};
			ret.add(pos);
		}
		
		//diag bottom-left
		k=1;
		while(x-k>-1 && y-k>-1 && cb.noPeices(x-k, y-k)){
			Integer[] pos = {x-k,y-k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x-k>-1 && y-k>-1 && !cb.noPeices(x-k, y-k) && white!=cb.board[x-k][y-k].peice.white){
			Integer[] pos = {x-k,y-k};
			ret.add(pos);
		}
		
		//diag top-left
		k=1;
		while(x-k>-1 && y+k<8 && cb.noPeices(x-k, y+k)){
			Integer[] pos = {x-k,y+k};
			ret.add(pos);
			k++;
		}
		
		//check if can eat the blockage
		if(x-k>-1 && y+k<8 && !cb.noPeices(x-k, y+k) && white!=cb.board[x-k][y+k].peice.white){
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
			while(x+k<8 && cb.noPeices(x+k, y)){
				Integer[] pos = {x+k,y};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(x+k<8 && !cb.noPeices(x+k, y) && white!=cb.board[x+k][y].peice.white){
				Integer[] pos = {x+k,y};
				ret.add(pos);
			}
			
			//left
			k=1;
			while(x-k>-1 && cb.noPeices(x-k, y)){
				Integer[] pos = {x-k,y};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if((x-k)>-1 && !cb.noPeices(x-k, y) && white!=cb.board[x-k][y].peice.white){
				Integer[] pos = {x-k,y};
				ret.add(pos);
			}
			
			//Up
			k=1;
			while(y-k>-1 && cb.noPeices(x, y-k)){
				Integer[] pos = {x,y-k};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(y-k>-1 && !cb.noPeices(x, y-k) && white!=cb.board[x][y-k].peice.white){
				Integer[] pos = {x,y-k};
				ret.add(pos);
			}
			
			//Down
			k=1;
			while(y+k<8 && cb.noPeices(x, y+k)){
				Integer[] pos = {x,y+k};
				ret.add(pos);
				k++;
			}
			
			//check if can eat the blockage
			if(y+k<8 && !cb.noPeices(x, y+k) && white!=cb.board[x][y+k].peice.white){
				Integer[] pos = {x,y+k};
				ret.add(pos);
			}	
			return ret;
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
	
	public boolean inBounds(int xPos,int yPos){
		return xPos>-1 && yPos>-1 && xPos<8 && yPos<8; 
	}
}
