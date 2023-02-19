#include "CTable.h"

//konstruktory
CTable::CTable()
{
	this->sName = sDefaultName;
	this->iTableLen = iDefaultTableLen;

	this->piTable = new int[iTableLen];

	cout << s_def_const_mess << sName << s_apostrophe << endl;
}

CTable::CTable(string sName, int iTableLen)
{
	this->sName = sName;
	if (iTableLen >= 0)
		this->iTableLen = iTableLen;
	else
		this->iTableLen = iDefaultTableLen;

	this->piTable = new int[this->iTableLen];

	cout << s_par_const_mess << sName << s_apostrophe << endl;
}

CTable::CTable(const CTable& pcOther)
{
	this->sName = pcOther.sName + s_add_when_copying;
	this->iTableLen = pcOther.iTableLen;

	this->piTable = new int[iTableLen];
	int* pi_temp = pcOther.piTable;

	for (int i = 0; i < iTableLen; i++)
		piTable[i] = pi_temp[i];

	cout << s_copy_const_mess << sName << s_apostrophe << endl;
}

CTable::CTable(CTable&& pcOther)
{
	this->sName = pcOther.sName + s_add_when_copying;
	this->iTableLen = pcOther.iTableLen;

	this->piTable = pcOther.piTable;

	pcOther.piTable = NULL;
	pcOther.iTableLen = 0;
	//cout << s_copy_const_mess << sName << s_apostrophe << endl;
}
//destruktor
CTable::~CTable()
{
	if(piTable != NULL)
		delete[] piTable;

	cout << s_destructor_mess << sName << s_apostrophe << endl;
}

//setery
void CTable::vSetName(string sName)
{
	this->sName = sName;
}
bool CTable::bSetNewSize(int iTableLen)
{
	if (iTableLen < 0) return false;

	else
	{
		if (iTableLen < this->iTableLen)
		{
			this->iTableLen = iTableLen;
			return true;
		}

		int* pi_temp = new int[iTableLen];

		for (int i = 0; i < iTableLen; i++)
			pi_temp[i] = piTable[i];

		this->iTableLen = iTableLen;

		delete[] piTable;
		piTable = pi_temp;

		return true;
	}
}

//metoda klonujaca
CTable* CTable::pcClone()
{
	CTable* pc_clone = new CTable(sName, iTableLen);

	pc_clone->piTable = new int[iTableLen];
	int* pi_temp = this->piGetTable();

	for (int i = 0; i < iTableLen; i++)
		pc_clone->piTable[i] = pi_temp[i];

	return pc_clone;
}

//dodatkowo
void CTable::vFillCTable()
{
	for (int i = 0; i < iTableLen; i++)
		piTable[i] = i;
}
void CTable::vShowCTable()
{
	cout << s_open_sqr_bracket;
	for (int i = 0; i < iTableLen; i++)
	{
		cout << piTable[i];
		if (i < iTableLen - 1) cout << s_comma;
	}
	cout << s_close_sqr_bracket << endl;
}

//getery
string CTable::sGetName()
{
	return this->sName;
}
int CTable::iGetTableLen()
{
	return this->iTableLen;
}
int* CTable::piGetTable()
{
	return this->piTable;
}

//procedury dodatkowe
void v_mod_tab(CTable* pcTab, int iNewSize)
{
	pcTab->bSetNewSize(iNewSize);
}

void v_mod_tab(CTable cTab, int iNewSize)
{
	cTab.bSetNewSize(iNewSize);
}

//lista 2
//operator kopiuje wartosc wskaznika piTable, wiec sprawia, ze przyrownane obiekty wskazuja na to samo miejsce w pamieci
//jesli usuniemy jeden z obiektow, to przy usuwaniu drugiego bedziemy probowali dealokowac miejsce, ktore zostalo juz zdealokowane
void CTable::operator=(CTable& pcOther)
{
	if(piTable != NULL)
		delete[] piTable;

	iTableLen = pcOther.iTableLen;

	this->piTable = new int[iTableLen];
	int* pi_temp = pcOther.piTable;

	for (int i = 0; i < iTableLen; i++)
		piTable[i] = pi_temp[i];
}
void CTable::operator=(CTable&& pcOther)
{
	piTable = pcOther.piTable;
	iTableLen = pcOther.iTableLen;

	pcOther.piTable = NULL;
	pcOther.iTableLen = 0;
}

void CTable::vSetValueAt(int iOffset, int iNewVal)
{
	if (iOffset < iTableLen)
		piTable[iOffset] = iNewVal;
}

CTable CTable::operator+(CTable& pcOther)
{
	CTable CTemp(s_sumResult, iTableLen + pcOther.iTableLen);
	for (int i = 0; i < CTemp.iTableLen; i++)
	{
		if (i < iTableLen) CTemp.piTable[i] = piTable[i];
		else CTemp.piTable[i] = pcOther.piTable[i - iTableLen];
	}
	return move(CTemp); //mozna nawet pominac move, bo kompilator sam wywola konstruktor przenoszacy
}

void CTable::operator--(int)
{
	bSetNewSize(iTableLen - 1);
}

