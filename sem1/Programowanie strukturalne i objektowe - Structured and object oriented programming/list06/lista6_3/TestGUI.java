package lista6_3;
import java.util.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TestGUI implements Subject
{	
	//g³ówne przyciski
	private JButton buttonSzukaj;
	private JButton buttonAktualizuj;
	private JButton buttonAdd;
	private JButton buttonW;
	private JButton buttonZ;
	private JButton buttonSort;
	private JButton buttonUsun;
	private JButton buttonHash;
	//g³ówne okno
	private JFrame frame;
	//uczelnia, na której pracujemy
	private Uczelnia uczelnia;
	//miejsce na wypisanie uczelni
	private JTextArea ucz;
	//lista obserwatorow
	private ArrayList<Observer1> obs = new ArrayList<>();
	
	
	public TestGUI(Uczelnia uczelnia)
	{
		this.uczelnia = uczelnia;
		Update upd = new Update(this);
		
		//ramka deklaracja
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panel
		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//przyciski
		buttonSzukaj = new JButton("Szukaj osoby/kursu na uczelni");
		buttonSzukaj.addActionListener(new Szukaj());	
		buttonSzukaj.setBounds(180,110,210,25);
		panel.add(buttonSzukaj);
		
		buttonHash = new JButton("Usun powtarzajace sie osoby");
		buttonHash.addActionListener(new UsunHash());	
		buttonHash.setBounds(180,140,210,25);
		panel.add(buttonHash);
		
		buttonAktualizuj = new JButton("Aktualizuj uczelnie");
		buttonAktualizuj.addActionListener(new Aktualizuj());
		buttonAktualizuj.setBounds(10,20,150,25);
		panel.add(buttonAktualizuj);
		
		buttonAdd = new JButton("Dodaj osobe/kurs do uczelni");
		buttonAdd.addActionListener(new Add());
		buttonAdd.setBounds(180,20,210,25);
		panel.add(buttonAdd);
		
		
		buttonW = new JButton("Wczytaj uczelnie");
		buttonW.addActionListener(new Wczytaj());
		buttonW.setBounds(10,110,150,25);
		panel.add(buttonW);
		
		buttonZ = new JButton("Zapisz uczelnie");
		buttonZ.addActionListener(new Zapisz());
		buttonZ.setBounds(10,140,150,25);
		panel.add(buttonZ);
		
		buttonSort = new JButton("Sortuj osoby/kursy");
		buttonSort.addActionListener(new Sortuj());
		buttonSort.setBounds(10,50,150,25);
		panel.add(buttonSort);
		
		buttonUsun = new JButton("Usun osobe/kurs z uczelni");
		buttonUsun.addActionListener(new Usun());
		buttonUsun.setBounds(180,50,210,25);
		panel.add(buttonUsun);
		
		//panel "boczny"
		JPanel panel2 = new JPanel();
		frame.add(panel2, BorderLayout.EAST);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Uczelnia aktualnie:", JLabel.LEFT);
		label.setFont(new Font("Name",1, 20));
		panel2.add(label);
		
		this.ucz = new JTextArea(500,35);
		this.ucz.setEditable(false);
		ucz.setText("Aby wczytac uczelnie kliknij:\n*Aktualizuj uczelnie*                                        ");
		panel2.add(ucz);
		
		//ikona for fun
		
		ImageIcon icon = new ImageIcon("pwr.png");
		JLabel lab = new JLabel(icon);
		lab.setBounds(20,200,300,200);
		panel.add(lab);
		JLabel laab = new JLabel("*wstawilem tutaj to logo, zeby nie miec tyle pustego miejsca :)*");
		laab.setFont(new Font("Name",1, 9));
		laab.setBounds(27,340,400,15);
		panel.add(laab);
		
		

		//ramka zamkniêcie
		frame.pack();
		frame.setTitle("Uczelnia (Etap5)");
		frame.setSize(800, 700);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//w JOptionPane.showOptionDialog: X = -1, YES = 0, NO = 1, CANCEL = 2
	}
	
	class Aktualizuj implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ucz.setText(uczelnia.toString());
		}
	}	
	class Sortuj implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] opcje = {"Osoby","Kursy"};
			int x = JOptionPane.showOptionDialog(null, "Co chcesz posortowac?", "Sortowanie", JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, opcje, 0);
			switch(x)
			{
			case 0:
				String[] opcjeOs = {"Nazwiskami","Nazwiskami i imionami","Nazwiskami i wiekiem"};
				int y = JOptionPane.showOptionDialog(null, "Jak chcesz je posortowac?", "Sortowanie osób",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opcjeOs, 0);
				switch(y)
				{
				case 0:
					uczelnia.sortNazw();
					JOptionPane.showMessageDialog(null, "Sortowanie pomyslne", "Sortowanie", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					uczelnia.sortNazwIm();
					JOptionPane.showMessageDialog(null, "Sortowanie pomyslne", "Sortowanie", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 2:
					uczelnia.sortNazwWiek();
					JOptionPane.showMessageDialog(null, "Sortowanie pomyslne", "Sortowanie", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				break;
			case 1:
				String[] opcjeK = {"Punktami ECTS","Nazwiskami prowadzacych"};
				int z = JOptionPane.showOptionDialog(null, "Jak chcesz je posortowac?", "Sortowanie kursów",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, opcjeK, 0);
				switch(z)
				{
				case 0:
					uczelnia.sortKects();
					JOptionPane.showMessageDialog(null, "Sortowanie pomyslne", "Sortowanie", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					uczelnia.sortKnazw();
					JOptionPane.showMessageDialog(null, "Sortowanie pomyslne", "Sortowanie", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				break;
			}
			testChange();
		}
	}
	class Add implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] opcje = {"Studenta","Pracownika","Kurs"};
			int x = JOptionPane.showOptionDialog(null, "Kogo/co chcesz dodac?", "Dodawanie", JOptionPane.YES_NO_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, opcje, 0);
			switch (x)
			{
			case 0:
				nowyStudent();	
				break;
			case 1:
				nowyPracownik();
				break;
			case 2:
				nowyKurs();
				break;
			}
			testChange();
		}
		private void nowyPracownik()
		{
			String im1 = JOptionPane.showInputDialog(null, "Podaj imie: ", "Nowy Pracownik", 0);
			String naz1 = JOptionPane.showInputDialog(null, "Podaj nazwisko: ", "Nowy Pracownik", 0);
			String PES1 = JOptionPane.showInputDialog(null, "Podaj PESEL: ", "Nowy Pracownik", 0);
			String wiek1 = JOptionPane.showInputDialog(null, "Podaj wiek: ", "Nowy Pracownik", 0);
			String plec1 = JOptionPane.showInputDialog(null, "Podaj plec: ", "Nowy Pracownik", 0);
			String stan = JOptionPane.showInputDialog(null, "Podaj stanowisko: ", "Nowy Pracownik", 0);
			String staz = JOptionPane.showInputDialog(null, "Podaj staz: ", "Nowy Pracownik", 0);
			String pen = JOptionPane.showInputDialog(null, "Podaj pensje: ", "Nowy Pracownik", 0);
			String[] ktory = {"Administracyjny", "Badawczo-Dydaktyczny"};
			int y = JOptionPane.showOptionDialog(null, "Jaki ma to byc pracownik?", "Nowy Pracownik",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, ktory, 0);
			switch(y)
			{
			case 0:
				String nad = JOptionPane.showInputDialog(null, "Podaj ilosc nadgodzin: ", "Nowy Pracownik", 0);
				try
				{
					PracownikAdministracyjny s = new PracownikAdministracyjny(im1,naz1,PES1,Integer.parseInt(wiek1),
													plec1,stan,Integer.parseInt(staz),Integer.parseInt(pen),Integer.parseInt(nad));
					uczelnia.addOsoba(s);
					JOptionPane.showMessageDialog(null, "Pomyslnie dodano pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception e1) {}
				break;
			case 1:
				String pkt = JOptionPane.showInputDialog(null, "Podaj punktacje za dorobek naukowy: ", "Nowy Pracownik", 0);
				try
				{
					PracownikBadawczoDydaktyczny s = new PracownikBadawczoDydaktyczny(im1,naz1,PES1,Integer.parseInt(wiek1),
													plec1,stan,Integer.parseInt(staz),Integer.parseInt(pen),Integer.parseInt(pkt));
					uczelnia.addOsoba(s);
					JOptionPane.showMessageDialog(null, "Pomyslnie dodano pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception e2) {}
				break;
			}
		}
		private void nowyStudent()
		{
			String[] TN = {"Tak","Nie"};
			String im = JOptionPane.showInputDialog(null, "Podaj imie: ", "Nowy Student", 0);
			String naz = JOptionPane.showInputDialog(null, "Podaj nazwisko: ", "Nowy Student", 0);
			String PES = JOptionPane.showInputDialog(null, "Podaj PESEL: ", "Nowy Student", 0);
			String wiek = JOptionPane.showInputDialog(null, "Podaj wiek: ", "Nowy Student", 0);
			String plec = JOptionPane.showInputDialog(null, "Podaj plec: ", "Nowy Student", 0);
			String ind = JOptionPane.showInputDialog(null, "Podaj indeks: ", "Nowy Student", 0);
			String ilK = JOptionPane.showInputDialog(null, "Podaj ilosc kursów: ", "Nowy Student", 0);
			boolean ERAS=false;
			if( JOptionPane.showOptionDialog(null, "Czy jest uczestnikiem ERASMUS?", "Nowy Student",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, TN, 0) == 0)
				ERAS = true;
			boolean st1=false;
			if( JOptionPane.showOptionDialog(null, "Czy jest studentem studiów I-go stopnia?", "Nowy Student",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, TN, 0) == 0)
				st1 = true;
			boolean st2=false;
			if( JOptionPane.showOptionDialog(null, "Czy jest studentem studiów II-go stopnia?", "Nowy Student",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, TN, 0) == 0)
				st2 = true;
			boolean stac=false;
			if( JOptionPane.showOptionDialog(null, "Czy jest studentem studiów stacjonarnych?", "Nowy Student",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, TN, 0) == 0)
				stac = true;
			boolean nstac = false;
			if( JOptionPane.showOptionDialog(null, "Czy jest studentem studiów niestacjonarnych?", "Nowy Student",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, TN, 0) == 0)
				nstac = true;
			try
			{
				Student s = new Student(im,naz,PES,Integer.parseInt(wiek),plec,Integer.parseInt(ind),Integer.parseInt(ilK),ERAS,st1,st2,stac,nstac);
				uczelnia.addOsoba(s);
				JOptionPane.showMessageDialog(null, "Pomyslnie dodano studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e)
			{
			}
		}
		private void nowyKurs()
		{
			JOptionPane.showMessageDialog(null, "Na poczatek stwórzmy pracownika prowadzecego kurs");
			
			String im1 = JOptionPane.showInputDialog(null, "Podaj imie: ", "Nowy Pracownik", 0);
			String naz1 = JOptionPane.showInputDialog(null, "Podaj nazwisko: ", "Nowy Pracownik", 0);
			String PES1 = JOptionPane.showInputDialog(null, "Podaj PESEL: ", "Nowy Pracownik", 0);
			String wiek1 = JOptionPane.showInputDialog(null, "Podaj wiek: ", "Nowy Pracownik", 0);
			String plec1 = JOptionPane.showInputDialog(null, "Podaj plec: ", "Nowy Pracownik", 0);
			String stan = JOptionPane.showInputDialog(null, "Podaj stanowisko: ", "Nowy Pracownik", 0);
			String staz = JOptionPane.showInputDialog(null, "Podaj staz: ", "Nowy Pracownik", 0);
			String pen = JOptionPane.showInputDialog(null, "Podaj pensjê: ", "Nowy Pracownik", 0);
			String pkt = JOptionPane.showInputDialog(null, "Podaj punktacje za dorobek naukowy: ", "Nowy Pracownik", 0);
			int x = 0;
			PracownikBadawczoDydaktyczny s = null;
			try
			{
				s = new PracownikBadawczoDydaktyczny(im1,naz1,PES1,Integer.parseInt(wiek1),
										plec1,stan,Integer.parseInt(staz),Integer.parseInt(pen),Integer.parseInt(pkt));
				uczelnia.addOsoba(s);
				x++;
			}catch (Exception e2) {}
			switch(x)
			{
			case 0:
				JOptionPane.showMessageDialog(null, "Nie udalo sie stworzyc pracownika, dodaj go pózniej do kursu, który stworzymy!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Prowadzacy stworzony, czas na kurs!");
				break;
			}
			String nazwa = JOptionPane.showInputDialog(null, "Podaj nazwe kursu: ", "Nowy Kurs", 0);
			String ECTS = JOptionPane.showInputDialog(null, "Podaj punkty ECTS za kurs: ", "Nowy Kurs", 0);
			try
			{
				Kursy kurs = new Kursy(nazwa,s,Integer.parseInt(ECTS));
				uczelnia.addKurs(kurs);
				JOptionPane.showMessageDialog(null, "Pomyslnie dodano kurs", "Informacja", JOptionPane.INFORMATION_MESSAGE);
			}catch (Exception e2) {}
		}
	}
	class Usun implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] opcje1 = {"Pierwsza","Wszystkie"};
			int x1 = JOptionPane.showOptionDialog(null, "Chcesz usunac pierwsza wyszukana osobe/kurs, czy wszystkie?", "Usuwanie", JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, opcje1, 0);
			switch (x1)
			{
			case 0:
				uczelnia.setUsuwanie(new usuwaniePierwszego());
				break;
			case 1:
				uczelnia.setUsuwanie(new usuwanieWszystkich());
				break;
			}
			
			String[] opcje = {"Studenta","Pracownika","Kurs"};
			int x = JOptionPane.showOptionDialog(null, "Kogo/co chcesz usunac", "Usuwanie", JOptionPane.YES_NO_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, opcje, 0);
			switch (x)
			{
			case 0:
				String[] UsunS = {"Imie", "Nazwisko","Indeks"};
				int y = JOptionPane.showOptionDialog(null, "Na jakiej podstawie chcesz go usunac", "Usuwanie studenta",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, UsunS, 0);
				switch(y)
				{
				case 0:
					String im = JOptionPane.showInputDialog(null, "Podaj imie: ", "Usuwanie studenta o imieniu...", 0);
					uczelnia.usunStudentImie(im);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usunieto studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					String naz = JOptionPane.showInputDialog(null, "Podaj nazwisko: ", "Usuwanie studenta o nazwisku...", 0);
					uczelnia.usunStudentNazwisko(naz);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usunieto studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 2:
					String ind = JOptionPane.showInputDialog(null, "Podaj indeks: ", "Usuwanie studenta o indeksie...", 0);
					uczelnia.usunStudentIndeks(ind);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usunieto studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono studenta", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				break;
			case 1:
				String[] UsunP = {"Imie", "Nazwisko","Stanowisko"};
				int z = JOptionPane.showOptionDialog(null, "Na jakiej podstawie chcesz go usunac", "Usuwanie pracownika",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, UsunP, 0);
				switch(z)
				{
				case 0:
					String im = JOptionPane.showInputDialog(null, "Podaj imie: ", "Usuwanie pracownika o imieniu...", 0);
					uczelnia.usunPracownikImie(im);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usuniêto pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					String naz = JOptionPane.showInputDialog(null, "Podaj nazwisko: ", "Usuwanie pracownika o nazwisku...", 0);
					uczelnia.usunPracownikNazwisko(naz);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyœlnie usuniêto pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 2:
					String st = JOptionPane.showInputDialog(null, "Podaj stanowisko: ", "Usuwanie pracownika o stanowisku...", 0);
					uczelnia.usunPracownikStanowisko(st);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usuniêto pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono pracownika", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				break;
			case 2:
				String[] UsunK = {"Pukty ECTS", "Nazwisko prowadzacego"};
				int q = JOptionPane.showOptionDialog(null, "Na jakiej podstawie chcesz go usunac", "Usuwanie kursu",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, UsunK, 0);
				switch(q)
				{
				case 0:
					String ects = JOptionPane.showInputDialog(null, "Podaj ilosc punktów ECTS za kurs: ", "Usuwanie kursu za ...", 0);
					uczelnia.usunKursECTS(ects);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usuniêto kurs", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono kursu", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					String naz = JOptionPane.showInputDialog(null, "Podaj nazwisko prowadzacego kurs: ", "Usuwanie kursu z prowadzacym o nazwisku...", 0);
					uczelnia.usunKursNazwisko(naz);
					if(uczelnia.getUsunTemp()!=0)
						JOptionPane.showMessageDialog(null, "Pomyslnie usunieto kurs", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Nie znaleziono kursu", "Informacja", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				break;
			}
			testChange();
		}
	}
	class Szukaj implements ActionListener
	{
		private JFrame frame1;
		private JTextArea info;
		private JComboBox glowne;
		private JComboBox combo2;
		private JTextField text;
		
		public void actionPerformed(ActionEvent e)
		{
			frame.dispose();
			
			frame1 = new JFrame();
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//panel
			JPanel panel1 = new JPanel();
			frame1.add(panel1, BorderLayout.CENTER);
			panel1.setLayout(null);
			
			//szukaj
			JButton szuk = new JButton("Szukaj");
			szuk.addActionListener(new search());
			szuk.setBounds(140,150,110,25);
			panel1.add(szuk);
			//back
			JButton back = new JButton("<-- wróc");
			back.addActionListener(new back());
			back.setBounds(10,400,110,25);
			panel1.add(back);
			
			//panel "boczny"
			JPanel panel2 = new JPanel();
			frame1.add(panel2, BorderLayout.EAST);
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
			
			JLabel label = new JLabel("Wyszukano:", JLabel.LEFT);
			label.setFont(new Font("Name",1, 20));
			panel2.add(label);
			
			this.info = new JTextArea(500,35);
			this.info.setEditable(false);
			info.setText("Tu pojawia sie iformacje o tym, co wyszukasz  		                                         ");
			panel2.add(info);
			
			//comboBox #1
			String[] cos = {"Student","Pracownik","Kurs"};
			glowne = new JComboBox(cos);
			glowne.addActionListener(new combo22());
			glowne.setBounds(30,30,150,25);
			panel1.add(glowne);
			
			//comboBox #2
			combo2 = new JComboBox();
			combo2.setBounds(200,30,150,25);
			panel1.add(combo2);
			
			//TextField
			text = new JTextField(50);
			text.setBounds(20,110,350,30);
			panel1.add(text);
			
			//opisy
			JLabel l1 = new JLabel("Kto/co:");
			l1.setBounds(30,10,150,15);
			panel1.add(l1);
			
			JLabel l2 = new JLabel("Na jakiej podstawie:");
			l2.setBounds(200,10,150,15);
			panel1.add(l2);
			
			JLabel l3 = new JLabel("Wpisz slowo, na podstawie którego wyszukam osobe/kurs");
			l3.setBounds(20,70,350,15);
			panel1.add(l3);
			JLabel l4 = new JLabel("Nastepnie kliknij *Szukaj*");
			l4.setBounds(20,90,350,15);
			panel1.add(l4);
			
			//frame
			frame1.pack();
			frame1.setTitle("Menu Szukania");
			frame1.setSize(930, 500);
			frame1.setResizable(false);
			frame1.setLocationRelativeTo(null);
			frame1.setVisible(true);
		}
		class combo22 implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] stud = {"Imie","Nazwisko","PESEL","Indeks"};
				String[] prac = {"Imie","Nazwisko","Staz","Stanowisko", "Nadgodziny", "Pensja"};
				String[] kurs = {"Nazwa","Nazwisko prowadzacego","Imie prowadzacego","ECTS"};
				String x = (String) glowne.getSelectedItem();
				if (x.equals("Student"))
				{
					combo2.removeAllItems();
					combo2.addItem("Imie");
					combo2.addItem("Nazwisko");
					combo2.addItem("PESEL");
					combo2.addItem("Indeks");
				}
				if (x.equals("Pracownik"))
				{
					combo2.removeAllItems();
					combo2.addItem("Imie");
					combo2.addItem("Nazwisko");
					combo2.addItem("Staz");
					combo2.addItem("Stanowisko");
					combo2.addItem("Nadgodziny");
					combo2.addItem("Pensja");
				}
				if (x.equals("Kurs"))
				{
					combo2.removeAllItems();
					combo2.addItem("Nazwa");
					combo2.addItem("Nazwisko prowadzacego");
					combo2.addItem("Imie prowadzacego");
					combo2.addItem("ECTS");	
				}
			}
		}
		class back implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				frame1.dispose();
				TestGUI nowy = new TestGUI(uczelnia);
			}
		}
		class search implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				int com1 = glowne.getSelectedIndex();
				int com2 = combo2.getSelectedIndex();
				String s = text.getText();
				int i;
				switch(com1)
				{
				case 0:
					switch(com2)
					{
					case 0:
						i = uczelnia.szukajStudentImie(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 1:
						i = uczelnia.szukajStudentNazwisko(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 2:
						i = uczelnia.szukajStudentPESEL(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 3:
						i = uczelnia.szukajStudentIndeks(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					}
					break;
				case 1:
					switch(com2)
					{
					case 0:
						i = uczelnia.szukajPracownikImie(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 1:
						i = uczelnia.szukajPracownikNazwisko(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 2:
						i = uczelnia.szukajPracownikStaz(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 3:
						i = uczelnia.szukajPracownikStanowisko(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 4:
						i = uczelnia.szukajPracownikNadgodziny(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					case 5:
						i = uczelnia.szukajPracownikPensja(s);
						if (i!=-1)
							info.setText(uczelnia.getOsoba(i).wszystkieDane());
						else
							info.setText("Nie znaleziono nikogo takiego");
						break;
					}
					break;
				case 2:
					switch(com2)
					{
					case 0:
						i = uczelnia.szukajKursNazwa(s);
						if (i!=-1)
							info.setText(uczelnia.getKurs(i).wszystkieDane());
						else
							info.setText("Nie znaleziono takiego kursu");
						break;
					case 1:
						i = uczelnia.szukajKursImie(s);
						if (i!=-1)
							info.setText(uczelnia.getKurs(i).wszystkieDane());
						else
							info.setText("Nie znaleziono takiego kursu");
						break;
					case 2:
						i = uczelnia.szukajKursNazwisko(s);
						if (i!=-1)
							info.setText(uczelnia.getKurs(i).wszystkieDane());
						else
							info.setText("Nie znaleziono takiego kursu");
						break;
					case 3:
						i = uczelnia.szukajKursECTS(s);
						if (i!=-1)
							info.setText(uczelnia.getKurs(i).wszystkieDane());
						else
							info.setText("Nie znaleziono takiego kursu");
						break;
					}
					break;
				}
			}
		}
	}
	class Wczytaj implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] opcje = {"Osoby", "Kursy"};
			int x = JOptionPane.showOptionDialog(null, "Co chcesz wczytac?", "Wczytaj", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcje, 0);
			String gdzie;
			switch(x)
			{
			case 0:
				gdzie = JOptionPane.showInputDialog(null, "Skad chcesz wczytac osoby?", "Wczytaj", 0);
				uczelnia.wczytajOsoby(gdzie);
				if(uczelnia.getWczytajTemp()==0)
					JOptionPane.showMessageDialog(null, "Cos poszlo nie tak", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Pomyslnie wczytano osoby", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				break;
			case 1:
				gdzie = JOptionPane.showInputDialog(null, "Skad chcesz wczytac kursy?", "Wczytaj", 0);
				uczelnia.wczytajKursy(gdzie);
				if(uczelnia.getWczytajTemp()==0)
					JOptionPane.showMessageDialog(null, "Cos poszlo nie tak", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Pomyslnie wczytano kursy", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			testChange();
		}
	}
	class Zapisz implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String[] opcje = {"Osoby", "Kursy"};
			int x = JOptionPane.showOptionDialog(null, "Co chcesz zapisac?", "Zapisz", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, opcje, 0);
			String gdzie;
			switch(x)
			{
			case 0:
				gdzie = JOptionPane.showInputDialog(null, "Gdzie chcesz zapisac osoby?", "Zapisz", 0);
				uczelnia.zapiszOsoby(gdzie);
				JOptionPane.showMessageDialog(null, "Pomyslnie zapisano osoby", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				break;
			case 1:
				gdzie = JOptionPane.showInputDialog(null, "Gdzie chcesz zapisac kursy?", "Zapisz", 0);
				uczelnia.zapiszKursy(gdzie);
				JOptionPane.showMessageDialog(null, "Pomyslnie zapisano kursy", "Informacja", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			testChange();
		}
	}
	class UsunHash implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			uczelnia.usunHash();
			JOptionPane.showMessageDialog(null, "Usunieto wszystkie powtarzajace sie osoby");
			testChange();
		}
	}

	
	@Override
	public void addObserver(Observer1 o)
	{
		obs.add(o);
	}
	@Override
	public void removeObserver(Observer1 o)
	{
		obs.remove(o);
	}
	@Override
	public void notifyObservers()
	{
		for(Observer1 o : obs)
			o.update(uczelnia.toString(), ucz);
	}
	
	public void testChange()
	{
		if (!(uczelnia.toString().equals(ucz.getText())))
			notifyObservers();
	}
}
