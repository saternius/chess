import java.awt.Color;
import java.awt.Graphics;
///Handles all the information contained within a square.
public class Square{
		String name;///< name of square, ex: g7
		Piece peice = null; ///< piece on this square
		int x;	///< square x position
		int y;	///< square y position

		//Graphical properties.
		Color color;	///< color of the square
		Color ogColor;	///< original color of the square white/green
		int xCoord;		///< x coordinate based off the screenSize
		int yCoord;		///< y coordinate based off the screenSize
		private int dim = gameApplet.boardDim/8;///< square width and height.
		private ChessBoard cb;
		
		///Initializes square
		Square(ChessBoard cb,int x,int y,Piece p, String n){
			this.cb = cb;
			this.peice = p;
			this.x = x;
			this.y = y;
			name = n;
			initGraphics();
		}
		
		///Change the piece on this square
		private void changePiece(boolean turn) {
		
			Square focusedSquare = cb.focusedSquare;
			cb.unfocusAllSquares();
			peice = focusedSquare.peice;
			if(peice!=null){
				peice.x = this.x;
				peice.y = this.y;
			}
			focusedSquare.peice = null;
			focusedSquare = null;
			cb.wTurn = !turn;
			if(cb.isMated()){
				System.out.println("is checked");
			}
			
			GameCanvas.saveState();
			
		}
		
		
		//---------------Anything below is beyond the first Assignment---------------------
		
		///Handles if this square is clicked on
		public boolean checkClicked(int mouseX, int mouseY) {
			Square focusedSquare = cb.focusedSquare;
			boolean hit = mouseX>xCoord && mouseX < xCoord+dim && mouseY>yCoord && mouseY<yCoord+dim;
			boolean turn = cb.wTurn;
			if(hit && peice!=null && (turn == peice.white)){
				//clicked to select a piece
				cb.unfocusAllSquares();
				color = Color.RED;
				if(peice.posMove()!=null){
					cb.focusedSquare = this;
				}
				
			}else if (hit && peice == null && focusedSquare!=null && color==Color.BLUE){
				//clicked on an empty square that the focused piece can validly move to
				changePiece(turn);
			}else if(hit && focusedSquare!=null && focusedSquare.peice!=null && peice!=null && (turn != peice.white) && color==Color.BLUE){
				//clicked on an enemy to eat
				changePiece(turn);
			}
		
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

		public void draw(Graphics stage) {
			stage.setColor(color);
			stage.fillRect(xCoord,yCoord,dim,dim);
			stage.setColor(Color.black);
			stage.drawRect(xCoord,yCoord,dim,dim);
			if(peice != null){
				peice.draw(stage,xCoord,yCoord);
			}

			stage.setColor(Color.black);
			stage.drawString(name, xCoord+5, yCoord+15);
			
		}
		
	}