
class Counta extends Thread
{
    private static IntCell n = new IntCell();
    @Override
    public void run()
    {
        int temp;
        for (int i = 0; i < 200000; i++)
        {
            synchronized(n)
            {
                temp = n.getN();
                n.setN(temp + 1);
            }
        }
    }
    public static void main(String[] args)
    {
        Counta p = new Counta();
        Counta q = new Counta();
        p.start();
        q.start();

        try
        {
            p.join();
            q.join();
        }
        catch (InterruptedException e) { }

        System.out.println("The value of n is " + n.getN());
    }
}

