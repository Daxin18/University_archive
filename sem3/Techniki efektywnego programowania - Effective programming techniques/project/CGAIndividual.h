#pragma once

#include<random>
#include<ctime>
#include "CMax3SatProblem.h"

class CGAIndividual
{
public:
	CGAIndividual(CMax3SatProblem& cProblem); //konstruktor dla problemu
	CGAIndividual(const CGAIndividual& cOther); //konstruktor kopiujacy
	~CGAIndividual(); //destruktor

	array<CGAIndividual*, 2> aCrossover(CGAIndividual& cOther); //krzyzowanie this z cOther, powstale dzieci zwracane w formie pary wskaznikow
	void vMutation(); //mutowanie osobnika (this)
	double dFitness(); //zwraca przystosowanie aka jakosc rozwiazania danego osobnika (this)



	void cTryToOptimize(); //modyfikacja - optymalizowanie osobnika



	bool operator[](int i_idx) { return (*pv_genotype)[i_idx]; }; //operator [] dla wygody korzystania z osobnikow, i tak sa glownie pojemnikiem na genotyp
	const bool operator[](int i_idx) const { return (*pv_genotype)[i_idx]; }; //rowniez operator[], ale w wersji const, internet mowil, ze warto zrobic oba
	void vShow(); //metoda pokazujaca calego osobnika w postaci genotypu i przystosowania w nowej linijce
private:
	CMax3SatProblem* pc_problem; // problem
	vector<bool>* pv_genotype; //genotyp
	int i_genotype_size; //ilosc zmiennych w genotypie

	CGAIndividual(CMax3SatProblem& cProblem, vector<bool>& v_new_genotype);//konstruktor prywatny, potrzebny do jednej metody
	void vSetRandomGenotype(); //losuje genotyp, metoda uzywana tylko w konstruktorze dla problemu
};

