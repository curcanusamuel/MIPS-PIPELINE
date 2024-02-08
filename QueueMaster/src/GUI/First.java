package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class First extends JFrame {

	private JPanel contentPane;
	private Label sa;
	private Label label;
	private JTextField clientsField;
	private JTextField queuesField;
	private JTextField simulationField;
	private JTextField minArrField;
	private JTextField maxArrField;
	private JTextField minServField;
	private JTextField maxServField;
	private Label label_3;
	private JTextPane averageWait;
	private Label label_4;
	private JTextPane averageServ;
	private Label label_5;
	private JTextPane peakHour;
	private JButton runButton;
	private JTextPane resultPane;
	public First() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 627);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(228, 226, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		sa = new Label("Simulation");
		sa.setBounds(21, 0, 547, 59);
		sa.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		sa.setAlignment(Label.CENTER);
		contentPane.add(sa);

		label = new Label("Clients:");
		label.setBounds(21, 80, 85, 37);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label);

		Label label_1 = new Label("Queues:");
		label_1.setBounds(21, 123, 91, 37);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_1);

		Label label_1_1 = new Label("Simulation time:");
		label_1_1.setBounds(21, 166, 167, 37);
		label_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_1_1);

		Label label_2 = new Label("Min Arrival Time:");
		label_2.setBounds(363, 80, 174, 37);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_2);

		Label label_2_1 = new Label("Max Arrival Time:");
		label_2_1.setBounds(363, 123, 185, 37);
		label_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_2_1);

		Label label_2_2 = new Label("Min Service Time:");
		label_2_2.setBounds(363, 166, 185, 37);
		label_2_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_2_2);

		Label label_2_3 = new Label("Max Service Time:");
		label_2_3.setBounds(363, 214, 194, 37);
		label_2_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(label_2_3);

		clientsField = new JTextField();
		clientsField.setBounds(112, 88, 185, 29);
		contentPane.add(clientsField);
		clientsField.setColumns(10);

		queuesField = new JTextField();
		queuesField.setColumns(10);
		queuesField.setBounds(112, 131, 185, 29);
		contentPane.add(queuesField);

		simulationField = new JTextField();
		simulationField.setColumns(10);
		simulationField.setBounds(194, 174, 103, 29);
		contentPane.add(simulationField);

		minArrField = new JTextField();
		minArrField.setColumns(10);
		minArrField.setBounds(565, 88, 174, 29);
		contentPane.add(minArrField);

		maxArrField = new JTextField();
		maxArrField.setColumns(10);
		maxArrField.setBounds(564, 131, 175, 29);
		contentPane.add(maxArrField);

		minServField = new JTextField();
		minServField.setColumns(10);
		minServField.setBounds(563, 174, 174, 29);
		contentPane.add(minServField);

		maxServField = new JTextField();
		maxServField.setColumns(10);
		maxServField.setBounds(563, 222, 174, 29);
		contentPane.add(maxServField);


		runButton = new JButton("RUN");
		runButton.setBackground(UIManager.getColor("CheckBox.foreground"));
		runButton.setFont(new Font("Times New Roman", Font.PLAIN, 34));
		runButton.setBounds(21, 209, 276, 42);
		contentPane.add(runButton);

		label_3 = new Label("Average waiting time:");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_3.setBounds(21, 555, 144, 29);
		contentPane.add(label_3);

		averageWait = new JTextPane();
		averageWait.setForeground(new Color(0, 0, 0));
		averageWait.setEditable(false);
		averageWait.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		averageWait.setBounds(166, 555, 103, 29);
		contentPane.add(averageWait);

		label_4 = new Label("Average service time:");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_4.setBounds(275, 555, 144, 29);
		contentPane.add(label_4);

		averageServ = new JTextPane();
		averageServ.setEditable(false);
		averageServ.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		averageServ.setBounds(416, 555, 103, 29);
		contentPane.add(averageServ);

		label_5 = new Label("Peak hour:");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_5.setBounds(525, 555, 76, 29);
		contentPane.add(label_5);

		peakHour = new JTextPane();
		peakHour.setEditable(false);
		peakHour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		peakHour.setBounds(597, 555, 65, 29);
		contentPane.add(peakHour);
		
		resultPane = new JTextPane();
		resultPane.setBounds(21, 271, 718, 277);

		JScrollPane scrollPane = new JScrollPane(resultPane);
		scrollPane.setBounds(21, 271, 718, 277);

		contentPane.add(scrollPane);

	}

	public void showStringError(String error) {
		JOptionPane.showMessageDialog(this, error);
	}

	public String getClients() {
		return this.clientsField.getText();
	}

	public String getQueues() {
		return this.queuesField.getText();
	}

	public String getSimulationTime() {
		return this.simulationField.getText();
	}

	public String getMinArrTime() {
		return this.minArrField.getText();
	}

	public String getMaxArrTime() {
		return this.maxArrField.getText();
	}

	public String getMaxServTime() {
		return this.maxServField.getText();
	}

	public String getMinServTime() {
		return this.minServField.getText();
	}

	public void updateResultPane(String newText) {
		this.resultPane.setText(newText);
	}

	public void updateAvWaitPane(String newText) {
		this.averageWait.setText(newText);
	}

	public void updateAvServPane(String newText) {
		this.averageServ.setText(newText);
	}
	public void updatePeakHourPane(String newText)
	{
		this.peakHour.setText(newText);
	}
	public void runListener(ActionListener actionListener) {
		this.runButton.addActionListener(actionListener);
	}

}
