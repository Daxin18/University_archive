#pragma once

#include<string>
#include<iostream>
#include<fstream>
#include<vector>
#include<array>

using namespace std;

//parametry problemu i/lub optymalizatora
#define I_CLAUSE_SIZE 3 //				x == 3 (dla innych wartosci mamy MaxXSat...)
#define I_POPULATION_SIZE 50 //			x >= 1 (preferowane przynajmniej 2 bo nie ma inaczej jak krzyzowac i najwyzej mutacja cos zmienia)
#define D_CROSSOVER_PROBABILITY 0.3 //	0 <= x <= 1
#define D_MUTATION_PROBABILITY 0.05 //	0 <= x <= 1
#define I_TOURNAMENT_SIZE 2 //			x >= 1

//inne stale
#define S_PERCENT "%"
#define S_SPACE " "
#define S_OPEN_BRACKET "("
#define S_CLOSE_BRACKET ")"
#define D_HUNDRED 100.0

class CMax3SatProblem
{
public:
	CMax3SatProblem(); //konstruktor
	~CMax3SatProblem(); //destruktor

	int iGetNumberOfClauses(); //metoda zwracajaca ilosc klauzul
	int iGetNumberOfVariables(); //metoda zwracajaca ilosc zmiennych

	bool bLoad(string sSourcePath); //metoda ladujaca problem z pliku sSourcePath do obiektu

	double dCompute(vector<bool>* pv_genotype); //metoda wyliczajaca ile procent klauzul spelnia dany genotyp
	void showClause(int i_idx); //metoda wypisujaca klauzule na ekran
private:
	int i_number_of_clauses; //ilosc klauzul
	int i_number_of_variables; //ilosc zmiennych
	vector<array<int, I_CLAUSE_SIZE>>* pv_clauses; //vector klauzul (tablic intow o rozmiarze I_CLAUSE_SIZE)

	bool bCheckClause(int i_index, vector<bool>* pv_genotype); //metoda zwracajaca czy klauzula o danym indeksie jest spelniona przy danym wartosciowaniu
};

