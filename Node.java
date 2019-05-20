package racetrack;
/**
 * 
 * @author donald
 *
 * Dec 15, 2018
 */
public class Node {
		int x;
		int y;
		int dis;
		int vx;
		int vy;
		Node node;
		public Node(int x, int y, int dis, int vx, int vy, Node node) {
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
			this.dis = dis;
			this.node = node;
		}
}
