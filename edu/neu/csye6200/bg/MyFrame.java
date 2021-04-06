package edu.neu.csye6200.bg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.java.dev.designgridlayout.DesignGridLayout;

/**
author name:YUE LIU
NU number:001353606
 *
 */
public class MyFrame extends BGApp implements Runnable{
	private Logger log = Logger.getLogger(MyFrame.class.getName());
	private JFrame frame = null;
	private JPanel mainPanel = null;
	private JPanel drawPanel = null;
	
	private JButton startBtn = null;
	private JButton stopBtn = null;
	private JButton exitBtn = null;

	private boolean sign = true;
	private boolean done = false;

	
	private JTextField nameTF = new JTextField();	// A name input field
	private JTextField idTF = new JTextField();// A id input field
	private static JTextField genTF = new JTextField();// a number of generations input field
	private JComboBox<String> jcb = new JComboBox<String>();// a comboBox to change rule

	static int rule = 1;
	
	static Object lock = new Object();
	// constructor
	public MyFrame() {
		log.info("App Started");
		initGUI();
		
	}

	//initialize the GUI
	public void initGUI() {
		frame = new JFrame();
		frame.setTitle("PlantGrowApp");
		frame.setLocation(100, 200);//set the frame's location
		frame.setSize(1000, 600); // set the size to something reasonable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if we press the close button, exit
		frame.setLayout(new BorderLayout());
		frame.add(getMainPanel(), BorderLayout.NORTH);
		frame.add(getDrawPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

	//create a panel that we'll draw into
	public JPanel getMainPanel() {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout()); // flow from left to right
		DesignGridLayout playout =  new DesignGridLayout(mainPanel);
		
		//create the start button
		startBtn = new JButton("Start"); // create button instances
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sign = true;
				synchronized (lock) {
					lock.notifyAll();
				}
			}
		});
		//create the stop button
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sign = false;
			}
		});
		//create the exit button
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		/**
		 *  ---------using designGridLayout-----------
		 */
		nameTF.setText("yue liu");// add user name
		idTF.setText("001353606");// add id
		
		playout.row().grid(new JLabel("Name")).add(nameTF);
		System.out.println("start");
		playout.row().grid(new JLabel("ID")).add(idTF);
		playout.emptyRow();
		playout.row().left().add(startBtn,stopBtn,exitBtn);//add button to the panel
		
		//create the comboBox
		String str1[] = {"rule1", "rule2"};
		jcb = new JComboBox<String>(str1);
		playout.row().grid(new JLabel("rule")).add(jcb);
		//create the input number of generations
		playout.row().grid(new JLabel("generations")).add(genTF);
		
		jcb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// if select one item in JComboBox
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// select which rule
					if (jcb.getSelectedItem().equals("rule1"))
						rule = 1;
					else
						rule = 2;
				}
			}
		});
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		return mainPanel;
	}
	//get the generation that user entered
	static int getGeneration() {
		String gen = genTF.getText();
		int generation = 0;
		//judge the generation are not 0
		if (gen.equals("0")) {
			JOptionPane.showMessageDialog(null, "enter the generation", 
					"warning",JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		if (gen.equals("")) {
			JOptionPane.showMessageDialog(null, "enter the generation", 
					"warning",JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		generation = Integer.parseUnsignedInt(gen);
		//System.out.println(a);
		return generation;
	}
	
	//create the draw panel
	public JPanel getDrawPanel() {
		drawPanel = new MyPanel();
		return drawPanel;	
	}
	//create the thread
	public void run() {
		new Thread(new Runnable() {
			public void run() {
				do {
					synchronized (lock) {//add the synchronized lock
						try {
							MyPanel.ctr = 0;
							lock.wait();
							do {
								try {
									Thread.sleep(40);
									if (!sign)
										lock.wait();
									MyPanel.ctr += 1;
									drawPanel.repaint();
									//System.out.println(MyPanel.ctr);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}while(MyPanel.ctr < Stem.root.stemList.size());
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						//System.out.println("uithread");
						try {
							Thread.sleep(40);
							MyPanel.ctr = 0;
							//System.out.println("endpainting");
							Stem.root.stemList.clear();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}while(!done);
			}
		}).start();
	}
	public void exit() {
	    	frame.dispose();
	    	System.exit(0);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
