#include "CGAIndividual.h"

CGAIndividual::CGAIndividual(CMax3SatProblem& cProblem)
{
	this->pc_problem = &cProblem;
	this->i_genotype_size = cProblem.iGetNumberOfVariables();
	this->pv_genotype = new vector<bool>();
	vSetRandomGenotype();
}
CGAIndividual::CGAIndividual(const CGAIndividual& cOther)
{
	this->pc_problem = cOther.pc_problem;
	this->i_genotype_size = cOther.i_genotype_size;
	this->pv_genotype = new vector<bool>();
	for (int i = 0; i < i_genotype_size; i++)
		(*pv_genotype).push_back(cOther[i]);
}
CGAIndividual::CGAIndividual(CMax3SatProblem& cProblem, vector<bool>& v_new_genotype)
{
	this->pc_problem = &cProblem;
	this->i_genotype_size = cProblem.iGetNumberOfVariables();
	this->pv_genotype = new vector<bool>();
	//nie sprawdzamy dlugosci vectorow, bo to konstruktor prywatny uzywany w metodzie, gdzie wszystko jest bezpieczne
	for (int i = 0; i < i_genotype_size; i++)
		(*pv_genotype).push_back(v_new_genotype[i]);
}

CGAIndividual::~CGAIndividual()
{
	pv_genotype->clear();
	if(pv_genotype != nullptr)
		delete pv_genotype;
	//nie chcemy usuwac problemu, bo wiele osobnikow moze miec do niego pointery,
	//zostawiam ten komentarz zebym sobie potem nie pomyslal zeby to robic jak cos sie wywali!!!
}

void CGAIndividual::vSetRandomGenotype()
{
	for (int i = 0; i < i_genotype_size; i++)
		(*pv_genotype).push_back(rand() % 2 == 0);
}

void CGAIndividual::vShow()
{
	for (int i = 0; i < i_genotype_size; i++)
		cout << (*pv_genotype)[i];
	cout << endl;
}

double CGAIndividual::dFitness()
{
	return pc_problem->dCompute(pv_genotype);
}

array<CGAIndividual*, 2> CGAIndividual::aCrossover(CGAIndividual& cOther)
{
	vector<bool> vChildren1 = vector<bool>();
	vector<bool> vChildren2 = vector<bool>();

	if (((double)rand() / (double)RAND_MAX) <= D_CROSSOVER_PROBABILITY)
		for (int i = 0; i < i_genotype_size; i++)
		{
			if (rand() % 2 == 0)
			{
				vChildren1.push_back((*pv_genotype)[i]);
				vChildren2.push_back(cOther[i]);
			}
			else
			{
				vChildren1.push_back(cOther[i]);
				vChildren2.push_back((*pv_genotype)[i]);
			}
		}
	else
	{
		vChildren1 = *(this->pv_genotype);
		vChildren2 = *(cOther.pv_genotype);
	}

	CGAIndividual* pcChild1 = new CGAIndividual(*pc_problem, vChildren1);
	CGAIndividual* pcChild2 = new CGAIndividual(*pc_problem, vChildren2);

	pcChild1->cTryToOptimize();
	pcChild2->cTryToOptimize();

	return array<CGAIndividual*, 2>{pcChild1, pcChild2};
}

void CGAIndividual::vMutation()
{
	for (int i = 0; i < i_genotype_size; i++)
		if (((double)rand() / (double)RAND_MAX) <= D_MUTATION_PROBABILITY)
			(*pv_genotype)[i] = !(*pv_genotype)[i];
}

//modyfikacja
void CGAIndividual::cTryToOptimize()
{
	for (int i = 0; i < i_genotype_size; i++)
	{
		double dPreviousFitness = dFitness(); //zapisujemy aktualny fitness
		(*pv_genotype)[i] = !(*pv_genotype)[i]; //zmieniamy wartosc zmiennej

		if (dFitness() <= dPreviousFitness) //jesli fitness sie nie poprawil, wracamy do poprzedniej wartosci
			(*pv_genotype)[i] = !(*pv_genotype)[i];
	}
}

