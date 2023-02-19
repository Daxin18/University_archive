#		MIPS_Zadanie_2
# Program szyfrujacy/deszyfrujacy wiadomosc napisana tekstem
# jawnym w jezyku polskim (bez polskich znakow).
# Program szyfruje/deszyfruje w oparciu o kod Cezara przy podanym
# przez uzytkownika kluczu.
#		Kamil Ciaglo 260413

# Rozpiska co gdzie trzymam, bo juz sie gubie...
# $s0 - wybor operacji (S/D)
# $s1 - klucz
# $s2 - input
# $s3 - normal
# $s4 - output
#
.data
#na poczatek szykuje stringi, ktore beda mi potrzebne w programie
#
#	rezerwuje o bajt wiecej niz trzeba, bo kazdy string
#	konczy sie nullem, na ktory potrzebujemy dodatkowego bajta
#	latwo mozna to sprawdzic rezerwujac tylko 1 bajt na wybor,
#	niezaleznie od tego co wpiszemy, mips przyjmie tam nulla 
#
input: .space 51	#rezerwujemy miejsce na tekst podany przez uzytkownika
normal:	.space 51	#rezerwujemy miejsce na tekst do normalizacji
output:	.space 51	#rezerwujemy miejsce na tekst do wyswietlenia (zaszyfrowany/deszyfrowany)
choice: .space 2	#rezerwujemy miejsce na string wyboru

zap1: .asciiz "Chcesz szyfrowac(S), czy deszyfrowac(D)?\nPodaj wybor:\n"	#zapytanie 1 - jaka operacje wykonac

wyb1: .asciiz "\nWybrano szyfrowanie!\n"		#informacja potwierdzajaca wybor z zapytania 1
wyb2: .asciiz "\nWybrano deszyfrowanie!\n"	#informacja potwierdzajaca wybor z zapytania 1

zap2: .asciiz "Podaj klucz z przedzialu 0-25:\n"	#zapytanie 2 - o podanie klucza

#zapytanie 3_1 - o tekst do szyfrowania
zap3_1: .asciiz "Podaj tekst jawny do zaszyfrowania podanym wyzej kluczem (max dlugosc 50 znakow, tylko duze litery):\n" 
#zapytanie 3_2 - o kryptogram do deszyfrowania
zap3_2: .asciiz "Podaj kryptogram do deszyfrowania podanym wyzej kluczem (max dlugosc 50 znakow, tylko duze litery):\n"

kom1_1: .asciiz "\nZaszyfrowany tekst to:\n"	#komunikat po szyfrowaniu
kom1_2: .asciiz "\nOdszyfrowany tekst to:\n"	#komunikat po deszyfrowaniu

error: .asciiz "\nPodano niepoprawne dane, wracam do poczatku\n"	#informacja o blednych danych
keyError: .asciiz "Podano niepoprawne dane, wracam do podawania klucza\n"	#informacja o blednym kluczu

.text
main:
#-------------------------------------------
#	zapytanie o operacje i wybor
#-------------------------------------------
#Legenda uzytych etykiet:
#ZS - czy wybrano S
#ZD - czy wybrano D
#Error - podano niepoprawne dane, komunikat + powrot do poczatku
#Key - etykieta wyboru klucza

	li $v0, 4	#ladujemy do rejestru v0 kod 4 - "print_string", gdzie w $a0 znajduje sie adres stringa w pamieci
	la $a0, zap1	#ladujemy zapytanie 1 do $a0, zaby je wyswietlic
	syscall

	li $v0, 8	#ladujemy do rejestru v0 kod 8 - "read_string", gdzie w $a0 znajduje sie adres stringa w
			#pamieci a w $a1 dlugosc bufora na ciag
	la $a0, choice	#dwie operacje nizej umieszczaja w rejestrach odpowiedni adres(w $a0) i wartosc(w $a1)
	li $a1, 2
	syscall

	la $t0, choice	#ladujemy adres wyboru do rejestru $t0
	lb $s0, ($t0)	#ladujemy bajt(char) z wyboru do rejestru $s0
	#lb - load byte

ZS:
	bne $s0, 83, ZD	#jesli wartosc $s0 jest rozna od 83 (S w kodzie ASCII) to przeskakujemy do etykiety ZD

	li $v0, 4	#jesli sa rowne to printujemy komunikat o tym, ze wybrano szyfrowanie
	la $a0, wyb1
	syscall

	b Key	#po printowaniu komunikatu przeskakujemy do wyboru klucza

ZD:
	bne $s0, 68, Error	#jesli wartosc $s0 jest rozna od 68 (D w kodzie ASCII) to przeskakujemy do etykiety Error

	li $v0, 4	#w tym przypadku printujemy komunikat o tym, ze wybrano deszyfrowanie
	la $a0, wyb2
	syscall

	b Key	#po printowaniu komunikatu przeskakujemy do wyboru klucza

