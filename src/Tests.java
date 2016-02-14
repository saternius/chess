import org.junit.Test;
import static org.junit.Assert.assertEquals;
/// Testing Units
public class Tests {
   
   @Test
   public void chessBoardConfig() {
	  ChessBoard test = new ChessBoard();
	  //Check if grid is made
	  for(int i=0; i<8;i++){
		  for(int j=0; j<8; j++){
			  assertEquals(true, test.board[j][i]!=null);  
		  }
	  } 
	  
	  //Check white piece placements
	  for(int i=6; i<8;i++){
		  for(int j=0; j<8; j++){
			  assertEquals(true, test.board[j][i].peice.white);  
		  }
	  } 
	
	  //Check black piece placements
	  for(int i=0; i<2;i++){
		  for(int j=0; j<8; j++){
			  assertEquals(false, test.board[j][i].peice.white);  
		  }
	  }
	  
	  //check for empty center
	  for(int i=2; i<6;i++){
		  for(int j=0; j<8; j++){
			  assertEquals(true, test.board[j][i].peice==null);  
		  }
	  }
	  
	  //Check for Pawns
	  for(int j=0; j<8; j++){
		 // assertEquals("Pawn", test.board[j][1].peice.type);  
	  }
	  for(int j=0; j<8; j++){
		 //assertEquals("Pawn", test.board[j][6].peice.type);  
	  }
	  
	  assertEquals("King", test.board[4][0].peice.type);
	  assertEquals("Rook", test.board[0][0].peice.type); 
	  assertEquals("Bishop", test.board[2][0].peice.type); 
	  assertEquals("Knight", test.board[1][0].peice.type); 
	  assertEquals("Queen", test.board[3][0].peice.type);
	  
	  assertEquals("King", test.board[4][7].peice.type);
	  assertEquals("Rook", test.board[0][7].peice.type); 
	  assertEquals("Bishop", test.board[2][7].peice.type); 
	  assertEquals("Knight", test.board[1][7].peice.type); 
	  assertEquals("Queen", test.board[3][7].peice.type); 
	  
	  
   }
   
   @Test
   public void RookMovement() {
	   ChessBoard test = new ChessBoard();
	   Rook r = (Rook) test.board[0][0].peice;
	   r.x = 0;
	   r.y = 0;
	  assertEquals(0,r.posMove().size()); //test if blocked
	   r.x = 3;
	   r.y = 4;
	   assertEquals(11,r.posMove().size()); //test if all over the board
	   r.x = 2;
	   r.y = 3;
	   assertEquals(11,r.posMove().size()); 
	   r.x = 6;
	   r.y = 1;
	   assertEquals(5,r.posMove().size()); 
   }
   
   @Test
   public void BishopMovement() {
	   ChessBoard test = new ChessBoard();
	   Bishop r = (Bishop) test.board[2][0].peice;
	   r.x = 0;
	   r.y = 0;
	  assertEquals(0,r.posMove().size()); //test if blocked
	  r.x = 3;
	  r.y = 3;
	  assertEquals(8,r.posMove().size()); //test if all over the board
	  r.x = 5;
	  r.y = 3;
	  assertEquals(7,r.posMove().size()); 
	  r.x = 5;
	  r.y = 6;
	  assertEquals(8,r.posMove().size()); 
   }
   
   @Test
   public void KnightMovement() {
	   ChessBoard test = new ChessBoard();
	   Knight r = (Knight) test.board[1][0].peice;	
	   assertEquals(2,r.posMove().size()); //test if blocked
	   r.x = 0;
	   r.y = 0;
	   assertEquals(1,r.posMove().size()); 
	   r.x = 3;
	   r.y = 3;
	   assertEquals(6,r.posMove().size());
	   r.x = 5;
	   r.y = 3;
	   assertEquals(6,r.posMove().size());
	   r.x = 1;
	   r.y = 2;
   }
   
   @Test
   public void QueenMovement() {
	   ChessBoard test = new ChessBoard();
	   Queen r = (Queen) test.board[3][0].peice;
	   r.x = 0;
	   r.y = 0;
	   assertEquals(0,r.posMove().size()); //test if blocked
	   r.x = 3;
	   r.y = 3;
	   assertEquals(19,r.posMove().size()); //test if all over the board
	   r.x = 5;
	   r.y = 3;
	   assertEquals(14,r.posMove().size());
	   r.x = 1;
	   r.y = 2;
	   assertEquals(16,r.posMove().size());
   }
   
   
   @Test
   public void KingMovement() {
	   ChessBoard test = new ChessBoard();
	   King r = (King) test.board[4][0].peice;
	   r.x = 0;
	   r.y = 0;
	   assertEquals(0,r.posMove().size()); //test if blocked
	   r.x = 3;
	   r.y = 4;
	   assertEquals(8,r.posMove().size()); //test if all over the board
	   r.x = 5;
	   r.y = 3;
	   assertEquals(8,r.posMove().size());
	   r.x = 3;
	   r.y = 7;
	   assertEquals(4,r.posMove().size()); //checks if king would be in check
   }
   
   @Test
   public void BishopKnightMovement() {
	   ChessBoard test = new ChessBoard();
	   BishopKnight r = (BishopKnight) test.board[5][1].peice;
	   r.x = 0;
	   r.y = 0;
	   assertEquals(1,r.posMove().size()); //test if blocked
	   r.x = 3;
	   r.y = 4;
	   assertEquals(16,r.posMove().size()); //test if all over the board
	   r.x = 5;
	   r.y = 3;
	   assertEquals(12,r.posMove().size());
	   r.x = 3;
	   r.y = 7;
	   assertEquals(6,r.posMove().size()); //checks if king would be in check
   }
   
   @Test
   public void RookKnightMovement() {
	   ChessBoard test = new ChessBoard();
	   RookKnight r = (RookKnight) test.board[2][1].peice;
	   r.x = 0;
	   r.y = 0;
	   assertEquals(1,r.posMove().size()); //test if blocked
	   r.x = 3;
	   r.y = 4;
	   assertEquals(19,r.posMove().size()); //test if all over the board
	   r.x = 5;
	   r.y = 3;
	   assertEquals(16,r.posMove().size());
	   r.x = 3;
	   r.y = 7;
	   assertEquals(7,r.posMove().size()); //checks if king would be in check
   }
   
   
   @Test
   public void checkingThreatMap(){
	   ChessBoard test = new ChessBoard();
	   test.wTurn = true;
	   test.updateThreats();
	   assertEquals(34,test.attacks.size()); 
	   test.board[3][3].peice = test.board[3][0].peice;
	   test.updateThreats();
	   assertEquals(29,test.attacks.size());
   }
   
   @Test
   public void checkingChecksAndMates() {
	   ChessBoard test = new ChessBoard();
	   assertEquals(false,test.isChecked());
	   assertEquals(false,test.isMated());
	   //test.board[5][6].peice = new Queen(true);
	   test.updateThreats();
	   assertEquals(false,test.isMated());
   }
   

   
}


















