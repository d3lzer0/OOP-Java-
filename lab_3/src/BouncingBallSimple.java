import java.awt.*;
import java.util.Formatter;
import java.util.Arrays;
import javax.swing.*;

// Extends JPanel, so as to override the paintComponent() for custom rendering
public class BouncingBallSimple extends JPanel {
	
	// Container box's width and height
	private static final int BOX_WIDTH = 640;
	private static final int BOX_HEIGHT = 480;

	// Ball's properties
	static Rectangle[] rectangles = new Rectangle[30];	
	private int ballRadius = 75; // Ball's radius
	
	/*private float ballX = ballRadius + 50; // Ball's center (x, y)
	private float ballY = ballRadius + 20;
	private float ballSpeedX = 3; // Ball's speed for x and y
	private float ballSpeedY = 2;*/
	
	private int[] ballX = new int[30];
	private int[] ballY = new int[30];
	private int[] ballSpeedY = new int[30];
	private int[] ballSpeedX = new int[30];
	
	private static final int UPDATE_RATE = 60; // Number of refresh per second

	// Constructor to create the UI components and init game objects.
	public BouncingBallSimple() {
		this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
		ballX[0] = ballRadius + 50;
		ballY[0] = ballRadius + 20;
		for (int i = 1; i < 30; i++) {
			ballX[i] = ballX[i - 1] + 10;
			ballY[i] = ballY[i - 1] + 10;
		}
		Arrays.fill(ballSpeedX, 2);
		Arrays.fill(ballSpeedY, 1);
		// Start the ball bouncing (in its own thread)
		Thread gameThread = new Thread() {
			public void run() {

				while (true) { // Execute one update step
				
					for (int i = 0; i < 30; i++) {
						ballX[i] += ballSpeedX[i];
						ballY[i] += ballSpeedY[i];
						// Check if the ball moves over the bounds; if so, adjust the position and speed.
						if ((ballX[i] - ballRadius) < 0) {
							ballSpeedX[i] = -ballSpeedX[i]; // Reflect along normal
							ballX[i] = ballRadius; // Re-position the ball at the edge
						} else {
							if ((ballX[i] + ballRadius) > BOX_WIDTH) {
								ballSpeedX[i] = -ballSpeedX[i];
								ballX[i] = BOX_WIDTH - ballRadius;
							}
						}

						// May cross both x and y bounds
						if ((ballY[i] - ballRadius) < 0) {
							ballSpeedY[i] = -ballSpeedY[i];
							ballY[i] = ballRadius;
						} else {
							if ((ballY[i] + ballRadius) > BOX_HEIGHT) {
								ballSpeedY[i] = -ballSpeedY[i];
								ballY[i] = BOX_HEIGHT - ballRadius;
							}
						}
					}
					// Refresh the display
						repaint(); // Callback paintComponent()
					
						// Delay for timing control and give other threads a chance
						try {
							Thread.sleep(1000 / UPDATE_RATE); // milliseconds
						} catch (InterruptedException ex) {
							// do nothing... (block exit)
						}
					
				}
			}
		};
		gameThread.start(); // Callback run()
	}

	// Custom rendering codes for drawing the JPanel
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background
		
		// Draw the box
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		
		/*// Draw the ball
		g.setColor(Color.RED);
		g.fillOval((int)(ballX - ballRadius), (int)(ballY - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));
		*/
		
		// Draw the rectangle
		for (int i = 0; i < 10; i++) {
			rectangles[i] = new Rectangle((short)(ballX[i] - ballRadius), (short)(ballY[i] - ballRadius), (short)(ballRadius * 2), (short)(ballRadius * 2));
			if ((i % 2) == 1) {
				rectangles[i].inColor = Color.BLACK;
			} else {
				rectangles[i].inColor = Color.RED;
			}
			rectangles[i].draw(g);
		}
		for (int i = 10; i < 20; i++) {
			rectangles[i] = new ColoredRect((short)(ballX[i] - ballRadius), (short)(ballY[i] - ballRadius), (short)(ballRadius * 2), (short)(ballRadius * 2));
			if ((i % 2) == 1) {
				rectangles[i].inColor = Color.BLACK;
			} else {
				rectangles[i].inColor = Color.RED;
			}
			rectangles[i].drawColoredRect(g);
		}
		for (int i = 20; i < 30; i++) {
			rectangles[i] = new DrawableRect((short)(ballX[i] - ballRadius), (short)(ballY[i] - ballRadius), (short)(ballRadius * 2), (short)(ballRadius * 2));
			rectangles[i].drawDrawableRect(g);
		}

		// Display the ball's information
		g.setColor(Color.GREEN);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		for (int i = 0; i < 10; i++) {
			formatter.format("Ball @%d(%d, %d) Speed=(%d, %d)", i, ballX[i], ballY[i], ballSpeedX[i], ballSpeedY[i]);
			g.drawString(sb.toString(), 20, 30 + i * 15);
			sb.delete(0, sb.length());
		}

	}

	// main program (entry point)
	public static void main(String[] args) {
		
		// Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				// Set up main window (using Swing's Jframe)
				JFrame frame = new JFrame("A Bouncing Ball");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BouncingBallSimple());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}