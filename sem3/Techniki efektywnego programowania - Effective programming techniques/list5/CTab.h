#pragma once

#include <iostream>;

using std::cout; using std::endl;
using std::move; using std::string;

#define DEF_TAB_SIZE 10

class CTab
{
public:
	CTab();
	CTab(const CTab& cOther);
	CTab(CTab&& cOther);
	CTab operator=(const CTab& cOther);
	~CTab();
	bool bSetSize(int iNewSize);
	int iGetSize();

	void operator=(CTab&& cOther);

private:
	void v_copy(const CTab& cOther);
	int* pi_tab;
	int i_size;

	const string s_copy_mess = "Copy ";
	const string s_destr_mess = "Destr ";
	const string s_op_eq_mess = "op= ";
	const string s_set_mess = "SET ";
	const string s_move_mess = "MOVE ";
};
