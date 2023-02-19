#		MIPS_Zadanie5
# Gra w kolko i krzyzyk. Uzytkownik vs komputer
# Uzytkownik najpierw inicjuje gre i wybiera znak (o, x)
# nastepnie wybiera ilosc rund (1-5), w kazdej rundzie
# zaczyna uzytkownik, a komputer odpowiada na ruch zgodnie z obrana
# strategia, wyniki kazdej rundy sa zliczane i wyswietlane na koniec
#		Kamil Ciaglo 260413

.data

start: 	.ascii "Witaj w grze w kolko i krzyzyk!\n"
	.ascii "Zacznijmy od wyboru znaku, podaj:\n"
wybor1:	.ascii "x - jesli chcesz grac krzyzykiem\n"
	.asciiz "o - jesli chcesz grac kolkiem\n"
	
error1: .asciiz "\nPodano nieobslugiwany znak! Podaj:\n"

komunikatX: .asciiz "\nWybrales X, przeciwnik bedzie gral O\n"
komunikatO: .asciiz "\nWybrales O, przeciwnik bedzie gral X\n"
		
rundy_kom:	.asciiz "Teraz podaj ilosc rund, ktore chcesz rozegrac (1-5):\n"

error2:	.ascii "Podano bledna wartosc, mozna grac tylko od 1 do 5 rund\n"	
	.asciiz "Podaj ponownie swoj wybor:\n"
	
startGry_kom1: .asciiz "Oto plansza do gry:\n\n"

startGry_kom2:	.ascii "W kazdym ruchu wybierzesz jedno niezajete pole wpisujac jego numer.\n"
		.asciiz	"Celem gry jest ulozenie swoich znakow w lini.\n\tJestes gotow?\n"

Gra_kom1:	.asciiz "Podaj numer pola, na ktorym chcesz postawic znak:\n"

numberError1:	.asciiz "Podano zla wartosc pola, sprobuj ponownie:\n"				
numberError2:	.asciiz "Podano zajete pole, sprobuj ponownie:\n"

Gra_kom2:	.asciiz "Przeciwnik wybral pole\n"

result_kom1:	.asciiz "Wygrales!\n"
result_kom2:	.asciiz "Prrzegrales!\n"
result_kom0:	.asciiz "Remis!\n"		

koniecGry_kom1:	.ascii "Zakonczono wszystkie rundy, 0 oznacza remis, 1 wygrana uzytkownika, 2 wygrana komputera\n"
		.asciiz "Oto wyniki:\n"
		
runda:	.asciiz "\n      Runda nr: "
wynik:	.asciiz ", wynik rundy: "
		

TAB: .asciiz "\t"
SPACE: .asciiz " "
NEWLINE: .asciiz "\n"	
X: .asciiz "X"
O: .asciiz "O"	
znak: .space 2	#miejsce na znak wybrany przez uzytkownika

current_game: .space 9	#plansza, na ktorej bedziemy grac

results: .space 20 #miejsce na wyniki - (0 - remis, 1 - wygral gracz, 2 - wygral komputer)


#	Legenda gdzie co trzymam
# $s0 - znak gracza
# $s1 - znak komputera
# $s2 - ilosc rund
# $s3 - aktualna runda
# $s7 - poprzedni ruch
# $s4 - iterator w rundzie
# $s5 - adres powrotu z rundy
# $s6 - tablica wynikow
#
.text

main:
#============================================
#		Szykowanie planszy
#============================================
	la $a0, current_game	#ladujemy "mape" do $a0
	jal default_map	#ustawiamy w niej wartosci domyslne (cyfry)
	
#============================================
#		Wybor znaku
#============================================

	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, start	#ladujemy do $a0 adres stringa
	syscall
	
