
#include <iostream>
#include <fstream>
#include "CGAOptimizer.h"

#define I_NUMBER_OF_ITERATIONS 100000
#define S_FILE_NAME "m3s_350_50.txt"

void test()
{
	srand(time(NULL)); //ta metoda musi byc wywolana tylko raz w mainie bo wrzucenie jej np do CGAIndividual::vSetRandomGenotype() psuje wszystko

	CMax3SatProblem cProblem = CMax3SatProblem();
	if (cProblem.bLoad(S_FILE_NAME))
	{
		cout << cProblem.iGetNumberOfClauses() << "\n\n" << cProblem.iGetNumberOfVariables() << "\n\n";
		/*
		CGAIndividual cIndividualTest(cProblem);
		cIndividualTest.vShow();
		cout << cIndividualTest.dFitness() << "%" << endl;
		cout << cIndividualTest[0] << endl << cIndividualTest[1] << endl << cIndividualTest[2] << endl;
		*/
		/*
		CGAOptimizer cOptimizerTest1(cProblem);
		cOptimizerTest1.vInitialize();
		cOptimizerTest1.vShowPopulation();
		cout << "\n\n\n\n";
		cOptimizerTest1.vRunIteration();
		cOptimizerTest1.vShowPopulation();
		*/
		///*
		CGAOptimizer cOptimizerTest2(cProblem);
		cOptimizerTest2.vInitialize();
		for (int i = 0; i < I_NUMBER_OF_ITERATIONS; i++)
		{
			cOptimizerTest2.vShowBest();
			cOptimizerTest2.vRunIteration();
		}
		cOptimizerTest2.vShowBest();
		//*/
	}
}

int main()
{
	test();
}

