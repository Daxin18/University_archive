#include "CZadania1do3.h"

//zadanie 1
bool b_alloc_table_add_5(int iSize)
{
    if (iSize <= 0) return false;

    int* pi_table;
    pi_table = new int[iSize];

    for (int i = 0; i < iSize; i++)
    {
        pi_table[i] = i + i_value_added_in_z1;
    }

    //wyswietlanie po alokacji i wypelnieniu wartosciami
    cout << s_open_sqr_bracket;
    for (int i = 0; i < iSize; i++)
    {
        cout << pi_table[i];
        if (i < iSize - 1) cout << s_comma;
    }
    cout << s_close_sqr_bracket << endl;

    //dealokacja
    delete[] pi_table;
    return true;
};

//zadanie 2
bool b_alloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY)
{
    if (iSizeX <= 0 || iSizeY <= 0) return false;

    *piTable = new(std::nothrow) int* [iSizeY];
    if ((*piTable) == NULL) return false;

    for (int i = 0; i < iSizeY; i++)
    {
        (*piTable)[i] = new(std::nothrow) int[iSizeX];
        if ((*piTable)[i] == NULL) return false;
    }

    return true;
};

//dodatkowo
void v_fill_table_2_dim(int*** piTable, int iSizeX, int iSizeY)
{
    for (int i = 0; i < iSizeY; i++)
        for (int j = 0; j < iSizeX; j++)
            (*piTable)[i][j] = j;
};

void v_show_table_2_dim(int*** piTable, int iSizeX, int iSizeY)
{
    for (int i = 0; i < iSizeY; i++)
    {
        cout << s_nt;
        for (int j = 0; j < iSizeX; j++)
        {
            cout << (*piTable)[i][j];
            if (j < iSizeX - 1) cout << s_comma;
        }
    }
    cout << endl;
};

//zadanie 3
//nie musimy w ogole podawac iSizeX, poniewaz usuwamy najpierw tablice wskaznikow na tablice "wierszami",
//a potem ja sama, nie musimy wchodzic do pojedynczych tablic
bool b_dealloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY)
{
    if (iSizeX <=0 || iSizeY <=0) return false;
    for (int i = 0; i < iSizeY; i++)
        delete[] (*piTable)[i];
    delete[] *piTable;
    return true;
};

//modyfikacja
bool b_alloc_table_3_dim(char*** &pcTable, int iSizeX, int iSizeY, int iSizeZ)
{
    if (iSizeX <= 0 || iSizeY <= 0 || iSizeZ <= 0) return false;

    pcTable = new(std::nothrow) char** [iSizeX];
    if (pcTable == NULL) return false;

    for (int i = 0; i < iSizeY; i++)
    {
        pcTable[i] = new(std::nothrow) char* [iSizeY];
        if (pcTable[i] == NULL) return false;

        for (int j = 0; j < iSizeY; j++)
        {
            pcTable[i][j] = new(std::nothrow) char[iSizeZ];
            if (pcTable[i][j] == NULL) return false;
        }
    }

    return true;
}
