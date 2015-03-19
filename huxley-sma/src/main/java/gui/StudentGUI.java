package gui;

import gui.actions.CancelAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import agents.student.StudentAgent;

public class StudentGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private LogoPane logoPane;
	private DescriptionPane descriptionPane;
	private RequestRecommendationPane requestRecommendationPane;
	private JButton closeButton;
	

	private StudentAgent myAgent;
	
	static Logger logger = LoggerFactory.getLogger(StudentGUI.class);
	
	public StudentGUI(StudentAgent a) {
		
		super("Student");
		myAgent = a;
		
		String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(laf);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logoPane = new LogoPane();
		requestRecommendationPane = new RequestRecommendationPane(this);
		// TODO adicionar instancia do problema para pegar titulo e descrição
		descriptionPane = new DescriptionPane("");
		descriptionPane.setVisible(false);
		
		initComponents();

		setSize(500, 450);
		this.setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void initComponents() {
		JPanel main = new JPanel(new MigLayout("", "grow", "[][][][][grow][]"));
		
		closeButton = new JButton(new CancelAction(this));
		
		//Make the agent terminate when the user hit closeButton
		closeButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myAgent.doDelete();
				
			}
		});
		
		// Make the agent terminate when the user closes 
		// the GUI using the X button	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		});
		
		main.add(logoPane, "grow, wrap");
		main.add(new JSeparator(), "spanx, gapleft rel, growx,wrap");
		main.add(requestRecommendationPane, "grow, wrap");
		main.add(new JSeparator(), "spanx, gapleft rel, growx,wrap");
		main.add(descriptionPane, "grow, wrap");
		main.add(closeButton, "center");

		setContentPane(main);
	}

	public StudentAgent getMyAgent() {
		return myAgent;
	}

	public JButton getRequestButton() {		
		return requestRecommendationPane.getRequestRecommendationButton();		
	}
	
	public void showProblem(String problemTitle) {
		
		descriptionPane.setTitle(problemTitle);
		//descriptionPane.setDescription(problemDescription);
		descriptionPane.revalidate();
		descriptionPane.repaint();
		descriptionPane.setVisible(true);
		
	}
	
	
	
}