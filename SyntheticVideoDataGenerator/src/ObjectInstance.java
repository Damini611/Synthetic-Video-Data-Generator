// Object Lifetime 80 to 200 //


/**
 *Object Instance Class.
 *Properties of an Object Like length, breadth, lifetime, leftover, startPixel, StartEdge, Speed are stored here
 *Also the Bounding box here is kept with Every Object. 
 * @author Damini Singh, Rudresh Ajgaonkar
 *
 */
public class ObjectInstance {
	private int objectId;
	int length = 5; 
	int breadth = 5;
	int lifetime;
	// to be decreased from lifetime to 0 in the segment.
	int leftOver; 
	// This describes the direction of the object in the canvas.
	int direction;
	// startPixel is a value between 0 to 640 (consider)
	int startPixel;
	// Start Edge will be either top(3), bottom(1), left(0) and right(2)
	int startEdge;
	
	// Need to give the dimensions for bounding box. 
	// Dimension for bounding box is defined as (x,y,width,height)
	
	int top_left_x;
	int top_left_y;

	int speed;		

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getTop_left_x() {
		return top_left_x;
	}
	public void setTop_left_x(int top_left_x) {
		this.top_left_x = top_left_x;
	}
	public int getTop_left_y() {
		return top_left_y;
	}
	public void setTop_left_y(int top_left_y) {
		this.top_left_y = top_left_y;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getBreadth() {
		return breadth;
	}
	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}
	public int getLifetime() {
		return lifetime;
	}
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	public int getLeftOver() {
		return leftOver;
	}
	public void setLeftOver(int leftOver) {
		this.leftOver = leftOver;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getStartPixel() {
		return startPixel;
	}
	public void setStartPixel(int startPixel) {
		this.startPixel = startPixel;
	}
	public int getStartEdge() {
		return startEdge;
	}
	public void setStartEdge(int startEdge) {
		this.startEdge = startEdge;
	}
	
}
