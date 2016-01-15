package programs.thoughtworks.ctm;

public class ConferenceTrackManager {
	public ConferenceTrackManager() {
		super();
	}

	public static void main(String[] args) throws Exception {
		Tasks listOfAll = Reader.readFromFile("test1.txt");
		Conference conf = new Scheduler(listOfAll).schedule();
		Writer.writeToConsole(conf);

		/*
		 * String s = "Writing Fast Tests Against Enterprise Rails 60min";
		 * String arr[] = s.split("\\d+", 2); System.out.println(arr[0]);
		 * System.out.println(arr[1]);
		 */
	}
}
