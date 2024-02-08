package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FirstPage extends JFrame {
	private JPanel contentPane;
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JTextField firstText;
	private JTextField secondText;
	private JButton addButton;
	private JLabel resultLabel;
	private JLabel result;
	private JButton substractButton;
	private JButton divideButton;
	private JButton derivateButton;
	private JButton multiplicateButton;
	private JButton integrateButton;
	private JScrollPane scrollPane;

	public FirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(620, 374);
		setBackground(new Color(228, 226, 205));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 226, 205));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JPanel panelAll = new JPanel();
		contentPane.add(panelAll);
		panelAll.setBounds(10,50,580,100);
		label = new JLabel("Polynomial Calculator");
		label.setSize(580, 53);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 2));
		panel1.setAlignmentX(LEFT_ALIGNMENT);
		label2 = new JLabel("First Polynomial:");
		
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 19));

		firstText = new JTextField();
	
		firstText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		firstText.setPreferredSize(new Dimension(275, 30));
		label3 = new JLabel("Second Polynomial:");
		label3.setFont(new Font("Times New Roman", Font.PLAIN, 19));

		secondText = new JTextField();
		
		secondText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		secondText.setPreferredSize(new Dimension(100, 20));
		
		
		result = new JLabel("Result:");
		
		result.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		resultLabel = new JLabel("-");
		resultLabel.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(resultLabel);
		
		resultLabel.setBackground(new Color(255, 255, 255));
		resultLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		resultLabel.setHorizontalAlignment(SwingConstants.LEADING);

		
		
		addButton=new JButton("Add");
		addButton.setPreferredSize(new Dimension(80,48));
		addButton.setBackground(new Color(204, 153, 102));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addButton.setBounds(28, 203, 151, 48);
		
		
		substractButton = new JButton("Substract");
		substractButton.setBackground(new Color(204, 153, 102));
		substractButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		substractButton.setBounds(223, 203, 151, 48);
	

		divideButton = new JButton("Divide");
		divideButton.setBackground(new Color(204, 153, 102));
		divideButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		divideButton.setBounds(427, 203, 151, 48);
		

		derivateButton = new JButton("Derivate");
		derivateButton.setBackground(new Color(204, 153, 102));
		derivateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		derivateButton.setBounds(223, 272, 151, 48);
	

		multiplicateButton = new JButton("Multiplicate");
		multiplicateButton.setBackground(new Color(204, 153, 102));
		multiplicateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		multiplicateButton.setBounds(28, 272, 151, 48);
		

		integrateButton = new JButton("Integrate");
		integrateButton.setBackground(new Color(204, 153, 102));
		integrateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		integrateButton.setBounds(427, 272, 151, 48);
		
	
		
		contentPane.add(label);
		panel1.add(label2);
		panel1.add(firstText);
		panel1.add(label3);
		panel1.add(secondText);
		panel1.add(result);
		panel1.add(scrollPane);
		panelAll.add(panel1);
		contentPane.add(addButton);
		contentPane.add(substractButton);
		contentPane.add(divideButton);
		contentPane.add(multiplicateButton);
		contentPane.add(derivateButton);
		contentPane.add(integrateButton);
		
		

	}


	public String getFirstInput() {
		return this.firstText.getText();
	}

	public String getSecondInput() {
		return this.secondText.getText();
	}

	public void updateResultLabel(String newValue) {
		this.resultLabel.setText(newValue);
	}
	public void showStringError(String error)
	{
		JOptionPane.showMessageDialog(this,error);
	}
	public void addListener(ActionListener actionListener) {
		this.addButton.addActionListener(actionListener);
	}

	public void substractListener(ActionListener actionListener) {
		this.substractButton.addActionListener(actionListener);
	}

	public void multiplicateListener(ActionListener actionListener) {
		this.multiplicateButton.addActionListener(actionListener);
	}

	public void divideListener(ActionListener actionListener) {
		this.divideButton.addActionListener(actionListener);
	}

	public void derivateListener(ActionListener actionListener) {
		this.derivateButton.addActionListener(actionListener);
	}

	public void integrateListener(ActionListener actionListener) {
		this.integrateButton.addActionListener(actionListener);
	}
}
