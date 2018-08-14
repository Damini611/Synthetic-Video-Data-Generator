
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("unused")
public class Driver {
	// Setting the Frame Dimensions; 
	/**
	 * Active List contains objects that are Alive at the moment in a segment.
	 * Bucket is a pool of objects from which we use Uniform Distribution to select.
	 * K Objects based on some criteria.
	 * @param args Basic String Args
	 */
	public static void main(String[] args) {
		
		ArrayList<ObjectInstance> activeList = new ArrayList<ObjectInstance>(); 
		ObjectCreator objectCreator = new ObjectCreator();
		HashMap<Integer, Integer> lifeTime = objectCreator.getObjectLifeTimeHashMap();
		// Instantiating Segment Generator Class.
		SegmentGenerator sGenerator = new SegmentGenerator();
		// The ArrayList below is to hold the Segment Objects returned from sGenerator. 
		ArrayList<Segment> segmentList = new ArrayList<Segment>();
		segmentList = sGenerator.getSegmentsAfterInitialisation();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long timestamp1 = timestamp.getTime();
		for (Segment seg : segmentList){
			// Max Number Of Objects Per Segment.
			int avgSegObjects = seg.getAverageNoOfObjects();
			System.out.println("SEG __ "+avgSegObjects);
			// No of Objects in the Active List.
			int activeListLength = activeList.size();
			System.out.println("Active List Length "+activeListLength);
			// Temp Array List Created per segment. 
			ArrayList<ObjectInstance> a1 = new ArrayList<ObjectInstance>();
			if (avgSegObjects > activeListLength){
				a1 = objectCreator.getKObjects(avgSegObjects-activeListLength, activeList);
				// We need a function here that will initialise the other parameters for an Object in the 
				// Active List.
				InitialiseObjectParamenters(a1);
				activeList.addAll(a1);
			}else if (avgSegObjects < activeListLength){
				// We need to figure out a method to delete an Element from the the Active List.
				// We eRmove the Object which has the least # leftOver#
				Collections.sort(activeList, new MyComparator());
				int elementsToBeRemoved = activeListLength - avgSegObjects;
				for (int i=0; i<elementsToBeRemoved;i++){
					// To Remove the Last Element From the List We need to use the Following.
					// :: Earlier we were using remove which was followed by Left Shift in an
					// arrayList.
					ObjectInstance objTemp = activeList.get(activeList.size()-1);
					int xxxx = objTemp.getObjectId();
					objTemp.setLeftOver(lifeTime.get(xxxx));
					activeList.remove(activeList.size()-1);
				}
			}
			// Time to add Frame Code here. Every Timestamp must be present in 30 frames.
			// Considering 30 FPS, Frames == 270 Total Frames.
			for (int frame=1; frame<=270; frame++){ 
				for (Iterator<ObjectInstance> iterator = activeList.iterator(); iterator.hasNext();){
					ObjectInstance obj = iterator.next();
					int m = obj.getLeftOver();
					m--;
					if (m > 0){
						obj.setLeftOver(m);
						movement(obj);
					}else{
						int ppp = obj.getObjectId();
						obj.setLeftOver(lifeTime.get(ppp));
						iterator.remove();
					}
					//function to move the object around based on the direction parameter.
				}
				
				if ((frame-1) % 30 == 0){
					timestamp1 = timestamp1 + 1;
				}
														
				for (ObjectInstance jj : activeList){
													
				File out = new File ("/C:/Users/Damini Singh/Google Drive/UTA/Spring_2017/Advanced_Database/Project/out.txt");					
				try {
					PrintWriter	outputstream = new PrintWriter(new FileWriter(out,true));
//					outputstream.println("SEG ["+seg.getSegId()+ "-"+frame+"] Object Id "+jj.getObjectId()+" Left Over: {"+jj.getLeftOver()+"}time "+timestamp1+" Bounding Box [ "+jj.getTop_left_x()+" , "+ jj.getTop_left_y()+" , "+jj.getLength()+" , "+jj.getBreadth()+" ]" );
					outputstream.println(seg.getSegId()+","+frame+","+jj.getObjectId()+","+jj.getLeftOver()+","+timestamp1+","+jj.getTop_left_x()+","+ jj.getTop_left_y()+","+jj.getLength()+","+jj.getBreadth());
//					System.out.println("SEG ["+seg.getSegId()+ "-"+frame+"] Object Id "+jj.getObjectId()+" Left Over: {"+jj.getLeftOver()+"}time "+timestamp1+" Bounding Box [ "+jj.getTop_left_x()+" , "+ jj.getTop_left_y()+" , "+jj.getLength()+" , "+jj.getBreadth()+" ]");
						
						outputstream.close();
					} 
				catch (IOException e) {
					e.printStackTrace();
					}
				
			
					} // End of iterating over Active list for printing to console
											
			} // End of Frame For loop
		} // End of segment For loop
	} // End of Main Function

