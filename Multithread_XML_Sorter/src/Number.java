import java.util.Comparator;

public class Number{
	private int number;
	private int count;
	
	public Number(int number) {
		this.number = number;
		this.count = 1;
	}
	
	public Number(String number) {
		this.number = Integer.parseInt(number);
		this.count = 1;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	
	public void addCount(int count){
		this.count += count;
	}
	
	public int getCount() {
		return count;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void incrementCount(){
		this.count++;
	}

	public static Comparator<Number> NumberComparator = new Comparator<Number>() {
		
		public int compare(Number num1, Number num2) {

		int number1 = num1.getNumber();
		int number2 = num2.getNumber();
		
		//ascending order
		return number1 - number2;
		}
	};

	public static Comparator<Number> CountComparator = new Comparator<Number>() {
		
		public int compare(Number num1, Number num2) {

		int count1 = num1.getCount();
		int count2 = num2.getCount();
		
		//descending order
		return count2 - count1;
		}
	};
}
