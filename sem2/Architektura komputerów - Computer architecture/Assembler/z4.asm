#		MIPS_Zadanie_4
# Program obliczajacy e^x, gdzie x jest
# liczba zmiennoprzecinkowa podana przez uzytkownika
# wartosc jest suma: x+(x^2)/2 +... dopoki
# kolejny wyraz ciagu nie jest "bardzo mala wartoscia"
# W zadaniu nalezy wykorzystac stos
#		Kamil Ciaglo 260413

#	float - pojedyncza precyzja (4 bajty)
#	double - podwojna precyzja (8 bajtow)

.data
inf1:	.asciiz "Program oblicza przyblizona wartosc wyrazenia e^x\n"

zap1:	.asciiz "Podaj x (liczba zmiennoprzecinkowa podwojnej precyzji z przedzialu [-707 ; 707]):\n" 

wyn1:	.asciiz "Wartosc wyrazenia e^("
wyn2:	.asciiz ") wynosi:\n"

threshold:	.double 0.00001		#dokladnosc - jesli nastepny wyraz ciagu bedzie mniejszy, niz dokladnosc to nie
					#bedzie juz brany pod uwage (threshold = "bardzo mala wartosc")
one:	.double 1	#jedynka - pozniej potrzebna
zero:	.double 0	#zero - pozniej potrzebne

.text
main:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, inf1	#ladujemy adres inf1
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, zap1	#ladujemy adres zap1
	syscall
	
	li $v0, 7	#ladujemy do $v0 kod 7 - read_double - odczytana wartosc wyladuje w $f0
	syscall
	
	mov.d $f20, $f0	#przerzucamy wartosc x z $f0 do $f20

#		!!!	
				#UWAGA
	subi $sp, $sp, 4	#zmniejszamy $sp o 4, bo liczby podwojnej precyzji maja swoje konkretne miejsca
				#na stosie i jak nie bedzie dobrze ustawiony $sp to program sie po prostu wywali
	
#		!!!
	
	move $s1, $sp	#zapisujemy stackPointer w $s1 (dopoki stos jest pusty)
	ldc1 $f22, threshold	#zapisujemy wartosc threshold w rejestrze $f22
	ldc1 $f24, one	#zapisujemy jedynke w dwoch rejestrach - w $f24 jako "liczcnik" i w $f26 do inkrementacji
	ldc1 $f26, one
	
	ldc1 $f28, zero	#ladujemy do $f28 wartosc 0
	sub.d $f30, $f28, $f22	#odejmujemy threshold od 0, zeby uzyskac wartosc -threshold i zapisujemy w $f30
	
#przed petla wrzucamy pierwszy wyraz ciagu (1) na stos
	sdc1 $f26, ($sp) 
#zostawiamy stack pointer na tym wyrazie !

#=====================================================================================================
#	petla zlicza kolejne wyrazy ciagu i dodaje je na stos dopoki nie przekrocza threshold
#=====================================================================================================
counting_loop:
	ldc1 $f4, ($sp)	#sciagamy ze stosu poprzedni wyraz
	mul.d $f4, $f4, $f20	#mnozymy go razy x
	div.d $f4, $f4, $f24	#dzielimy wyraz przez "licznik"
	add.d $f24, $f24, $f26	#inkrementujemy "licznik"
	
	c.lt.d $f4, $f28	#sprawdzamy, czy wyraz jest ujemny
	bc1t negative	#jesli tak, to skaczemy do etykiety negative, w przeciwnym wypadku
			#sprawdzamy "standardowy warunek"
	
	c.lt.d $f4, $f22	#sprawdzamy, czy otrzymany wyraz jest juz mniejszy niz threshold
	bc1t adding	#jesli tak, to wychodzimy z petli do etykiety "adding"
	bc1f counting_loop_p2	#jesli nie, to przechodzimy do drugiej czesci petli (p2 = part 2)
negative:
	c.lt.d $f30, $f4	#sprawdzamy, czy -threshold jest mniejszy od wyrazu
	bc1t adding	#jesli tak - wychodzimy z petli do etykiety "adding", jesli nie, lecimy dalej
	
counting_loop_p2:
	subi $sp, $sp, 8	#odejmujemy 8 od stackPointera - bo pracujemy na liczbach podwojnej precyzji
	sdc1 $f4, ($sp)	#umieszczamy wyraz na stosie
	
	j counting_loop	#robimy kolejna petle	

#==========================================
#		koniec petli
#		counting_loop
#==========================================
								
#na wyjsciu z poprzedniej petli stackPointer wskazuje wyraz na szczycie stosu				
adding:
	ldc1 $f4, zero	#ladujemy do $f4 wartosc 0 zanim zaczniemy dodawanie - tu bedziemy trzymac sume wyrazow
	
#petla dodaje do siebie wszystkie wyrazy ze stosu
adding_loop:
	ldc1 $f6, ($sp)	#sciagamy ze stosu wyraz
	add.d $f4, $f4, $f6	#dodajemy go do $f4 - naszej sumy
	
	beq $sp, $s1 printResult	#jesli $sp jest rowny poczatkowej wartosci (doszlismy do dna stosu
					#to wychodzimy z petli
	
	addi $sp, $sp, 8	#zwiekszamy stackPointer
	j adding_loop	#robimy kolejna petle
	
printResult:
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, wyn1	#ladujemy adres wyn1
	syscall

	li $v0, 3	#ladujemy do $v0 kod 3 - print_double
	mov.d $f12, $f20	#przerzucamy x do $f12, zeby go wydrukowac
	syscall
	
	li $v0, 4	#ladujemy do $v0 kod 4 - print_string
	la $a0, wyn2	#ladujemy adres wyn2
	syscall
	
	li $v0, 3	#ladujemy do $v0 kod 3 - print_double
	mov.d $f12, $f4	#przerzucamy sume do $f12, zeby ja wydrukowac
	syscall
	
end:
	li $v0, 10	#ladujemy do $v0 kod 10 - zakoncz prace programu
	syscall
