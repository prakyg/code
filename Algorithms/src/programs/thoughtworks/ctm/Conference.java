package programs.thoughtworks.ctm;

import java.util.ArrayList;
import java.util.List;

public class Conference {
	private final List<Track> trackList;

	public Conference() {
		trackList = new ArrayList<Track>();
	}

	public void add(Track track) {
		trackList.add(track);
	}

	public void print() {
		int i = 1;
		for (Track track : trackList) {
			System.out.println("Track " + i++);
			track.print();
			System.out.println();
			System.out.println();
		}
	}
}
