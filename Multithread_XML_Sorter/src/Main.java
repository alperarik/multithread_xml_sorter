public class Main {
	
	private final static int NUM_OF_THREAD = 10;
	
	public static void main(String[] args) {
		
		System.out.println("XML files are generating");
		System.out.println();
		
		Thread[] threads =  new Thread[NUM_OF_THREAD];
		
		for(int i = 0; i < NUM_OF_THREAD; i++){
			
			threads[i] = new Thread(new FileGenerator(String.valueOf(i)));
			threads[i].start();
		}

		waitThreads(threads);
		
		System.out.println("\nXML files have created");
		System.out.println("------------------------");
		
		
		System.out.println("XML files are sorting");
		System.out.println();
		
		for(int i = 0; i < NUM_OF_THREAD; i++){
			
			threads[i] = new Thread(new FileSorter(String.valueOf(i)));
			threads[i].start();
		}
		
		waitThreads(threads);
		
		System.out.println("XML files have sorted");
		System.out.println("------------------------");
		
		System.out.println("XML files are merging");
		System.out.println();
		
		Thread merge = new Thread(new FileMerger());
		merge.start();
		
		waitThreads(merge);
		
		System.out.println("\nXML files have merged");
		System.out.println("------------------------");
		
	}

	private static void waitThreads(Thread[] threads){
		for(Thread t : threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void waitThreads(Thread thread){
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
