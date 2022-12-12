package se77;

public class SwitchPatternMatchingExample {

	@SuppressWarnings("preview")
	public static void printGreeting(Employee employee) {

		// employee can be directly checked for type and casted
		switch (employee) {
		
		// the variable can even be used for another boolean expression in a when clause
		case PremiumEmployee pe when pe.getName().equals("Kurt") -> System.out.println(pe.getName() + " is the best!");

		case PremiumEmployee pe -> System.out.println("Hello PremiumEmployee " + pe.getName() + "!");

		case SecondClassEmployee sce -> System.out.println("Hello SecondClassEmployee " + sce.getName() + "!");

		case Employee e -> System.out.println("Hello Employee " + e.getName() + "!");
		}
	}

}

class Employee {
	private String name;

	public String getName() {
		return name;
	}

	public Employee(String name) {
		this.name = name;
	}

}

class PremiumEmployee extends Employee {

	public PremiumEmployee(String name) {
		super(name);
	}

}

class SecondClassEmployee extends Employee {

	public SecondClassEmployee(String name) {
		super(name);
	}

}
