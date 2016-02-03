import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;



public class ChessBoard {
	boolean printCoords = true;
	public static Square[][] board = new Square[8][8];
	static boolean turn = true;
	boolean peiceFocused = false;
	Square focusedSquare = null;
	public static int[] whitePeices = {
					  0,0,0,0,0,0,0,0,
			          0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
		              0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  1,1,1,1,1,1,1,1,
	                  4,2,3,5,6,3,2,4
					  };
	public static int[] blackPeices = {
					  4,2,3,6,5,3,2,4,
	                  1,1,1,1,1,1,1,1,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0
					  };
	
	public ChessBoard(){
		//assign color the board
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				String[] letters = {"A","B","C","D","E","F","G","H"};
				String name = letters[i]+(j+1);
				board[j][i] = new Square((j+1+(i%2))%2,j,i,whitePeices[(i*8+j)],blackPeices[(i*8+j)],name);		
			}
		}
	}
	
	//Draws the board
	public void draw(Graphics stage){
		
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					stage.setColor(sq.color);
					stage.fillRect(sq.x,sq.y,sq.dim,sq.dim);
					if(sq.peice != null){
						sq.peice.draw(stage,sq.x,sq.y);
					}
					if(printCoords){
						stage.setColor(Color.black);
						stage.drawString(sq.name, sq.x+5, sq.y+15);
					}
			}
		}
		
	}
	

	public class Square{
		Color color;
		Color ogColor;
		int x;
		int y;
		int dim = gameApplet.boardDim/8;
		String name;
		Peice peice = null;
		int xPos;
		int yPos;
		Square(int c,int xPos,int yPos,int whitePeiceType, int blackPeiceType, String n){
			this.xPos = xPos;
			this.yPos = yPos;
			name = xPos+":"+yPos;
			if(c!=0){
				color = Color.decode("#006633");
				ogColor = Color.decode("#006633");
			}else{
				color = Color.white;
				ogColor = Color.white;
			}
			x = xPos*dim;
			y = yPos*dim;
			
			int peiceType = whitePeiceType | blackPeiceType ;
			boolean white = whitePeiceType !=0;
			//System.out.println("Type: "+peiceType);
			switch(peiceType){
				case 0:
					break;
				case 1:
					peice = new Pawn(white);
					break;
				case 2:
					peice = new Knight(white);
					break;
				case 3:
					peice = new Bishop(white);
					break;
				case 4:
					peice = new Rook(white);
					break;
				case 5:
					peice = new Queen(white);
					break;
				case 6:
					peice = new King(white);
					break;
			
			}
		}
		public boolean checkClicked(int mouseX, int mouseY) {
			boolean hit = mouseX>x && mouseX < x+dim && mouseY>y && mouseY<y+dim;
			if(hit && peice!=null && (turn == peice.white)){
				//in bounds
				unfocusAllSquares();
				color = Color.RED;
				peiceFocused = peice.posMove(xPos, yPos);
				if(peiceFocused){
					focusedSquare = this;
				}
			}else if (hit && peice == null && focusedSquare!=null && color==Color.BLUE){
				peice = focusedSquare.peice;
				focusedSquare.peice = null;
				focusedSquare = null;
				unfocusAllSquares();
				turn = !turn;
			}
			return false;
		}
		public void ogColor() {
			color = ogColor;
			
		}
		
	}

	public void unfocusAllSquares(){
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				board[j][i].ogColor();
			}
		}
	}

	public static void clicked(int x, int y) {
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					sq.checkClicked(x,y);
			}
		}
	}
	
}
