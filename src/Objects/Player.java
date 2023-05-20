package Objects;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player extends JLabel {
	ImageIcon Icon; // �÷��̾� �̹���
	String ImageSource; //�÷��̾� �̹��� �������� �ּ�
	int PlayerX, PlayerY; //�÷��̾� ��ǥ
	int nowPlayerTile = 0; //�÷��̾ �ִ� Ÿ�� ��ġ
	String Id; // �÷��̾� �̸�
	int Playernum; // �÷��̾� ��ȣ
	int money = 1000; // ��
	int[] PlayerCity; // �÷��̾ ������ �ִ� ��
	
	public Player(int x, int y, String Id, int playnu, String imageSource) {
		this.PlayerX = x;
		this.PlayerY = y;
		this.ImageSource = imageSource;
		this.Id = Id;
		this.Playernum = playnu;
		this.nowPlayerTile = 0;
		Icon = new ImageIcon(ImageSource);
		Image originalImage = Icon.getImage();
		Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        Icon = new ImageIcon(scaledImage);
		setIcon(Icon);
		setSize(50,50);
		setLocation(PlayerX,PlayerY);
	}
	
	public void move(int a, Player p) {
		if(this.nowPlayerTile + a >= 12) {
			this.money += 1000;
			a += 1;
		}
		this.nowPlayerTile = (this.nowPlayerTile+a)%12;
		printwhere(this.nowPlayerTile);
		if (p.Playernum == 0) { // 플레이어 1의 경우
			switch(this.nowPlayerTile) {
				case 0:
					p.setLocation(800, 775);
					break;
				case 1:
					p.setLocation(550, 775);
					break;
				case 2:
					p.setLocation(300, 775);
					break;
				case 3:
					p.setLocation(50, 775);
					break;
				case 4:
					p.setLocation(50, 525);
					break;
				case 5:
					p.setLocation(50, 275);
					break;
				case 6:
					p.setLocation(50, 25);
					break;
				case 7:
					p.setLocation(300, 25);
					break;
				case 8:
					p.setLocation(550, 25);
					break;
				case 9:
					p.setLocation(800, 25);
					break;
				case 10:
					p.setLocation(800, 275);
					break;
				case 11:
					p.setLocation(800, 525);
					break;

			}
		}
		else { // 플레이어 2의 경우
			switch(this.nowPlayerTile) {
				case 0:
					p.setLocation(875, 775);
					break;
				case 1:
					p.setLocation(625, 775);
					break;
				case 2:
					p.setLocation(375, 775);
					break;
				case 3:
					p.setLocation(125, 775);
					break;
				case 4:
					p.setLocation(125, 525);
					break;
				case 5:
					p.setLocation(125, 275);
					break;
				case 6:
					p.setLocation(125, 25);
					break;
				case 7:
					p.setLocation(375, 25);
					break;
				case 8:
					p.setLocation(625, 25);
					break;
				case 9:
					p.setLocation(875, 25);
					break;
				case 10:
					p.setLocation(875, 275);
					break;
				case 11:
					p.setLocation(875, 525);
					break;

			}
		}
	}
	
	public int getNumTile() {
		return nowPlayerTile;
	}
	
	public void printwhere(int a) {
		
		switch(a) {
		case 0: //출발
			System.out.print(this.Id);
			System.out.println("이(가) 출발지역에 도착했습니다!");
			break;
		case 1: 
			System.out.print(this.Id);
			System.out.println("이(가) 제주에 도착했습니다!");
			break;
		case 2:
			System.out.print(this.Id);
			System.out.println("이(가) 울산에 도착했습니다!");
			break;
		case 3:
			System.out.print(this.Id);
			System.out.println("이(가) 로또에 도착했습니다!");
			break;
		case 4:
			System.out.print(this.Id);
			System.out.println("이(가) 대전에 도착했습니다!");
			break;
		case 5:
			System.out.print(this.Id);
			System.out.println("이(가) 광주에 도착했습니다!");
			break;
		case 6:
			System.out.print(this.Id);
			System.out.println("이(가) 국내여행에 도착했습니다!");
			break;
		case 7:
			System.out.print(this.Id);
			System.out.println("이(가) 인천에 도착했습니다!");
			break;
		case 8:
			System.out.print(this.Id);
			System.out.println("이(가) 대구에 도착했습니다!");
			break;
		case 9:
			System.out.print(this.Id);
			System.out.println("이(가) 사회복지기금에 도착했습니다!");
			break;
		case 10:
			System.out.print(this.Id);
			System.out.println("이(가) 부산에 도착했습니다!");
			break;
		case 11:
			System.out.print(this.Id);
			System.out.println("이(가) 서울에 도착했습니다!");
			break;
		default:
			break;
		}
	}
}

