package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Running extends Object{
	private int diceValue;
	private Random random = new Random();
	private JPanel boardPanel;
    private JFrame frame;
    private Player player1;
    private Player player2;
    private DicePanel dicePanel;
    int randomnum; // 주사위 값
    int turns = -1; // 턴 수
	ImageIcon[] diceImages = new ImageIcon[6];
	public Events e;


	public Running() {
	    frame = new JFrame("Board");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());

	    player1 = new Player(0, 0, "율전이", 0, "player1.png");
	    player2 = new Player(0, 0, "명륜이", 1, "player2.png");

	    boardPanel = new JPanel(null) { // 레이아웃 매니저를 사용하지 않음
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("board.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
	    
        e = new Events(boardPanel);

	    dicePanel = new DicePanel();
	    dicePanel.setBounds(397, 425, dicePanel.getPreferredSize().width, dicePanel.getPreferredSize().height);
        boardPanel.add(dicePanel);
	    

	    JButton rollButton = new JButton("주사위 굴리기");
	    rollButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            randomnum = dicePanel.roll();
	            turns += 1;
	            Run(randomnum, turns);
	        }
	    });
	  
	    rollButton.setBounds(430, 625, 125, rollButton.getPreferredSize().height);
	    boardPanel.add(rollButton);

	    player1.setBounds(800,775, 100, 100);
	    boardPanel.add(player1);

	    player2.setBounds(875,775, 100, 100);
	    boardPanel.add(player2);

	   
	    frame.add(boardPanel, BorderLayout.CENTER);
	    frame.setPreferredSize(new Dimension(1000, 1000));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	}
	
	
    public void DisplayBoard() { 
		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void Run(int a, int turn) {
		if(turn % 2 == 0) {
			player1.move(a, player1);
			e.happens(player1,player2);
		}
		else {
			player2.move(a, player2);
			e.happens(player2,player1);
		}	
	}
	
}
