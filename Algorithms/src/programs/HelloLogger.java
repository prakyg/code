package programs;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloLogger {

	private static Logger theLogger = Logger.getLogger(HelloLogger.class.getName());

	private String theMessage;

	public HelloLogger(String message) {
		theMessage = message;
	}

	public void sayHello() {
		theLogger.fine("Hello logging!");
		theLogger.log(Level.FINE, "Hello again");
		System.err.println(theMessage);
	}

	public static void main(String[] args) {
		Handler[] handlers = Logger.getLogger("").getHandlers();
		for (int index = 0; index < handlers.length; index++) {
			handlers[index].setLevel(Level.FINE);
		}
		theLogger.setLevel(Level.FINE);
		HelloLogger hello = new HelloLogger("Hello World");
		hello.sayHello();
	}
}
