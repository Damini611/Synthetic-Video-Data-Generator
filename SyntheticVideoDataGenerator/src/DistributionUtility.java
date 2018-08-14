import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;

/**
 * This Class is a Utility Class that is a collection of Distributions in the Project.
 * @author Damini Singh, Rudresh Ajgaonkar
 *
 */
public class DistributionUtility {
	public DistributionUtility() {
		// TODO Blank Constructor
	}
	
	/**
	 * Considering *Poission distribution* while calculating the lifetime of various Objects.
	 * Poisson Distribution provides values independent of Each Other.
	 * Average value considered is 100
	 * @return Object Life Time [Poission Distribution]
	 */
	public int getObjectLifetime(){
		int average = 180;
		PoissonDistribution distribution = new PoissonDistribution(average);
		return distribution.sample();
	}
	
	/**
	 * Using Normal Distribution We get the Average Number Of Objects Per Segment.
	 * We Use Mean = 2 and Standard deviation = 1
	 * @return average_in_int
	 */
	public int getAvgNumberOfObjectsPerSegment(){
		int mean = 2;
		int sd = 1;
		NormalDistribution ob1 = new NormalDistribution(mean, sd);
		@SuppressWarnings("deprecation")
		Double x1 = new Double(ob1.sample());
		int average_in_int = (int) Math.round(x1);
		if (average_in_int <= mean-sd-1){
			average_in_int =mean-sd;
		}
		if (average_in_int >= mean+sd+1){
			average_in_int = mean+sd;
		}
		return average_in_int;
	}
	
	/**
	 * Specifies the Direction of the Object Movement.
	 * For example if an object enters the screen from left, it can either go E, NE or SE.
	 * Similarly there will be other directions for Object Initiating movement from right,bottom,top.
	 * @return direction
	 */
	public int getObjectDirection(){
		// 
		//Similarly for other directions also.
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(0, 2);
		int direction = distribution.sample();
		return direction;
	}
	/**
	 * Object Starts from Edge Left - 0, Bottom -1 , Right - 2, Top - 3.
	 * @return startEdge.
	 */
	public int getStartEdge(){
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(0, 3);
		int startEdge = distribution.sample();
		return startEdge;
	}

	/**
	 * Gets the Vertical Point on the Screen. The Range Assumed here is 0 to 640.
	 * @return point between 0 to 640.
	 */
	public int getVerticalStartPoint() {
		int x = 0 ;
		int y = 640;
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(x, y);
		int z = distribution.sample();
		return z;
	}

	/**
	 * Considering the Horizontal Range as 0 to 960. Gets the Horizontal start point for the Object.
	 * @return Horizontal Pixel Coordinate. 
	 */
	public int getHorizontalStartPoint() {
		int x = 0;
		int y = 960;
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(x, y);
		int z = distribution.sample();
		return z;
	}
	
	/**
	 * Size of the Objects can range from 1x, 2x .. 5x 
	 * x - Pixel
	 * @return size
	 */
	public int getObjectSize(){
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(1, 5);
		int size = distribution.sample();
		return size;
	}
	
	/**
	 *Here we are Considering Variable Speed for Various Object.
	 *We Are Using Uniform Distribution for the Speed of the Object. 	
	 * @return Speed of the Object.
	 */
   public int getObjectSpeed(){
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(1, 3);
		int z = distribution.sample();
		return (z*2);
	}
   }

