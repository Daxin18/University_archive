// constants
const MIN_N = 5;
const MAX_N = 20;
const DEFAULT_N = 10;
const MIN_NUMBER_RANGE = 1;
const MAX_NUMBER_RANGE = 99;

const errorMessage = document.getElementById("error-message");
const tableDiv = document.getElementById("table-div");

// z1
const given_n = window.prompt("Give the n value for number of rows and columns: ", "");
let n = parseInt(given_n, 10);
if(isNaN(n) || MIN_N > n || n > MAX_N)
{
    n = DEFAULT_N;
    errorMessage.innerText = "Given n value (\"" + given_n + "\") is not valid, n was set to the default number = " + DEFAULT_N;
}

const table = document.createElement("table");
const body = document.createElement("tbody");

const numbers = Array.from({length: n}, () => Math.floor(Math.random() * (MAX_NUMBER_RANGE - MIN_NUMBER_RANGE + 1) + MIN_NUMBER_RANGE))

for (var row = -1; row < n; row++) 
{
    const tr = document.createElement("tr");

    for (var col = -1; col <n; col++)
    {
        const isHeader = row === -1 || col === -1;
        const cell = document.createElement(isHeader ? "th" : "td");
        if(isHeader)
        {
            cell.innerText = (numbers[row === -1 ? col : row] ?? "").toString(); // ?? zwraca prawa strone, jesli lewa to null albo undefined
        }
        else
        {
            const product = numbers[col] * numbers[row];
            cell.innerText = product.toString();
            cell.classList.add(product % 2 === 0 ? "even" : "odd");
        }

        tr.appendChild(cell);
    }

    body.appendChild(tr);
}

table.appendChild(body);
tableDiv.appendChild(table);
