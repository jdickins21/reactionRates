
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * Jacob Dickinson
 * MasterView
 * 6/23/15
 * Displays GUI calls functions in graphControler to which calls functions from graphModel to return 
 * and display on a 2D point graph in the view.  
 */
public class MasterView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// location of frame at start
	private int initialX = 850;
	private int initialY = 550;
	
	//num of row and col for input side
	private int ROW = 4;
	private int COL = 1;
	
	//num of row col for text box
	private int INcol = 10;
	
	//input panel size
	private int INPANW = 200;
	private int MIDH = 120;
	
	//button dimension
	private int BUTTONDIM = 10;
	
	private double lastEnz = .056;
	private double lastSub = 0;
	
	private PropertyChangeListener listener;
	
	//title to be changed
	private String TITLE = "Reaction Rates 2.0";
	
	//array of data structures
	private Types[] types = Types.typeSet();
	
	//current type in equation
	private Types currType = types[0];
	
	//random field for seeding mutants
	Random rand = new Random();
	
	private JTextArea velocity;
	
	public MasterView(){
		setup();
		this.pack();
	}
	
	public void setup(){
		//needed now, added to view later
		final GraphView graph = new GraphView();
//master JPanel of the JFrame//////////////////////////////////////
		//will hold all other JPanels
		JPanel master = new JPanel();
		master.setBackground(Color.WHITE);
		master.setLayout(new BoxLayout(master, BoxLayout.X_AXIS));
		add(master);
		
//The user input half of the frame/////////////////////////////////
		JPanel input = new JPanel();
		input.setBackground(Color.WHITE);
		//input.setBorder(BorderFactory.createLineBorder(Color.black));
		input.setMaximumSize(new Dimension(INPANW, INPANW));
		input.setLayout(new GridLayout(ROW, COL));
		
	//panel to hold JFormattedTextField on the left and the label "Enzyme" on the right
		JPanel enzyme = new JPanel();
		//color of panel
		enzyme.setBackground(Color.WHITE);
		//set layout
		enzyme.setLayout(new BoxLayout(enzyme, BoxLayout.Y_AXIS));
		
		//label "Enzyme" in the top field of enzyme JPanel
		//Spaces after the label to align with next input box
		JLabel enzLab = new JLabel("    [Enzyme] μM ");
		
		//text box to be added to left in enzyme JPanel
		final JTextField enzIn = new JTextField(".056");
		enzIn.setColumns(INcol);
		enzIn.addPropertyChangeListener("enzyme", listener);
		//add border to text field
		enzIn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//force select all
		enzIn.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						enzIn.selectAll();
					}
				});
			}
		});
		
		//panel to control location
		JPanel enzINpan = new JPanel();
		enzINpan.setBackground(Color.WHITE);
		//enzINpan.setBorder(BorderFactory.createLineBorder(Color.black));
		enzINpan.add(enzIn, BorderLayout.WEST);
		
		//add label and input in order
		enzyme.add(enzLab);
		enzyme.add(enzINpan);
		
	//next panel to hold text field on left and label "Substrate" on right
		JPanel substrt = new JPanel();
		substrt.setBackground(Color.WHITE);
		substrt.setLayout(new BoxLayout(substrt, BoxLayout.Y_AXIS));
		
		//label for text box
		//spaces to align
		JLabel strtLab = new JLabel("    [Substrate] mM");
		
		//text box
		final JTextField strtIn = new JTextField("0");
		strtIn.setColumns(INcol);
		strtIn.addPropertyChangeListener("enzyme", listener);
		strtIn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		strtIn.addFocusListener(new java.awt.event.FocusAdapter(){
			public void focusGained(java.awt.event.FocusEvent evt){
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						strtIn.selectAll();
					}
				});
			}
		});
		
		JPanel subINpan = new JPanel();
		subINpan.add(strtIn, BorderLayout.WEST);
		subINpan.setBackground(Color.WHITE);
		

		substrt.add(strtLab);
		substrt.add(subINpan);
		
	//mutant check box
		JPanel checkPan = new JPanel();
		checkPan.setBackground(Color.WHITE);
		//checkPan.setBorder(BorderFactory.createLineBorder(Color.black));
		JCheckBox mutant = new JCheckBox("mutant", false);
		//checkbox states action
		mutant.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	//found in MutantFuction.java
		    	currType = MutantFunction.mutate(event, enzIn, strtIn, velocity, graph, currType, types);
		    }
		});
		
		checkPan.add(mutant);
		checkPan.setMaximumSize(new Dimension(Integer.MAX_VALUE, checkPan.getMinimumSize().height));
		
		//add everything to input panel
		input.add(checkPan);
		input.add(enzyme);
		input.add(substrt);
		
	//velocity of current point
		JPanel midPan = new JPanel();
		midPan.setBackground(Color.WHITE);
		//midPan.setBorder(BorderFactory.createLineBorder(Color.black));
		midPan.setMaximumSize(new Dimension(INPANW, MIDH));
		
		velocity = new JTextArea("0");
		velocity.setEditable(false);
		velocity.setPreferredSize(new Dimension(50, 20));
		velocity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel velocPan = new JPanel();
		velocPan.setBackground(Color.WHITE);
		velocPan.setLayout(new BoxLayout(velocPan, BoxLayout.Y_AXIS));
		JLabel velocLab = new JLabel("Velocity mM/min:");

		velocPan.add(velocLab);
		velocPan.add(velocity);
		
		midPan.add(velocPan);
		
	//enter button signals controller to relay data to model for manipulation
		JButton enter = new JButton("Enter");
		enter.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e){
				  //found in EnterFunction.java
				  EnterFunction.enter(enzIn, strtIn, velocity, lastEnz, lastSub, currType, graph);
			  }
			});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setMaximumSize(new Dimension(BUTTONDIM, BUTTONDIM));
		
	//clear button to remove points from graph
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//found in ClearFunction.java
				ClearFunction.clear(graph, enzIn, strtIn, velocity);
			}
		});
		
		buttonPanel.add(enter);
		buttonPanel.add(clear);
		input.add(buttonPanel);
		
	//Add 2D graph to render points to represent results of model
		//input panel
		//and mid panel

			master.add(input);
			master.add(midPan);
			master.add(graph);
			
	//Final setup
			setTitle(TITLE);
			setSize(initialX,initialY);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			//set default button
			this.getRootPane().setDefaultButton(enter);
	}
}