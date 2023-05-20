package Objects;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tiles extends JLabel{
	String Name; //�� �̸�
	ImageIcon Icon; // �÷��̾� �̹���
	String ImageSource;
	int Num; //�� ��ȣ
	int Purchased; //���ŵ� ������ �ƴ���
	int Buildings[]; //���ŵ� ���̶�� ��ġ�� �ǹ� ���� ǥ��
	int Price1; // ���� ���� ����
	int Price2; // 1�� �ǹ� ����
	int Price3; // 2�� �ǹ� ����
	int Price4; // 3�� �ǹ� ����
	int Pay; //���� ���� ��, ���� ����, �ǹ� ���� * 1.2
	int TakeOverMoney; //���� ���� ��, ���� ��� *1.2����
	int Owner; //������ ������ -1, ���� ���� ǥ�� 0 �Ǵ� 1
	int Type; //0 �⺻ ��, 1 ���ε�, 2 ��������, 3 ���, 4 ���
	
	//�Ϲ� Ÿ�� �ʱ�ȭ
	public Tiles(String Name, int Num, int Purchased,int[] Buildings,int Owner,int Type){
		super();
		this.Name = Name;
		this.Num = Num;
		this.Purchased = Purchased;
		this.Buildings = Buildings;
		this.Price1 = Num*100;
		this.Price2 = Num*30;
		this.Price3 = Num*40;
		this.Price4 = Num*50;
		this.Owner = Owner;
		this.Type = Type;
;
	}
	
	// Ư�� Ÿ�� �ʱ�ȭ
	public Tiles(String Name, int Num, int Type) {
		super();
		this.Name = Name;
		this.Num = Num;
		this.Type = Type;
	}
	
}
