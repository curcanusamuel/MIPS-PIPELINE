package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class First extends JFrame {

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

	public First() {
		setBackground(new Color(228, 226, 205));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(620, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 226, 205));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("Polynomial Calculator");
		label.setSize(580, 53);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label);

		label2 = new JLabel("First Polynomial:");
		label2.setBounds(12, 69, 151, 32);
		label2.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		contentPane.add(label2);

		label3 = new JLabel("Second Polynomial:");
		label3.setBounds(12, 105, 153, 32);
		label3.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		contentPane.add(label3);

		firstText = new JTextField();
		firstText.setBounds(182, 69, 410, 32);
		firstText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(firstText);
		firstText.setColumns(10);

		secondText = new JTextField();
		secondText.setBounds(182, 105, 410, 32);
		secondText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		secondText.setColumns(10);
		contentPane.add(secondText);

		result = new JLabel("Result:");
		result.setBounds(12, 141, 58, 37);
		result.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		contentPane.add(result);

		resultLabel = new JLabel("-");
		resultLabel.setBackground(new Color(255, 255, 255));
		resultLabel.setBounds(182, 141, 410, 37);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 141, 410, 40);
		contentPane.add(scrollPane);
		resultLabel.setBackground(new Color(255, 255, 255));
		resultLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		resultLabel.setHorizontalAlignment(SwingConstants.LEADING);

		scrollPane.setViewportView(resultLabel);

		addButton = new JButton("Add");
		addButton.setBackground(new Color(204, 153, 102));
		addButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(28, 203, 151, 48);
		contentPane.add(addButton);

		substractButton = new JButton("Substract");
		substractButton.setBackground(new Color(204, 153, 102));
		substractButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		substractButton.setBounds(223, 203, 151, 48);
		contentPane.add(substractButton);

		divideButton = new JButton("Divide");
		divideButton.setBackground(new Color(204, 153, 102));
		divideButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		divideButton.setBounds(427, 203, 151, 48);
		contentPane.add(divideButton);

		derivateButton = new JButton("Derivate");
		derivateButton.setBackground(new Color(204, 153, 102));
		derivateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		derivateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		derivateButton.setBounds(223, 272, 151, 48);
		contentPane.add(derivateButton);

		multiplicateButton = new JButton("Multiplicate");
		multiplicateButton.setBackground(new Color(204, 153, 102));
		multiplicateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		multiplicateButton.setBounds(28, 272, 151, 48);
		contentPane.add(multiplicateButton);

		integrateButton = new JButton("Integrate");
		integrateButton.setBackground(new Color(204, 153, 102));
		integrateButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		integrateButton.setBounds(427, 272, 151, 48);
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

	public void showStringError(String error) {
		JOptionPane.showMessageDialog(this, error);
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
