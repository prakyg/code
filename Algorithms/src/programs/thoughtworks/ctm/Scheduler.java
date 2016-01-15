package programs.thoughtworks.ctm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Scheduler {
	Tasks taskList;
	List<Task> t; // replace it with a linked list for faster deletions
	Conference conf;

	public Scheduler(Tasks taskList) {
		this.taskList = taskList;
		t = new LinkedList<Task>(taskList.getAllTasks());
		conf = new Conference();
	}

	public Conference schedule() {
		while (!t.isEmpty()) {
			conf.add(scheduleOneTrack());
		}
		return conf;
	}

	private Track scheduleOneTrack() {
		Session s1 = scheduleOneSession(180);
		Session s2 = scheduleOneSession(240);
		Track track = new Track(s1, s2, 900);
		return track;
	}

	private Session scheduleOneSession(int dur) {
		// System.out.println("Creating session with duration: "+dur +" min");
		Session currSession = new Session(dur);
		int durSum = 0;
		Iterator<Task> itr = t.iterator();
		while (itr.hasNext()) {
			Task i = itr.next();

			if (durSum + i.getDuration() <= dur) {
				// System.out.println(i.toString());
				currSession.add(i);
				durSum += i.getDuration();
				itr.remove();

			}
		}
		return currSession;
	}
}
