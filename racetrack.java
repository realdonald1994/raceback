package racetrack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author donald
 *
 * Dec 15, 2018
 */
public class racetrack {

	public static int map[][];
	public static List<Node> startPoints;
	public static List<Node> endPoints;
	public static int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
	public static int findMinPath(int[][] arr, Node startPoint, Node endPoint) {
		Map<String, Integer> hashmap = new HashMap<>();
		List<Node> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				String s = i + "," + j;
				hashmap.put(s, 99);
			}
		}
		Node start = new Node(startPoint.x, startPoint.y, 0, 0, 0, null);
		list.add(start);
		hashmap.put(startPoint.x + "," + startPoint.y, 0);
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.offer(start);
		Node temp = null;
		while (!queue.isEmpty()) {
			temp = queue.poll();
			if (temp.x == endPoint.x && temp.y == endPoint.y) {
				break;
			}
			for (int i = 0; i < 8; i++) {
				
				int newX = temp.x + temp.vx + direction[i][0];
				int newY = temp.y + temp.vy + direction[i][1];
				
				if (newX < 0 || newX >= map.length || newY < 0 || newY >= map.length) {
					continue;
				}
				
				if (arr[newX][newY] == 1) {
					continue;
				}
				

				if ((temp.dis + 1) <= hashmap.get(newX + "," + newY)) {
					Node next = new Node(newX, newY, temp.dis + 1, temp.vx + direction[i][0],
							temp.vy + direction[i][1], temp);
					hashmap.put(newX + "," + newY, temp.dis + 1);
					list.add(next);
					queue.offer(next);
				} else {
					continue;
				}

			}

		} 
		return hashmap.get(endPoint.x + "," + endPoint.y);

	}
	public static void readFile(String name) throws IOException {
		startPoints = new ArrayList<>();
		endPoints = new ArrayList<>();
		map = new int[26][26];
		File file = new File(name);
		InputStream in = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line1 = reader.readLine();
		int startPointNum = Integer.parseInt(line1);
		String line2 = reader.readLine();
		int endPointNum = Integer.parseInt(line2);
		for (int i = 0; i < startPointNum; i++) {
			String l = reader.readLine();
			int x = Integer.parseInt(l.split(" ")[0]);
			int y = Integer.parseInt(l.split(" ")[1]);
			startPoints.add(new Node(x, y, 0, 0, 0, null));

		}
		for (int i = 0; i < endPointNum; i++) {
			String l = reader.readLine();
			int x = Integer.parseInt(l.split(" ")[0]);
			int y = Integer.parseInt(l.split(" ")[1]);
			endPoints.add(new Node(x, y, 0, 0, 0, null));

		}
		String line;
		int i=25;
		while ((line = reader.readLine()) != null) {
			int j=1;
			String s[] = line.trim().split(" ");
			for(String n:s) {
				map[j][i] = Integer.parseInt(n);
				j++;
			}
			i--;
		}
		in.close();
			}
	public static void BFS() throws IOException {
		readFile(Play.INPUTFILE);
		System.out.println("Read File "+Play.INPUTFILE+"....Complete");
		wirte("./src/output.txt", "\n"+ "Read File "+Play.INPUTFILE+"....Complete");
		int minimum = 99;
		int start_X = -1;
		int start_Y = -1;
		int end_X = -1;
		int end_Y = -1;
		for (Node start : startPoints) {
			for (Node end : endPoints) {
				int result = findMinPath(map, start, end);
				if (result < minimum) {
					end_X = end.x;
					end_Y = end.y;
					start_X = start.x;
					start_Y = start.y;
					minimum = result;
				}
			}
		}
		System.out.println("The start point is (" + start_X + "," + start_Y + ").");
		wirte("./src/output.txt", "\n"+"The start point is (" + start_X + "," + start_Y + ").");
		System.out.println("The end point is (" + end_X + "," + end_Y + ").");
		wirte("./src/output.txt", "\n"+"The end point is (" + end_X + "," + end_Y + ").");
		System.out.println("The minimum steps is: " + minimum);
		wirte("./src/output.txt", "\n"+"The minimum steps is: " + minimum);
		
		
	}
	public  static void wirte(String filename,String content) throws IOException {
		FileWriter writer = new FileWriter(filename, true);  
		   writer.write(content);   
        writer.close();   
	}

}