error1Return:
	
	li $v0, 8	#ladujemy do $v0 kod 8 - read_string
	la $a0, znak	#ladujemy do $a0 adres odczytanego znaku
	li $a1, 2	#ladujemy do $a1 jego wielkosc
	syscall
	
	lb $t0, znak	#ladujemy do $t0 wybrany znak zeby go sprawdzic	
	beq $t0, 120, wybrano_X	#sprawdzamy czy wybrano x (120 - kod 'x' w ASCII)
	beq $t0, 111, wybrano_O #sprawdzamy czy wybrano o (111 - kod 'o' w ASCII)
	
	#jesli nie wybrano ani x ani o, wyswietlamy komunikat o bledzie
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, error1	#ladujemy do $a0 adres stringa
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, wybor1	#ladujemy do $a0 adres stringa
	syscall
	
	j error1Return	#wracamy do wyboru znaku
	
wybrano_X:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, komunikatX	#ladujemy do $a0 adres stringa
	syscall
	
	lb $s0, X	#zapisujemy wybor gracza i komputera
	lb $s1, O	#(jako wielkie litery, dla czytelnosci)
	
	j rundy	#skaczemy do wyboru ilosci rund
	
wybrano_O:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, komunikatO	#ladujemy do $a0 adres stringa
	syscall	
	
	lb $s0, O	#zapisujemy wybor gracza i komputera
	lb $s1, X	#(jako wielkie litery, dla czytelnosci)
	
	j rundy	#skaczemy do wyboru ilosci rund
	
#============================================
#		Wybor ilosci rund
#============================================
rundyError:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, error2	#ladujemy do $a0 adres stringa
	syscall	
	
	j error2Return	#przeskakujemy do error2Return
rundy:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, rundy_kom	#ladujemy do $a0 adres stringa
	syscall
error2Return:	
	li $v0, 5	#ladujemy do $v0 kod 5 - read_int
	syscall
	
	move $t0, $v0	#przenosimy wybor do rejestru $t0
	
	bgt $t0, 5, rundyError	#jesli wybor jest wiekszy od 5, skaczemy do rundyError
	blt $t0, 1, rundyError	#jesli wybor jest mniejszy niz 1, skaczemy do rundyError
	
	#jesli doszlismy tu - wybor jest poprawny, wiec go przenosimy
	move $s2, $t0
#============================================
#		Printowanie planszy
#============================================	
print_plansza:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, startGry_kom1	#ladujemy do $a0 adres stringa
	syscall
	
	la $a0, current_game	#wstawiamy mape do $a0
	jal print_map	#printujemy ja
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, startGry_kom2	#ladujemy do $a0 adres stringa
	syscall
	
	li $v0, 8	#ladujemy do $v0 kod 8 - read_string
	la $a1, 1
	syscall	#ten syscall pozwoli zdecydowac uzytkownikowi kiedy zaczac rozgrywke
	
#=======================================
#		Rozgrywka
#=======================================
game_prepare:
	li $s3, 0	#szykujemy iterator rund
	la $s6, results	#szykujemy wyniki
game_loop:
	addi $s3, $s3, 1	#zwiekszamy numer rundy
	
	la $a0, current_game	#czyscimy mape
	jal default_map
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, runda	#ladujemy do $a0 adres stringa
	syscall
	
	li $v0, 1	#ladujemy do $v0 kod 1 - print_int
	move $a0, $s3	#przerzucamy do $a0 numer rundy
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, NEWLINE	#ladujemy do $a0 adres stringa
	syscall
	
	la $a0, current_game	#wstawiamy mape do $a0
	jal print_map	#printujemy ja
	
	la $a0, current_game	#ladujemy mape
	jal round	#gramy runde, funkcja zwroci wynik rundy do $v0
	
	move $t0, $v0	#przenosimy wynik rundy do $t0
	
	beq $t0, 1, won	#sprawdzamy czy gracz wygral
	beq $t0, 2, lost	#i czy przegral
	#w przeciwnym wypadku
		li $v0, 4	#ladujemy do $v0 kod 4 - print_string
		la $a0, result_kom0	#ladujemy do $a0 adres stringa
		syscall
		j goOn	#lecimy dalej
	
	won:
		li $v0, 4	#ladujemy do $v0 kod 4 - print_string
		la $a0, result_kom1	#ladujemy do $a0 adres stringa
		syscall
		j goOn	#lecimy dalej
	lost:
		li $v0, 4	#ladujemy do $v0 kod 4 - print_string
		la $a0, result_kom2	#ladujemy do $a0 adres stringa
		syscall
		j goOn	#lecimy dalej
	
	goOn:
	
	sb $t0, ($s6)	#zapisujemy wynik rundy
	addi $s6, $s6, 4	#zwiekszamy adres
	
	bne $s3, $s2, game_loop	#jesli nie doszlismy do konca rund, gramy kolejna
	#w przeciwnym wypadku konczymy rozgrywke
