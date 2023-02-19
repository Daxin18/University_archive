#include <iostream>
#include "CZadania1do3.h"
#include "CTable.h"

int main()
{
    //zadanie 1
    int iSize = 8;
    cout << "\nb_alloc_table_add_5:  " << b_alloc_table_add_5(iSize) << endl;

    //zadanie 2
    int iSizeX = 5;
    int iSizeY = 3;
    int*** piTable = new int**;
    //int** piiTable; //jesli chce tego uzyc to musze przekazac referencje w metodach
    cout << "\nb_alloc_table_2_dim:  " << b_alloc_table_2_dim(piTable, iSizeX, iSizeY) << endl;
    //dodatkowo
    v_fill_table_2_dim(piTable, iSizeX, iSizeY);
    v_show_table_2_dim(piTable, iSizeX, iSizeY);

    //zadanie 3
    cout << "\nb_dealloc_table_2_dim:  " << b_dealloc_table_2_dim(piTable, iSizeX, iSizeY) << endl << endl;
    
    //zadanie 4
    CTable cTableObj;
    cTableObj.vSetName("TEST");
    cTableObj.vFillCTable();
    cTableObj.vShowCTable();
    cout << "bSetNewSize: " << cTableObj.bSetNewSize(10) << endl;
    cTableObj.vFillCTable();
    cTableObj.vShowCTable();

    CTable* pcTableObjClone = cTableObj.pcClone();

    CTable pcTableObjCopy(cTableObj);

    v_mod_tab(pcTableObjClone, 2);
    v_mod_tab(pcTableObjCopy, 2);

    delete pcTableObjClone;

    CTable cToArray1;
    CTable cToArray2("TEST", 5);
    CTable cToArray3(cTableObj);
    CTable CTableTable[3] = { cToArray1, cToArray2, cToArray3 };

    //modyfikacja
    char*** pcTable;
    cout << "\nb_alloc_table_3_dim: " << b_alloc_table_3_dim(pcTable, 8, 7, 5) << endl << endl;

    return 0;
}

