#pragma once

#include "CNodeDynamic.h"

class CTreeDynamic
{
public:
	CTreeDynamic();
	~CTreeDynamic();

	CNodeDynamic* pcGetRoot();
	void vPrintTree();

	void v_tree_test1();

	bool bMoveSubtree(CNodeDynamic* pcParentNode, CNodeDynamic* pcNewChildNode);

	void v_tree_test2();

	void v_tree_test_mod();
	void vPruneByVal(int iVal);
private:
	CNodeDynamic* pc_root;

	//stale
	const int i_test1_root = 0;
	const int i_test1_child0 = 1;
	const int i_test1_child1 = 2;
	const int i_test1_child0_child0 = 11;
	const int i_test1_child0_child1 = 12;
	const int i_test1_child1_child0 = 21;
	const int i_test1_child1_child1 = 22;

	const int i_test2_root = 900;
	const int i_test2_child0 = 100;
	const int i_test2_child1 = 200;
	const int i_test2_child0_child0 = 110;
	const int i_test2_child0_child1 = 120;
	const int i_test2_child1_child0 = 210;
	const int i_test2_child1_child1 = 220;

	const int i_test_mod_root = 0;
	const int i_test_mod_child0 = 1;
	const int i_test_mod_child1 = 0;
	const int i_test_mod_child0_child0 = 2;
	const int i_test_mod_child0_child1 = 3;
	const int i_test_mod_child1_child0 = 1;
	const int i_test_mod_child1_child1 = 0;
};

