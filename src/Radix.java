import java.util.LinkedList;

public class Radix implements Runnable {
	
	int[] data;
	HistoDisp graph;
	long tic, toc;
	boolean perf;
	
	public Radix (int[] input, HistoDisp h, boolean performance) {
		data= input;
		graph= h;
		perf= performance;
	}
	
	public void run() {
		tic= System.nanoTime();
		String[] info= new String[data.length];
		int size= 0;
		for (int i= 0; i<info.length; i++) {
			info[i]= Integer.toString(data[i]);
			size= info[i].length()>size?info[i].length():size;
		}
		for (int k= 0; k<size; k++) {
			info= sortOnDigit(info, k);
			if (!perf) { display(info);}
		}
		for (int j= 0; j<data.length; j++) {
			data[j]= Integer.parseInt(info[j]);
		}
		toc= System.nanoTime();
		System.out.println("Radix  : "+(toc-tic)+"  Success : "+Sorting.isSorted(data));
	}
	
	public String[] sortOnDigit (String[] input, int n) {
		LinkedList<LinkedList<String>> tab= new LinkedList<LinkedList<String>>();
		for (int k= 0; k<10; k++) {
			tab.add(new LinkedList<String>());
		}
		for (int i= 0; i<input.length; i++) {
			try{
				tab.get(Integer.parseInt(String.valueOf(input[i].charAt(input[i].length()-1-n)))).addLast(input[i]);
			} catch (Exception e) {
				tab.get(0).addLast(input[i]);
			}
		}
		int q= 0;
		for (int j= 0; j<10; j++) {
			while(!tab.get(j).isEmpty()) {
				input[q]= tab.get(j).pollFirst();
				q++;
			}
		}
		return input;
	}
	
	public void display (String[] input) {
		for (int j= 0; j<data.length; j++) {
			data[j]= Integer.parseInt(input[j]);
		}
		graph.setData(data);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
	}

}
