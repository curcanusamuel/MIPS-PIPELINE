package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class SecondPage extends JFrame {

    private JPanel contentPane;
    private JLabel firstLabel;
    private JLabel secondLabel;
    private JLabel secondL;
    private JLabel firstL;

    public SecondPage() {
    	setBackground(new Color(228, 226, 205));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 602, 241);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(228, 226, 205));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        firstL = new JLabel("First Polynomial:");
        firstL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        firstL.setBounds(10, 24, 229, 28);
        contentPane.add(firstL);

        firstLabel = new JLabel("-");
        firstLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        firstLabel.setBounds(10, 62, 568, 42);
        contentPane.add(firstLabel);

        secondL = new JLabel("Second Polynomial:");
        secondL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        secondL.setBounds(10, 112, 229, 28);
        contentPane.add(secondL);

        secondLabel = new JLabel("-");
        secondLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        secondLabel.setBounds(10, 152, 568, 42);
        contentPane.add(secondLabel);
    }

    public void updateFirstLabel(String newValue) {
        firstLabel.setText(newValue);
    }

    public void updateSecondLabel(String newValue) {
        secondLabel.setText(newValue);
    }
}
