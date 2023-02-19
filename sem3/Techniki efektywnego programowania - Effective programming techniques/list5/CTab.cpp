#include "CTab.h"

CTab::CTab()
{
	pi_tab = new int[DEF_TAB_SIZE];
	i_size = DEF_TAB_SIZE;
}

CTab::CTab(const CTab& cOther)
{
	v_copy(cOther);
	cout << s_copy_mess;
}

CTab::~CTab()
{
	if (pi_tab != NULL) delete pi_tab;
	cout << s_destr_mess;
}

CTab CTab::operator=(const CTab& cOther)
{
	if (pi_tab != NULL) delete pi_tab;
	v_copy(cOther);
	cout << s_op_eq_mess;
	return(*this);
}

void CTab::v_copy(const CTab& cOther)
{
	pi_tab = new int[cOther.i_size];
	i_size = cOther.i_size;
	for (int ii = 0; ii < cOther.i_size; ii++)
		pi_tab[ii] = cOther.pi_tab[ii];
}

bool CTab::bSetSize(int iNewSize)
{
	if (pi_tab == NULL || iNewSize < 0)
		return false;
	if (iNewSize < i_size)
		this->i_size = iNewSize;
	else
	{
		int* pi_temp = new int[iNewSize];

		for (int i = 0; i < iNewSize; i++)
			pi_temp[i] = pi_tab[i];

		this->i_size = iNewSize;

		delete[] pi_tab;
		pi_tab = pi_temp;
	}
	cout << s_set_mess;
	return true;
}

int CTab::iGetSize()
{
	return(i_size);
}

CTab::CTab(CTab&& cOther)
{
	pi_tab = cOther.pi_tab;
	i_size = cOther.i_size;
	cOther.pi_tab = NULL;
	cOther.i_size = 0;
	cout << s_move_mess;
}

void CTab::operator=(CTab&& cOther)
{
	if(pi_tab != NULL)
		delete pi_tab;
	/*
	pi_tab = new int[cOther.i_size];
	i_size = cOther.i_size;
	for (int ii = 0; ii < cOther.i_size; ii++)
		pi_tab[ii] = cOther.pi_tab[ii];*/
	pi_tab = cOther.pi_tab;
	i_size = cOther.i_size;

	cOther.pi_tab = NULL;
	cOther.i_size = 0;	
}