#====================================
#		KONIEC
#====================================
Results_prepare:
	li $s3, 0	#szykujemy iterator rund
	la $t0, results	#szykujemy adres tablicy wynikow
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, koniecGry_kom1	#ladujemy do $a0 adres stringa
	syscall
Results_loop:
	addi $s3, $s3, 1	#zwiekszamy numer rundy
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, runda	#ladujemy do $a0 adres stringa
	syscall
	
	li $v0, 1	#ladujemy do $v0 kod 1 - print_int
	add $a0, $zero, $s3	#ladujemy do $a0 numer rundy
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, wynik	#ladujemy do $a0 adres stringa
	syscall
	
	lb $t1, ($t0)	#ladujemy wynik do $t1
	addi $t0, $t0, 4	#zwiekszamy adres
	
	li $v0, 1	#ladujemy do $v0 kod 1 - print_int
	move $a0, $t1	#ladujemy do $a0 wynik rundy
	syscall
	
	bne $s3, $s2, Results_loop	#jesli nie doszlismy do konca rund, powtarzamy petle
	#w przeciwnym wypadku konczymy
end:	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, NEWLINE	#ladujemy do $a0 adres stringa
	syscall

	li $v0, 10
	syscall

#---------------------------------------------------------------------------------------------------------------#	
#---------------------------------------------------------------------------------------------------------------#
#				!	!!	!!!	FUNKCJE		!!!	!!	!			#
#---------------------------------------------------------------------------------------------------------------#		
#---------------------------------------------------------------------------------------------------------------#				
#=====================================
#		round
#=====================================
# w $a0 adres planszy/mapy, zwraca w $v0 wynik rundy (0 - remis, 1 - wygral gracz, 2 - wygral komputer)
round:
	move $s5, $ra	#zapamietujemy adres powrotu w $s5 - bedziemy wywolywac kolejne funkcje, co nadpisaloby $ra
	la $a0, current_game	#zapamietujemy mape
	li $s4, 0	#szykujemy iterator dla zliczania ruchow
round_loop:
#================================================================================== Gracz
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, Gra_kom1	#ladujemy do $a0 adres stringa
	syscall
	
	errorReturn:	#tab w tym miejscu ulatwia mi prace z kodem, nie jest przypadkowy
	
	li $v0, 5	#ladujemy do $v0 kod 5 - read_int
	syscall
	
	move $s7, $v0	#przerzucamy wybor gracza do $s7
	
	bgt $s7, 9, round_error1 #sprawdzamy, czy liczba jest w zakresie pol
	blt $s7, 1, round_error1 #sprawdzamy, czy liczba jest w zakresie pol
	
	la $a0, current_game	#szykujemy sie do checkField
	move $a1, $s7
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	move $t0, $v0	 #wynik przenosimy do $t0
	beq $t0, 1, round_error2	#jesli pole bylo zajete, wyswietlamy komunikat i wracamy
	#w przeciwnym razie mozemy zajac pole
	
	move $t0, $s7	#przerzucamy wybor do $t0
	subi $t0, $t0, 1	#zmniejszamy o 1, zeby dostac dobry adres
	la $t1, current_game	#przerzucamy mape do $t1
	add $t0, $t1, $t0	#dodajemy adres pola
	move $t2, $s0	#przerzucamy znak gracza
	sb $t2, ($t0)	#zapisujemy znak gracza w dobrym miejscu
	
	addi $s4, $s4, 1	#zwiekszamy iterator
	
	la $a0, current_game	#przerzucamy mape zeby ja pokazac
	jal print_map
	
	la $a0, current_game	#szykujemy sie do sprawdzenia, czy gracz wygral
	move $a1, $s7
	jal checkForWin	#(0 - nie wygral, 1 - wygral)
	
	move $t0, $v0	#przerzucamy wynik funkcji
	beq $t0, 1, WIN # jesli gracz wygral, konczymy
	beq $s4, 9, TIE	# jesli byl to 9. ruch, mamy remis
