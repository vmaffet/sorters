
public class Sorting {
	
	int[] data;
	boolean perf;
	
	public static void main (String[] args) {
		new Sorting();
	}
	
	public Sorting () {
		perf= false;
		genAlea(200,1000);
		bubble();
		select();
		insert();
		merge();
		quick();
		radix();
		String[] yo= {"Vincent", "Cesar", "Max", "Colin"};
		int[] yeah= {43, 633, 213, 2};
		Quick q= new Quick();
		yo= q.quickSortThat(yo, yeah);
		for (int i= 0; i<yo.length; i++) {
			System.out.println(yo[i]);
		}
	}
	
	public void genAlea(int n, int max) {
		data= new int[n];
		for (int i= 0; i<n; i++) {
			data[i]= (int)(Math.random()*max);
		}
	}
	
	public void bubble () {
		int[] output= copy();
		Bubble b= new Bubble(output, new HistoDisp(), perf);
		Thread sinking= new Thread(b);
		sinking.start();
	}
	
	public void select () {
		int[] output= copy();
		Select s= new Select(output, new HistoDisp(), perf);
		Thread selection= new Thread(s);
		selection.start();
	}
	
	public void insert () {
		int[] output= copy();
		Insert i= new Insert(output, new HistoDisp(), perf);
		Thread put= new Thread(i);
		put.start();
	}
	
	public void merge () {
		int[] output= copy();
		Merge m= new Merge(output, new HistoDisp(), perf);
		Thread arr= new Thread(m);
		arr.start();
	}
	
	public void quick () {
		int[] output= copy();
		Quick q= new Quick(output, new HistoDisp(), perf);
		Thread qqq= new Thread(q);
		qqq.start();
	}
	
	public void radix () {
		int[] output= copy();
		Radix r= new Radix(output, new HistoDisp(), perf);
		Thread rad= new Thread(r);
		rad.start();
	}
	
	public static boolean isSorted (int[] tab) {
		for (int i= 1; i<tab.length; i++) {
			if (tab[i] < tab[i-1]) {
				return false;
			}
		}
		return true;
	}
	
	public int[] copy () {
		int[] mirror= new int[data.length];
		for (int i= 0; i<data.length; i++) {
			mirror[i]= data[i];
		}
		return mirror;
	}
}
