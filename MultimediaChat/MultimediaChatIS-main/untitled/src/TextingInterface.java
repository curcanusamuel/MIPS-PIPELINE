import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextingInterface extends JFrame{
    private static JEditorPane chatArea;
    private static StringBuilder chatMessages = new StringBuilder("<html>");

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public TextingInterface() {
        JFrame frame = new JFrame("Texting App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        DefaultListModel<String> peopleListModel = new DefaultListModel<>();
        JList<String> peopleList = new JList<>(peopleListModel);
        peopleListModel.addElement("Friend 1");
        peopleListModel.addElement("Friend 2");
        peopleListModel.addElement("Friend 3");

        peopleList.setCellRenderer(new FriendListCellRenderer());

        peopleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        peopleList.setBorder(BorderFactory.createEtchedBorder());

        chatArea = new JEditorPane();
        chatArea.setEditable(false);
        chatArea.setContentType("text/html");
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        JTextField textInput = new JTextField();
        textInput.setPreferredSize(new Dimension(700, 30));
        textInput.setBorder(new LineBorder(new Color(30, 60, 100), 2));

        JButton sendPictureButton = new JButton("Send Picture");
        JButton sendTextButton = new JButton();
        try {
            BufferedImage arrowImage = ImageIO.read(new File("arrow.png")); // Replace with your arrow icon file path
            int arrowSize = 20; // Set the desired size
            BufferedImage scaledArrowImage = new BufferedImage(arrowSize, arrowSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledArrowImage.createGraphics();
            g2d.drawImage(arrowImage, 0, 0, arrowSize, arrowSize, null);
            g2d.dispose();
            ImageIcon arrowIcon = new ImageIcon(scaledArrowImage);
            sendTextButton.setIcon(arrowIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JButton sendEmojiButton = new JButton("Send Emoji");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendTextButton);
        buttonPanel.add(sendPictureButton);
        buttonPanel.add(sendEmojiButton);

        sendPictureButton.setBackground(Color.LIGHT_GRAY);
        sendPictureButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendEmojiButton.setBackground(Color.LIGHT_GRAY);
        sendEmojiButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(textInput);
        inputPanel.add(buttonPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, peopleList, chatScrollPane);
        splitPane.setDividerLocation(150);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        sendPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "You sent a picture";
                addMessageToChatArea(message);
                textInput.setText("");
            }
        });

        sendTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textInput.getText();
                if (!message.isEmpty()) {
                    addMessageToChatArea("You: " + message);
                    textInput.setText("");
                }
            }
        });


        sendEmojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "You sent an emoji";
                addMessageToChatArea(message);
                textInput.setText("");
            }
        });

        textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "You: " + textInput.getText();
                addMessageToChatArea(message);
                textInput.setText("");
            }
        });

        frame.setVisible(true);
    }

    private static void addMessageToChatArea(String message) {
        chatMessages.append("<p style='background-color: #ADD8E6; padding: 5px; margin: 5px;'>").append(message).append("</p>");
        chatArea.setText(chatMessages.toString());
    }

    private static class FriendListCellRenderer extends DefaultListCellRenderer {
        private ImageIcon customProfileIcon;

        public FriendListCellRenderer() {
            customProfileIcon = createProfileIconFromImageFile("test.png"); // Replace with the path to your image
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (renderer instanceof JLabel) {
                JLabel label = (JLabel) renderer;
                label.setIcon(customProfileIcon);
            }

            return renderer;
        }

        private ImageIcon createProfileIconFromImageFile(String imagePath) {
            try {
                BufferedImage image = ImageIO.read(new File(imagePath));
                int diameter = 30;
                BufferedImage scaledImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = scaledImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setClip(new Ellipse2D.Double(0, 0, diameter, diameter));
                g2d.drawImage(image, 0, 0, diameter, diameter, null);
                g2d.dispose();
                return new ImageIcon(scaledImage);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}