#================================================================================== Komputer
	#j round_loop	#<-- do testow wszystkiego poza AI
	
#--- PROTOCOL 1 --- Link to Pilot --- jesli dostepne jest pole na srodku, wybieramy je

	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 5
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	move $t0, $v0	 #wynik przenosimy do $t0
	beq $t0, 1, continue1	#jesli pole bylo zajete, lecimy dalej
	#w przeciwnym razie mozemy zajac pole
	la $t0, current_game
	move $t2, $s1	#przerzucamy znak AI
	sb $t2, 4($t0)	#zapisujemy znak AI w polu 5
	
	li $s7, 5	#zapisujemy ruch AI
	
	addi $s4, $s4, 1	#zwiekszamy iterator
	
	j print_AI #wracamy do petli

#--- PROTOCOL 2 --- Uphold the Mission --- jesli da sie wygrac, zrob to
continue1:
	la $a0, current_game	#szykujemy sie do sprawdzenia, czy mozemy wygrac
	move $a1, $s1
	jal possibleWin	#(0 - nie mozemy, inny numer - pole, ktorym wygramy)
	
	move $t0, $v0	#przerzucamy wynik
	beq $t0, 0, continue2	#jesli nie ma szansy wygranej, lecimy dalej
	#w przeciwnym wypadku mamy mozliwosc wygranej i z niej korzystamy
	la $t1, current_game	#przerzucamy adres tablicy

	move $s7, $t0	#zapisujemy ruch AI
	
	subi $t0, $t0, 1	#odejmujemy 1 od wyniku, zeby otrzymac indeks pola
	add $t1, $t1, $t0	#ustawiamy dobry adres
	move $t0, $s1	#przerzucamy znak AI
	sb $t0, ($t1)	#zapisujemy znak AI w polu, ktore umozliwi wygrana
	
	addi $s4, $s4, 1	#zwiekszamy iterator
	
	j print_AI #wracamy do petli
	
#--- PROTOCOL 3 --- Protect the Pilot --- jesli przeciwnik moze wygrac, nie dopuszczamy do tego
continue2:
	la $a0, current_game	#szykujemy sie do sprawdzenia, czy gracz moze wygrac
	move $a1, $s0
	jal possibleWin	#(0 - nie mozemy, inny numer - pole, ktorym wygramy)
	
	move $t0, $v0	#przerzucamy wynik
	beq $t0, 0, continue3	#jesli nie ma szansy wygranej, lecimy dalej
	#w przeciwnym wypadku gracz ma mozliwosc wygranej, wiec ja blokujemy
	la $t1, current_game	#przerzucamy adres tablicy
	
	move $s7, $t0	#zapisujemy ruch AI
	
	subi $t0, $t0, 1	#odejmujemy 1 od wyniku, zeby otrzymac indeks pola
	add $t1, $t1, $t0	#ustawiamy dobry adres
	move $t0, $s1	#przerzucamy znak AI
	sb $t0, ($t1)	#zapisujemy znak AI w polu, ktore umozliwi wygrana
	
	addi $s4, $s4, 1	#zwiekszamy iterator
	
	j print_AI #wracamy do petli
	
#--- PROTOCOL 4 --- Don't care --- wstawiamy znak na pierwsze wolne miejsce
continue3:
	li $t8, 0	#szykujemy w $t8 iterator
continue_loop:
	addi $t8, $t8, 1	#zwiekszamy iterator

	la $a0, current_game	#szykujemy sie do checkField
	move $a1, $t8
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, continue_loop	#jesli jest zajete to sprawdzamy kolejne pole
	
	move $t0, $t8	#przerzucamy wynik (wolne pole)
	
	move $s7, $t0	#zapisujemy ruch AI
	
	la $t1, current_game	#przerzucamy adres tablicy
	subi $t0, $t0, 1	#odejmujemy 1 od wyniku, zeby otrzymac indeks pola
	add $t1, $t1, $t0	#ustawiamy dobry adres
	move $t0, $s1	#przerzucamy znak AI
	sb $t0, ($t1)	#zapisujemy znak AI w polu, ktore jest wolne
	
	addi $s4, $s4, 1	#zwiekszamy iterator
	
	j print_AI #wracamy do petli - tutaj jest to tylko, gdybym chcial rozbudowac bardziej
	
