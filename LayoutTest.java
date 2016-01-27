import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LayoutTest extends JFrame {
    JPanel contentPane;
    JLabel bildMap;
    JLabel bildRocket;
    JLabel outputField;
    TextField textField;
    Icon map;
    Icon rocket;

    public LayoutTest() {
        super("Digital Inner Space");

        setVisible(true);
        setSize(800, 600);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        TextField textField = new TextField();
        textField.setBackground(new Color(0, 0, 0));
        textField.setForeground(new Color(255, 255, 255));
        textField.setFont(new Font("DialogInput", Font.PLAIN, 32));
        textField.setBounds(10, 668, 998, 50);
        contentPane.add(textField);

        TextArea outputField = new TextArea();
        outputField.setForeground(new Color(255, 255, 255));
        outputField.setBackground(new Color(0, 0, 0));
        outputField.setFont(new Font("DialogInput", Font.PLAIN, 22));
        outputField.setEditable(false);
        outputField.setBounds(10, 403, 998, 259);
        contentPane.add(outputField);

        map = new ImageIcon(getClass().getResource("motherboard.png"));
        bildMap = new JLabel(map);
        bildMap.setBounds(15, 16, 988, 381);
        contentPane.add(bildMap);

        rocket = new ImageIcon(getClass().getResource("rocket.png"));
        bildRocket = new JLabel(rocket);
        bildRocket.setBounds(200, 320, 64, 64);
        contentPane.add(bildRocket);

        repaint();
    }
}
