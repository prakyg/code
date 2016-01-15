package programs.designPatterns.CommandPattern;

public class Light {
	public Light() {
		super();
	}

	public void on() {
		System.out.println("Light Turned on");
	}

	public void off() {
		System.out.println("Light Turned off");
	}
}