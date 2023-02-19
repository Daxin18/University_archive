#		MIPS_Zadanie_1
# Program obliczajacy a(wynik) na podstawie danych b,c,d podanych przez uzytkownika
# a obliczane jest na podstawie jednego (wybranego) z trzech rownan
# uzytkownik moze wybrac, czy po obliczeniu wyniku chce kontynuowac
#	Kamil Ciaglo 260413

.data
#na poczatek szykuje stringi, ktore beda mi potrzebne w programie
#
#	\n to znak nowej linii
#
#	.ascii rozni sie od .asciiz tym, ze trzeba w nim manualnie wpisac nulla (\0),
#	latwiej jest dzieki temu np napisac printowanie stringa jak nizej (str1 i row1-3),
#	co dokladniej opisuje w kodzie nizej, przy wywolywaniu syscalla dla nich
#

newline: .asciiz "\n"					#do ladniejszego wyswietlania

str1: .ascii "Wybierz, co program powinien obliczyc:\n"	#string 1 - pierwsza opcja wyboru
row1: .ascii "1. a=b*(c-d)\n" 				#rownanie 1
row2: .ascii "2. a=(b+c)-d\n"				#rownanie 2
row3: .ascii "3. a=b/c*d\n\0"				#rownanie 3

kom1: .asciiz "Wybrano rownanie 1: a=b*(c-d\n"		#komunikat 1
kom2: .asciiz "Wybrano rownanie 2: a=(b+c)-d\n"		#komunikat 2
kom3: .asciiz "Wybrano rownanie 3: a=b/c*d\n"		#komunikat 3

zap1: .asciiz "podaj b:\n"				#zapytanie 1 (o podanie b)
zap2: .asciiz "podaj c:\n"				#zapytanie 2 (o podanie c)
zap3: .asciiz "podaj d:\n"				#zapytanie 3 (o podanie d)

str2: .ascii "Kontynuowac od poczatku?\n"		#string 2 - druga opcja wyboru
opt1: .ascii "0. nie\n"					#opcja 1
opt2: .ascii "1. tak\n\0"				#opcja 2

zlaw: .asciiz "Podana wartosc jest zla\n"		#na wszelki wypadek
test: .asciiz "to tylko test\n"

wyniki: .asciiz "Wynik rownania to: "			#wyswietlane przy wyniku

.text
# etykieta main - poczatek kodu, do ktorego wracamy w kilku przypadkach
# (przy wpisaniu blednych danych, lub gdy chcemy kontynuowac)
main:
#--------------------------------------
#	printujemy pierwsze stringi
#--------------------------------------
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, str1	#ladujemy string 1 do a0, zeby go wyswietlic	
syscall	

#	!!! instrukcje zakomentowane ponizej potrzebne by byly gdybym korzystal z .asciiz !!!
#		(o czym wspominam w komentarzach wyzej przy deklaracji danych w pamieci)
#	pod warunkiem, ze nie zapisalbym stringow w jednym nieczytelnym wierszu jeden po drugim np:
#	str1: .asciiz "Wybierz rownanie:\n1. a=b*(c-d)\n2. ...."

#li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
#la $a0, row1	#ladujemy rownanie 1 do a0, zeby je wyswietlic	
#syscall	
#li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
#la $a0, row2	#ladujemy rownanie 2 do a0, zeby je wyswietlic	
#syscall
#li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
#la $a0, row3	#ladujemy rownanie 3 do a0, zeby je wyswietlic	
#syscall	
#----------------------------
#	wybor rownania
#----------------------------
li $v0,5	#kod 5 w rejestrze $v0 - "read_int" - odczytuje liczbe calkowita "z klawiatury"
syscall
move $t0, $v0	#zapisujemy odczytana wartosc w rejestrze t0
#---------------------------------------
#	"switch" dla wyboru rownania
#---------------------------------------
#Legenda znaczenia uzytych etykiet:
#CRX - "czy to rownanie X", kod sprawdzajacy czy podano liczbe odpowiadajaca rownaniu X
#zle - podano zle dane, wracamy na poczatek
#POD - "podawanie danych" - kod czytajacy wartosci b,c,d "z klawiatury"
CR1:
bne $t0, 1, CR2	#sprawdzamy, czy wartosc jest rowna 1(jesli nie, to przechodzimy dalej do etykiety CR2)
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, kom1	#ladujemy komunikat 1, aby go wyswietlic
syscall
b	POD	#przechodzimy do podawania danych

