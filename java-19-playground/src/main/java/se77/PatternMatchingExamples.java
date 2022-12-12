package se77;

import static java.lang.Math.*;

@SuppressWarnings("preview")
public class PatternMatchingExamples {

	// Calculates the distance between two Points, if the method arguments are of
	// type Point
	public static double calculateDistance(Object a, Object b) {
		// The Java 14 way to define the Point variables p1 + p2
		if (a instanceof Point p1 && b instanceof Point p2) {
			return sqrt(pow(p2.x() - p1.x(), 2) + pow(p2.y() - p1.y(), 2));
		}
		return Double.NaN;
	}

	// In Java 19 you can define the variables x1 X2, Y1 and Y2 in the pattern
	public static double calculateDistanceJava19(Object a, Object b) {
		// The Java 19 way to define the two inner values directly
		if (a instanceof Point(int x1,int y1) && b instanceof Point(int x2,int y2)) {
			return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
		}
		return Double.NaN;
	}

}

// Records were introduced in Java 14
record Point(int x, int y) {
};
