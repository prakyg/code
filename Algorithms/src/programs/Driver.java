package programs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.openjdk.jol.info.ClassLayout;

public class Driver implements Serializable {
	public Driver() {
		super();
	}

	private String val;
	private static final String fileName = "SerializationTest.txt";

	public static void main(String[] args) throws Exception {
		Driver driver = new Driver();
		driver.val = "Hello Mr. Bing";

		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(driver);
		fos.close();

		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Driver d = (Driver) ois.readObject();
		ois.close();
		System.out.println("Value of string " + d.val);
	}
}
