import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.Timer;

/// This class is responsible for all the GUI that gets displayed and input handling.
public class GameCanvas extends Canvas implements MouseMotionListener,
		MouseListener, MouseWheelListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1470670740765816534L;
	boolean repaintInProgress = false;// checks if painting is going on
	public static ChessBoard mainBoard;
	public static ArrayList<ChessBoard> boards = new ArrayList<ChessBoard>();
	
	// inits the GameCanvas and adds all the eventListener
	GameCanvas() { 
		setIgnoreRepaint(true); 
		Chrono chrono = new Chrono(this);
		new Timer(18, chrono).start();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
		mainBoard = new ChessBoard();
		saveState();
	}

	public void myRepaint() { 
		if (repaintInProgress)
			return;
		repaintInProgress = true;
		BufferStrategy strategy = getBufferStrategy();// Prevents flickering
		Graphics stage = strategy.getDrawGraphics();
		mainBoard.draw(stage);
		
		if (stage != null)
			stage.dispose();
		// show next buffer
		strategy.show();
		// synchronized the blitter page shown
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent m) {// When the mouse is pressed
		// TODO Auto-generated method stub
		mainBoard.clicked(m.getX(),m.getY());
	}

	@Override
	public void mouseReleased(MouseEvent m) {// What happens when the mouse is released
		
	}

	@Override
	public void mouseDragged(MouseEvent m) {

	}

	@Override
	public void mouseMoved(MouseEvent m) {
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==32){
			if(boards.size()<2){
				return;
			}
			
			boards.remove(boards.size()-1);
			mainBoard = new ChessBoard(boards.get(boards.size()-1));
			mainBoard.updateThreats();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void saveState() {
		boards.add(new ChessBoard(mainBoard));
	}
}