Error:
	li $v0, 4	#ladujemy do $v0 kod 4 - "print_string"
	la $a0, error	#ladujemy do $a0 adres stringa error do wydrukowania
	syscall
	b main		#wracamy do maina (na poczatek)

#-------------------------------------------------
#	zapytanie o podanie klucza
#	i przeskok do odpowiednich fragmentow
#	szyfrowania/deszyfrowania
#-------------------------------------------------
#Legenda uzytych etykiet:
#Key - etykieta wyboru klucza
#KeyError - podano niepoprawne dane, komunikat + powrot do podawania klucza
#PodajKryptogram - etykieta mowi sama za siebie
#PodajTekst - etykieta mowi sama za siebie
KeyError:
	li $v0, 4	#ladujemy do $v0 kod 4 - "print_string"
	la $a0, keyError	#ladujemy do $a0 adres stringa z keyError
	syscall
#ze wzgledu na polozenie KeyError w kodzie nie musimy wykonywac skoku do etykiety Key
Key:
	li $v0, 4	#ladujemy do $v0 kod 4 - "print_string"
	la $a0, zap2	#ladujemy do $a0 adres stringa z zapytaniem 2 - o podanie klucza
	syscall

	li $v0, 5	#ladujemy do $v0 kod 5 - "read_int"
	syscall
	move $s1, $v0 	#zapisujemy odczytana wartosc w $s1

	li $t0, 25	#ladujemy do rejestru $t1 wartosc 25 - max wartosc klucza

	blt $s1, $zero, KeyError	#jesli wartosc klucza jest mniejsza niz 0 - skaczemy do keyError
	bgt $s1, $t0, KeyError	#jesli wartosc klucza jest wieksza niz 25 - skaczemy do keyError
	#bgt - branch if greater than
	#blt - branch if less than

#znowu sprawdzamy, jaki byl nasz wybor wczesniej, aby przejsc do dobrej petli
#jesli dotarlismy tutaj, to $t7=83('S') lub $t7=68('D')
	bne $s0, 83, PodajKryptogram	#jesli wartosc $t7 jest rozna od 83 (S w kodzie ASCII) to przeskakujemy
					#do etykiety PodajKryptogram
	b PodajTekst	#w przeciwnym wypadku (gdy jest rowna) - do etykiety PodajTekst

#-------------------------------------------
#	podanie tekstu i szyfrowanie
#-------------------------------------------
PodajTekst:
	li $v0, 4	#ladujemy do $v0 kod 4 - "print_string"
	la $a0, zap3_1	#ladujemy do $a0 adres stringa z zapytaniem 3_1 - o podanie tekstu
	syscall

	li $v0, 8	#ladujemy do $v0 kod 8 - "read_string"
	la $a0, input	#ladujemy do $a0 adres stringa input
	li $a1, 51	#i do $a1 jego rozmiar
	syscall

	la $s2, input	#ladujemy do rejestru $s2 adres stringa input - na potem
	la $s3, normal	#ladujemy adres normal do $s3
	addu $t2, $s3, $zero	#adres aktualnego bajta z normal

	li $t3, 0	#$t3 posluzy nam jako "licznik", wiec ustawiamy go na 0

Normalizuj_loop:
#w $t0 trzymam kolejne bajty, a $t3 posluzy mi za "licznik", $s2 to input, $s3 to normalizowana wersja
#w $t1 trzymam adres bajta inputu, w $t2 bajta normalizacji
	addu $t1, $s2, $t3	#adres aktualnego bajta z inputu
	addiu $t3, $t3, 1	#zwiekszamy licznik
	lb $t0, ($t1)	#ladujemy do rejestru $t0 bajt o adresie zawartym w $t1
	beq $t0, 10, Pre_Szyfruj_loop	# 10 - kod entera '\n' (line feed - koniec linii)
	beq $t0, 0, Pre_Szyfruj_loop	#jesli bajt jest rowny 0 (NULL w ASCII), to znaczy ze mamy koniec stringa
					#i przeskakujemy do Szyfruj_loop
	bgt $t0, 90, Normalizuj_loop	#jesli bajt jest wiekszy niz 90 ('Z' w ASCII), to wracamy na start petli
	blt $t0, 65, Normalizuj_loop	#jesli bajt jest mniejszy niz 65 ('A' w ASCII), to wracamy na start petli
	#w przeciwnym wypadku wykonaja sie ponizsze instrukcje

	sb $t0, ($t2)	#zapisujemy bajt w adresie bajta normalizacji
	addiu $t2, $t2, 1	#zwiekszamy adres o 1

	b Normalizuj_loop	#powtarzamy petle

Pre_Szyfruj_loop:
#w $t0 trzymam kolejne bajty, w $t1 ich adresy, w $s4 adres output, w $t3 "licznik", w $s1 klucz, w $s3 normalizacja
	sb $zero, ($t2)	#dopisujemy nulla pominietego w Normalizuj_loop
	li $t3, 0	#ustawiamy licznik na 0
	la $t2, output	#ustawiamy $t2 na output

