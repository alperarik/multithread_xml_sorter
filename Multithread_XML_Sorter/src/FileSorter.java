
import java.util.ArrayList;
import java.util.Collections;

public class FileSorter implements Runnable{
	private String fileID;
	private String fileName;
	private ArrayList<Number> numbers;
	
	public FileSorter(String fileID) {
		this.fileID = fileID;
		this.fileName = "randomNumbers-"+fileID+".xml";
	}
	
	public void run() {
		
		numbers = FileReader.readXML(this.fileName,false);
		sort(numbers);
		FileGenerator.createXML("sortedNumbers-"+this.fileID+".xml", numbers, true);
	}

	private void sort(ArrayList<Number> numbers){
		Collections.sort(numbers, Number.NumberComparator);
	}
	
}
