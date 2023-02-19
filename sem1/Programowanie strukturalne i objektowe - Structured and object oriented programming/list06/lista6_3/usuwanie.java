package lista6_3;

public interface usuwanie
{
	//usuwanie pracownika
	public void usunPracownikImie(String s,Uczelnia u);

	public void usunPracownikNazwisko(String s, Uczelnia u);

	public void usunPracownikStaz(String s, Uczelnia u);

	public void usunPracownikStanowisko(String s, Uczelnia u);

	
	//usuwanie studenta
	public void usunStudentImie(String s, Uczelnia u);

	public void usunStudentNazwisko(String s, Uczelnia u);

	public void usunStudentIndeks(String s, Uczelnia u);

	
	//usuwanie kursu
	public void usunKursECTS(String s, Uczelnia u);

	public void usunKursNazwisko(String s, Uczelnia u);
}