#================================================================================== Wyniki i bledy
print_AI:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, Gra_kom2	#ladujemy do $a0 adres stringa
	syscall
	
	la $a0, current_game
	jal print_map
	
	la $a0, current_game	#szykujemy sie do sprawdzenia, czy AI wygralo
	move $a1, $s7
	jal checkForWin	#(0 - nie wygral, 1 - wygral)
	
	move $t0, $v0	#przerzucamy wynik funkcji
	beq $t0, 1, LOSS # jesli AI wygralo, konczymy
	#jesli nie, lecimy dalej
	j round_loop

WIN:	
	li $v0, 1	#ustawiamy wynik
	jr $s5	#i wracamy
LOSS:
	li $v0, 2	#ustawiamy wynik
	jr $s5	#i wracamy
TIE:
	li $v0, 0	#ustawiamy wynik
	jr $s5	#i wracamy

round_error1:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, numberError1	#ladujemy do $a0 adres stringa
	syscall
	
	j errorReturn	#wracamy do podawania pola
round_error2:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, numberError2	#ladujemy do $a0 adres stringa
	syscall
	
	j errorReturn	#wracamy do podawania pola
#========================================
#		possibleWin
#========================================
# w $a0 adres planszy/mapy, w $a1 znak, ktory chcemy sprawdzic, zwraca w $v0 informacje (0 - nie ma jak wygrac; numer pola, ktorym mozemy wygrac)
possibleWin:
	move $t0, $a0	#przerzucamy adres mapy
	move $t1, $a1	#przerzucamy znak, ktory sprawdzamy
	move $t9, $ra
	
	move $t0, $a0	#przerzucamy adres mapy
	lb $t2, ($t0)	#zapisujemy pole nr1
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr2
	addi $t0, $t0, 1
	
	bne $t2, $t3, co1	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co1
	jal pick_3	#jesli tak, to wybieramy pole 3
co1:	
	lb $t2, ($t0)	#zapisujemy pole nr3
	
	bne $t2, $t3, co2	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co2
	jal pick_1	#jesli tak, to wybieramy pole 1
co2:
	addi $t0, $t0, 3
	lb $t3, ($t0)	#zapisujemy pole nr6
	
	bne $t2, $t3, co3	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co3
	jal pick_9	#jesli tak, to wybieramy pole 9
co3:
	addi $t0, $t0, 3
	lb $t2, ($t0)	#zapisujemy pole nr9
	
	bne $t2, $t3, co4	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co4
	jal pick_3	#jesli tak, to wybieramy pole 3
co4:
	subi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr8
	
	bne $t2, $t3, co5	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co5
	jal pick_7	#jesli tak, to wybieramy pole 9
co5:
	subi $t0, $t0, 1
	lb $t2, ($t0)	#zapisujemy pole nr7
	
	bne $t2, $t3, co6	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co6
	jal pick_9	#jesli tak, to wybieramy pole 9
co6:
	subi $t0, $t0, 3
	lb $t3, ($t0)	#zapisujemy pole nr4
	
	bne $t2, $t3, co7	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co7
	jal pick_1	#jesli tak, to wybieramy pole 1
co7:
	subi $t0, $t0, 3
	lb $t2, ($t0)	#zapisujemy pole nr1
	
	bne $t2, $t3, co8	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co8
	jal pick_7	#jesli tak, to wybieramy pole 7
co8:
	addi $t0, $t0, 4
	lb $t2, ($t0)	#zapisujemy pole nr5
	
	bne $t2, $t3, co9	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co9
	jal pick_6	#jesli tak, to wybieramy pole 6
co9:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr6
	
	bne $t2, $t3, co10	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co10
	jal pick_4	#jesli tak, to wybieramy pole 4
co10:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr7
	
	bne $t2, $t3, co11	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co11
	jal pick_3	#jesli tak, to wybieramy pole 3
