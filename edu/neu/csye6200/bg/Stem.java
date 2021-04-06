/**
 *  Filename: Assign5.java
author name:YUE LIU
NU number:001353606
 */
package edu.neu.csye6200.bg;

import java.util.ArrayList;



public class Stem {
	int age;
	double x1;
	double y1;
	double x2;
	double y2;
	double angle;

//	create StemList
	public ArrayList<Stem> stemList = new ArrayList<Stem>();
	static Stem root = new Stem(0, 500.0, 400.0, 500.0, 300.0, Math.PI / 2);
	
//  create the Stem stucture
	public Stem(int age, double x1, double y1, double x2, double y2, double angle) {
		this.age=age;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.angle=angle;
		// TODO Auto-generated constructor stub
	}
	public Stem() {
		
	}

	public void getGen() {
		// TODO Auto-generated method stub
		root.age = MyFrame.getGeneration();
		//System.out.println("get gener");
	}
}
