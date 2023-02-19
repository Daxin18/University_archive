#include "CMax3SatProblem.h"

CMax3SatProblem::CMax3SatProblem()
{
	this->i_number_of_clauses = 0;
	this->i_number_of_variables = 0;
	this->pv_clauses = new vector<array<int, I_CLAUSE_SIZE>>;
}

CMax3SatProblem::~CMax3SatProblem()
{
	delete pv_clauses;
}

bool CMax3SatProblem::bLoad(string sSourcePath)
{
	ifstream sSourceFile(sSourcePath);

	if (!sSourceFile) //jesli plik nie zostal otwarty, zwracamy false, nie ma sensu nic dalej robic bez niego
		return false;

	string sBracket1, sBracket2;
	int iFirstVariable, iSecondVariable, iThirdVariable;
	array<int, I_CLAUSE_SIZE> aClause;
	i_number_of_clauses = 0;
	i_number_of_variables = 0;

	while (sSourceFile.good())
	{
		sSourceFile >> sBracket1 >> iFirstVariable >> iSecondVariable >> iThirdVariable >> sBracket2;
		i_number_of_clauses++;

		aClause[0] = iFirstVariable;
		aClause[1] = iSecondVariable;
		aClause[2] = iThirdVariable;

		for (int i : aClause)
		{
			if (abs(i) > i_number_of_variables)
				i_number_of_variables = abs(i);
		}

		pv_clauses->push_back(aClause);
	}
	i_number_of_variables++; //do tej pory mielismy najwieksza zmienna, ich ilosc to (wartosc_najwiekszej + 1)

	return true;
}

int CMax3SatProblem::iGetNumberOfClauses()
{
	return i_number_of_clauses;
}

int CMax3SatProblem::iGetNumberOfVariables()
{
	return i_number_of_variables;
}

double CMax3SatProblem::dCompute(vector<bool>* pv_genotype)
{
	double i_fits = 0;

	for (int i = 0; i < i_number_of_clauses; i++)
		if (bCheckClause(i, pv_genotype))
			i_fits++;

	return (i_fits / (double)i_number_of_clauses) * D_HUNDRED;
}
bool CMax3SatProblem::bCheckClause(int i_idx, vector<bool>* pv_genotype)
{
	int i_var1 = ((*pv_clauses)[i_idx])[0];
	int i_var2 = ((*pv_clauses)[i_idx])[1];
	int i_var3 = ((*pv_clauses)[i_idx])[2];

	bool b_temp1 = (*pv_genotype)[abs(i_var1)];
	bool b_temp2 = (*pv_genotype)[abs(i_var2)];
	bool b_temp3 = (*pv_genotype)[abs(i_var3)];

	if (i_var1 < 0)
		b_temp1 = !b_temp1;
	if (i_var2 < 0)
		b_temp2 = !b_temp2;
	if (i_var3 < 0)
		b_temp3 = !b_temp3;

	return b_temp1 || b_temp2 || b_temp3;	
}

void CMax3SatProblem::showClause(int i_idx)
{
	int i_var1 = ((*pv_clauses)[i_idx])[0];
	int i_var2 = ((*pv_clauses)[i_idx])[1];
	int i_var3 = ((*pv_clauses)[i_idx])[2];

	cout << S_OPEN_BRACKET << i_var1 << S_SPACE << i_var2 << S_SPACE << i_var3 << S_SPACE << S_CLOSE_BRACKET << endl;
}



