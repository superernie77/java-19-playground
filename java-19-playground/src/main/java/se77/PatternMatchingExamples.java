package se77;

import static java.lang.Math.*;

public class PatternMatchingExamples {
	
	// Calculates the distance between two Point, if the parameters are of type Point
	public static double calculateDistance(Object a, Object b) {
		if (a instanceof Point p1 && b instanceof Point p2 ) {
			return sqrt(pow(p2.x()-p1.x(),2)+pow(p2.y()-p1.y(),2));
		}
		return Double.NaN;
	}
	
	

}


record Point (int x, int y) {};
