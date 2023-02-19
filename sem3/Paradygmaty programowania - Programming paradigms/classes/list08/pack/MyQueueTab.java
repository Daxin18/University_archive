package pack;


import java.util.ArrayList;

public class MyQueueTab<E> implements MyQueue<E>
{
    ArrayList<E> al;
    int size;
    int r;
    int f;

    public MyQueueTab()
    {
        this.al = new ArrayList<>();
        al.add(null);
        this.size = 2;
        this.r = 0;
        this.f = 0;
    }
    public MyQueueTab(int size)
    {
        this.al = new ArrayList<>();
        for(int i=0; i<=size; i++)
            al.add(null);
        this.size = size + 1; //tylko po to, zeby zgadzalo sie z rysunkiem z listy 7 (gdzie kolejka wielkosci 3 ma liste z 4 elementami)
        this.r = 0;
        this.f = 0;
    }


    public void enqueue( E x ) throws FullException {
        if (!isFull())
        {
            al.set(r++, x);
            if (r==size)
                r = 0;
        }
        else
            throw new FullException("MyQueueTab: enqueue");
    }

    public void dequeue( )
    {
        if(!isEmpty())
        {
            if (f!=size-1)
                f++;
            else
                f = 0;
        }
    }

    public E first( ) throws EmptyException
    {
        if(!isEmpty())
            return al.get(f);
        else
            throw new EmptyException("MyQueueTab: first");
    }

    public boolean isEmpty( )
    {
        return r==f;
    }

    public boolean isFull( )
    {
        return r==size-1?f==0:r==f-1;
    }
}
