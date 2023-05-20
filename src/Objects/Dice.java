package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class DicePanel extends JPanel {
    private ImageIcon[] diceImages;
    private Random random;
    private JLabel diceLabel;

    public DicePanel() {
        setLayout(new FlowLayout());

        random = new Random();
        diceImages = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            diceImages[i] = new ImageIcon("dice" + (i + 1) + ".png");
        }

        diceLabel = new JLabel(diceImages[0]); // 초기 이미지 설정
        add(diceLabel);
    }

    public int roll() {
        int diceValue = random.nextInt(6) + 1;
        diceLabel.setIcon(diceImages[diceValue - 1]);
        return diceValue;
    }
}
