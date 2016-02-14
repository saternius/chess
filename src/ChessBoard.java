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
	public Square[][] board = new Square[8][8]; ///< All the squares on the board. Organized by x,y coordinates: board[x][y]
	public boolean wTurn = true; ///<true if it is white's turn 
	public Piece wKing; ///<the white King
	public Piece bKing; ///<the black King
	public ArrayList<Piece> wArmy = new ArrayList<Piece>();///< All the black pieces that are not the king
	public ArrayList<Piece> bArmy = new ArrayList<Piece>();///< All the white pieces that are not the king 
	public ArrayList<Square> attacks = new ArrayList<Square>(); ///<List of squares in which the opponent is threatening
	public Square focusedSquare = null; ///< Square that is currently selected
	public int[] wStartPos = {
					  0,0,0,0,0,0,0,0,
			          0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
		              0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  0,0,0,0,0,0,0,0,
	                  1,1,1,1,1,1,1,1,
	                  4,2,3,5,6,3,2,4
					  }; ///<Starting white pieces
	public int[] bStartPos = { 
					  4,2,3,5,6,3,2,4,
	                  1,1,1,1,1,1,1,1,
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
				Piece peice = getPieceFromSet(wStartPos[(i*8+j)],bStartPos[(i*8+j)],j,i);
				board[j][i] = new Square(this,j,i,peice,name);
			}
		}
	}
	
	///Copies board
	public ChessBoard(ChessBoard cb){
		
		//Create empty board
		for(int i=0;i<8;i++){
			for(int j=0; j<8;j++){
				String[] letters = {"A","B","C","D","E","F","G","H"};
				String name = letters[j]+(8-i);
				board[j][i] = new Square(this,j,i,null,name);
			}
		}
		//Place pieces where they were
		for(int i=0;i<cb.board.length;i++){
			for(int j=0;j<cb.board[i].length;j++){
				if(cb.board[j][i].peice!=null){
					Piece p = getPieceFromName(cb.board[j][i].peice);
					//Piece p = new Piece(this,cb.board[j][i].peice);
					board[j][i].peice = p;
						if(p.white){
							wArmy.add(p);
						}else{
							bArmy.add(p);
						}
				}
			}
		}
		wTurn = cb.wTurn;
		wKing = cb.wKing;
		bKing = cb.bKing;
	}

	
	private Piece getPieceFromName(Piece piece) {
		String type = piece.type;
		Piece p = null;
		switch(type){
			case "Pawn":
				p = new Pawn(this,piece);
				break;
			case "Knight":
				p = new Knight(this,piece);
				break;
			case "Bishop":
				p = new Bishop(this,piece);
				break;
			case "Rook":
				p = new Rook(this,piece);
				break;
			case "Queen":
				p = new Queen(this,piece);
				break;
			case "King":
				p = new King(this,piece);
				break;
			case "BishopKnight":
				p = new BishopKnight(this,piece);
				break;
			case "RookKnight":
				p = new RookKnight(this,piece);
				break;
			
		}
		return p;
	}

	///Takes accepts the value from both starting positions and returns a piece based of the value.
	public Piece getPieceFromSet(int whitePieceType, int blackPieceType,int x, int y){
		int peiceType = whitePieceType | blackPieceType ;
		boolean white = whitePieceType !=0;
		Piece peice = null;
		switch(peiceType){
			case 0:
				break;
			case 1:
				peice = new Pawn(this,white,x,y);
				break;
			case 2:
				peice = new Knight(this,white,x,y);
				break;
			case 3:
				peice = new Bishop(this,white,x,y);
				break;
			case 4:
				peice = new Rook(this,white,x,y);
				break;
			case 5:
				peice = new Queen(this,white,x,y);
				break;
			case 6:
				peice = new King(this,white,x,y);
				if(white){
					wKing = peice;
				}else{
					bKing = peice;
				}
				break;
			case 7:
				peice = new RookKnight(this,white,x,y);
				break;
			case 8:
				peice = new BishopKnight(this,white,x,y);
				break;
		
		}
		if(white){
			wArmy.add(peice);
		}else{
			bArmy.add(peice);
		}
		return peice;

	}
	
	///Returns true if there is noPeices in the specified coords
	public boolean noPeices(int x, int y){
		return board[x][y].peice==null;
	}
	
	///Returns true if King is in checked position
	public boolean isChecked(){
		updateThreats();
		Piece king = wKing;
		if(!wTurn){
			king = bKing;
		}

		return (attacks.indexOf(board[king.x][king.y])>-1);
		
	}
	
	///Return true if king is mated
	public boolean isMated(){
		boolean isChecked = isChecked();
		if(!isChecked){
			return false;
		}
	
		int options = wTurn?sizeOfOptions(wArmy):sizeOfOptions(bArmy);	
		
		if(options==0){
			console.log("is mated");
		}
		
		return options == 0;
	}
	
	public static int sizeOfOptions(ArrayList<Piece> pieces){
		int options = 0;
		console.log(pieces);
		for(int i=0; i<pieces.size();i++){
			if(pieces.get(i)!=null){
				options += pieces.get(i).posMove().size();
			}
		}
		return options;
	}
	
	///Updates the attacks array
	public void updateThreats(){
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
	
	
	
	///Alerts a square that the board has been clicked
	public void clicked(int x, int y) {
		//System.out.println("clicked");
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					sq.checkClicked(x,y);
			}
		}
	}
	
	///Clears all the squares of any highlighted colors
	public void unfocusAllSquares(){
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
				board[j][i].restoreColor();
			}
		}
	}
	
	///Function that draws the ChessBoard
	public void draw(Graphics stage){
		for(int i=0;i<board.length;i++){
			for(int j=0; j<board[i].length;j++){
					Square sq = board[j][i];
					sq.draw(stage);	
			}
		}	
	}
}






