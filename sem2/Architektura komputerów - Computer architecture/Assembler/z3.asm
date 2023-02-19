#		MIPS_Zadanie_3
# Program porownujacy dwa podane przez uzytkownika teksty
# zaczynajac od ostatnich znakow. Na stos odkladamy ' ' (spacje)
# jesli znaki sa rowne, lub '*' jesli znaki sa rozne.
# Na koniec program przerzuca wszystkie znaki ze stosu do bufora,
# dodaje na koniec 0 i drukuje utworzony string wraz z liczba
# jednakowych znakow i "odpowiednimi komentarrzami"
#		Kamil Ciaglo 260413

#	32 - kod ' ' (spacji) w ASCII
#	42 - kod '*' w ASCII

# Legenda co gdzie trzymam
# $s0 - rozmiar tekst1
# $s1 - rozmiar tekst2
# $s2 - ilosc jednakowych znakow
# $s7 - poczatkowy adres stosu
# 
# 

.data
tekst1: .space 51	#rezerwujemy miejsce na pierwszy tekst
tekst2: .space 51	#rezerwujemy miejsce na drugi tekst
output: .space 51	#rezerwujemy miejsce na ciag wyjsciowy

newline: .asciiz "\n"

info1: .ascii "Program porowna dwa podane przez uzytkownika teksty (o ile sa rownej dlugosci)"
info1_1: .asciiz "maksymalna dlugosc tekstow to 50 znakow.\n"

zap1: .asciiz "\nPodaj pierwszy tekst:\n"
zap2: .asciiz "\nPodaj drugi tekst:\n"

info2: .ascii "\n'*' oznacza, ze teksty w tym miejscu sie roznia, a ' ' (spacja), ze sa identyczne.\n"
info2_1: .asciiz "Oto porownanie tekstow:\n"

info3: .asciiz "\nIlosc jednakowych znakow: "

error1: .asciiz "\nPodano teksty o roznych rozmiarach, wracam do wyboru drugiego tekstu\n"


.text
main:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, info1	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
#=================================
#	pobieranie tekstow
#=================================
Podaj1:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, zap1	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
	
	li $v0, 8	#kod 8 - "read_string"
	la $a0, tekst1	#do a0 ladujemy adres w ktorym zapiszemy stringa
	li $a1, 51	#do a1 ladujemy jego rozmiar +1 na nulla
	syscall
	
	jal checkSize	#wywolujemy funkcje checkSize, ktora w $v0 zwroci rozmiar stringa
	
	move $s0, $v0	#przenosimy rozmiar stringa 1 do $s0
	
Podaj2:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, zap2	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
	
	li $v0, 8	#kod 8 - "read_string"
	la $a0, tekst2	#do a0 ladujemy adres w ktorym zapiszemy stringa
	li $a1, 51	#do a1 ladujemy jego rozmiar +1 na nulla
	syscall
	
	jal checkSize	#wywolujemy funkcje checkSize, ktora w $v0 zwroci rozmiar stringa
	
	move $s1, $v0	#przenosimy rozmiar stringa 2 do $s1
Sprawdz_rozmiary:
	bne $s0, $s1 Error	#jesli rozmiary sa rozne, to przeskakujemy do etykiety Error
Szyfruj:	
	#w przeciwnym wypadku ustawiamy odpowiednio rejestry i wywolujemy funkcje Cypher
	la $a0, tekst1
	la $a1, tekst2
	move $a2, $s0 #jesli tu jestesmy, to rozmiary obu ciagow sa takie same wiec rownie dobrze mozemy podac $s1
	
	jal Cypher
	
	move $s2, $v0	#ustawiamy wartosc zwrocona przez funkcje na poprawne miejsce
Przenies_ze_stosu:
	
	la $a0, output	#w $a0 ustawiamy adres outputu
	jal MoveFromStack	#wywolujemy funkcje przerzucajaca ze stosu wszystkie znaki
	
	j PrintResult	#przechodzimy do pokazywania wynikow
#==============================
#	rozne rozmiary
#==============================
Error:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, error1	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
	
	j Podaj2	#zgodnie z komunikatem wracamy do podawania znaku
#=========================
#	konczenie
#=========================
PrintResult:
	li $v0, 4	#kod 4 - "print_string"
	la $a0, info2	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, newline	#do $a0 ladujemy adres nowej linii zeby ladnie wygladalo
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, tekst1	#do $a0 ladujemy adres tekstu1
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, newline	#do $a0 ladujemy adres "nowej linii"
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, tekst2	#do $a0 ladujemy adres tekstu2
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, newline	#do $a0 ladujemy adres "nowej linii"
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, output	#do $a0 ladujemy adres outputu
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, newline	#do $a0 ladujemy adres nowej linii zeby ladnie wygladalo
	syscall
	
	li $v0, 4	#kod 4 - "print_string"
	la $a0, info3	#do $a0 ladujemy adres stringa ktory chcemy wyprintowac
	syscall
	
	li $v0, 1	#kod 1 - "print_int"
	move $a0, $s2 	#do $a0 ladujemy inta ktorego chcemy wyprintowac
	syscall
	