co11:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr8
	
	bne $t2, $t3, co12	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co12
	jal pick_2	#jesli tak, to wybieramy pole 2
co12:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr9
	
	bne $t2, $t3, co13	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co13
	jal pick_1	#jesli tak, to wybieramy pole 1
co13:
	move $t0, $a0	#przerzucamy adres mapy
	lb $t3, ($t0)	#zapisujemy pole nr1
	
	bne $t2, $t3, co14	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co14
	jal pick_9	#jesli tak, to wybieramy pole 9
co14:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr2
	
	bne $t2, $t3, co15	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co15
	jal pick_8	#jesli tak, to wybieramy pole 8
co15:
	addi $t0, $t0, 1
	lb $t3, ($t0)	#zapisujemy pole nr3
	
	bne $t2, $t3, co16	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co16
	jal pick_7	#jesli tak, to wybieramy pole 7
co16:
	move $t0, $a0	#przerzucamy adres mapy
	lb $t2, ($t0)	#zapisujemy pole nr1
	
	bne $t2, $t3, co17	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co17
	jal pick_2	#jesli tak, to wybieramy pole 2
co17:
	addi $t0, $t0, 8
	lb $t2, ($t0)	#zapisujemy pole nr9
	
	bne $t2, $t3, co18	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co18
	jal pick_6	#jesli tak, to wybieramy pole 6
co18:
	subi $t0, $t0, 2
	lb $t2, ($t0)	#zapisujemy pole nr7
	
	bne $t2, $t3, co19	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co19
	jal pick_5	#jesli tak, to wybieramy pole 5
co19:
	subi $t0, $t0, 6
	lb $t3, ($t0)	#zapisujemy pole nr1
	
	bne $t2, $t3, co20	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co20
	jal pick_4	#jesli tak, to wybieramy pole 4	
co20:
	addi $t0, $t0, 8
	lb $t3, ($t0)	#zapisujemy pole nr9
	
	bne $t2, $t3, co21	#jesli nie sa rowne, sprawdzamy dalej
	#w przeciwnym wypadku sprawdzamy, czy jest na nich dobry znak
	bne $t2, $t1, co21
	jal pick_8	#jesli tak, to wybieramy pole 8	
		
co21:	
	li $v0, 0	#jesli tu jestesmy, to znaczy, ze nie ma szansy wygranej aktualnie z danym znakiem
	jr $t9
	
#----------- wybieranie pol, ktorymi mozna wygrac
pick_1:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 1
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 1	#wybieramy 1
	jr $t9		#i wracamy
pick_2:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 2
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci

	li $v0, 2	#wybieramy 2
	jr $t9		#i wracamy
pick_3:	
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 3
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 3	#wybieramy 3
	jr $t9		#i wracamy
pick_4:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 4
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 4	#wybieramy 4
	jr $t9		#i wracamy
pick_5:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 5
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 5	#wybieramy 5
	jr $t9		#i wracamy
pick_6:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 6
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 6	#wybieramy 6
	jr $t9		#i wracamy
pick_7:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 7
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 7	#wybieramy 7
	jr $t9		#i wracamy
pick_8:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 8
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 8	#wybieramy 8
	jr $t9		#i wracamy
pick_9:
	move $t8, $ra
	la $a0, current_game	#szykujemy sie do checkField
	li $a1, 9
	jal checkField	#sprawdzamy, czy pole nie jest zajete	(0 - wolne, 1 - zajete)
	
	beq $v0, 1, pickReturn	#jesli pole jest zajete to sprawdzamy inne mozliwosci
	
	li $v0, 9	#wybieramy 9
	jr $t9		#i wracamy
pickReturn:
	jr $t8
	#jr $ra	#<-- do starszych testow (nie dziala)
	
