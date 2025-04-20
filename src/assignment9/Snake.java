package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private boolean growing = false;
	
	public Snake() {
		segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
		deltaX = MOVEMENT_SIZE;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1 && deltaY <= 0) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2 && deltaY >= 0) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3 && deltaX >= 0) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4 && deltaX <= 0) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
	    BodySegment head = segments.getFirst();
	    double newX = head.getX() + deltaX;
	    double newY = head.getY() + deltaY;
	    segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));
	    if (!growing) {
	        segments.removeLast();
	    } else {
	        growing = false; 
	    }
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double distanceSquared = dx * dx + dy * dy;
		double threshold = SEGMENT_SIZE + Food.FOOD_SIZE;

		if (distanceSquared <= threshold * threshold) {
			growing = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();

		return x >= 0.0 && x <= 1.0 && y >= 0.0 && y <= 1.0;
	}
	
	public boolean hasSelfCollision() {
		BodySegment head = segments.getFirst();
		double headX = head.getX();
		double headY = head.getY();
		for (int i = 1; i < segments.size(); i++) {
			BodySegment segment = segments.get(i);
			double dx = headX - segment.getX();
			double dy = headY - segment.getY();
			double distance = Math.sqrt(dx * dx + dy * dy);
			
			if (distance < SEGMENT_SIZE * 0.9) {
				return true;
			}
		}
		return false;
	}
	
	
}
