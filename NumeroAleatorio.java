import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NumeroAleatorio{
	int size = 5;
	boolean born=false;
	ArrayList<Integer> list;



	public NumeroAleatorio(){
		list = new ArrayList<Integer>(size);
		init();
	}

	public NumeroAleatorio(int s){
		this.size = s;
		list = new ArrayList<Integer>(size);
		init();
	}

	public NumeroAleatorio(int s, boolean t){
		this.size = s;
		this.born = t;
		list = new ArrayList<Integer>(size);
		init();
	}

	private void init(){
		for(int i = 1; i <= size; i++) {
            list.add(i-1);
        }
    }

    public int get(){
		int numtemp = 0;
		if (list.isEmpty()){
			born = false;
			numtemp = 0;
		}
		else {
        	Random rand = new Random();
        	int index = rand.nextInt(list.size());
       		numtemp = list.get(index);
        if (born){
        	list.remove(index);	
        } else { }
		}
	return numtemp;
	}


	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void show(){
	for (int i = 0; i < list.size(); i++){	
		System.out.println(""+list.get(i));
	}
	System.out.println("--------------------");
}

}