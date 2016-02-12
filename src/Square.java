import java.awt.Color;
///Handles all the information contained within a square.
public class Square{
		static Square focusedSquare = null; ///< Square that is currently selected
		String name;///< name of square, ex: g7
		Piece peice = null; ///< piece on this square
		int x;	///< square x position
		int y;	///< square y position
		
		//Graphical properties.
		Color color;	///< color of the square
		Color ogColor;	///< original color of the square white/green
		int xCoord;		///< x coordinate based off the screenSize
		int yCoord;		///< y coordinate based off the screenSize
		int dim = gameApplet.boardDim/8;///< square width and height.
		
		
		///Initializes square
		Square(int x,int y,Piece p, String n){
			this.peice = p;
			this.x = x;
			this.y = y;
			name = n;
			initGraphics();
		}
		
		///Change the piece on this square
		private void changePiece(boolean turn) {
			ChessBoard.unfocusAllSquares();
			peice = focusedSquare.peice;
			if(peice!=null){
				peice.x = this.x;
				peice.y = this.y;
			}
			focusedSquare.peice = null;
			focusedSquare = null;
			ChessBoard.wTurn = !turn;
			
		}
		
		
		//---------------Anything below is beyond the first Assignment---------------------
		
		///Handles if this square is clicked on
		public boolean checkClicked(int mouseX, int mouseY) {
			boolean hit = mouseX>xCoord && mouseX < xCoord+dim && mouseY>yCoord && mouseY<yCoord+dim;
			boolean turn = ChessBoard.wTurn;
	
			if(hit && peice!=null && (turn == peice.white)){
				//clicked to select a piece
				ChessBoard.unfocusAllSquares();
				color = Color.RED;
				if(peice.posMove()!=null){
					focusedSquare = this;
				}
				
			}else if (hit && peice == null && focusedSquare!=null && color==Color.BLUE){  
				//clicked on an empty square that the focused piece can validly move to
				changePiece(turn);
			}else if(hit && focusedSquare!=null && focusedSquare.peice!=null && peice!=null && (turn != peice.white) && color==Color.BLUE){
				//clicked on an enemy to eat
				changePiece(turn);
			}
			//ChessBoard.isChecked();
			ChessBoard.isMated();
			return false;
		}

		///Initializes the graphical properties of the square.
		private void initGraphics() {
			if((x+1+(y%2))%2 == 0){
				color = Color.decode("#006633");
				ogColor = Color.decode("#006633");
			}else{
				color = Color.white;
				ogColor = Color.white;
			}
			xCoord = x*dim;
			yCoord = y*dim;
			
		}
		///Restores the square to it's original color of green/white
		public void restoreColor() {
			color = ogColor;
		}
		
	}