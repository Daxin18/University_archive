#pragma once

#include <cstddef>
#include<iostream>
#include<vector>

using std::cout; using std::endl;
using std::string;

template <typename T>
class CNodeDynamic
{
public:
	CNodeDynamic();
	~CNodeDynamic();

	void vSetValue(T iNewVal);
	int iGetChildrenNumber();
	void vAddNewChild();
	CNodeDynamic<T>* pcGetChild(int iChildOffset);

	void vPrint();
	void vPrintAllBelow();

	bool bMoveSubtree(CNodeDynamic<T>* pcNewChildNode); //pcParentNode to this

	bool bIsFromTheSameTreeAs(CNodeDynamic<T>* pcSecondNode);

	void vPruneByVal(T iVal);
	void vRemoveChild(int iChildnumber);

	//modyfikacja
	T* Concatenate();

private:
	std::vector<CNodeDynamic<T>*> v_children;
	CNodeDynamic<T>* pc_parent_node;
	T i_val;

	int iFindChild(CNodeDynamic<T>* pcChildNode);
};

template <typename T>
CNodeDynamic<T>::CNodeDynamic()
{
	pc_parent_node = NULL;
}

template <typename T>
CNodeDynamic<T>::~CNodeDynamic()
{
	for (int i = 0; i < (int)v_children.size(); i++)
		delete v_children[i];
}

template <typename T>
void CNodeDynamic<T>::vSetValue(T iNewVal)
{
	i_val = iNewVal;
}

template <typename T>
int CNodeDynamic<T>::iGetChildrenNumber()
{
	return v_children.size();
}

template <typename T>
void CNodeDynamic<T>::vAddNewChild()
{
	CNodeDynamic* CNewChild;
	CNewChild = new CNodeDynamic();
	(*CNewChild).pc_parent_node = this;
	v_children.push_back(CNewChild);
}

template <typename T>
CNodeDynamic<T>* CNodeDynamic<T>::pcGetChild(int iChildOffset)
{
	if (iChildOffset >= 0 && iChildOffset < (int)v_children.size())
		return v_children[iChildOffset];
	else
		return NULL;
}

template <typename T>
void CNodeDynamic<T>::vPrint()
{
	cout << " " << i_val;
}

template <typename T>
void CNodeDynamic<T>::vPrintAllBelow()
{
	vPrint();
	for (int i = 0; i < (int)v_children.size(); i++)
		(*v_children[i]).vPrintAllBelow();
}

template <typename T>
bool CNodeDynamic<T>::bMoveSubtree(CNodeDynamic<T>* pcNewChildNode) //pcParentNode to this
{
	if (pcNewChildNode == NULL || pcNewChildNode->pc_parent_node == NULL || this->bIsFromTheSameTreeAs(pcNewChildNode))
		return false;

	int i_child_position = (*pcNewChildNode).pc_parent_node->iFindChild(pcNewChildNode);
	(*pcNewChildNode).pc_parent_node->v_children.erase((*pcNewChildNode).pc_parent_node->v_children.begin() + i_child_position);

	(*pcNewChildNode).pc_parent_node = this;

	this->v_children.push_back(pcNewChildNode);

	return true;
}

template <typename T>
int CNodeDynamic<T>::iFindChild(CNodeDynamic<T>* pcChildNode)
{
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		if (v_children[i] == pcChildNode)
			return i;
	}
	return -1;
}

template <typename T>
bool CNodeDynamic<T>::bIsFromTheSameTreeAs(CNodeDynamic<T>* pcSecondNode)
{
	if (this == NULL || pcSecondNode == NULL)
		return false;
	if (this->pc_parent_node != NULL)
		return this->pc_parent_node->bIsFromTheSameTreeAs(pcSecondNode);
	if (pcSecondNode->pc_parent_node != NULL)
		return this->bIsFromTheSameTreeAs(pcSecondNode->pc_parent_node);

	return (this == pcSecondNode);
}

template <typename T>
void CNodeDynamic<T>::vPruneByVal(T iVal)
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

template <typename T>
void CNodeDynamic<T>::vRemoveChild(int iChildNumber)
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

//modyfikacja
template <typename T>
T* CNodeDynamic<T>::Concatenate()
{
	return NULL;
}

template <>
string* CNodeDynamic<string>::Concatenate()
{
	string* s_result = new string(i_val);
	for (int i = 0; i < (int)v_children.size(); i++)
	{
		*s_result += *(v_children[i]->Concatenate());
	}
	return s_result;
}