end:	
	li $v0, 10
	syscall



#				FUNKCJE


#===================================
#	funkcja MoveFromStack
#===================================
# w $a0 mamy adres outputu, ta funkcja nic nie zwraca
MoveFromStack:
	move $t0, $a0	#przerzucamy adres outputu do $t0
		
Move_loop:
	lb $t1, ($sp)	#zabieramy ze stosu bajt i zapisujemy go w $t1
	sb $t1, ($t0)	#zapisujemy ten bajt w adresie z $t0
	
	addi $sp, $sp, 1	#zwiekszamy o 1 adres stosu
	addi $t0, $t0, 1	#i adres outputu
	bgt $sp, $s7, Move_end	#jesli przekroczymy po zwiekszeniu poczatkowy adres stosu, mozemy wychodzic z petli
	j Move_loop	#jesli nie, to iterujemy po petli dalej
	
Move_end:
	subi $sp, $sp, 1	#odejmujemy 1 od adresu stosu, bo jestesmy "za wysoko"
	sb $zero, ($t0)	#uzupelniamy string o "nulla"
	jr $ra	#wracamy do miejsca wywolania funkcji
	
#==============================
#	funkcja Cypher
#==============================
# w $a0 i $a1 ma stringi do porownania, w $a2 ich rozmiar, a w $v0 zwracamy ilosc takich samych znakow
Cypher:	
	move $s7, $sp	#zapisuje sobie adres poczatku stosu
	move $t0, $a2	#w t0 ustawiam sobie "iterator" ktory pomoze mi porownywac teksty
	li $t1, 0	#w t1 bede zliczal jednakowe znaki
Cypher_loop:
	subi $t0, $t0, 1	#odejmuje 1 od "iteratora", bo w pierwszej petli pokazuje on rozmiar,
				#ale po dodaniu do adresu wskaze enter albo nulla
				#(w zaleznosci na czym skonczylismy sprawdzanie tekstu)	
	
	la $t2, ($a0)	#ladujemy adres stringa 1
	la $t3, ($a1)	#ladujemy adres stringa 2
	add $t2, $t2, $t0	#zwiekszmy oba adresy o "iterator"
	add $t3, $t3, $t0
	lb $t4, ($t2)	#ladujemy bajty z adresow do konkretnych rejestrow
	lb $t5, ($t3)
	
	beq $t4, $t5, spacja	#jesli bajty sa rowne to skaczemy do spacji, jesli nie...
	li $t9, 42
	sb $t9, ($sp)	#...odkladamy na stos gwiazdke (42 - kod '*' w ASCII)
	
	j dalej	#przeskakujemy do etykiety dalej (za dodawanie spacji)
spacja:
	li $t9, 32
	sb $t9, ($sp)	#odkladamy na stos spacje (32 - kod ' ' w ASCII)
	addi $t1, $t1, 1	#zwiekszamy licznik jednakowych znakow
dalej:
	beqz $t0, Cypher_end	#jesli bralismy znaki "z indexu 0", to mozemy zakonczyc petle i dzialanie funkcji
	#jesli nie, to szykujemy sie na kolejna iteracje petli
	subi $sp, $sp, 1	#zmniejszamy adres stosu o 1 (znaki zajmuja 1 bajt, a nie jak slowa w materialach - 4)
	j Cypher_loop
	
Cypher_end:
	move $v0, $t1	#ustawiamy nasza wartosc, ktora ma zwrocic funkcja	
	jr $ra	#wracamy do miejsca wywolania funkcji
#=================================
#	funkcja checkSize
#=================================
# w $a0 mamy string do sprawdzenia, w $v0 zwracamy jego rozmiar (ilosc znakow bez nulla/entera)
checkSize:
	move $t0, $a0	#przenosimy adres do $t0
	li $t2, 0	#ustawiamy $t2 na 0 (na wszelki wypadek)
checkSize_loop:
	lb $t1, ($t0)	#ladujemy do $t1 bajt z adresu $t0
	
	beq $t1, 10, checkSize_end	#jesli dojdziemy do entera
	beq $t1, 0, checkSize_end	#lub 0 to przechodzimy do konca funkcji
	#w przeciwnym wypadku zwiekszamy $t2 - licznik
	addi $t2, $t2, 1
	#oraz $t0 - adres
	addi $t0, $t0, 1
	
	j checkSize_loop
	
checkSize_end:
	move $v0, $t2	#ustawiamy wynik tam gdzie ma byc
	jr $ra	#wracamy do miejsca wywolania funkcji
