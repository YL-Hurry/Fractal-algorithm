/**
 * 
 */
package edu.neu.csye6200.bg;



/**
author name:YUE LIU
NU number:001353606
 */
public class BGRule {

//  create child stem rule1
	public void child1(Stem s) {
		Stem.root.stemList.add(Stem.root);
		if(s.age>1) {
			Stem s1 = new Stem();
			Stem.root.stemList.add(grow(s1,s, (Math.PI) / 6));
			//System.out.println((int)s.x1 +"  "+(int)s.x2+"  "+(int)s.y1+"  "+(int)s.y2);
			Stem s2 = new Stem();
			Stem.root.stemList.add(grow(s2,s,-(Math.PI) / 6));
			child1(s1);
			child1(s2);
		}
	}
//  create child stem rule2
	public void child2(Stem s) {
		Stem.root.stemList.add(Stem.root);
		if(s.age>1) {
			Stem s1 = new Stem();
			Stem.root.stemList.add(grow(s1,s,(Math.PI) / 7));
			//System.out.println((int)s.x1 +"  "+(int)s.x2+"  "+(int)s.y1+"  "+(int)s.y2);
			Stem s2 = new Stem();
			Stem.root.stemList.add(grow(s2,s,0));
			Stem s3 = new Stem();
			Stem.root.stemList.add(grow(s3,s,-(Math.PI) / 7));
			child2(s1);
			child2(s2);
			child2(s3);
		}
	}
	//the grow method
	private Stem grow(Stem s1,Stem s2,double x) {
		s1.age=s2.age-1;
		s1.x1 = s2.x2;
		s1.y1 = s2.y2;
		s1.angle = s2.angle + x;
		s1.x2 = s1.x1 - 50 * Math.cos(s1.angle);
		s1.y2 = s1.y1 - 50 * Math.sin(s1.angle);
		return s1;
	}

}
