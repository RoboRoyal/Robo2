package SonarUtil;

import java.io.IOException;

public class SonarExec implements Runnable {
	private boolean RUN = false;
	private Thread t;
	// private pingerReader left, right;
	static String left = "SonarUtil/left.txt";
	static String right = "SonarUtil/right.txt";

	public static int lighter() {
		int dir = 0;
		int bucket = 0;
		try {
			Process p = Runtime.getRuntime().exec("python3 lighterPy.py ");
			// Process p2 = Runtime.getRuntime().exec("python3 right.py ");
			p.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}

		int min = Sonar_Test.getSize(left);
		if (Sonar_Test.size(left) > Sonar_Test.getSize(right))
			min = Sonar_Test.getSize(right);
		min = min - 1;
		System.out.println("Min: " + min);
		Search.left = Sonar_Test.readInSmall(left, min);
		Search.right = Sonar_Test.readInSmall(right, min);
		System.out.println("Got data; " + Search.left.length + ", " + Search.right.length);
		try {
			bucket = Search.findBucket();
			System.out.println("Bucket is: " + bucket);
		} catch (Exception e) {
			System.out.println("Fail1: " + e);
			e.printStackTrace();
		}
		try {
			dir = Search.findDir(bucket);
			System.out.println("Dir is: " + dir);
		} catch (Exception e) {
			System.out.println("Fail2: " + e);
			e.printStackTrace();
		}
		return dir;
	}

	public static int light() {
		int dir = 0;
		int bucket = 0;
		socketMe2 me = new socketMe2();
		me.start();
		System.out.println(me.send("start"));
		me.waitEnd();
		// System.out.println(me.send("close"));
		// me.close();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}

		int min = Sonar_Test.getSize(left);
		if (Sonar_Test.size(left) > Sonar_Test.getSize(right))
			min = Sonar_Test.getSize(right);
		min = min - 1;
		System.out.println("Min: " + min);
		Search.left = Sonar_Test.readInSmall(left, min);
		Search.right = Sonar_Test.readInSmall(right, min);
		System.out.println("Got data; " + Search.left.length + ", " + Search.right.length);
		try {
			bucket = Search.findBucket();
			System.out.println("Bucket is: " + bucket);
		} catch (Exception e) {
			System.out.println("Fail1: " + e);
			e.printStackTrace();
		}
		try {
			dir = Search.findDir(bucket);
			System.out.println("Dir is: " + dir);
		} catch (Exception e) {
			System.out.println("Fail2: " + e);
			e.printStackTrace();
		}
		return dir;
	}

	public void norm() {
		// first time
		//

		// LOOP:----------------
		// connection
		// wait for left
		// start left reader
		/*
		 * -when left done done, search for bucket -when right ready, start
		 * reading right. Verify correct version
		 */
		// when both are ready and bucket is found, start search
		// ...
		// when search is done, update pinger connection
		// END LOOP:-----------

		// close everything with end()
	}

	public void setup() {
		// left = new pingerReader("dosent.exist", "LEFT");
		// right = new pingerReader("dosent.exist2", "RIGHT");
		// start py prog?
		// set up socket connection
		// test connect
	}

	public void run() {
		try {
			norm();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		if (!RUN) {
			RUN = true;
			if (t == null) {
				t = new Thread(this, "SonarExec");
				setup();
				t.start();
			} else {
				System.out.println("Problem making SonarExec:" + t);
			}
		} else {
			System.out.println("Trying to make second instance of SonarExec");
		}
	}

}
