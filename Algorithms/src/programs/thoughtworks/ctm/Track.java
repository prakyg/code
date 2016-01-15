package programs.thoughtworks.ctm;

import java.util.ArrayList;
import java.util.List;

public class Track {
	private final List<Session> sessionList;
	private final int dayStartTime;

	public Track(Session morn, Session eve, int dayStartTime) {
		this.sessionList = new ArrayList<Session>(4);
		this.dayStartTime = dayStartTime;
		sessionList.add(morn);
		sessionList.add(Session.getLunchSession()); // lunch
		sessionList.add(eve);
		sessionList.add(Session.getNetworkingSession()); // networking
	}

	public void print() {
		for (Session session : sessionList) {
			session.print();
		}
	}
}
