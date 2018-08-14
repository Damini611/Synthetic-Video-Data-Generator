import java.util.Comparator;

/**
 * Used to Compare Various Objects in the canvas and Arrange them in Sorted Order.
 * @author Damini Singh, Rudresh Ajgaonkar.
 *
 */
public class MyComparator implements Comparator<ObjectInstance> {

	@Override
	public int compare(ObjectInstance o1, ObjectInstance o2) {
		// TODO Auto-generated method stub
		if (o1.getLeftOver() > o2.getLeftOver()){
			return -1;
		}else if (o1.getLeftOver() < o2.getLeftOver()){
			return 1;
		}
		return 0;
	}

}
