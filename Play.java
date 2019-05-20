package racetrack;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * @author donald
 *
 * Dec 15, 2018
 */
public class Play {
public static String INPUTFILE="dataset_assignment3/7.txt";
public static void main(String[] args) throws IOException {
	long startTime = System.currentTimeMillis();
	System.out.println("\n"+"Program is running...");
	wirte("./src/output.txt", "\n"+"Program is running...");
	racetrack.BFS();
	long endTime = System.currentTimeMillis();
	long cost = endTime - startTime;
	System.out.println("Time cost: " + cost +"ms.");
	wirte("./src/output.txt", "\n"+ "Time cost: " + cost +"ms.");
}
public  static void wirte(String filename,String content) throws IOException {
	FileWriter writer = new FileWriter(filename, true);  
	   writer.write(content);   
       writer.close();   
}
}
