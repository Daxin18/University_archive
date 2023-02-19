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
	if (iTableLen > 0)
		this->iTableLen = iTableLen;
	else
		this->iTableLen = iDefaultTableLen;

	this->piTable = new int[this->iTableLen];

	cout << s_par_const_mess << sName << s_apostrophe << endl;
}

CTable::CTable(CTable& pcOther)
{
	this->sName = pcOther.sGetName() + s_add_when_copying;
	this->iTableLen = pcOther.iGetTableLen();

	this->piTable = new int[iTableLen];
	int* pi_temp = pcOther.piGetTable();

	for (int i = 0; i < iTableLen; i++)
		piTable[i] = pi_temp[i];

	cout << s_copy_const_mess << sName << s_apostrophe << endl;
}

//destruktor
CTable::~CTable()
{
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

	this->iTableLen = iTableLen;
	
	delete[] piTable;
	piTable = new int[iTableLen];
	
	return true;
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

