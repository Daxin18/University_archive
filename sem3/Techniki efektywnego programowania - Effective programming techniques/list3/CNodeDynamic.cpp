#include "CNodeDynamic.h"

CNodeDynamic::CNodeDynamic()
{
	i_val = 0;
	pc_parent_node = NULL;
}

CNodeDynamic::~CNodeDynamic()
{
	for (int i = 0; i < (int)v_children.size(); i++)
		delete v_children[i];
}

void CNodeDynamic::vSetValue(int iNewVal) 
{
	i_val = iNewVal;
}

int CNodeDynamic::iGetChildrenNumber()
{
	return v_children.size();
}

void CNodeDynamic::vAddNewChild()
{
	CNodeDynamic* CNewChild;
	CNewChild = new CNodeDynamic();
	(*CNewChild).pc_parent_node = this;
	v_children.push_back(CNewChild);
}

CNodeDynamic* CNodeDynamic::pcGetChild(int iChildOffset)
{
	if (iChildOffset >= 0 && iChildOffset < (int)v_children.size())
		return v_children[iChildOffset];
	else
		return NULL;
}

void CNodeDynamic::vPrint()
{
	cout << " " << i_val;
}

void CNodeDynamic::vPrintAllBelow()
{
	vPrint();
	for (int i = 0; i < (int)v_children.size(); i++)
		(*v_children[i]).vPrintAllBelow();
}

bool CNodeDynamic::bMoveSubtree(CNodeDynamic* pcNewChildNode) //pcParentNode to this
{
	if (pcNewChildNode == NULL || pcNewChildNode->pc_parent_node == NULL || this->bIsFromTheSameTreeAs(pcNewChildNode))
		return false;

	int i_child_position = (*pcNewChildNode).pc_parent_node->iFindChild(pcNewChildNode);
	(*pcNewChildNode).pc_parent_node->v_children.erase((*pcNewChildNode).pc_parent_node->v_children.begin() + i_child_position);
	
	(*pcNewChildNode).pc_parent_node = this;

	this->v_children.push_back(pcNewChildNode);

	return true;
}

int CNodeDynamic::iFindChild(CNodeDynamic* pcChildNode)
{
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		if (v_children[i] == pcChildNode)
			return i;
	}
	return -1;
}

bool CNodeDynamic::bIsFromTheSameTreeAs(CNodeDynamic* pcSecondNode)
{
	if (this == NULL || pcSecondNode == NULL)
		return false;
	if (this->pc_parent_node != NULL)
		return this->pc_parent_node->bIsFromTheSameTreeAs(pcSecondNode);
	if (pcSecondNode->pc_parent_node != NULL)
		return this->bIsFromTheSameTreeAs(pcSecondNode->pc_parent_node);

	return (this == pcSecondNode);
}

void CNodeDynamic::vPruneByVal(int iVal)
{
	int iOriginalChildren = (int)v_children.size(); //dzieki iOriginalChildren nie odwiedzimy dwukrotnie zadnego dziecka
													//(co dzieje sie przy uzyciu samego (int)v_children.size() w forze,
													//jesli usuniemy ktores dziecko i dopiszemy jego dzieci do v_children)
	//cout << "Prune: " << i_val << endl;
	for (int i = 0; i < iOriginalChildren; i++)
	{
		v_children[i]->vPruneByVal(iVal);
		if (v_children[i]->i_val == iVal)
		{
			vRemoveChild(i--);
			iOriginalChildren--;
		}
	}
}

void CNodeDynamic::vRemoveChild(int iChildNumber)
{
	CNodeDynamic* pcTemp;
	pcTemp = v_children[iChildNumber];
	v_children.erase(v_children.begin() + iChildNumber);
	for (int i = 0; i < (int)pcTemp->v_children.size(); i++)
	{
		pcTemp->v_children[i]->pc_parent_node = this;
		v_children.push_back(pcTemp->v_children[i]);
	}
	pcTemp->v_children.clear();
	delete pcTemp;
}

