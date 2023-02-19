#pragma once

#include <iostream>
#include <string>

using std::string; using std::cout;
using std::endl;

//stale
const int i_value_added_in_z1 = 5;
const string s_nt = "\n\t";
const string s_comma = ", ";
const string s_open_sqr_bracket = "[";
const string s_close_sqr_bracket = "]";

//zadanie 1
bool b_alloc_table_add_5(int iSize);

//zadanie 2
bool b_alloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY);
//dodatkowo
void v_fill_table_2_dim(int*** piTable, int iSizeX, int iSizeY);
void v_show_table_2_dim(int*** piTable, int iSizeX, int iSizeY);

//zadanie 3
bool b_dealloc_table_2_dim(int*** piTable, int iSizeX, int iSizeY);

//modyfikacja
bool b_alloc_table_3_dim(char***& pcTable, int iSizeX, int iSizeY, int iSizeZ);
