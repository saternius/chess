import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

// The ChessBoard class is responsible for..
//
//
public class ChessBoard {
	public static Square[][] board = new Square[8][8];
	static boolean wTurn = true; 
	public static Piece wKing;
	public static Piece bKing;
	public static ArrayList<Square> attacks = new ArrayList<Square>(); /*List of squares in which the opponent is threatening*/
	public static int[] wStartPos = { //Starting white pieces
					  0,0,0,0,0,0,0,0,
			          0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
		              0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  1,1,1,1,1,1,1,1,
	                  4,2,3,5,6,3,2,4
					  };
	public static int[] bStartPos = { //Starting black pieces
					  4,2,3,5,6,3,2,4,
	                  1,1,1,1,1,1,1,1,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0
					  };
	
	public ChessBoard(){
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				String[] letters = {"A","B","C","D","E","F","G","H"};
				String name = letters[j]+(8-i);
				Piece peice = getPiece(wStartPos[(i*8+j)],bStartPos[(i*8+j)]);
				board[j][i] = new Square(j,i,peice,name);
			}
		}
		
	}
	
	//Takes in both the white and black positioning arrays, and returns the appropriate Piece 
	public Piece getPiece(int whitePieceType, int blackPieceType){
		int peiceType = whitePieceType | blackPieceType ;
		boolean white = whitePieceType !=0;
		Piece peice = null;
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
				if(white){
					wKing = peice;
				}else{
					bKing = peice;
				}
				break;
		
		}
		return peice;

	}
	
	//Returns true if there is noPeices in the specified coords
	public static boolean noPeices(int x, int y){
		return board[x][y].peice==null;
	}
	
	//Returns true if King is in checked position
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
	
	//Return true if king is mated
	public static boolean isMated(){
		return isChecked() && (( wTurn && (wKing.posMove(0, 0, false).size()==0)) || ( !wTurn && (bKing.posMove(0, 0, false).size()==0))  );
	}
	
	//Updates the attacks array
	public static void updateThreats(){
		attacks = new ArrayList<Square>();
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				Square sq = board[j][i];
				if(sq.peice != null){
					
					ArrayList<Square> threats = sq.peice.posMove(j, i, false);
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
	
	//Alerts a square that the board has been clicked
	public static void clicked(int x, int y) {
		//System.out.println("clicked");
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					sq.checkClicked(x,y);
			}
		}
	}
	
	//Clears all the squares of any highlighted colors
	public static void unfocusAllSquares(){
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				board[j][i].restoreColor();
			}
		}
	}
	
	//Function that draws the ChessBoard
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






