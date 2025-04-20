package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
			
		private Snake snake;
		private Food food;
		private int score;
		
		public Game() {
			StdDraw.enableDoubleBuffering();
	        StdDraw.setScale(0, 1);
			snake = new Snake();
			food = new Food();
	}
	
		public void play() {
			showIntroScreen();
			while (true) {
				resetGame();
				snake.changeDirection(4); 

				while (snake.isInbounds() && !snake.hasSelfCollision()) {
					int dir = getKeypress();
					if (dir != -1) {
						snake.changeDirection(dir);
					}

					snake.move();

					if (snake.eatFood(food)) {
						food = new Food();
						score++;
					}

					updateDrawing();
					StdDraw.pause(100);
				}

				showGameOverScreen();

				while (!StdDraw.isKeyPressed(KeyEvent.VK_R)) {
					StdDraw.pause(10);
				}
				while (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
					StdDraw.pause(10);
				}
			}
		}
		
		private void resetGame() {
			snake = new Snake();
			food = new Food();
			score = 0;			
		}
	
		private void showIntroScreen() {
		    StdDraw.clear();
		    StdDraw.setPenColor(Color.BLACK);
		    StdDraw.text(0.5, 0.6, "WELCOME TO SNAKE");
		    StdDraw.text(0.5, 0.5, "Use W A S D to move");
		    StdDraw.text(0.5, 0.4, "Press SPACE to start");
		    StdDraw.show();
		    
		    while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
		    	}
	        while (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
	        }

		}
	

	private void showGameOverScreen() {
		StdDraw.clear();
		StdDraw.setPenColor(Color.RED);
		StdDraw.text(0.5, 0.6, "Game Over!");
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.5, 0.5, "Final Score: " + score);
		StdDraw.text(0.5, 0.4, "Press R to Restart");
		StdDraw.show();			
		}

	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.textLeft(0.02, 0.95, "Score: " + score);
		StdDraw.pause(40);
		StdDraw.show();
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
