#pragma once

#include <string>
#include <iostream>
#include "CZadania1do3.h"

using std::string; using std::cout;
using std::endl;

class CTable
{
public:
	//konstruktory
	CTable();
	CTable(string sName, int iTableLen);
	CTable(CTable& pcOther);
	//destruktor
	~CTable();
	//setery
	void vSetName(string sName);
	bool bSetNewSize(int iTableLen);
	//metoda klonujaca
	//metoda w teorii moglaby wykorzystac konstruktor kopiujacy, jednak w specyfikacji nie ma nic o tym, ze ma dopisywac "_copy" do
	//nazwy, czy wyswietlac jej, wiec musimy tu przepisywac wiekszosc kodu
	CTable* pcClone();
	//dodatkowo
	void vFillCTable();
	void vShowCTable();
private:
	//pola
	string sName;
	int iTableLen;
	int* piTable;
	//stale uzywane przez obiekty klasy CTable
	const string sDefaultName = "DEFAULT";
	const int iDefaultTableLen = 8;
	const string s_def_const_mess = "bezp: '";
	const string s_par_const_mess = "parametr: '";
	const string s_copy_const_mess = "kopiuj: '";
	const string s_destructor_mess = "usuwam: '";
	const string s_apostrophe = "'";
	const string s_add_when_copying = "_copy";
	//getery
	string sGetName();
	int iGetTableLen();
	int* piGetTable();
};

//procedury dodatkowe
//procedura w ktorej jako parametr podajemy wskaznik, zmodyfikuje obiekt, na ktory on wskazuje z kolei metoda druga
//utworzy kopie obiektu z uzyciem konstruktora kopiujacego, co widac w konsoli (bo konstruktory mialy wypisywac obiekty) i to ja zmodyfikuje
//w procedurze pierwszej kopiujemy wskaznik, ale jego kopia nadal wskazuje na ten sam obiekt
void v_mod_tab(CTable* pcTab, int iNewSize);
void v_mod_tab(CTable cTab, int iNewSize);

