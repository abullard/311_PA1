import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Austin Bullard on 2/17/17.
 * Erin Elsbernd
 *
 */
public class NearestPoints {
	private ArrayList<Float> pointList = new ArrayList<Float>();
	private HashTable duHashMich; 
	
	public static void main(String[] args) {
		NearestPoints np = new NearestPoints();
		np.nearestPoints("/Users/erelsbernd/Documents/IowaState/IowaStateSpring2017/cs311/CS311_NearestPoints/points.txt");
		np.buildDataStructure();
		np.naiveNearestPoints(591063.5f);
	}

    public void nearestPoints(String dataFile) {
    	
    	//read in file and populate the array list pointList
    	try {
           File file = new File(dataFile);

           Scanner input = new Scanner(file);
           
           while (input.hasNextLine()) {
              String line = input.nextLine();
              pointList.add(Float.parseFloat(line));
           }
           input.close();
    	} catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void nearestPoints(ArrayList<Float> pointSet) {
    	
    	//populate the array list pointList
    	for (Float f: pointSet) {
    		pointList.add(f);
    	}
    }

    /**
     *  Returns an array list of points (from the set S) that are close
		to p.  Two points/numbers p and q are close if abs(p − q) ≤ 1.
     * @param p
     */
    public ArrayList<Float> naiveNearestPoints(float p) {
    	ArrayList<Float> nearPoints = new ArrayList<Float>();
    	
    	//Iterate through all the points and find the "closest" ones to p
    	for (Float f: pointList) {
    		if (Math.abs(p-f) <= 1) {
    			//System.out.println(f.toString());
    			nearPoints.add(f);
    		}
    	}
    	return nearPoints;
    }

    /**
     * Builds the data structure to quickly answer nearest point
		queries.
     */
    public void buildDataStructure() {
    	duHashMich = new HashTable(pointList.size());
    	
    	//populate duHashMich table with points
    	for(Float f: pointList) {
    		Tuple t = new Tuple((int)Math.floor(f),f);
    		duHashMich.add(t);
    		//System.out.println(t.getkey() + "," + t.getValue());
    	}
    }

    public void npHashNearestPoints(float p) {

    }

    public void allNearestPointsNaive() {

    }

    public void allNearestPointsHash() {

    }
}
