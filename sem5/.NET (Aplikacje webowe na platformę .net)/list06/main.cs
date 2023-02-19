using System;

namespace Lista6_
{
    class main
    {
        static void Main(string[] args)
        {
            //Z1();
            //Z2();
            //Z3();
            Z4();
            //Z5();
            //Z6();
        }

        static void Z1()
        {
            var t1 = ("Jan", "Kowalski", 34, 12345.67m);

            PrintTuple(t1);
        }
        static void PrintTuple((string name, string surname, int age, decimal salary) tuple)
        {
            //przepisanie krotki do zmiennych i wypisanie
            (string name, string surname, int age, decimal salary) = tuple;
            Console.WriteLine($"{name} {surname}, aged: {age}, earning: {salary}");
            
            //wypisanie zawartosci krotki dostepem do nazw elementow
            Console.WriteLine($"{tuple.name} {tuple.surname}, aged: {tuple.age}, earning: {tuple.salary}");
            
            //wypisanie zawartosci krotki dostem do elementow na podstawie numerowanych wlasciwosci
            Console.WriteLine($"{tuple.Item1} {tuple.Item2}, aged: {tuple.Item3}, earning: {tuple.Item4}");

            //przypisanie krotki z nazwanymi argumentami do elementu o niejawnie okreslonym typie i dostep do elementow po nazwie
            var person = tuple;
            Console.WriteLine($"{person.name} {person.surname}, aged: {person.age}, earning: {person.salary}");

            //nazwy elementow krotki wywnioskowane na podstawie nazw zmiennych
            string s1 = tuple.name;
            string s2 = tuple.surname;
            int i1 = tuple.age;
            decimal d1 = tuple.salary;
            var v1 = (s1, s2, i1, d1);
            Console.WriteLine($"{v1.s1} {v1.s2}, aged: {v1.i1}, earning: {v1.d1}");
        }
        
        static void Z2()
        {
            string @class = "class";

            Console.WriteLine(@class);
        }

        static void Z3()
        {
            //1. CreateInstance, tworzy Array o danym typie i rozmiarze
            Array test = Array.CreateInstance(typeof(int), 10);

            for (int i = 0; i < test.Length; i++)
            {
                //2. SetValue, ustawia dana wartosc na danym indeksie
                test.SetValue(i+1, i);
            }
            PrintArray(test);

            //3. Reverse - odwraca kolejnosc elementow w danej tablicy 1-wymiarowej
            Array.Reverse(test);
            PrintArray(test);

            //4. Sort - sortuje Array korzystajac z podanego IComparer
            //w przypadku null (jak tutaj) korzysta z implementacji IComparable kazdego elementu
            Array.Sort(test);
            PrintArray(test);
        }
        static void PrintArray(Array array)
        {
            Console.Write("[");
            for (int i=0; i< array.Length; i++)
            {
                //5. GetValue - zwraca wartosc elementu na podanym indeksie
                Console.Write(array.GetValue(i));
                //6. GetUpperBound - zwraca najwyzszy indeks w podanym wymiarze tabeli 
                if (i < array.GetUpperBound(0))
                    Console.Write(", ");
            }
            Console.WriteLine("]");
        }

        static void Z4()
        {
            var a1 = new { name = "Jan", surname = "Kowalski", age = 34, salary = 12345.67 };

            PrintAnonymous(a1);
        }

        static void PrintAnonymous(dynamic arg)
        {
            Console.WriteLine($"{arg.name} {arg.surname}, aged: {arg.age}, earning: {arg.salary}");
        }

        static void Z5()
        {
            DrawCard(".NET", width: 1, symbol: '♥', second: "Lista 6");
            Console.WriteLine();
            DrawCard("Ryszard");
            Console.WriteLine();
            DrawCard("Jan", "Kowalski", 'O', 4, 50);
        }
        static void DrawCard(string first, string second = "Rys", char symbol = 'X', int width = 2, int minWidth = 20)
        {
            int maxTextLength = Math.Max(first.Length, second.Length);
            int calculatedWidth = Math.Max(minWidth,(2 * (width + 1) + maxTextLength));
            int row = 0;
            while (row < width)
            {
                Console.WriteLine(new string(symbol, calculatedWidth));
                row++;
            }

            DrawCardLine(first, symbol, width, calculatedWidth);
            DrawCardLine(second, symbol, width, calculatedWidth);

            while (row > 0)
            {
                Console.WriteLine(new string(symbol, calculatedWidth));
                row--;
            }
        }
        static void DrawCardLine(String text, char symbol, int borderWidth, int width)
        {
            int indent = (width - ((2 * borderWidth) + (text.Length))) / 2;
            Console.Write(new string(symbol, borderWidth));
            Console.Write(text.PadLeft(text.Length + indent).PadRight(text.Length + (text.Length%2==0? 2 * indent : 2 * indent +1)));
            Console.WriteLine(new string(symbol, borderWidth));
        }

        static void Z6()
        {
            int[] toPrint = CountMyTypes(1, 1.5, 2, "string", "str", 15.0, -12.0, true, "three", new Object());
            Console.WriteLine($"Integers (even): {toPrint[0]}");
            Console.WriteLine($"Doubles (positive): {toPrint[1]}");
            Console.WriteLine($"Strings (length at least 5): {toPrint[2]}");
            Console.WriteLine($"Everything else: {toPrint[3]}");
        }
        static int[] CountMyTypes(params Object[] parameters)
        {
            int[] result = new int[4];
            int @int = 0;
            int @double = 0;
            int @string = 0;
            int other = 0;

            foreach(var param in parameters)
            {
                switch (param)
                {
                    case int par when par % 2 == 0:
                        @int++;
                        break;
                    case double and > 0:
                        @double++;
                        break;
                    case string par when par.Length >= 5:
                        @string++;
                        break;
                    default:
                        other++;
                        break;
                }
            }
            result[0] = @int;
            result[1] = @double;
            result[2] = @string;
            result[3] = other;

            return result;
        }
    }
}
