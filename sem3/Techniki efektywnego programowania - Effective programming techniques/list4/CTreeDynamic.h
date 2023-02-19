#pragma once

#include "CNodeDynamic.h"
#include <string>

using std::string;

template <typename T>
class CTreeDynamic
{
public:
	CTreeDynamic();
	~CTreeDynamic();

	CNodeDynamic<T>* pcGetRoot();
	void vPrintTree();

	void v_tree_test_int();

	bool bMoveSubtree(CNodeDynamic<T>* pcParentNode, CNodeDynamic<T>* pcNewChildNode);

	void v_tree_test_double();

	void v_tree_test_string();
	void vPruneByVal(T iVal);

    //modyfikacja
    T* Concatenate();

private:
	CNodeDynamic<T>* pc_root;

	const int i_test_int_root = 0;
	const int i_test_int_child0 = 1;
	const int i_test_int_child1 = 2;
	const int i_test_int_child0_child0 = 11;
	const int i_test_int_child0_child1 = 12;
	const int i_test_int_child1_child0 = 21;
	const int i_test_int_child1_child1 = 22;

	const double d_test_double_root = 0.1;
	const double d_test_double_child0 = 1.1;
	const double d_test_double_child1 = 2.1;
	const double d_test_double_child0_child0 = 11.1;
	const double d_test_double_child0_child1 = 12.1;
	const double d_test_double_child1_child0 = 21.1;
	const double d_test_double_child1_child1 = 22.1;

	const string s_test_string_root = "str0";
	const string s_test_string_child0 = "str1";
	const string s_test_string_child1 = "str0";
	const string s_test_string_child0_child0 = "str2";
	const string s_test_string_child0_child1 = "str3";
	const string s_test_string_child1_child0 = "str1";
	const string s_test_string_child1_child1 = "str0";
};

template <typename T>
CTreeDynamic<T>::CTreeDynamic()
{
    pc_root = new CNodeDynamic<T>();
}

template <typename T>
CTreeDynamic<T>::~CTreeDynamic()
{
    delete pc_root;
}

template <typename T>
CNodeDynamic<T>* CTreeDynamic<T>::pcGetRoot()
{
    return(pc_root);
}

template <typename T>
void CTreeDynamic<T>::vPrintTree()
{
    pc_root->vPrintAllBelow();
}

template <typename T>
void CTreeDynamic<T>::v_tree_test_int()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(i_test_int_root);
    (*pc_root).pcGetChild(0)->vSetValue(i_test_int_child0);
    (*pc_root).pcGetChild(1)->vSetValue(i_test_int_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(i_test_int_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(i_test_int_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(i_test_int_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(i_test_int_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

template <typename T>
bool CTreeDynamic<T>::bMoveSubtree(CNodeDynamic<T>* pcParentNode, CNodeDynamic<T>* pcNewChildNode)
{
    bool bToReturn = pcParentNode->bMoveSubtree(pcNewChildNode);
    return (bToReturn);
}

template <typename T>
void CTreeDynamic<T>::v_tree_test_double()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(d_test_double_root);
    (*pc_root).pcGetChild(0)->vSetValue(d_test_double_child0);
    (*pc_root).pcGetChild(1)->vSetValue(d_test_double_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(d_test_double_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(d_test_double_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(d_test_double_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(d_test_double_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

template <typename T>
void CTreeDynamic<T>::v_tree_test_string()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(s_test_string_root);
    (*pc_root).pcGetChild(0)->vSetValue(s_test_string_child0);
    (*pc_root).pcGetChild(1)->vSetValue(s_test_string_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(s_test_string_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(s_test_string_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(s_test_string_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(s_test_string_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

template <typename T>
void CTreeDynamic<T>::vPruneByVal(T iVal)
{
    pc_root->vPruneByVal(iVal);
}

//modyfikacja
template <typename T>
T* CTreeDynamic<T>::Concatenate()
{
    return (*pc_root).Concatenate();
}
