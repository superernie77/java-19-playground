package se77;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static se77.PatternMatchingExamples.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class PatternMatchingExampleTest {
	
	@Test
	public void testCalculateDistance() {
		Point a = new Point(1, 0);
		
		Point b = new Point(0, 1);
		
		double distance = calculateDistance(a, b);
		
		assertThat(distance, closeTo(1.41d, 0.01d));
	}

}