Szyfruj_loop:
	addu $t1, $s3, $t3	#adres aktualnego bajta z normalizacji
	addiu $t3, $t3, 1	#zwiekszamy licznik	
	lb $t0, ($t1)	#laduje do rejestru $t0 bajt o adresie zawartym w $t1
	beq $t0, 0, PrintResult		#jesli bajt jest rowny 0 (NULL w ASCII), to znaczy ze mamy koniec stringa
					#i przeskakujemy do Szyfruj_loop
	#w przeciwnym wypadku wykonaja sie ponizsze instrukcje
	addu $t0, $t0, $s1	#zwiekszamy wartosc bajtu o klucz
	bgt $t0, 90, tooBig	#jesli wartosc jest wieksza niz 90('Z' w ASCII) to przeskakujemy do etykiety tooBig
	sb $t0, ($t2)	#zapisujemy bajt w output
	addiu $t2, $t2, 1	#zwiekszamy adres o 1
	b Szyfruj_loop	#i powtarzamy petle

tooBig:
	subu $t0, $t0, 26	#odejmujemy od wartosci ilosc liczb w alfabecie (ustawiajac poprawna wartosc)
	sb $t0, ($t2)	#zapisujemy bajt w output
	addiu $t2, $t2, 1	#zwiekszamy adres o 1
	b Szyfruj_loop	#i powtarzamy petle

#------------------------------------------------
#	podanie kryptogramu i deszyfrowanie
#------------------------------------------------
PodajKryptogram:
	li $v0, 4	#ladujemy do $v0 kod 4 - "print_string"
	la $a0, zap3_2	#ladujemy do $a0 adres stringa z zapytaniem 3_1 - o podanie tekstu
	syscall

	li $v0, 8	#ladujemy do $v0 kod 8 - "read_string"
	la $a0, input	#ladujemy do $a0 adres stringa input
	li $a1, 51	#i do $a1 jego rozmiar
	syscall

	la $s2, input	#ladujemy do rejestru $s2 adres stringa input - na potem
	la $s4, output	#ladujemy adres output do $s4
	addu $t2, $s4, $zero	#adres aktualnego bajta z output

	li $t3, 0	#$t3 posluzy nam jako "licznik", wiec ustawiamy go na 0

Deszyfruj_loop:
	addu $t1, $s2, $t3	#adres aktualnego bajta z input
	addiu $t3, $t3, 1	#zwiekszamy licznik
	lb $t0, ($t1)	#laduje do rejestru $t0 bajt o adresie zawartym w $t1
	beq $t0, 10, PrintResult	# 10 - kod entera '\n' (line feed - koniec linii)
	beq $t0, 0, PrintResult		#jesli bajt jest rowny 0 (NULL w ASCII), to znaczy ze mamy koniec stringa
				#i przeskakujemy do Szyfruj_loop
	bgt $t0, 90, Deszyfruj_loop	#jesli bajt jest wiekszy niz 90 ('Z' w ASCII), to wracamy na start petli
	blt $t0, 65, Deszyfruj_loop	#jesli bajt jest mniejszy niz 65 ('A' w ASCII), to wracamy na start petli
	#w przeciwnym wypadku wykonaja sie ponizsze instrukcje
	subu $t0, $t0, $s1	#zmniejszamy wartosc bajtu o klucz
	blt $t0, 65, tooSmall	#jesli wartosc jest mniejsza niz 65('A' w ASCII) to przeskakujemy do etykiety tooSmall
	sb $t0, ($t2)	#zapisujemy bajt w output
	addiu $t2, $t2, 1	#zwiekszamy adres o 1
	b Deszyfruj_loop	#i powtarzamy petle

tooSmall:
	addu $t0, $t0, 26	#dodajemy do wartosci ilosc liczb w alfabecie (ustawiajac poprawna wartosc)
	sb $t0, ($t2)	#zapisujemy bajt w output
	addiu $t2, $t2, 1	#zwiekszamy adres o 1
	b Deszyfruj_loop	#i powtarzamy petle

#--------------------------------
#	konczenie programu
#-------------------------------
PrintResult:
	sb $zero, ($t2)	#dopisujemy pominietego przy szyfrowaniu/deszyfrowaniu nulla

	bne $s0, 83, Deszyfrowano	#ponownie sprawdzamy czy wybrane wszesniej bylo szyfrowanie, jesli nie,
					#to znaczy ze deszyfrowanie
	li $v0, 4	#kod 4 - "print_string"
	la $a0, kom1_1	#kom1_1 w $a0 do wyprintowania
	syscall

	b Result	#skaczemy do etykiety Result
			
Deszyfrowano:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, kom1_2	#kom1_2 w $a0 do wyprintowania
	syscall
	#nie musimy przeskakiwac ze wzgledu na ulozenie kodu
Result:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, output	#output w $a0 do wyprintowania
	syscall

End:	#konczymy program (kod 10 - "exit")
	li $v0 10
	syscall
