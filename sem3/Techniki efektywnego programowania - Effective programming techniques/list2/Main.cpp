
#include "CTable.h"

int main()
{
    //z1,2
    /*
    CTable c_tab_0, c_tab_1;
    c_tab_0.bSetNewSize(6);
    c_tab_1.bSetNewSize(4);
    c_tab_0 = c_tab_1;
    */

    //z3
    ///*
    CTable c_tab_0, c_tab_1;
    c_tab_0.bSetNewSize(6);
    c_tab_1.bSetNewSize(4);
    
    c_tab_0.vSetValueAt(0, 1);
    c_tab_0.vSetValueAt(1, 2);
    c_tab_0.vSetValueAt(2, 3);
    c_tab_0.vSetValueAt(3, 4);
    c_tab_0.vSetValueAt(4, 5);
    c_tab_0.vSetValueAt(5, 6);

    c_tab_1.vSetValueAt(0, 51);
    c_tab_1.vSetValueAt(1, 52);
    c_tab_1.vSetValueAt(2, 53);
    c_tab_1.vSetValueAt(3, 54);

    c_tab_0 = c_tab_1;
    c_tab_1.vSetValueAt(2, 123);
    c_tab_0.vShowCTable();
    c_tab_1.vShowCTable();
    //*/

    //z4
    ///*
    CTable c_tab_2 = c_tab_0 + c_tab_1;
    c_tab_2.vShowCTable();
    //*/

    //modyfikacja
    c_tab_2--;
    c_tab_2.vShowCTable();

    CTable c_tab_3 ("pusta", 0);
    c_tab_3.vShowCTable();
    c_tab_3--;
    c_tab_3.vShowCTable();
}


