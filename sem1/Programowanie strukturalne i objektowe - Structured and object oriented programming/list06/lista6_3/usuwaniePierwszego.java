package lista6_3;

public class usuwaniePierwszego implements usuwanie
{
	//usuwanie pracownika
		public void usunPracownikImie(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof PracownikUczelni)
				{
					PracownikUczelni P = (PracownikUczelni) u.getOsoby().get(i);
					if (P.getImie().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		public void usunPracownikNazwisko(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof PracownikUczelni)
				{
					PracownikUczelni P = (PracownikUczelni) u.getOsoby().get(i);
					if (P.getNazwisko().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		public void usunPracownikStaz(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof PracownikUczelni)
				{
					PracownikUczelni P = (PracownikUczelni) u.getOsoby().get(i);
					if (P.getStaz()==Integer.parseInt(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		public void usunPracownikStanowisko(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof PracownikUczelni)
				{
					PracownikUczelni P = (PracownikUczelni) u.getOsoby().get(i);
					if (P.getStanowisko().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		
		//usuwanie studenta
		public void usunStudentImie(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof Student)
				{
					Student P = (Student) u.getOsoby().get(i);
					if (P.getImie().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		public void usunStudentNazwisko(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof Student)
				{
					Student P = (Student) u.getOsoby().get(i);
					if (P.getNazwisko().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		public void usunStudentIndeks(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getOsoby().size(); i++)
			{
				if (u.getOsoby().get(i) instanceof Student)
				{
					Student P = (Student) u.getOsoby().get(i);
					if (P.getIndeks().equals(s))
					{
						u.getOsoby().remove(u.getOsoby().get(i));
						i--;
						u.setUsunTemp(1);
						break;
					}
				}
			}
		}
		
		//usuwanie kursu
		public void usunKursECTS(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getKursy().size(); i++)
			{
				if (u.getKursy().get(i).getECTS()==Integer.parseInt(s))
				{
					u.getKursy().remove(u.getKursy().get(i));
					i--;
					u.setUsunTemp(1);
					break;
				}
			}
		}
		public void usunKursNazwisko(String s, Uczelnia u)
		{
			u.setUsunTemp(0);
			for(int i=0; i<u.getKursy().size(); i++)
			{
				if (u.getKursy().get(i).getProwadzacy().getNazwisko().equals(s))
				{
					u.getKursy().remove(u.getKursy().get(i));
					i--;
					u.setUsunTemp(1);
					break;
				}
			}
		}
}
