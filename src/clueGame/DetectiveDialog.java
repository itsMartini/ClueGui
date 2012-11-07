package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;

public class DetectiveDialog extends JDialog{
	private List<Card> deck;
	private PeoplePanel peeps;
	private RoomsPanel rumes;
	private WeaponsPanel wepins;
	private PersonGuessPanel guessWho;
	private RoomGuessPanel knockKnock;
	private WeaponGuessPanel boomHeadshot;
	
	public DetectiveDialog(List<Card> deck) {
		this.deck = new ArrayList<Card>(deck);
		peeps = new PeoplePanel();
		rumes = new RoomsPanel();
		wepins = new WeaponsPanel();
		guessWho = new PersonGuessPanel();
		knockKnock = new RoomGuessPanel();
		boomHeadshot = new WeaponGuessPanel();
		
		setTitle("Detective Notes");
		
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = (int) (screenRes.height*0.9);
		int width = height*47/56;
		setSize(width, height);
		setLayout(new GridLayout(3,2));
		add(peeps);
		add(guessWho);
		add(rumes);
		add(knockKnock);
		add(wepins);
		add(boomHeadshot);
	}
	
	public class PeoplePanel extends JPanel {
		private ArrayList<JCheckBox> peepChecks;
		public PeoplePanel() {
			setLayout(new GridLayout(0,2));
			
			peepChecks = new ArrayList<JCheckBox>();
			for (Card c : deck) {
				if (c.getType() == CardType.PERSON) {
					peepChecks.add(new JCheckBox(c.getName()));
				}
			}
			for (JCheckBox j : peepChecks) {
				add(j);
			}
			
			setBorder(new TitledBorder(new EtchedBorder(), "People"));
		}
	}
	
	public class RoomsPanel extends JPanel {
		private ArrayList<JCheckBox> rumeChecks;
		public RoomsPanel() {
			setLayout(new GridLayout(0,2));
			
			rumeChecks = new ArrayList<JCheckBox>();
			for (Card c : deck) {
				if (c.getType() == CardType.ROOM) {
					rumeChecks.add(new JCheckBox(c.getName()));
				}
			}
			for (JCheckBox j : rumeChecks) {
				add(j);
			}
			
			setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		}
	}

	public class WeaponsPanel extends JPanel {
		private ArrayList<JCheckBox> wepinChecks;
		public WeaponsPanel() {
			setLayout(new GridLayout(0,2));
			
			wepinChecks = new ArrayList<JCheckBox>();
			for (Card c : deck) {
				if (c.getType() == CardType.WEAPON) {
					wepinChecks.add(new JCheckBox(c.getName()));
				}
			}
			for (JCheckBox j : wepinChecks) {
				add(j);
			}
			
			setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		}
	}

	public class PersonGuessPanel extends JPanel {
		private ArrayList<String> peepText;
		private JComboBox peepCombo;
		public PersonGuessPanel() {
			peepText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.PERSON) {
					peepText.add(c.getName());
				}
			}
			String[] temp = peepText.toArray(new String[peepText.size()]);
			peepCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,1));
			add(peepCombo);
			
			setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		}
	}

	public class RoomGuessPanel extends JPanel {
		private ArrayList<String> rumeText;
		private JComboBox rumeCombo;
		public RoomGuessPanel() {
			rumeText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.ROOM) {
					rumeText.add(c.getName());
				}
			}
			String[] temp = rumeText.toArray(new String[rumeText.size()]);
			rumeCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,1));
			add(rumeCombo);
			
			setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		}
	}

	public class WeaponGuessPanel extends JPanel {
		private ArrayList<String> wepinText;
		private JComboBox wepinCombo;
		public WeaponGuessPanel() {
			wepinText = new ArrayList<String>();
			for (Card c : deck) {
				if (c.getType() == CardType.WEAPON) {
					wepinText.add(c.getName());
				}
			}
			String[] temp = wepinText.toArray(new String[wepinText.size()]);
			wepinCombo = new JComboBox(temp);
			setLayout(new GridLayout(1,1));
			add(wepinCombo);
			
			setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		}
	}
}