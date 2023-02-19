
#include <iostream>
#include "CMySmartPointer.h"
#include "CTab.h"
#include "CTable.h"

//using std::cout; using std::endl; //<-- "CTab.h" juz ma te linijki kodu, nie ma co powtarzac

#define Z3sieWywali false
#define testOperatora true

int main()
{

    //==========================
    //      Smart Pointer
    //==========================
    /*                                   //<--- Smart Pointer
    cout << endl << "------- Smart Pointer --------" << endl << endl; 
     
    int* pi_1;
    pi_1 = new int(1);
    CMySmartPointer<int> pi(pi_1);

    if (testOperatora)
    {
        int* pi_2;
        pi_2 = new int(2);
        CMySmartPointer<int> pi2(pi_2);

        cout << *pi << endl;
        cout << *pi2 << endl;

        pi = pi2;
    }
 
    cout << *pi << endl;

    if (Z3sieWywali)
    {
        int test = 2;

        CMySmartPointer<int> pi2(&test);
    }
    */                                   //<--- Smart Pointer

    //==========================
    //      Move Semantics
    //==========================
    /*                                   //<--- CTab
    cout << endl << "------- CTab --------" << endl << endl;

    CTab c_tab = CTab();
    CTab c_other = CTab(move(c_tab));
    c_other.bSetSize(3);

    c_tab = move(c_other);

    cout << c_other.bSetSize(6) << endl;
    cout << c_tab.bSetSize(2) << endl;

    //c_other = c_tab;
    //c_tab = move(c_tab);
    cout << endl;

    */                                   //<--- CTab

    //zadanie 5
    ///*                                   //<--- CTable
    cout << endl << "------- CTable --------" << endl << endl;

    CTable c_table = CTable();
    CTable c_table2 = CTable("c_other", 5);

    c_table.vFillCTable();
    c_table2.vFillCTable();

    c_table.vShowCTable();
    c_table2.vShowCTable();

    cout << endl << "------- Dodawanie i zapisanie wyniku w drugiej tablicy" << endl << endl;
    c_table2 = c_table + c_table2;

    c_table.vShowCTable();
    c_table2.vShowCTable();

    cout << endl << "------- Przeniesienie wartosci" << endl << endl;

    c_table = move(c_table2);

    c_table.vShowCTable();
    c_table2.vShowCTable();

    //*/                                   //<--- CTable

    //==========================
    //       Modyfikacja
    //==========================
    ///*                                   //<--- modyfikacja
    cout << endl << "------- Modyfikacja --------" << endl << endl;

    int* pi_mod;
    pi_mod = new int(1);
    CTab* cTab;
    cTab = new CTab();
    CMySmartPointer<CTab> spi_mod(cTab);
    CMySmartPointer<CTab> spi_mod_copy = spi_mod.CDuplicate();

    cout << endl << endl << (&(*spi_mod) == &(*spi_mod_copy)) << endl << endl;
    //*/
}

