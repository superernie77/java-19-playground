package se77;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SwitchPatternMatchingExampleTest {

	@Test
	public void testGreetings() {
		Employee e = new Employee("Wurt");
		
		Employee pe = new PremiumEmployee("Bert");
		
		Employee kurt = new PremiumEmployee("Kurt");
		
		Employee sce = new SecondClassEmployee("Ernie");
		
		List.of(e,pe,sce, kurt).forEach(SwitchPatternMatchingExample::printGreeting);
		
	}
}
