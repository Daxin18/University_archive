#pragma once

#include "CMax3SatProblem.h"
#include "CGAIndividual.h"

using namespace std;


class CGAOptimizer
{
public:
	CGAOptimizer(CMax3SatProblem& cProblem); //konstruktor optymalizatora dla danego problemu
	~CGAOptimizer(); //destruktor

	void vInitialize(); //inicjalizuje Optimizera losujac populacje

	void vRunIteration(); //wykonuje jedna iteracje, ktorej dokladny opis jest na liscie
	
	CGAIndividual* pcGetBestFound() { return pc_best_found; }; //zwraca wskaznik na najlepszego znalezionego osobnika

	void vShowPopulation(); //wyswietla cala populacje w formie -  genotyp \n fitness % \n
	void vShowBest(); //wyswietla fitness pc_best_found
private:
	CMax3SatProblem* pc_problem; //rozwiazywany problem
	CGAIndividual* pc_best_found; //najlepszy znaleziony osobnik

	vector<CGAIndividual*> v_population; // populacja

	int iChooseParent(); //wybiera rodzica (do krzyzowania)
	void vSetPopulation(vector<CGAIndividual*>& v_new_one); //ustawia nowa populacje usuwajac cala poprzednia
	void vCheckForBest(CGAIndividual* cToCheck); //sprawdza, czy cToCheck jest lepszy niz najlepszy osobnik, jesli tak - sam sie nim staje
};