CR2:
bne $t0, 2, CR3	#sprawdzamy, czy wartosc jest rowna 2(jesli nie, to przechodzimy dalej do etykiety CR3)
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, kom2	#ladujemy komunikat 2, aby go wyswietlic
syscall
b	POD	#przechodzimy do podawania danych

CR3:
bne $t0, 3, zle	#sprawdzamy, czy wartosc jest rowna 3(jesli nie, to przechodzimy dalej do etykiety zle)
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, kom3	#ladujemy komunikat 3, aby go wyswietlic
syscall
b	POD	#przechodzimy do podawania danych

zle:
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, zlaw	#ladujemy zlaw (string informujacy o podaniu blednych danych) do $a0, zeby go wyswietlic
syscall
b	Kontynuuj	#po zlych danych przeskakujemy do zapytania czy kontynuowac
#-------------------------------------------------
#		  podawanie danych
#	(na koncu "switch" przerzucajacy nas
#	  do odpowiedniego miejsca w kodzie,
#	   aby dobrze policzyc wszystko)
#-------------------------------------------------
#Legenda znaczenia uzytych etykiet:
#POD - "podawanie danych" - kod czytajacy wartosci b,c,d "z klawiatury"
#ZCRX - "znowu sprawdzam czy to rownanie X" - kod sprawdzajacy czy mamy do czynienia z konkretnym rownaniem
#RX - "rownanie X" - kod obliczajacy dane rownanie zgodnie z jego wzorem
#Kontynuuj - kod pytajacy czy chcemy kontynuowac
POD:
li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, zap1	#ladujemy zapytanie 1 (o wartosc b), aby je wyswietlic
syscall
li $v0, 5	#kod 5 w rejestrze $v0 - "read_int" - odczytuje liczbe calkowita "z klawiatury"
syscall
move $t1, $v0	#zapisujemy odczytana wartosc B w rejestrze $t1

li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, zap2	#ladujemy zapytanie 2 (o wartosc c), aby je wyswietlic
syscall
li $v0, 5	#kod 5 w rejestrze $v0 - "read_int" - odczytuje liczbe calkowita "z klawiatury"
syscall
move $t2, $v0	#zapisujemy odczytana wartosc C w rejestrze $t2

li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, zap3	#ladujemy zapytanie 3 (o wartosc d), aby je wyswietlic
syscall
li $v0, 5	#kod 5 w rejestrze $v0 - "read_int" - odczytuje liczbe calkowita "z klawiatury"
syscall
move $t3, $v0	#zapisujemy odczytana wartosc D w rejestrze $t3

ZCR1:
bne $t0, 1, ZCR2	#sprawdzamy, czy wartosc w $t0 jest rowna 1(jesli nie, to przechodzimy dalej do etykiety ZCR2)
b	R1	#jesli wartosc w $t0 =1 to przechodzimy do kodu na rownanie 1
ZCR2:
bne $t0, 2, ZCR3	#sprawdzamy, czy wartosc w $t0 jest rowna 1(jesli nie, to przechodzimy dalej do etykiety ZCR3)
b	R2	#jesli wartosc w $t0 =2 to przechodzimy do kodu na rownanie 2
ZCR3:
b	R3	#do etykiety POD: dostaniemy sie tylko jesli wartosc w $t0 nalezy do zbioru {1,2,3}
		#zatem mozemy bez sprawdzania przeskoczyc do kodu na rownanie 3

