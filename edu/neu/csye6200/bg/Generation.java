package edu.neu.csye6200.bg;
/*author name:YUE LIU
NU number:001353606*/
import java.util.ArrayList;

public class Generation {
	@SuppressWarnings("rawtypes")
	ArrayList[] gen = new ArrayList[10];
	public void add(int i) {
		gen[i]=Stem.root.stemList;
	}
}
