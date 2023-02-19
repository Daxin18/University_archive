import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class ZbiorDrzew {
    PriorityQueue<HuffmanNode> kolejka;
    private HuffmanNode root;
    String tekst = "";
    String tekst2 = "";
    OdczytZPliku odczytZPliku = new OdczytZPliku();
    ArrayList<Character> arrayList = new ArrayList<>();

    public ZbiorDrzew() {
        kolejka = new PriorityQueue<HuffmanNode>(new MyComparator());
    }

    public void utworzDrzewa(String nazwa) {
        int[] litery = odczytZPliku.odczyt(nazwa);
        for (int i = 0; i < litery.length; i++) {
            if (litery[i] > 0) {
                if (i < 26) {
                    char znak = (char) ('a' + i);
                    HuffmanNode hn = new HuffmanNode();
                    hn.waga = litery[i];
                    hn.setZnak(znak);

                    hn.left = null;
                    hn.right = null;

                    kolejka.add(hn);
                } else {
                    if (i == 26) {
                        char znak = ' ';
                        HuffmanNode hn = new HuffmanNode();
                        hn.waga = litery[i];
                        hn.setZnak(znak);

                        hn.left = null;
                        hn.right = null;

                        kolejka.add(hn);
                    }
                    if (i == 27) {
                        char znak = '.';
                        HuffmanNode hn = new HuffmanNode();
                        hn.waga = litery[i];
                        hn.setZnak(znak);

                        hn.left = null;
                        hn.right = null;

                        kolejka.add(hn);
                    }
                    if (i == 28) {
                        char znak = ',';
                        HuffmanNode hn = new HuffmanNode();
                        hn.waga = litery[i];
                        hn.setZnak(znak);

                        hn.left = null;
                        hn.right = null;

                        kolejka.add(hn);
                    }
                }
            }
        }
    }

    public void realizujHuffmana() {
        while (kolejka.size() > 1) {
            //najmniejszy element
            HuffmanNode pierwszy = kolejka.peek();
            kolejka.poll();
            //drugi najmniejszy element
            HuffmanNode drugi = kolejka.peek();
            kolejka.poll();

            HuffmanNode laczenie = new HuffmanNode();
            laczenie.waga = pierwszy.waga + drugi.waga;
            laczenie.setZnak('-');

            laczenie.left = pierwszy;
            laczenie.right = drugi;
            root = laczenie;
            kolejka.add(laczenie);
        }
        drukuj(root, "");
        System.out.println(root.right.left.getZnak());
    }

    public void drukuj(HuffmanNode node, String s) {
        if (node.left == null && node.right == null && Character.isDefined(node.getZnak())) {
            System.out.println(node.getZnak() + " " + node.waga + " " + s);
            tekst = tekst + s;
            return;
        }


        drukuj(node.left, s + "0");
        drukuj(node.right, s + "1");

    }

    public void zamiana(HuffmanNode node, String s) {
        if (node.left == null && node.right == null && Character.isDefined(node.getZnak())) {
            LiteraKod literaKod = new LiteraKod(node.getZnak(), s);
            return;
        }

        zamiana(node.left, s + "0");
        zamiana(node.right, s + "1");
    }

    public String odkodowanie(String string, HuffmanNode curr) {
    	HuffmanNode current = curr;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (current.left == null && current.right == null) {
                System.out.println("cos robie");
                sb.append(current.getZnak());
                current = root;
            }

            if (string.charAt(i) == '0') {
                current = current.left;
            }
            else if (string.charAt(i) == '1') {
                current = current.right;
            }
        }
        if (current.left == null && current.right == null) {
            sb.append(current.getZnak());
        }
        return sb.toString();
    }


    public static int znajdzWysokosc(HuffmanNode node) {
        if (node == null) {
            return -1;
        }

        int wysokosclewa = znajdzWysokosc(node.left);
        int wysokoscprawa = znajdzWysokosc(node.right);

        if (wysokosclewa > wysokoscprawa) {
            return wysokosclewa + 1;
        } else {
            return wysokoscprawa + 1;
        }
    }

    public void litery() {
        for (int i = 0; i < getTekst2().length(); i++) {
            arrayList.add(getTekst2().charAt(i));
        }
    }


    public void kodowanie(String nazwa) {
        for (int i = 0; i < odczytZPliku.getArrayList().size(); i++) {
            tekst2 = tekst2 + LiteraKod.szukanie(odczytZPliku.getArrayList().get(i));
        }
        System.out.print(tekst2);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nazwa)))) {
            bw.write(tekst2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public String getTekst2() {
        return tekst2;
    }
    public String showSymbolsFrequencyAndCode()
	{
		System.out.println("Kolejnosc symboli jak w drzewie od lewej strony");
		return leaves(root);
	}
	private String leaves(HuffmanNode node)
	{
		StringBuilder S = new StringBuilder();
		if(node.left==null && node.right==null)
			S.append("Symbol-kod:\t\'"+node.getZnak()+"\t ilosc wystapien: "+ node.waga+"\n");
		else
		{
			S.append(leaves(node.left));
			S.append(leaves(node.right));
		}
		return S.toString();
	}
}


