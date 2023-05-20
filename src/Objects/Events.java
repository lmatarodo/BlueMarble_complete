package Objects;

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Socket.Client;

public class Events extends Object{
	Client client;
	int[] initarr = {0,0,0,0};
	ArrayList<Tiles> map = new ArrayList<>();
	int input,howmany;
	int land,house,building,hotel;
	Scanner scn = new Scanner(System.in);
	private JPanel boardPanel;
    private JLabel[] imageLabels = new JLabel[12];//인수를 실행하기 위한 local JLabel 변수
    
	
	public Events(JPanel boardPanel){
		map.clear();
		map.add(new Tiles("출발",0,4));
		map.add(new Tiles("제주",1,0,initarr,-1,0));
		map.add(new Tiles("울산",2,0,initarr,-1,0));
		map.add(new Tiles("무인도",3,1));
		map.add(new Tiles("대전",4,0,initarr,-1,0));
		map.add(new Tiles("광주",5,0,initarr,-1,0));
		map.add(new Tiles("국내여행",6,2));
		map.add(new Tiles("인천",7,0,initarr,-1,0));
		map.add(new Tiles("대구",8,0,initarr,-1,0));
		map.add(new Tiles("기부",9,3));
		map.add(new Tiles("부산",10,0,initarr,-1,0));
		map.add(new Tiles("서울",11,0,initarr,-1,0));
		this.boardPanel = boardPanel;
        for (int i = 0; i < 12; i++) {
            imageLabels[i] = new JLabel();
        }
	}
	
	
	public void happens(Player me, Player you) {
		// 타일 번호에 따라 이벤트 발생
		int a = me.getNumTile();

		switch(a) {
		case 0: //출발
			
			break;
		case 1: 
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[1]);
			}
			break;
		case 2:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[2]);
			}
			break;
		case 3:
			Lotto(me);
			break;
		case 4:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[4]);
			}
			break;
		case 5:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[5]);
			}
			break;
		case 6:
			tour(me,you);
			break;
		case 7:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[7]);
			}
			break;
		case 8:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[8]);
			}
			break;
		case 9:
			donate(me);
			break;
		case 10:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[10]);
			}
			break;
		case 11:
			if(map.get(a).Purchased == 0) {
				buy(me,a);
			}
			else if(map.get(a).Purchased != 0 && map.get(a).Purchased != me.Playernum+1) {
				pay(me,you,a, imageLabels[11]);
			}
			break;
		default:
			break;
		}
		if (me.Playernum == 0) {
			System.out.println("율전이 잔고: " + me.money);
			System.out.println("명륜이 잔고: " + you.money);
			System.out.println("==================================================================================================");
		}
		else {
			System.out.println("율전이 잔고: " + you.money);
			System.out.println("명륜이 잔고: " + me.money);
			System.out.println("==================================================================================================");
		}
	
	}
	
	public void buy(Player p,int tilenum){
		System.out.println("땅을 구매하시겠습니까? 구매: 1, 패스: 2");
		input = 0;
		land = map.get(tilenum).Price1;
		house = land + map.get(tilenum).Price2;
		building = house + map.get(tilenum).Price3;
		hotel = building + map.get(tilenum).Price4;
		while(input != 1 && input != 2) {
			input = scn.nextInt();
			if(input == 1) {
				System.out.println("건물을 몇 개 짓겠습니까? 땅:0, 집:1, 빌딩:2, 호텔:3");
				System.out.println("땅: " + land +" 집: " + house + " 빌딩: " + building + " 호텔: " + hotel);
				howmany = scn.nextInt();
				switch(howmany){
				case 0:
					if(p.money < land) {
						System.out.println("돈이 부족합니다..");
						break;
					}
					else {
						p.money -= land;
						map.get(tilenum).Purchased = p.Playernum + 1;
						System.out.println(p.Id+ "이(가) " + map.get(tilenum).Name + "에 땅을 구매하셨습니다.");
						map.get(tilenum).Pay = (int)(land*1.2);
						map.get(tilenum).TakeOverMoney = (int)(map.get(tilenum).Pay*1.2);
						switch(tilenum) {
						case 1:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
							}	
							break;
						case 2:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
							}
							break;
						case 4:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
							}
							break;
						case 5:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
							}
							break;
						case 7:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[6] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[6] = imageLabel;
							}
							break;
						case 8:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
							}
							break;
						case 10:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
							}
							break;
						case 11:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
							}
							break;
						}					   
					}
					break;
				case 1:
					if(p.money < house) {
						System.out.println("돈이 부족합니다..");
						break;
					}
					else {
						p.money -= house;
						map.get(tilenum).Purchased = p.Playernum + 1;
						System.out.println(p.Id+ "이(가) " + map.get(tilenum).Name + "에 집을 구매하셨습니다.");
						map.get(tilenum).Pay = (int)(house*1.2);
						map.get(tilenum).TakeOverMoney = (int)(map.get(tilenum).Pay*1.2);
						switch(tilenum) {
						case 1:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}	
							break;
						case 2:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 4:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 5:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 7:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 8:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 10:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 11:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("house.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						}					   
					}
					break;
				case 2:
					if(p.money < building) {
						System.out.println("돈이 부족합니다..");
						break;
					}
					else {
						p.money -= building;
						map.get(tilenum).Purchased = p.Playernum + 1;
						System.out.println(p.Id+ "이(가) " + map.get(tilenum).Name + "에 빌딩을 구매하셨습니다.");
						map.get(tilenum).Pay = (int)(building*1.2);
						map.get(tilenum).TakeOverMoney = (int)(map.get(tilenum).Pay*1.2);
						switch(tilenum) {
						case 1:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}	
							break;
						case 2:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 4:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 5:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 7:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 8:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 10:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 11:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("building.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						}					   
					}
					break;
				case 3:
					if(p.money < hotel) {
						System.out.println("돈이 부족합니다..");
						break;
					}
					else {
						p.money -= hotel;
						map.get(tilenum).Purchased = p.Playernum + 1;
						System.out.println(p.Id+ "이(가) " + map.get(tilenum).Name + "에 호텔을 구매하셨습니다.");
						map.get(tilenum).Pay = (int)(hotel*1.2);
						map.get(tilenum).TakeOverMoney = (int)(map.get(tilenum).Pay*1.2);
						switch(tilenum) {
						case 1:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[1] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}	
							break;
						case 2:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[2] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 875, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 4:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[4] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 5:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(75, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[5] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(125, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 7:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(325, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[7] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(375, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 8:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(575, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[8] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(625, 125, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 10:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[10] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 375, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						case 11:
							if (p.Playernum == 0) {
								ImageIcon imageIcon = new ImageIcon("player1Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							else {
								ImageIcon imageIcon = new ImageIcon("player2Land.png");
								Image image = imageIcon.getImage();
								int width = image.getWidth(null);
								int height = image.getHeight(null);
								Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
								imageIcon = new ImageIcon(resizedImage);
								JLabel imageLabel = new JLabel(imageIcon);
								imageLabel.setBounds(825, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel);
								boardPanel.repaint();
								imageLabels[11] = imageLabel;
								ImageIcon imageIcon2 = new ImageIcon("hotel.png");
								Image image2 = imageIcon2.getImage();
								int width2 = image2.getWidth(null);
								int height2 = image2.getHeight(null);
								Image resizedImage2 = image2.getScaledInstance(30, 70, Image.SCALE_SMOOTH);
								imageIcon2 = new ImageIcon(resizedImage2);
								JLabel imageLabel2 = new JLabel(imageIcon2);
								imageLabel2.setBounds(875, 625, 30, 50);
								boardPanel.setLayout(null);
								boardPanel.add(imageLabel2);
								boardPanel.repaint();
							}
							break;
						}
					}
					break;
					default:
						System.out.println("다시 입력하세요!!");
						continue;
				}
			}	
			else if(input != 1 && input != 2) {
				System.out.println("다시 입력하세요!!");
			}
		}
		
	}
	
	public void pay(Player me,Player you, int tilenum, JLabel formerimage) {
		if(me.money < map.get(tilenum).Pay) {
			if(me.Playernum == 0) {
				end(you);
			}
			else
				end(you);
		}
		else {
			me.money -= map.get(tilenum).Pay;
			System.out.println(me.Id +"이(가) " + map.get(tilenum).Pay+"만큼 지불했습니다..");
			if(me.Playernum == 0) {
				you.money += map.get(tilenum).Pay;
				System.out.println(you.Id + you.money);
				System.out.println(me.Id + me.money);
			}
			else {
				you.money += map.get(tilenum).Pay;
				System.out.println(you.Id+you.money);
				System.out.println(me.Id+me.money);
			}
		}
		if(me.money >= map.get(tilenum).TakeOverMoney) {
			System.out.println(map.get(tilenum).Name + "을 인수하시겠습니까? 인수 비용:" + map.get(tilenum).TakeOverMoney +"  인수:1 패스:2");
			input = scn.nextInt();
			if(input == 1) {
				me.money -= map.get(tilenum).TakeOverMoney;
				map.get(tilenum).Purchased = me.Playernum + 1;
				System.out.println(me.Id + "이(가) " + map.get(tilenum).Name + "을(를) 인수하였습니다.");
				if (me.Playernum == 0) {
					int x = formerimage.getX();
					int y = formerimage.getY();
					boardPanel.remove(formerimage);
					ImageIcon imageIcon = new ImageIcon("player1Land.png");
					Image image = imageIcon.getImage();
					int width = image.getWidth(null);
					int height = image.getHeight(null);
					Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(resizedImage);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(x, y, 30, 50);
					boardPanel.setLayout(null);
					boardPanel.add(imageLabel);
					boardPanel.repaint();
					imageLabels[tilenum] = imageLabel;
				}
				else {
					int x = formerimage.getX();
					int y = formerimage.getY();
					boardPanel.remove(formerimage);
					ImageIcon imageIcon = new ImageIcon("player2Land.png");
					Image image = imageIcon.getImage();
					int width = image.getWidth(null);
					int height = image.getHeight(null);
					Image resizedImage = image.getScaledInstance(30, 50, Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(resizedImage);
					JLabel imageLabel = new JLabel(imageIcon);
					imageLabel.setBounds(x, y, 30, 50);
					boardPanel.setLayout(null);
					boardPanel.add(imageLabel);
					boardPanel.repaint();
					imageLabels[tilenum] = imageLabel;
				}
			}
		}
	}
	public void tour(Player me,Player you) {
		int where = -1;
		while((where <0 || where > 11) || where == 6) {
			System.out.println("가고 싶은 장소의 번호를 입력하세요 0 ~ 11");
			where = scn.nextInt();
		}
		
		System.out.println(where+ "로 날아갑니다");
		if(where == 0) {
			me.move(12-me.nowPlayerTile, me);
		}
		else if(where >= me.nowPlayerTile) {		
			me.move(where-me.nowPlayerTile, me);
		}
		else {
			me.move(11-me.nowPlayerTile+where, me
					);
		}
		happens(me,you);
	}
	public void Lotto(Player p) {
		Random random = new Random();
		int tmp;
		tmp = random.nextInt(1501); 
		System.out.println("로또 결과..." + tmp + "원 획득!");
		p.money += tmp;
	}
	public void donate(Player p) {
		if (p.money < 1000) {
			p.money = 0;
		}
		else {
			p.money -= 1000;
		}
		System.out.println("받은만큼 내셔야죠.. ");
	}
	
	public void end(Player p) {
		System.out.println("승자 : " + p.Id + "!!!!");
		client = new Client(p.Playernum,p.money);
		client.start();
		System.exit(1);
	}
}
