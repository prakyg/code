package programs.thoughtworks.ctm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Reads all the input data specified and returns a Tasks instance?
public abstract class Reader {
	private static String LIGHTNING = "LIGHTNING";

	private Reader() {
	}

	public static Tasks readFromFile(String fileName) throws FileNotFoundException, IOException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Please place file in the application_root folder");
			e.printStackTrace();
			throw new FileNotFoundException();
		}
		String line = null;
		Tasks taskList = new Tasks();
		line = br.readLine();
		while (line != null) {
			// System.out.println(line);
			taskList.add(processLineIntoTask(line));
			line = br.readLine();
		}
		// taskList.print();
		return taskList;
	}

	private static Task processLineIntoTask(String line) throws IOException{
        Task task = new Task("",1);
        String duration;Integer dura;
        if (line.trim().toUpperCase().endsWith(LIGHTNING)){
            return Task.newLightningTask(line.substring(0,line.length()-LIGHTNING.length())); 
        }else{
            String[] arr = line.split("\\d+", 2);
            assert arr.length ==2;
            if(arr.length == 2) {
                duration = line.substring(arr[0].length()).trim();
                dura = Integer.valueOf(duration.substring(0,duration.length()-3));
                if (dura <=360)
                    return new Task(arr[0].trim(),dura);
                else
                    return null; //return empty task here
            }
            else{
                throw new IOException("Task is not in correct format");
            }
        }
    }
}
