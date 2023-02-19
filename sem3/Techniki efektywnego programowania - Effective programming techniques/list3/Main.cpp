
#include "CTreeStatic.h"
#include "CTreeDynamic.h"

int main()
{
    //====================
    // STATYCZNE DRZEWA
    //====================
    ///*
    cout << "Operacje:\n #1 - przeniesienie poddrzewa (dziecko 0 korzenia)"
        << "\n #2 - proba przeniesienia korzenia (skutkujaca porazka)"
        << "\n #3 - proba przeniesienia wezla z tego samego drzewa (skutkujaca porazka)" << endl << endl;
    cout << "------Drzewa Statyczne------" << endl;
    //metody "v_tree_test", ktore tworza drzewa i wypisuja je (wraz z vPrintUp dla root child_0 child_0)
    CTreeStatic c_tree1;
    c_tree1.v_tree_test1();

    CTreeStatic c_tree2;
    c_tree2.v_tree_test2();

    //metody przenoszace poddrzewa wraz z wypisaniem wyniku operacji (tj. 1-true, poddrzewo przeniesione, 0-false, poddrzewo nie zostalo przeniesione)
    cout << endl << endl;
    cout << " #1: " << c_tree1.bMoveSubtree(c_tree1.pcGetRoot(), c_tree2.pcGetRoot()->pcGetChild(0)) << endl; //#1
    cout << " #2: " << c_tree1.bMoveSubtree(c_tree1.pcGetRoot()->pcGetChild(0), c_tree2.pcGetRoot()) << endl; //#2
    cout << " #3: " << c_tree1.bMoveSubtree(c_tree1.pcGetRoot(), c_tree1.pcGetRoot()->pcGetChild(0)) << endl; //#3
    
    //wypisanie drzew po przeniesieniu poddrzew
    cout << endl;
    c_tree1.vPrintTree();
    cout << endl << endl;
    c_tree2.vPrintTree();
    cout << endl << endl;
    c_tree1.pcGetRoot()->pcGetChild(0)->pcGetChild(0)->vPrintUp(); //sprawdza, czy naprawiono wszystkie dzieci drzewa, do ktorego przenieslismy
    cout << endl << endl;
    c_tree1.pcGetRoot()->pcGetChild(2)->pcGetChild(0)->vPrintUp(); //sprawdza, czy naprawiono dzieci przenoszonego drzewa

    //*/
    //====================
    // DYNAMICZNE DRZEWA
    //====================
    ///*
    cout << endl << endl << "------Drzewa Dynamiczne------" << endl;
    //metody "v_tree_test", ktore tworza drzewa i wypisuja je
    CTreeDynamic c_Dtree1;
    c_Dtree1.v_tree_test1();

    CTreeDynamic c_Dtree2;
    c_Dtree2.v_tree_test2();

    //metody przenoszace poddrzewa wraz z wypisaniem wyniku operacji (tj. 1-true, poddrzewo przeniesione, 0-false, poddrzewo nie zostalo przeniesione)
    cout << endl << endl;
    cout << " #1: " << c_Dtree1.bMoveSubtree(c_Dtree1.pcGetRoot(), c_Dtree2.pcGetRoot()->pcGetChild(0)) << endl; //#1
    cout << " #2: " << c_Dtree1.bMoveSubtree(c_Dtree1.pcGetRoot()->pcGetChild(0), c_Dtree2.pcGetRoot()) << endl; //#2
    cout << " #3: " << c_Dtree1.bMoveSubtree(c_Dtree1.pcGetRoot(), c_Dtree1.pcGetRoot()->pcGetChild(0)) << endl; //#3

    //wypisanie drzew po przeniesieniu poddrzew
    cout << endl;
    c_Dtree1.vPrintTree();
    cout << endl << endl;
    c_Dtree2.vPrintTree();
    //*/
    //====================
    //    MODYFIKACJA
    //====================
    ///*
    cout << endl << endl << "------Modyfikacja------" << endl;
    //metoda "v_tree_test_mod", ktora tworzy drzewo i wypisuje je
    CTreeDynamic c_DtreeMod;
    c_DtreeMod.v_tree_test_mod();

    c_DtreeMod.vPruneByVal(1);

    cout << endl;
    c_DtreeMod.vPrintTree();

    //*/
    cout << endl;
}