#========================================
#		checkField
#========================================
# w $a0 adres planszy/mapy, w $a1 wybrane pole, zwraca w $v0 informacje (0 - pole wolne, 1 - pole zajete)
checkField:
	move $t5, $a0	#przerzucamy adres mapy
	move $t6, $a1	#przerzucamy wybrane pole
	
	subi $t6, $t6, 1	#zmniejszamy wybrane pole (by dodac jego wartosc do adresu i uzyskac faktyczny adres pola)
	add $t5, $t5, $t6
	
	lb $t5, ($t5)	#zapisujemy bajt zapisany w polu
	lb $t6, X	#zapisujemy w $t1 znak X
	lb $t7, O	#zapisujemy w $t2 znak O
	
	beq $t5, $t6, zajete	#jesli na wybranym polu byl X, przeskakujemy do etykiety zajete
	beq $t5, $t7, zajete	#jesli na wybranym polu bylo O, przeskakujemy do etykiety zajete
	#jesli jest tam cokolwiek innego, zwracamy 0
	li $v0, 0	#ladujemy do $v0 0
	jr $ra	#i wracamy
	
zajete:
	li $v0, 1	#ladujemy do $v0 1
	jr $ra	#i wracamy
	
#========================================
#		checkForWin
#========================================
# w $a0 adres planszy/mapy, w $a1 wybrane pole, zwraca w $v0 informacje (0 - nie wygral, 1 - wygral)
checkForWin:
	move $t0, $a0	#przerzucamy adres mapy
	move $t1, $a1	#przerzucamy wybrane pole
	li $v0, 0	#domyslnie ustawiamy 0 - brak wygranej
	move $t8, $ra	#ustawiamy $t8 na adres powrotu
	
	beq $t1, 1, l1	#sprawdzamy wybrane pole
	beq $t1, 2, l2	#sprawdzamy wybrane pole
	beq $t1, 3, l3	#sprawdzamy wybrane pole
	beq $t1, 4, l4	#sprawdzamy wybrane pole
	beq $t1, 5, l5	#sprawdzamy wybrane pole
	beq $t1, 6, l6	#sprawdzamy wybrane pole
	beq $t1, 7, l7	#sprawdzamy wybrane pole
	beq $t1, 8, l8	#sprawdzamy wybrane pole
	beq $t1, 9, l9	#sprawdzamy wybrane pole
	
	jr $t8	#nie powinnismy tu nigdy dojsc, jednak wole miec pewnosc ze nie zwrocimy blednego wyniku

l1:
	jal row1	#sprawdzamy wygrana w rzedzie 1
	move $t0, $a0	#resetujemy adres mapy
	jal column1	#sprawdzamy wygrana w kolumnie 1
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal1	#sprawdzamy wygrana w przekotnej 1
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l2:
	jal row1	#sprawdzamy wygrana w rzedzie 1
	move $t0, $a0	#resetujemy adres mapy
	jal column2	#sprawdzamy wygrana w kolumnie 2
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l3:
	jal row1	#sprawdzamy wygrana w rzedzie 1
	move $t0, $a0	#resetujemy adres mapy
	jal column3	#sprawdzamy wygrana w kolumnie 3
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal2	#sprawdzamy wygrana w przekotnej 2
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l4:
	jal row2	#sprawdzamy wygrana w rzedzie 2
	move $t0, $a0	#resetujemy adres mapy
	jal column1	#sprawdzamy wygrana w kolumnie 1
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l5:
	jal row2	#sprawdzamy wygrana w rzedzie 2
	move $t0, $a0	#resetujemy adres mapy
	jal column2	#sprawdzamy wygrana w kolumnie 2
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal1	#sprawdzamy wygrana w przekotnej 1
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal2	#sprawdzamy wygrana w przekotnej 2
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l6:
	jal row2	#sprawdzamy wygrana w rzedzie 2
	move $t0, $a0	#resetujemy adres mapy
	jal column3	#sprawdzamy wygrana w kolumnie 3
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l7:
	jal row3	#sprawdzamy wygrana w rzedzie 3
	move $t0, $a0	#resetujemy adres mapy
	jal column1	#sprawdzamy wygrana w kolumnie 1
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal2	#sprawdzamy wygrana w przekotnej 2
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l8:
	jal row3	#sprawdzamy wygrana w rzedzie 3
	move $t0, $a0	#resetujemy adres mapy
	jal column2	#sprawdzamy wygrana w kolumnie 2
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1
l9:
	jal row3	#sprawdzamy wygrana w rzedzie 3
	move $t0, $a0	#resetujemy adres mapy
	jal column3	#sprawdzamy wygrana w kolumnie 3
	move $t0, $a0	#resetujemy adres mapy
	jal diagonal1	#sprawdzamy wygrana w przekotnej 1
	
	jr $t8	#zwracamy wynik 0 (jesli zadna z wewnetrznych funkcji nie zwrocila 1

