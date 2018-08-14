import java.util.ArrayList;
/**
 * Segment Class Specifies Video Segments.
 * Considering 90 Sec Video, We make Segments of size 10% of the Total Video.
 *@author Damini Singh, Rudresh Ajgaonkar
 *Variables : segId  - Specifies Unique Id for a Segment
 *			  averageNoOfObjects - Specifies Assigned Max Number Of Objects per Segment.
 */
class Segment {
	private int segId;
	private int averageNoOfObjects;
	public int getSegId() {
		return segId;
	}
	public void setSegId(int segId) {
		this.segId = segId;
	}
	/**
	 * Finding Average Number Of Objects Per Segment.
	 * We Use Uniforms
	 *@return averageNoOfObjects
	 */
	public int getAverageNoOfObjects() {
		return averageNoOfObjects;
	}
	/**
	 * 
	 *@param averageNoOfObjects Avg No Of Objects Per Segment.
	 */
	public void setAverageNoOfObjects(int averageNoOfObjects) {
		this.averageNoOfObjects = averageNoOfObjects;
	}
}


public class SegmentGenerator {
		
		public SegmentGenerator() {	
	
		} 
	/**
	 * Uses normal Distribution with Mean = 2 , SD =1.
	 * Create 10 Segments and Assigns them Average Number Of Objects.
	 * The Method getSegmentsAfterInitialisation returns an ArrayList of Segments.
	 *@return ArrayList of Segments.
	 */
	public ArrayList<Segment> getSegmentsAfterInitialisation(){
		DistributionUtility distributionUtility = new DistributionUtility();
		ArrayList<Segment> s1 = new ArrayList<Segment>();
		for (int i=1; i<10; i++){
			Segment segment = new Segment();
			segment.setSegId(i);
			segment.setAverageNoOfObjects(distributionUtility.getAvgNumberOfObjectsPerSegment());
			s1.add(segment);
		}	
		return s1;
	}
	
	
	
}
