package model;

public class Counter {
	int count;
	public Counter() {
		this.count = 0;
	}
	
	public void inc() {
		this.count++;
	}
	
	public int getCount() {
		return this.count;
	}
}
