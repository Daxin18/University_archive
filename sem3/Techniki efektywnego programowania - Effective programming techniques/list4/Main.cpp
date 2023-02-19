
#include "CTreeDynamic.h"

int main()
{
    //=======================
    //      Drzewo Intow
    //=======================
    ///*
    cout << endl << endl << "------Int------" << endl;

    CTreeDynamic<int> c_DtreeInt;
    c_DtreeInt.v_tree_test_int();

    c_DtreeInt.vPruneByVal(1);
    c_DtreeInt.vPrintTree();

    //*/
    //=======================
    //     Drzewo Double'i
    //=======================
    ///*
    cout << endl << endl << "------Int------" << endl;

    CTreeDynamic<double> c_DtreeDouble;
    c_DtreeDouble.v_tree_test_double();

    c_DtreeDouble.vPruneByVal(1.1);
    c_DtreeDouble.vPrintTree();


    //*/
    //=======================
    //    Drzewo Stringow
    //=======================
    ///*
    cout << endl << endl << "------String------" << endl;

    CTreeDynamic<string> c_DtreeString;
    c_DtreeString.v_tree_test_string();
    CTreeDynamic<string> c_DtreeString2;
    c_DtreeString2.v_tree_test_string();

    c_DtreeString.vPruneByVal("str1");
    c_DtreeString.vPrintTree();
    cout << endl;

    c_DtreeString.bMoveSubtree(c_DtreeString.pcGetRoot(), c_DtreeString2.pcGetRoot()->pcGetChild(0));
    cout << endl;
    c_DtreeString.vPrintTree();
    cout << endl;
    c_DtreeString2.vPrintTree();

    //*/
    //====================
    //    Modyfikacja
    //====================
    ///*
    cout << endl << endl << "------Modyfikacja------" << endl;
    cout << endl;
    cout << "String: " << *c_DtreeString.Concatenate() << endl;
    cout << "Double: " << c_DtreeDouble.Concatenate() << endl; //<-- wypisuje adres, zeby uniknac read access violation z powodu nulla jako adresu
    cout << "Int: " << c_DtreeInt.Concatenate() << endl; //<-- wypisuje adres, zeby uniknac read access violation z powodu nulla jako adresu
    //*/


    cout << endl;
}

