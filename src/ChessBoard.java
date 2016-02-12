import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/// The ChessBoard class keeps track of all the squares on the board, along with who's turn it is and various game rules.
/** 0: no Peice\n
 	1: Pawn\n
 	2: Knight\n
 	3: Bishop\n
 	4: Rook\n
 	5: Queen\n
 	6: King\n
 	7: BishopKnight\n
 	8: RookKnight\n
*/ 
public class ChessBoard {
	public static Square[][] board = new Square[8][8]; ///< All the squares on the board. Organized by x,y coordinates: board[x][y]
	static boolean wTurn = true; ///<true if it is white's turn 
	public static Piece wKing; ///<the white King
	public static Piece bKing; ///<the black King
	public static ArrayList<Square> attacks = new ArrayList<Square>(); ///<List of squares in which the opponent is threatening
	public static int[] wStartPos = {
					  0,0,0,0,0,0,0,0,
			          0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
		              0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  1,1,8,1,1,7,1,1,
	                  4,2,3,5,6,3,2,4
					  }; ///<Starting white pieces
	public static int[] bStartPos = { 
					  4,2,3,5,6,3,2,4,
	                  1,1,7,1,1,8,1,1,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0
					  };///<Starting black pieces
	
	///Initializes all the squares and places the pieces on the squares corresponding to bStartPos and wStartPos
	public ChessBoard(){
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				String[] letters = {"A","B","C","D","E","F","G","H"};
				String name = letters[j]+(8-i);
				Piece peice = getPiece(wStartPos[(i*8+j)],bStartPos[(i*8+j)],j,i);
				board[j][i] = new Square(j,i,peice,name);
			}
		}
		
	}
	
	///Takes accepts the value from both starting positions and returns a piece based of the value.
	public Piece getPiece(int whitePieceType, int blackPieceType,int x, int y){
		int peiceType = whitePieceType | blackPieceType ;
		boolean white = whitePieceType !=0;
		Piece peice = null;
		switch(peiceType){
			case 0:
				break;
			case 1:
				peice = new Pawn(white,x,y);
				break;
			case 2:
				peice = new Knight(white,x,y);
				break;
			case 3:
				peice = new Bishop(white,x,y);
				break;
			case 4:
				peice = new Rook(white,x,y);
				break;
			case 5:
				peice = new Queen(white,x,y);
				break;
			case 6:
				peice = new King(white,x,y);
				if(white){
					wKing = peice;
				}else{
					bKing = peice;
				}
				break;
			case 7:
				peice = new RookKnight(white,x,y);
				break;
			case 8:
				peice = new BishopKnight(white,x,y);
				break;
		
		}
		return peice;

	}
	
	///Returns true if there is noPeices in the specified coords
	public static boolean noPeices(int x, int y){
		return board[x][y].peice==null;
	}
	
	///Returns true if King is in checked position
	public static boolean isChecked(){
		updateThreats();
		Piece king = wKing;
		String player = "white";
		if(!wTurn){
			player = "black";
			king = bKing;
		}
		if((attacks.indexOf(board[king.x][king.y])>-1)){
			System.out.println(player+" is checked");
		}
		
		return (attacks.indexOf(board[king.x][king.y])>-1);
		
	}
	
	///Return true if king is mated
	public static boolean isMated(){
		return isChecked() && (( wTurn && (wKing.posMove().size()==0)) || ( !wTurn && (bKing.posMove().size()==0))  );
	}
	
	///Updates the attacks array
	public static void updateThreats(){
		attacks = new ArrayList<Square>();
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				Square sq = board[j][i];
				if(sq.peice != null){
					
					ArrayList<Square> threats = sq.peice.threatMap();
					if(wTurn != sq.peice.white){
						for(int k=0; k<threats.size();k++){
							attacks.add(threats.get(k));
						}
					
					}
				}
			}
		}
	}
	
	
	//-----------------Anything below is beyond the first Assignment-------------------
	
	///Alerts a square that the board has been clicked
	public static void clicked(int x, int y) {
		//System.out.println("clicked");
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					sq.checkClicked(x,y);
			}
		}
	}
	
	///Clears all the squares of any highlighted colors
	public static void unfocusAllSquares(){
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				board[j][i].restoreColor();
			}
		}
	}
	
	///Function that draws the ChessBoard
	public void draw(Graphics stage){
		boolean printCoords = true;	
		boolean printThreats = false;
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					stage.setColor(sq.color);
					stage.fillRect(sq.xCoord,sq.yCoord,sq.dim,sq.dim);
					stage.setColor(Color.black);
					stage.drawRect(sq.xCoord,sq.yCoord,sq.dim,sq.dim);
					if(sq.peice != null){
						sq.peice.draw(stage,sq.xCoord,sq.yCoord);
					}
					if(printCoords){
						stage.setColor(Color.black);
						stage.drawString(sq.name, sq.xCoord+5, sq.yCoord+15);
					}
			}
		}
		
			if(printThreats){
				stage.setColor(Color.ORANGE);
				for(int i=0; i<attacks.size();i++){
					Square sq = attacks.get(i);
					stage.fillRect(sq.xCoord,sq.yCoord,sq.dim,sq.dim);
				}
			}
		
	}
	
	
	
}






