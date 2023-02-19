import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OdczytZPliku {

    private ArrayList<Character> arrayList;
    public int [] odczyt(String nazwa){
        int Aa=0,Bb=0,Cc=0,Dd=0,Ee=0,Ff=0,Gg=0,Hh=0,Ii=0,Jj=0,Kk=0,Ll=0,Mm=0,Nn=0,Oo=0,Pp=0,Qq=0,Rr=0,Ss=0,Tt=0,Uu=0,Vv=0,Ww=0,Xx=0,Yy=0,Zz=0,spacja=0, kropka = 0, przecinek =0;
        arrayList = new ArrayList<Character>();
        String linia;
        char [] litery;
        try(Scanner scanner = new Scanner(new File(nazwa))) {
            while(scanner.hasNextLine()) {
                linia = scanner.nextLine();
                litery = linia.toCharArray();

                for (int j = 0; j < litery.length; j++){
                    if(litery[j] == 'A' || litery[j] =='a'){
                        Aa++;
                        arrayList.add(litery[j]);

                    }
                    if(litery[j] == 'B' || litery[j] =='b'){
                        Bb++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'C' || litery[j] =='c'){
                        Cc++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'D' || litery[j] =='d'){
                        Dd++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'E' || litery[j] =='e'){
                        Ee++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'F' || litery[j] =='f'){
                        Ff++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'G' || litery[j] =='g'){
                        Gg++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'H' || litery[j] =='h'){
                        Hh++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'I' || litery[j] =='i'){
                        Ii++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'J' || litery[j] =='j'){
                        Jj++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'K' || litery[j] =='k'){
                        Kk++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'L' || litery[j] =='l'){
                        Ll++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'M' || litery[j] =='m'){
                        Mm++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'N' || litery[j] =='n'){
                        Nn++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'O' || litery[j] =='o'){
                        Oo++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'P' || litery[j] =='p'){
                        Pp++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'Q' || litery[j] =='q'){
                        Qq++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'R' || litery[j] =='r'){
                        Rr++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'S' || litery[j] =='s'){
                        Ss++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'T' || litery[j] =='t'){
                        Tt++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'U' || litery[j] =='u'){
                        Uu++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'V' || litery[j] =='v'){
                        Vv++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'W' || litery[j] =='w'){
                        Ww++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'X' || litery[j] =='x'){
                        Xx++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'Y' || litery[j] =='y'){
                        Yy++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == 'Z' || litery[j] =='z'){
                        Zz++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == ' '){
                        spacja++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == '.'){
                        kropka++;
                        arrayList.add(litery[j]);
                    }
                    if(litery[j] == ','){
                        przecinek++;
                        arrayList.add(litery[j]);
                    }


                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int [] tablica = {Aa,Bb,Cc,Dd,Ee,Ff,Gg,Hh,Ii,Jj,Kk,Ll,Mm,Nn,Oo,Pp,Qq,Rr,Ss,Tt,Uu,Vv,Ww,Xx,Yy,Zz,spacja,kropka,przecinek};
        return tablica;
    }

    public ArrayList<Character> getArrayList() {
        return arrayList;
    }
}
