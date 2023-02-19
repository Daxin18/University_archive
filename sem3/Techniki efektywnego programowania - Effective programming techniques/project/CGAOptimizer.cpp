#include "CGAOptimizer.h"

CGAOptimizer::CGAOptimizer(CMax3SatProblem& cProblem)
{
	this->pc_problem = &cProblem;
	this->pc_best_found = nullptr;
	this->v_population = vector<CGAIndividual*>();
}
CGAOptimizer::~CGAOptimizer()
{
	delete pc_best_found;
	for (CGAIndividual* c_indiv : v_population) //nie jestem pewien, czy destruktor vectora sie tym zajmie, takze wole dla bezpieczenstwa wszystko poczyscic recznie
		delete c_indiv;
	//delete pc_problem //nie, bo problem moze byc (i bedzie) uzywany przez inne klasy
}


void CGAOptimizer::vInitialize()
{
	for (int i = 0; i < I_POPULATION_SIZE; i++)
	{
		v_population.push_back(new CGAIndividual(*pc_problem));

		if (i == 0)
			this->pc_best_found = new CGAIndividual(*v_population[i]);
		else
			vCheckForBest(v_population[i]);
	}
}

void CGAOptimizer::vShowPopulation()
{
	for (CGAIndividual* cIndividual : v_population)
	{
		cIndividual->vShow();
		cout << cIndividual->dFitness() << S_PERCENT << endl;
	}
}
void CGAOptimizer::vShowBest()
{
	cout << (*pc_best_found).dFitness() << S_PERCENT << endl;
}

void CGAOptimizer::vRunIteration()
{
	vector<CGAIndividual*> v_new_population = vector<CGAIndividual*>();

	for (int i = 0; i < I_POPULATION_SIZE; i++)
	{
		CGAIndividual* cParent1 = v_population[iChooseParent()];
		CGAIndividual* cParent2 = v_population[iChooseParent()];
		array<CGAIndividual*, 2> aTemp = cParent1->aCrossover(*cParent2);
		CGAIndividual* cChildren1 = aTemp[0];
		CGAIndividual* cChildren2 = aTemp[1];

		cChildren1->vMutation();
		cChildren2->vMutation();

		v_new_population.push_back(cChildren1);
		vCheckForBest(cChildren1);

		//odkomentowac ponizsze, jesli chcemy aby pc_best_found byl najlepszym osobnikiem z aktualnej populacji
		//if (i == 0)
		//	pc_best_found = v_new_population[i];

		if ((++i) < I_POPULATION_SIZE) //dla populacji o nieparzystej liczbie osobnikow nie dodamy drugiego dziecka
		{
			v_new_population.push_back(cChildren2);
			vCheckForBest(cChildren2);
		}
	}

	vSetPopulation(v_new_population);
}
int CGAOptimizer::iChooseParent()
{
	int iToReturn = 0;
	double dBestFitness = 0;
	int iTempIdx = 0;
	double dTempFitness = 0;
	for (int i = 0; i < I_TOURNAMENT_SIZE; i++)
	{
		iTempIdx = (rand() % I_POPULATION_SIZE);
		dTempFitness = v_population[i]->dFitness();
		if (dTempFitness > dBestFitness)
		{
			dBestFitness = dTempFitness;
			iToReturn = iTempIdx;
		}
	}

	return iToReturn;
}

void CGAOptimizer::vSetPopulation(vector<CGAIndividual*>& v_new_one)
{
	for (CGAIndividual* c_indiv : v_population)
		delete c_indiv;

	v_population.clear();

	for (CGAIndividual* c_indiv : v_new_one)
		v_population.push_back(c_indiv);	
}

void CGAOptimizer::vCheckForBest(CGAIndividual* cToCheck)
{
	if (pc_best_found->dFitness() < cToCheck->dFitness())
	{
		delete pc_best_found;
		pc_best_found = new CGAIndividual(*cToCheck);
	}
}

