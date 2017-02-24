import java.io.*;
import java.util.Scanner;

/**
 * Created by Austin Bullard on 2/17/17.
 *
 */
public class RecSys {

    private int[][] mrMatrix;

    public static void main(String[] args) throws FileNotFoundException{
        RecSys sys = new RecSys("./hello.txt");
    }

    public RecSys(String mrMatrix) throws FileNotFoundException {
        //try to open the file
        try{
            //create the readers
            FileReader reader = new FileReader(new File(mrMatrix));
            BufferedReader in = new BufferedReader(reader);

            //create a line scanner for the initial number of users and movies from the text file
            Scanner scan = new Scanner(in.readLine());
            int users = scan.nextInt();
            int movies = scan.nextInt();

            //create the matrix, we now know the size
            this.mrMatrix = new int[users][movies];

            //for ever user in the matrix, parse their movie ratings
            for(int i = 0; i < users; i++) {
                parseText(in.readLine(), i, movies);
            }

            in.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("File could not be found.");
            e.printStackTrace();
        }

    }

    public float RatingOf(int u, int m) {


        return 0.0f;
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


        for(int i = 0; i < movies; i++) {
            mrMatrix[user][i] = scan.nextInt();
        }
        System.out.println();
    }

    private void write(int point) throws IOException {
        try{
            PrintWriter writer = new PrintWriter("matrixpoints.txt", "UTF-8");
            writer.println(point);
            writer.close();
        } catch (IOException e) {
            System.out.println("There was an error creating and writing to the file.");
            e.printStackTrace();
        }
    }


}
