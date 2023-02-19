package pack;

public class Main
{
	public static void main(String args[])
	{
		testMagazyn();
	}
	
	public static void testMagazyn()
	{
		Firma firma1 = new Firma(3);
		
		Magazyn mag1 = new Magazyn();
		Magazyn mag2 = new Magazyn();
		Magazyn mag3 = new Magazyn();
		
		Klient k1 = new Klient();
		Klient k2 = new Klient("Ktos_inny");
		Klient k3 = new Klient("Anonim");
		Klient k4 = new Klient("Pan_Klient");
		Klient k5 = new Klient("Pani_Klient");
		Klient k6 = new Klient("AiSD");
		Klient k7 = new Klient("Gosc_od_kartonow");
		Klient k8 = new Klient("POLREGIO"); //tak, pisze to w pociagu, stad ten pomysl
		Klient k9 = new Klient("Sandman"); //akurat leci ta piosenka na playliscie
		
		Zamowienie z1 = new Zamowienie();
		Zamowienie z2 = new Zamowienie("Towar",5,150);
		Zamowienie z3 = new Zamowienie("Laptop",1,2500);
		Zamowienie z4 = new Zamowienie("Karton", 200, 5);
		Zamowienie z5 = new Zamowienie("Wiertarka", 3, 200);
		Zamowienie z6 = new Zamowienie("Kurtka", 2, 150);
		Zamowienie z7 = new Zamowienie("Pociag", 1, 540320);
		Zamowienie z8 = new Zamowienie("Okulary", 1, 320);
		Zamowienie z9 = new Zamowienie("Ksiazka", 15, 20);
		
		k1.addZamowienie(z3);
		k1.addZamowienie(z2);
		k1.addZamowienie(z5);
		k1.addZamowienie(z1);
		
		k2.addZamowienie(z4);
		k2.addZamowienie(z4);
		k2.addZamowienie(z5);
		k2.addZamowienie(z6);
		k2.addZamowienie(z5);
		k2.addZamowienie(z5);
		
		k3.addZamowienie(z1);
		k3.addZamowienie(z1);
		k3.addZamowienie(z2);
		k3.addZamowienie(z3);
		k3.addZamowienie(z4);
		k3.addZamowienie(z2);
		
		k4.addZamowienie(z8);
		k4.addZamowienie(z9);
		k4.addZamowienie(z2);
		k4.addZamowienie(z9);
		
		k5.addZamowienie(z4);
		k5.addZamowienie(z6);
		k5.addZamowienie(z1);
		k5.addZamowienie(z2);
		
		k6.addZamowienie(z3);
		k6.addZamowienie(z4);
		k6.addZamowienie(z8);
		k6.addZamowienie(z1);
		
		k7.addZamowienie(z4);
		k7.addZamowienie(z4);
		k7.addZamowienie(z4);
		k7.addZamowienie(z4);
		k7.addZamowienie(z4);
		
		k8.addZamowienie(z7);
		k8.addZamowienie(z8);
		
		k9.addZamowienie(z6);
		k9.addZamowienie(z9);
		k9.addZamowienie(z2);
		k9.addZamowienie(z1);
		
		mag1.addKlient(k1);
		mag1.addKlient(k2);
		mag1.addKlient(k3);
		
		mag2.addKlient(k4);
		mag2.addKlient(k5);
		mag2.addKlient(k6);
		
		mag3.addKlient(k7);
		mag3.addKlient(k8);
		mag3.addKlient(k9);
		
		firma1.addMagazyn(mag1);
		firma1.addMagazyn(mag2);
		firma1.addMagazyn(mag3);
		
		firma1.zlicz_przychody();
		firma1.zlicz_przychody();
	}
}
