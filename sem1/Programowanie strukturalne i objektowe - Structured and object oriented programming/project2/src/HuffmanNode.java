public class HuffmanNode {
    int waga;
    private char znak;
    HuffmanNode left;
    HuffmanNode right;
    public HuffmanNode(){
        this.right=null;
        this.left=null;
    }

    public int getWaga() {
        return waga;
    }

    public char getZnak() {
        return znak;
    }

    public void setZnak(char znak) {
        this.znak = znak;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }
}
