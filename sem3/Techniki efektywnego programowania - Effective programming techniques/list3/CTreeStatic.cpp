
#include "CTreeStatic.h"

CTreeStatic::CTreeStatic()
{
	c_root = CNodeStatic();
}

CTreeStatic::~CTreeStatic()
{

}

CNodeStatic* CTreeStatic::pcGetRoot()
{
	return(&c_root);
}

void CTreeStatic::vPrintTree()
{
	c_root.vPrintAllBelow();
}

void CTreeStatic::v_tree_test1()
{
    c_root.vAddNewChild();
    c_root.vAddNewChild();
    c_root.vSetValue(i_test1_root);
    c_root.pcGetChild(0)->vSetValue(i_test1_child0);
    c_root.pcGetChild(1)->vSetValue(i_test1_child1);
    c_root.pcGetChild(0)->vAddNewChild();
    c_root.pcGetChild(0)->vAddNewChild();
    c_root.pcGetChild(0)->pcGetChild(0)->vSetValue(i_test1_child0_child0);
    c_root.pcGetChild(0)->pcGetChild(1)->vSetValue(i_test1_child0_child1);
    c_root.pcGetChild(1)->vAddNewChild();
    c_root.pcGetChild(1)->vAddNewChild();
    c_root.pcGetChild(1)->pcGetChild(0)->vSetValue(i_test1_child1_child0);
    c_root.pcGetChild(1)->pcGetChild(1)->vSetValue(i_test1_child1_child1);

    cout << endl;

    c_root.vPrintAllBelow();

    cout << endl << endl;

    c_root.pcGetChild(0)->pcGetChild(1)->vPrintUp();

    cout << endl;
}

bool CTreeStatic::bMoveSubtree(CNodeStatic* pcParentNode, CNodeStatic* pcNewChildNode)
{
    bool bToReturn = pcParentNode->bMoveSubtree(pcNewChildNode);

    return (bToReturn);
}

void CTreeStatic::v_tree_test2()
{
    c_root.vAddNewChild();
    c_root.vAddNewChild();
    c_root.vSetValue(i_test2_root);
    c_root.pcGetChild(0)->vSetValue(i_test2_child0);
    c_root.pcGetChild(1)->vSetValue(i_test2_child1);
    c_root.pcGetChild(0)->vAddNewChild();
    c_root.pcGetChild(0)->vAddNewChild();
    c_root.pcGetChild(0)->pcGetChild(0)->vSetValue(i_test2_child0_child0);
    c_root.pcGetChild(0)->pcGetChild(1)->vSetValue(i_test2_child0_child1);
    c_root.pcGetChild(1)->vAddNewChild();
    c_root.pcGetChild(1)->vAddNewChild();
    c_root.pcGetChild(1)->pcGetChild(0)->vSetValue(i_test2_child1_child0);
    c_root.pcGetChild(1)->pcGetChild(1)->vSetValue(i_test2_child1_child1);

    cout << endl;

    c_root.vPrintAllBelow();

    cout << endl << endl;

    c_root.pcGetChild(0)->pcGetChild(1)->vPrintUp();

    cout << endl;
}
