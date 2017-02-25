import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Erin Elsbernd, Austin Bullard
 *
 */
public class RecSys {

    private float[][] mrMatrix;
    private PrintWriter writer;
    private NearestPoints near;

   /** public static void main(String[] args) throws FileNotFoundException{
        RecSys sys = new RecSys("./hello.txt");
        System.out.println(sys.ratingOf(2,3));
    }*/

    public RecSys(String mrMatrix) throws FileNotFoundException {
        //try to open the file
        try{
            //create the readers
            FileReader reader = new FileReader(new File(mrMatrix));
            BufferedReader in = new BufferedReader(reader);
            writer = new PrintWriter("matrixpoints.txt", "UTF-8");

            //create a line scanner for the initial number of users and movies from the text file
            Scanner scan = new Scanner(in.readLine());
            int users = scan.nextInt();
            int movies = scan.nextInt();

            //create the matrix, we now know the size
            this.mrMatrix = new float[users][movies + 1];

            //for ever user in the matrix, parse their movie ratings
            for(int i = 0; i < users; i++) {
                parseText(in.readLine(), i, movies);
            }

            //close all file IOs and the scanner
            in.close();
            writer.close();
            reader.close();
            scan.close();

            //create new nearest points object, with the points from the movie rating matrix
            near = new NearestPoints("./matrixpoints.txt");
        } catch (IOException e) {
            System.out.println("File could not be found.");
            e.printStackTrace();
        }

    }

    public float ratingOf(int u, int m) {
        float rating = mrMatrix[u][m + 1]; // + 1
        ArrayList list;
        if(rating != 0) {
            return rating;
        } else {
            float avg = 0.0f;
            int count = 0;
            list = near.npHashNearestPoints(mrMatrix[u][0]);

            for(int i = 0; i < list.size(); i++) {
                for(int j = 0; j < mrMatrix.length; j++) {
                    if(mrMatrix[j][0] == (float) list.get(i)) {
                        if(mrMatrix[j][m + 1] != 0) {
                            count++;
                            avg += mrMatrix[j][m + 1];
                        }
                    }
                }
            }
            return avg / (float) count;
        }
    }

    /**
     * Helper method that parses the next line of text and adds the point to the hash table
     * @param line
     *  the line to be parsed, given from BufferedReader
     * @param user
     *  the user that we're reading movie ratings for
     * @param movies
     *  the maximum number of movies the user may have rated
     */
    private void parseText(String line, int user, int movies) {
        Scanner scan = new Scanner(line);
        //receive the point from the line of text
        float point = scan.nextFloat();

        //add point to the HashTable
        write(point);
        mrMatrix[user][0] = point;

        for(int i = 1; i < movies + 1; i++) {
            mrMatrix[user][i] = scan.nextInt();
        }
        scan.close();
    }

    private void write(float point) {
        writer.println(point + " ");
    }
}