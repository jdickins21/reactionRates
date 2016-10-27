
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * Jacob Dickinson
 * Graph
 * 6/24/15
 * initialize graph to be created in graphView 
 */
public class GraphView extends JPanel{
	
	//panel max size
	private int PANMAX = 550;
	
	//graph max size
	private int GRAPHMAX = 500;
	
	private final ChartPanel charPan;
	
	private static final long serialVersionUID = 1L;
	
	//Holds all points inputed
	private XYSeries list = new XYSeries("List");
	
	public GraphView(){
		this.setBackground(Color.WHITE);
		this.setMaximumSize(new Dimension(PANMAX, PANMAX));
		
		//panel for graph 
		JPanel gPanel = new JPanel();
		gPanel.setLayout(new BoxLayout(gPanel, BoxLayout.Y_AXIS));
		gPanel.setBackground(Color.WHITE);
	
	//Graph
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		charPan = createPanel();
		//off to stop confusing zooms
		charPan.setDomainZoomable(false);
		charPan.setRangeZoomable(false);
		charPan.setPopupMenu(null);
		charPan.setPreferredSize(new Dimension(GRAPHMAX, GRAPHMAX));
		charPan.getChart().removeLegend();
		gPanel.add(charPan);
		
	//Zoom Panel	
		JPanel zPan = new JPanel();
		zPan.setPreferredSize(new Dimension(20, 50));
		zPan.setLayout(new FlowLayout());
		zPan.setBackground(Color.WHITE);
		
		//Buttons and labels added to gPanel
		JButton min = new JButton("-");
		min.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				XYPlot plot = (XYPlot)charPan.getChart().getPlot();
				ValueAxis xAxis = plot.getDomainAxis();
				xAxis.setRange(0, (xAxis.getUpperBound()) / 2);
			}
		});
		
		JLabel zoom = new JLabel("zoom");
		
		JButton max = new JButton("+");
		max.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				XYPlot plot = (XYPlot)charPan.getChart().getPlot();
				ValueAxis xAxis = plot.getDomainAxis();
				xAxis.setRange(0, (xAxis.getUpperBound()) * 2);
			}
		});
		//blank for alignment correction
		JLabel blank = new JLabel("          ");
		blank.setBackground(Color.WHITE);
		
		//add all components to the zoom panel
		zPan.add(blank);
		zPan.add(min);
		zPan.add(zoom);
		zPan.add(max);
		
	//Axis Labels
		//x axis added to gPanel
		//y axis in the this panel
		JLabel x = new JLabel("[S](mM)");
		x.setBackground(Color.WHITE);
		JLabel y = new JLabel();
		ImageIcon yIcon = new ImageIcon("image/y.png");
		y.setIcon(yIcon);
		
		//labels added
		gPanel.add(x);
		this.add(y, BorderLayout.WEST);
		
		//add zoom buttons
		gPanel.add(zPan, BorderLayout.SOUTH);
		this.add(gPanel, BorderLayout.CENTER);
	}
	/*
	 * creates the graph for the panel
	 */
	private ChartPanel createPanel(){
		XYSeriesCollection xyCollection = new XYSeriesCollection();
		xyCollection.addSeries(list);
		
		//graph
		JFreeChart chart = ChartFactory.createScatterPlot(null, null, null, (XYDataset)xyCollection, PlotOrientation.VERTICAL, true, true, false);
		
		//struct holds all data points
		XYPlot xyPlot = chart.getXYPlot();
        xyPlot.setDomainCrosshairVisible(false);
        xyPlot.setRangeCrosshairVisible(false);
        
        //renders data points
        XYItemRenderer render = xyPlot.getRenderer();
        Shape shape1 = new Rectangle2D.Double(4, 4, 4, 4);
        render.setSeriesShape(0, shape1);
		render.setSeriesPaint(0, Color.BLACK);
		
		//scale of axis
		TickUnitSource ticks = NumberAxis.createIntegerTickUnits();
		
		//x axis
		NumberAxis domain = newZeroBasedNumberAxis();
        xyPlot.setDomainAxis(domain);
		domain.setStandardTickUnits(ticks);
        domain.setVerticalTickLabels(true);
        domain.setAutoRangeMinimumSize(10, true);
        
        //y axis
		NumberAxis range = newZeroBasedNumberAxis();
        xyPlot.setRangeAxis(range);
        range.setStandardTickUnits(ticks);
        range.setAutoRangeMinimumSize(10, true);
      
		return new ChartPanel(chart);
	}
	
	/*
	 * returns series to be cleaned out by the clear button
	 */
	public XYSeries getSeries(){
		return list;
	}
	
	/*
	 * Number axis to keep x and y axis above negative when zooming out
	 */
	@SuppressWarnings("serial")
	private static NumberAxis newZeroBasedNumberAxis() {
		NumberAxis axis = new NumberAxis(null /* maybe title */) {
            @Override
            public Range getRange() {
                Range sRange = super.getRange();
                // ensure lowerBound < upperBound to prevent exception
                return new Range(
                        Math.max(0, sRange.getLowerBound()),
                        Math.max(1e-8, sRange.getUpperBound()));
            }
        };
        return axis;
    }
	/*
	 * return back to original state after data points are erased when clicking the clear button
	 */
	public void restoreAutoBounds(){
		charPan.restoreAutoDomainBounds();
        charPan.restoreAutoRangeBounds();
	}
}