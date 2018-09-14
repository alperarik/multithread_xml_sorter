import java.util.ArrayList;
import java.util.PriorityQueue;


public class FileMerger implements Runnable{

	public void run() {
		ArrayList<ArrayList<Number>> sortedNumbersList =  new ArrayList<ArrayList<Number>>();
		
		//Sorted files are reading
		for(int  i = 0; i < Constants.NUM_OF_FILE; i++){
			ArrayList<Number> tmp;
			String fileName = "sortedNumbers-" + i + ".xml";
			System.out.println(fileName);
			tmp = FileReader.readXML(fileName,true);

			sortedNumbersList.add(tmp);
		}
		
		ArrayList<Number> mergedArray = mergeKSortedArray(sortedNumbersList);
		
		System.out.println("\nCount of each number in merged file");
		for(Number n : mergedArray){
			System.out.format("%7d - %7d\n",n.getNumber(),n.getCount());
		}
	}
	
	public ArrayList<Number> mergeKSortedArray(ArrayList<ArrayList<Number>> sortedNumbersList) {
		
		PriorityQueue<Number> queue = new PriorityQueue<Number>(Number.NumberComparator);
		
		//add arrays to heap
		for (int i = 0; i < sortedNumbersList.size(); i++) {
			for(int j = 0; j < sortedNumbersList.get(i).size(); j++){
				queue.add(sortedNumbersList.get(i).get(j));
			}
		}
		
		ArrayList<Number> result = new ArrayList<Number>();
 
		//while heap is not empty
		while(!queue.isEmpty()){
			Number number = queue.poll();
			addNumber(result, number);
		}
 
		return result;
	}
	
	private void addNumber(ArrayList<Number> numbers, Number number){

		for(int i = 0; i < numbers.size(); i++){
			if(numbers.get(i).getNumber() == number.getNumber()){
				numbers.get(i).addCount(number.getCount());
				return;
			}
		}
		
		numbers.add(number);
	}

}