#-------------------------------
#	kody rownan
#-------------------------------
#nieformalny zapis ktora wartosc jest w ktorym rejestrze
# b=$t1		c=$t2		d=$t3
#R1: a=b*(c-d)
#R2: a=(b+c)-d
#R3: a=b/c*d
R1:
sub $t2, $t2, $t3	#odejmujemy $t3(d) od $t2(c) i zapisujemy wynik w $t2 (wartosc c nie bedzie juz nam potrzebna,
			#wiec mozemy "nadpisac" jej rejestr)
mul $t1, $t1, $t2	#mnozymy $t1(b) razy $t2(c-d) i zapisujemy wynik w $t1 (bo b nie bedzie potem potrzebne)
move $t0, $t1		#zapisujemy wynik w $t0 (aby ujednolicic wszystkie rownania)
j	Wynik		#przeskakujemy do etykiety, w ktorej wyswietlamy wynik
R2:
add $t1, $t1, $t2	#dodajemy $t1(b) i $t2(c) i zapisujemy wynik w $t1 (wartosc b nie bedzie juz nam potrzebna,
			#wiec mozemy "nadpisac" jej rejestr)
sub $t1, $t1, $t3	#odejmujemy $t3(d) od $t1(b+c) i zapisujemy wynik w $t1
move $t0, $t1		#zapisujemy wynik w $t0 (aby ujednolicic wszystkie rownania)
j	Wynik		#przeskakujemy do etykiety, w ktorej wyswietlamy wynik
R3:
div $t1, $t1, $t2	#dzielimy $t1(b) przez $t2(c) i wynik zapisujemy w $t1 (wartosc b nie bedzie juz nam potrzebna,
			#wiec mozemy "nadpisac" jej rejestr)
mul $t1, $t1, $t3	#mnozymy $t1(b/c) razy $t3(d) i zapisujemy wynik w $t1
move $t0, $t1		#zapisujemy wynik w $t0 (aby ujednolicic wszystkie rownania)
j	Wynik		#przeskakujemy do etykiety, w ktorej wyswietlamy wynik

#-------------------
#	wynik 
#-------------------
Wynik:
li $v0, 4		#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, wyniki		#ladujemy string wyniki do $a0 zeby go wyswietlic
syscall

li $v0, 1		#ladujemy do rejestru v0 kod 3 - "print_int", gdzie w $a0 znajduje sie wartosc inta do wyswietlenia
move $a0, $t0		#przerzucamy wynik z $t0 do $a0, zeby go wyswietlic
syscall

li $v0, 4		#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, newline		#ladujemy string newline do $a0 zeby go wyswietlic, bedzie ladniej wygladac...
syscall

#-------------------------
#	kontynuowanie 
#-------------------------
#Legenda znaczenia uzytych etykiet:
# CX - "czy wybrano wartosc X", kod sprawdzajacy czy wybrano wartosc X
#zle - podano zle dane, wracamy na poczatek (etykieta znajduje sie wyzej, w sekcji ["switch" dla wyboru rownania])
#Kontynuuj - kod pytajacy czy chcemy kontynuowac
Kontynuuj:
li $v0, 4		#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
la $a0, str2		#ladujemy string 2 do $a0 zeby go wyswietlic (string pytajacy czy chcemy kontynuowac)
syscall

li $v0, 5		#kod 5 w rejestrze $v0 - "read_int" - odczytuje liczbe calkowita "z klawiatury"
syscall
move $t0, $v0 		#zapisujemy odczytana wartosc w rejestrze t0

C1:
bne $t0, 1, C0		#sprawdzamy, czy podana wartosc (przeniesiona do rejestru $t0)=1, jesli nie to przeskakujemy do C0
b	main		#jesli tak, to wykonujemy skok do maina, bo 1=TAK, chce kontynuowac
C0:
bne $t0, 0, zle		#sprawdzamy, czy podana wartosc (przeniesiona do rejestru $t0)=0, jesli nie to przeskakujemy do zle
li $v0, 10		#ladujemy do rejestru v0 kod 10 - "exit", konczacy program przy nastepnym syscallu
syscall
