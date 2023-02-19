#pragma once

#include <cstddef>
#include<iostream>
#include<vector>

using std::cout; using std::endl;

class CNodeDynamic
{
public:
	CNodeDynamic();
	~CNodeDynamic();

	void vSetValue(int iNewVal);
	int iGetChildrenNumber();
	void vAddNewChild();
	CNodeDynamic* pcGetChild(int iChildOffset);

	void vPrint();
	void vPrintAllBelow();

	bool bMoveSubtree(CNodeDynamic* pcNewChildNode); //pcParentNode to this

	bool bIsFromTheSameTreeAs(CNodeDynamic* pcSecondNode);

	void vPruneByVal(int iVal);
	void vRemoveChild(int iChildnumber);

private:
	std::vector<CNodeDynamic*> v_children;
	CNodeDynamic* pc_parent_node;
	int i_val;

	int iFindChild(CNodeDynamic* pcChildNode);
};

