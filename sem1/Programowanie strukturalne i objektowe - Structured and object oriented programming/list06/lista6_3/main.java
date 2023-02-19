package lista6_3;
import java.util.*;

import javax.swing.JOptionPane;

public class main
{
	public static void main(String args[])
	{
		//PracownikBadawczoDydaktyczny(imie, nazwisko, PESEL, wiek, plec, stanowisko, staz, pensja, punktacja)
		PracownikBadawczoDydaktyczny pracownik1 = new PracownikBadawczoDydaktyczny("Julian", "Bak", "01234123412", 43, "M", "Asystent", 2, 2100, 1);
		PracownikBadawczoDydaktyczny pracownik2 = new PracownikBadawczoDydaktyczny("Kryspin", "Kalinowski", "01346123412", 32, "M", "Adiunkt", 4, 2200, 4);
		PracownikBadawczoDydaktyczny pracownik3 = new PracownikBadawczoDydaktyczny("Heronim", "Kozlowski", "01234176543", 62, "M", "Profesor Nadzwyczajny", 35, 3100, 20);
		PracownikBadawczoDydaktyczny pracownik4 = new PracownikBadawczoDydaktyczny("Oktawian", "Kazmierczak", "01239631412", 52, "M", "Profesor Zwyczajny", 20, 3300, 12);
		PracownikBadawczoDydaktyczny pracownik5 = new PracownikBadawczoDydaktyczny("Fabian", "Zawadzki", "01230273916", 42, "M", "Wykladowca", 15, 2600, 6);
		PracownikBadawczoDydaktyczny pracownik11 = new PracownikBadawczoDydaktyczny("Roza", "Szymanska", "01212573742", 52, "K", "Wykladowca", 25, 3100, 2);
		PracownikBadawczoDydaktyczny pracownik12 = new PracownikBadawczoDydaktyczny("Elwira", "Sikorska", "01230826319", 61, "K", "Wykladowca", 26, 2800, 3);
		PracownikBadawczoDydaktyczny pracownik13 = new PracownikBadawczoDydaktyczny("Urszula", "Wojcik", "01234172403", 38, "K", "Profesor Nadzwyczajny", 5, 2600, 2);
		//PracownikAdministracyjny(imie, nazwisko, PESEL, wiek, plec, stanowisko, staz, pensja, nadgodziny)
		PracownikAdministracyjny pracownik6 = new PracownikAdministracyjny("Celina","Kubiak","09345681552",32,"K","Referent",5,2000,8);
		PracownikAdministracyjny pracownik7 = new PracownikAdministracyjny("Bogna","Borkowska","09123573459",52,"K","Referent",15,2100,2);
		PracownikAdministracyjny pracownik8 = new PracownikAdministracyjny("Daniela","Kucharska","00472951552",44,"K","Specjalista",12,2300,4);
		PracownikAdministracyjny pracownik9 = new PracownikAdministracyjny("Ilona","Szewczyk","09345852552",38,"K","Referent",6,2100,3);
		PracownikAdministracyjny pracownik10 = new PracownikAdministracyjny("Lucyna","Malinowska","87523181552",58,"K","Starszy specjalista",21,2600,8);
		//Kursy(nazwa, prowadz�cy, ECTS)
		Kursy kurs1 = new Kursy("PSiO_W", pracownik5, 0);
		Kursy kurs2 = new Kursy("PSiO_C", pracownik3, 2);
		Kursy kurs3 = new Kursy("PSiO_L", pracownik4, 2);
		Kursy kurs4 = new Kursy("Fizyka_1_W", pracownik11, 0);
		Kursy kurs5 = new Kursy("Fizyka_1_C", pracownik4, 2);
		Kursy kurs6 = new Kursy("OSK_W", pracownik12, 0);
		Kursy kurs7 = new Kursy("OSK_C", pracownik13, 2);
		//Student(imie, nazwisko, PESEL, wiek, plec, indeks, iloscKursow, ERASMUS, stopien1, stopien2, stacjonarne, niestacjonarne)
		Student student1 = new Student("Jan", "Piotrowski", "01252594852", 20, "M", 260601, 4, false, true, false, true, false);
		Student student2 = new Student("Gniewomir", "Lis", "01252926391", 20, "M", 260602, 4, true, true, false, true, false);
		Student student3 = new Student("Pawel", "Jakubowski", "08252274852", 22, "M", 260603, 7, false, true, false, false, true);
		Student student4 = new Student("Alan", "Jasinski", "92615731852", 19, "M", 260604, 5, false, true, false, true, false);
		Student student5 = new Student("Barbara", "Jakubowska", "08252275852", 22, "K", 260605, 7, false, true, false, false, true);
		Student student6 = new Student("Kornelia", "Lewandowska", "01232275852", 20, "K", 260606, 2, true, true, false, true, false);
		Student student7 = new Student("Gabriela", "Dabrowska", "01229125912", 19, "K", 260607, 4, false, true, false, true, false);
		Student student8 = new Student("Bogumila", "Stepien", "01215326522", 20, "K", 260608, 4, true, true, false, true, false);
		Student student9 = new Student("Jakub", "Piotrowski", "01252594852", 20, "M", 260601, 4, false, true, false, true, false);
		//Student1
		student1.setKurs(1, kurs4);
		student1.setKurs(2, kurs5);
		student1.setKurs(3, kurs6);
		student1.setKurs(4, kurs7);
		//Student2
		student2.setKurs(1, kurs4);
		student2.setKurs(2, kurs5);
		student2.setKurs(3, kurs6);
		student2.setKurs(4, kurs7);
		//Student3
		student3.setKurs(1, kurs1);
		student3.setKurs(2, kurs2);
		student3.setKurs(3, kurs3);
		student3.setKurs(4, kurs4);
		student3.setKurs(5, kurs5);
		student3.setKurs(6, kurs6);
		student3.setKurs(7, kurs7);
		//Student4
		student4.setKurs(1, kurs1);
		student4.setKurs(2, kurs2);
		student4.setKurs(3, kurs3);
		student4.setKurs(4, kurs4);
		student4.setKurs(5, kurs5);
		//Student5
		student5.setKurs(1, kurs1);
		student5.setKurs(2, kurs2);
		student5.setKurs(3, kurs3);
		student5.setKurs(4, kurs4);
		student5.setKurs(5, kurs5);
		student5.setKurs(6, kurs6);
		student5.setKurs(7, kurs7);
		//Student6
		student6.setKurs(1, kurs6);
		student6.setKurs(2, kurs7);
		//Student7
		student7.setKurs(1, kurs4);
		student7.setKurs(2, kurs5);
		student7.setKurs(3, kurs6);
		student7.setKurs(4, kurs7);
		//Student8
		student8.setKurs(1, kurs4);
		student8.setKurs(2, kurs5);
		student8.setKurs(3, kurs6);
		student8.setKurs(4, kurs7);
		//Student9
		student9.setKurs(1, kurs4);
		student9.setKurs(2, kurs5);
		student9.setKurs(3, kurs6);
		student9.setKurs(4, kurs7);
		//w ko�cu mamy wszystko, co jest potrzebne do stworzenia uczelni... tworzymy i zape�niamy j�...
		ArrayList<Osoba> uczelnia = new ArrayList<Osoba>();
		//zape�nianie uczelni ludzmi (nie po kolei, �eby wyszukiwanie by�o lekko trudniejsze
		uczelnia.add(pracownik1);
		uczelnia.add(pracownik2); 
		uczelnia.add(pracownik3);
		uczelnia.add(pracownik4);
		uczelnia.add(student1);
		uczelnia.add(student2); 
		uczelnia.add(student3); 
		uczelnia.add(pracownik5);
		uczelnia.add(pracownik6);
		uczelnia.add(pracownik7); 
		uczelnia.add(pracownik8);
		uczelnia.add(student4);
		uczelnia.add(student5); 
		uczelnia.add(student6); 
		uczelnia.add(pracownik9);
		uczelnia.add(pracownik10);
		uczelnia.add(pracownik11); 
		uczelnia.add(student7);
		uczelnia.add(student8);
		uczelnia.add(student9);
		uczelnia.add(pracownik12); 
		uczelnia.add(pracownik13);
		//wprowadzanie wszystkich danych uczelni za�atwione, dorobi� jeszcze list� kurs�w.
		ArrayList<Kursy> kursy = new ArrayList<Kursy>();
		kursy.add(kurs1);
		kursy.add(kurs2);
		kursy.add(kurs3);
		kursy.add(kurs4);
		kursy.add(kurs5);
		kursy.add(kurs6); 
		kursy.add(kurs7); 
		
		Uczelnia uczelnia1 = new Uczelnia(uczelnia, kursy);
		Uczelnia uczelnia2 = new Uczelnia();

		
		TestGUI GUI = new TestGUI(uczelnia1);
		//System.out.println(JOptionPane.showOptionDialog(null, "test warto�ci", "TEST", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 0 ));
		//System.out.println(JOptionPane.showInputDialog(null, "Podaj ilo�� nadgodzin: ", "Nowy Pracownik", 0));
	}
}