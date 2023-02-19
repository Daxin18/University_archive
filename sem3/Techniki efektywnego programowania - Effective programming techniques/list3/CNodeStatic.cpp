
#include "CNodeStatic.h"

CNodeStatic::CNodeStatic()
{
	i_val = 0;
	pc_parent_node = NULL;
}

CNodeStatic::~CNodeStatic()
{
	
}

void CNodeStatic::vSetValue(int iNewVal)
{
	i_val = iNewVal;
}

int CNodeStatic::iGetChildrenNumber()
{
	return(v_children.size());
}

void CNodeStatic::vAddNewChild()
{
	CNodeStatic CNewChild;
	CNewChild.pc_parent_node = this;
	v_children.push_back(CNewChild);

	vFixAllChildren(pc_parent_node);
}

CNodeStatic* CNodeStatic::pcGetChild(int iChildOffset)
{
	if (iChildOffset >= 0 && iChildOffset < (int)v_children.size())
		return &(v_children[iChildOffset]);
	else
		return NULL;
}

void CNodeStatic::vPrint()
{
	cout << s_space << i_val;
}

void CNodeStatic::vPrintAllBelow()
{
	vPrint();
	for (int i = 0; i < (int)v_children.size(); i++)
		v_children[i].vPrintAllBelow();
}

void CNodeStatic::vPrintUp()
{
	cout << s_space << i_val;
	if (pc_parent_node != NULL)
		pc_parent_node->vPrintUp();
}

bool CNodeStatic::bMoveSubtree(CNodeStatic* pcNewChildNode) //pcParentNode to this
{
	if (pcNewChildNode == NULL || pcNewChildNode->pc_parent_node == NULL || this->bIsFromTheSameTreeAs(pcNewChildNode))
		return false;

	//wstawianie kopii do nowego drzewa
	CNodeStatic cNewNode;
	cNewNode = (*(*pcNewChildNode).pcAlmostCopy(this));

	cNewNode.pc_parent_node = this;

	this->v_children.push_back(cNewNode);

	vFixAllChildren(pc_parent_node);

	//usuwanie z pierwotnego drzewa (takie samo jak w dynamicznych)
	int i_child_position = (*pcNewChildNode).pc_parent_node->iFindChild(pcNewChildNode);
	(*pcNewChildNode).pc_parent_node->v_children.erase((*pcNewChildNode).pc_parent_node->v_children.begin() + i_child_position);

	return true;
}

CNodeStatic* CNodeStatic::pcAlmostCopy(CNodeStatic* pcNewParent)
{
	CNodeStatic* pcCopy;
	pcCopy = new CNodeStatic();
	(*pcCopy).i_val = i_val;
	pcCopy->pc_parent_node = pcNewParent;
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		pcCopy->v_children.push_back(*(v_children[i].pcAlmostCopy(pcCopy)));
	}

	return pcCopy;
}

int CNodeStatic::iFindChild(CNodeStatic* pcChildNode)
{
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		if ((&v_children[i]) == pcChildNode)
			return i;
	}
	return -1;
}

bool CNodeStatic::bIsFromTheSameTreeAs(CNodeStatic* pcSecondNode)
{
	if (this == NULL || pcSecondNode == NULL)
		return false;
	if (this->pc_parent_node != NULL)
		return this->pc_parent_node->bIsFromTheSameTreeAs(pcSecondNode);
	if (pcSecondNode->pc_parent_node != NULL)
		return this->bIsFromTheSameTreeAs(pcSecondNode->pc_parent_node);

	return (this == pcSecondNode);
}

void CNodeStatic::vFixAllChildren(CNodeStatic* pcGoodParent)
{
	pc_parent_node = pcGoodParent;
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		v_children[i].vFixAllChildren(this);
	}
}
