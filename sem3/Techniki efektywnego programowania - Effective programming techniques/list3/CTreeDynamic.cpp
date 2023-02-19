#include "CTreeDynamic.h"

CTreeDynamic::CTreeDynamic()
{
    pc_root = new CNodeDynamic();
}

CTreeDynamic::~CTreeDynamic()
{
	delete pc_root;
}

CNodeDynamic* CTreeDynamic::pcGetRoot()
{
	return(pc_root);
}

void CTreeDynamic::vPrintTree()
{
	pc_root->vPrintAllBelow();
}

void CTreeDynamic::v_tree_test1()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(i_test1_root);
    (*pc_root).pcGetChild(0)->vSetValue(i_test1_child0);
    (*pc_root).pcGetChild(1)->vSetValue(i_test1_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(i_test1_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(i_test1_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(i_test1_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(i_test1_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

bool CTreeDynamic::bMoveSubtree(CNodeDynamic* pcParentNode, CNodeDynamic* pcNewChildNode)
{
    bool bToReturn = pcParentNode->bMoveSubtree(pcNewChildNode);
    return (bToReturn);
}

void CTreeDynamic::v_tree_test2()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(i_test2_root);
    (*pc_root).pcGetChild(0)->vSetValue(i_test2_child0);
    (*pc_root).pcGetChild(1)->vSetValue(i_test2_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(i_test2_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(i_test2_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(i_test2_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(i_test2_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

void CTreeDynamic::v_tree_test_mod()
{
    (*pc_root).vAddNewChild();
    (*pc_root).vAddNewChild();
    (*pc_root).vSetValue(i_test_mod_root);
    (*pc_root).pcGetChild(0)->vSetValue(i_test_mod_child0);
    (*pc_root).pcGetChild(1)->vSetValue(i_test_mod_child1);
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->vAddNewChild();
    (*pc_root).pcGetChild(0)->pcGetChild(0)->vSetValue(i_test_mod_child0_child0);
    (*pc_root).pcGetChild(0)->pcGetChild(1)->vSetValue(i_test_mod_child0_child1);
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->vAddNewChild();
    (*pc_root).pcGetChild(1)->pcGetChild(0)->vSetValue(i_test_mod_child1_child0);
    (*pc_root).pcGetChild(1)->pcGetChild(1)->vSetValue(i_test_mod_child1_child1);

    cout << endl;

    (*pc_root).vPrintAllBelow();

    cout << endl << endl;
}

void CTreeDynamic::vPruneByVal(int iVal)
{
    pc_root->vPruneByVal(iVal);
}
