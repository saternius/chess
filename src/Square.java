import java.awt.Color;

public class Square{
		static boolean peiceFocused = false;	/*true if a piece has been clicked on*/
		static Square focusedSquare = null;     /*Square that is currently selected*/
		Color color;
		Color ogColor;
		int x;
		int y;
		int dim = gameApplet.boardDim/8;
		String name;
		Piece peice = null;
		int xPos;
		int yPos;
		Square(int xPos,int yPos,Piece p, String n,boolean c){
			this.peice = p;
			this.xPos = xPos;
			this.yPos = yPos;
			name = n;
			if(c){
				color = Color.decode("#006633");
				ogColor = Color.decode("#006633");
			}else{
				color = Color.white;
				ogColor = Color.white;
			}
			x = xPos*dim;
			y = yPos*dim;
			
		}
		public boolean checkClicked(int mouseX, int mouseY) {
			boolean hit = mouseX>x && mouseX < x+dim && mouseY>y && mouseY<y+dim;
			boolean turn = ChessBoard.wTurn;
			
			if(hit && peice!=null && (turn == peice.white)){
				//clicked on your peice
				ChessBoard.unfocusAllSquares();
				color = Color.RED;
				
				peiceFocused = peice.posMove(xPos, yPos, true)!=null;
				if(peiceFocused){
					focusedSquare = this;
					
				}
			}else if (hit && peice == null && focusedSquare!=null && color==Color.BLUE){
				System.out.println("esse");
				//clicked on a square with no enemy to move your peice
				peice = focusedSquare.peice;
				focusedSquare.peice = null;
				focusedSquare = null;
				ChessBoard.unfocusAllSquares();
				ChessBoard.wTurn = !turn;
			}else if(hit && focusedSquare!=null && focusedSquare.peice!=null && peice!=null && (turn != peice.white) && color==Color.BLUE){
				//clicked on a square with an enemy peice
				System.out.println("EAT");
				peice = focusedSquare.peice;
				focusedSquare.peice = null;
				focusedSquare = null;
				ChessBoard.unfocusAllSquares();
				ChessBoard.wTurn = !turn;
			}
		
			ChessBoard.isChecked();
			if(peice!=null){
				peice.x = xPos;
				peice.y = yPos;
			}
			
			return false;
		}
		public void ogColor() {
			color = ogColor;
		}
		
	}