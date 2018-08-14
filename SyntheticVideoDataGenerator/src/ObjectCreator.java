import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;

/**
 * Creates Necessary Objects Required in the Program.
 * @author Damini Singh, Rudresh Ajgaonkar
 * Bucket Symbolizes a Pool Of Objects. We Pick a Object Using Uniform Distribution.
 */
public class ObjectCreator{
	ArrayList<ObjectInstance> bucket = new ArrayList<ObjectInstance>();
	DistributionUtility distributionUtility = new DistributionUtility();
	HashMap<Integer, Integer> objectLifeTime = new HashMap<Integer,Integer>();
	public ObjectCreator() {
		// Constructor
		for (int i=1; i<=10;i++){
			ObjectInstance objectInstance = new ObjectInstance();
			objectInstance.setObjectId(i);
			int lifetime = distributionUtility.getObjectLifetime();
			objectInstance.setLifetime(lifetime);
			objectInstance.setLeftOver(lifetime);
			objectLifeTime.put(i,lifetime);
			bucket.add(objectInstance);
		}
	}
	
	/**
	 * Provides a Mapping of Objects and their Lifetime.
	 * @return A HashMap Of Objects and Their LifeTime.
	 */
	public HashMap<Integer, Integer> getObjectLifeTimeHashMap(){
		return this.objectLifeTime;
	}
	
	
	/**
	 * Recursive Method to Avoid Respawning an Element that has Already in the Active List.
	 * @param k - Number Of Objects to be spawned.
	 * @param AList Active List
	 * @return ArrayList of K Objects.
	 */
	public ArrayList<ObjectInstance> getKObjects(int k, ArrayList<ObjectInstance> AList){
		// Selecting "k" number of objects
		ArrayList<ObjectInstance> temp = new ArrayList<ObjectInstance>();
		ArrayList<ObjectInstance> result = new ArrayList<ObjectInstance>();
		int count = 0;
		result = generateKObj(count,k, AList, temp);
		return result;
	}
	
	/**
	 * Recursive Function to get Unique Objects From the Bucket.
	 * @param count Temporary Variable to Keep Count Of Objects in the List to be returned
	 * @param k Number Of Objects Required 
	 * @param aList Active List
	 * @param FList Final List
	 * @return ArrayList of K Objects that are not present in the Active List.
	 */
	private ArrayList<ObjectInstance> generateKObj(int count, int k, ArrayList<ObjectInstance> aList, ArrayList<ObjectInstance> FList) {
		// TODO Auto-generated method stub
		if (count < k){
			int x = generateUniformInteger();
			ObjectInstance Objj = bucket.get(x);
			if (aList.contains(Objj) || FList.contains(Objj)){
				generateKObj(count, k, aList, FList);
			}else{
				FList.add(Objj);
				count++;
				generateKObj(count, k, aList, FList);
			}
		}
		return FList;
	}
	
	/**
	 * StandAlone Function to Produce a Value using Uniform Distribution.
	 * @return Integer between 0 and 9
	 */
	public int generateUniformInteger(){
		UniformIntegerDistribution distribution = new UniformIntegerDistribution(0, 9);
		int x = distribution.sample();
		return x;
	}
		
}