import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ZbiorDrzew zbiorDrzew = new ZbiorDrzew();
        zbiorDrzew.utworzDrzewa("ala.txt");
        zbiorDrzew.realizujHuffmana();
        System.out.println(zbiorDrzew.odkodowanie("1011110000100001111110110", zbiorDrzew.getRoot()));
       // zbiorDrzew.showSymbolsFrequencyAndCode();
    }
}
