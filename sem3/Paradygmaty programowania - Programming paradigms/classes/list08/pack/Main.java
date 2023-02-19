package pack;

public class Main
{
    public static void main(String[] args)
    {
	    MyQueueTab<Integer> Q = new MyQueueTab<>(3);
        try
        {
            Q.enqueue(0);
            Q.enqueue(1);
            Q.enqueue(2); //kolejka jest pelna
            System.out.println(Q.isFull()?"Full":"Not full");
            System.out.println(Q.first());
            Q.dequeue();
            System.out.println(Q.first());
            Q.dequeue(); //w kolejce zostal jeden element
            Q.enqueue(3);
            System.out.println(Q.isFull()?"Full":"Not full");
            Q.enqueue(4); //kolejka znow jest pelna
            System.out.println(Q.isFull()?"Full":"Not full");
            System.out.println(Q.first());
            Q.dequeue();
            System.out.println(Q.first());
            Q.dequeue();
            System.out.println(Q.isEmpty()?"Empty":"Not empty");
            System.out.println(Q.first());
            Q.dequeue();
            System.out.println(Q.isEmpty()?"Empty":"Not empty");
        }
        catch (Exception e) //oba wyjatki obsluguje tak samo, nie ma wiec sensu robic osobnych "catch"
        {
            e.printStackTrace();
        }
    }
}