	/**
	 * Movement Of the Object in the Canvas.
	 * Object Spawned From a  Side Can Move in 3 Directions.
	 *@param obj Object Instance 
	 */
	private static void movement(ObjectInstance obj) {
		// Random Assignment Attached to Object.
		int pixelMovement = obj.getSpeed();
		int startEdge = obj.getStartEdge();
		int dir = obj.getDirection();
		
		if (startEdge == 0){  // LEFT 0
			// when the object enters from left(start edge = 0), it can either go E(0), NE(1) or SE(2)
			// Including the movement of an object in all three directions
			if(dir == 0){
				// Direction is East - x is incremented by pixel movement and y remains constant
				obj.setTop_left_x(obj.getTop_left_x()+pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y());
			}
			else if(dir == 1){
				// Direction is North East - Both the coordinates would be incremented by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()+pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()+pixelMovement);
			}
			else {
				// Direction is South-East - x coordinate would be incremented and y cooridnate would be decremented by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()+pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()-pixelMovement);
			}
		}
		
		if (startEdge == 1){
			// When the object enters from the bottom(start edge =1) of the screen it can either go N(0), NW(1), NE(2)
			if(dir == 0){
				// Direction is North- x remains constant and y is incremented by pixel movement
				obj.setTop_left_x(obj.getTop_left_x());
				obj.setTop_left_y(obj.getTop_left_y()+pixelMovement);
			}
			else if(dir == 1){
				// Direction is North East - x would decrease and y would increase by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()-pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()+pixelMovement);
			}
			else{
				// Direction is North West - x and y would increase by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()+pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()+pixelMovement);
			}					
		}
		
		if (startEdge == 2){
			// when the object enters from Right(start edge = 2), it can either go W(0), NW(1) or SW(2)
			if(dir == 0){ 
				// Direction is West
				obj.setTop_left_x(obj.getTop_left_x()-pixelMovement);// y-coordinate would remain constant, but x-coordinate would be decremented by pixel movement
				obj.setTop_left_y(obj.getTop_left_y());
			}
			else if(dir == 1){ 
				// Direction is North West  - X would decrement and Y would increment
				obj.setTop_left_x(obj.getTop_left_x()-pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()+pixelMovement);
			}
			else {
				// Direction is South-West - y and x would be decremented by pixel movement.
				obj.setTop_left_x(obj.getTop_left_x()-pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()-pixelMovement);
			}

		}
		
		if (startEdge == 3){
			// When the object enters from Top- It can either go S(0), SW(1),SE(2)
			if(dir == 0){
				// Direction is South - x would remain constant and y would decrease by pixel movement
				obj.setTop_left_x(obj.getTop_left_x());
				obj.setTop_left_y(obj.getTop_left_y()-pixelMovement);	
			}
			else if(dir == 1){
				// Direction is South-West - x and y would decrease by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()-pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()-pixelMovement);	
			}
			else{
				// Direction is South-East- x would increase and y would decrease by pixel movement
				obj.setTop_left_x(obj.getTop_left_x()+pixelMovement);
				obj.setTop_left_y(obj.getTop_left_y()-pixelMovement);	
			}
			
		}
}

	/**
	 * This method Initialises Various Object Attributes Like Speed, Side, Length, Side ETC.
	 *@param a1 Object instance Array Of All Active Objects 
	 */
	private static void InitialiseObjectParamenters(ArrayList<ObjectInstance> a1) {
		// TODO Auto-generated method stub
		DistributionUtility distributionUtility = new DistributionUtility();
		for(ObjectInstance a : a1){
			int side = distributionUtility.getStartEdge();
			a.setStartEdge(side);
			int speed = distributionUtility.getObjectSpeed();
			a.setSpeed(speed);
			if (side == 0 || side == 2){
				// Left 0 and right 2
				// Setting the start pixel point of the object
				int pixelPosition = distributionUtility.getVerticalStartPoint();
				a.setStartPixel(pixelPosition);
				a.setTop_left_y(pixelPosition);
				// Setting the size of the object
				int sizeMultiplier = distributionUtility.getObjectSize();
				//initial length of object
				a.setLength(5*sizeMultiplier); 
				a.setBreadth(5*sizeMultiplier);
				// Setting the direction of movement of the object.
				int direction = distributionUtility.getObjectDirection();
				a.setDirection(direction);
				if (side == 2){ 
					// Right direction
					a.setTop_left_x(960+a.getLength());
				}
				if (side == 0){
					// Left Direction
					a.setTop_left_x(0-a.getLength());
				}
			}
			if (side == 1 || side == 3 ){
				// top 3 and bottom 1
				int pixelPosition = distributionUtility.getHorizontalStartPoint();
				a.setStartPixel(pixelPosition);
//				a.setTop_left_x(pixelPosition);
				//Setting the size of object
				int sizeMultiplier = distributionUtility.getObjectSize();
				a.setLength(a.getLength()*sizeMultiplier);
				a.setBreadth(a.getBreadth()*sizeMultiplier);
				//Setting the direction of movement of object
				int direction = distributionUtility.getObjectDirection();
				a.setDirection(direction);
				if (side == 1){
					//Bottom direction
					a.setTop_left_y(0-a.getBreadth());
				}
				if (side == 3){
					// Top Direction
					a.setTop_left_y(640+a.getBreadth());
				}
			}
		}	
	}
}