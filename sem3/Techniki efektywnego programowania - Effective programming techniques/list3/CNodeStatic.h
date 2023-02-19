#pragma once

#include <cstddef>
#include<iostream>
#include<vector>

using std::cout; using std::endl;

#define s_space " "

class CNodeStatic
{
public:
	CNodeStatic();
	~CNodeStatic();

	void vSetValue(int iNewVal);

	int iGetChildrenNumber();
	void vAddNewChild();
	CNodeStatic* pcGetChild(int iChildOffset);

	void vPrint();
	void vPrintAllBelow();
	void vPrintUp();

	bool bMoveSubtree(CNodeStatic* pcNewChildNode); //pcParentNode to this
	CNodeStatic* pcAlmostCopy(CNodeStatic* pcNewParent);
	int iFindChild(CNodeStatic* pcChildNode);

	bool bIsFromTheSameTreeAs(CNodeStatic* pcSecondNode);

	void vFixAllChildren(CNodeStatic* pcGoodParent);
private:
	std::vector<CNodeStatic> v_children;
	CNodeStatic* pc_parent_node;
	int i_val;
};

