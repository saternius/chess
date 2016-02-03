
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
/*
 * The main activity of the game.
 * Handles all processes outside of the GameCanvas
 */
public class gameApplet extends JApplet {
	static int boardDim = 400;
	static Image peicesPng;
    public void init() {
    	peicesPng = getImage(getDocumentBase(), "peices.png");
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameCanvas canvas = new GameCanvas();
				add(canvas, BorderLayout.CENTER);
				setSize(boardDim, boardDim);
				setVisible(true);	
				canvas.createBufferStrategy(2);
			}
		});
    }
}