#============funkcje wewnetrzne	
	row1:
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 1
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 1
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	row2:
		addi $t0, $t0, 3	#zwiekszamy adres o 3, aby dostac sie do drugiego rzedu
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 1
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 1
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	row3:
		addi $t0, $t0, 6	#zwiekszamy adres o 6, aby dostac sie do trzeciego rzedu
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 1
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 1
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	column1:
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 3
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 3
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	column2:
		addi $t0, $t0, 1	#zwiekszamy o 1, zeby znalezc sie w drugiej kolumnie
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 3
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 3
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	column3:
		addi $t0, $t0, 2	#zwiekszamy o 2, zeby znalezc sie w trzeciej kolumnie
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 3
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 3
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	diagonal1:
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 4
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 4
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkcji
	diagonal2:
		addi $t0, $t0, 2	#zwiekszamy o 2, zeby znalezc sie w polu 3
		lb $t2, ($t0)	#zapisujemy pole nr1
		addi $t0, $t0, 2
		lb $t3, ($t0)	#zapisujemy pole nr2
		addi $t0, $t0, 2
		lb $t4, ($t0)	#zapisujemy pole nr3
		
		bne $t2, $t3, checkReturn	#jesli pola sa rozne - wracamy
		bne $t2, $t4, checkReturn	#jesli pola sa rozne - wracamy
		#tu znajdziemy sie tylko jesli wszystkie 3 pola sa rowne
		li $v0, 1 	#ustawiamy wynik na 1
		
		jr $t8	#wychodzimy z funkji
		
#powrot z funkcji wewnetrznych
checkReturn:
	jr $ra	
	
#======================================
#		print_map
#======================================
# w $a0 adres planszy do wydrukowania
print_map:
	move $t0, $a0	#przerzucamy adres z $a0 do $t1
	li $t1, 0, #ustawiamy "iterator"
	li $t2, 0, #ustawiamy drugi "iterator"
print_loop:
	bne $t2, 0, cont	#jesli nie mamy pierwszej litery w rzedzie, lecimy dalej
	#w przeciwnym wypadku dodajemy tabulacje
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, TAB	#ladujemy do $a0 adres stringa
	syscall
cont:
	addi $t1, $t1, 1	#zwiekszamy oba iteratory
	addi $t2, $t2, 1
	
	li $v0, 11	#ladujemy do $v0 kod 11 - print_character
	lb $a0, ($t0)	#ladujemy do $a0 char
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, SPACE	#ladujemy do $a0 adres stringa
	syscall	
	
	addi $t0, $t0, 1	#zwiekszamy adres $t0
	
	beq $t1, 9, end_print	#jesli $t1==9, wychodzimy z petli
	# w przeciwnym wypadku sprawdzamy $t2
	blt $t2, 3, print_loop	#jesli $t2<3, printujemy dalej
	#w przeciwnym wypadku printujemy nowa linie i zerujemy "iterator"
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, NEWLINE	#ladujemy do $a0 adres stringa
	syscall
	
	li $t2, 0	#zerujemy "iterator
	
	j print_loop	#wracamy do petli
	
end_print:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, NEWLINE	#ladujemy do $a0 adres stringa
	syscall
	
	jr $ra	

#======================================
#		default_map
#======================================
# w $a0 adres planszy/mapy do wyczyszczenia
default_map:
	move $t1, $a0	#przerzucamy adres z $a0 do $t1
	li $t2, '1'
	sb $t2, ($t1)		#dodajemy do tablicy po kolei cyfry
	addi $t1, $t1, 1	#i zwiekszamy adres
	li $t2, '2'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '3'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '4'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '5'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '6'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '7'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '8'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	li $t2, '9'
	sb $t2, ($t1)		
	addi $t1, $t1, 1	
	
	jr $ra
	