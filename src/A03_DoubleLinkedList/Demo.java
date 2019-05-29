package A03_DoubleLinkedList;

public class Demo {

    public static void main(String[] args)
    {
        TestDoubleLinkedList test = new TestDoubleLinkedList();
        try {
            test.getFirst();
            test.getLast();
            test.insertAfterCurrentAndMove();
            test.insertAfterCurrentAndMoveException();
            test.moveAndGetCurrent();
            test.next();
            test.previous();
            test.remove();
            test.removeException();
            test.removeCurrent();
            test.getCurrentException();
            test.getCurrentException2();
            test.getCurrentException3();
            test.getCurrentException4();
            test.getCurrentException5();
            test.getCurrentException6();
            test.removeCurrentException();
            test.removeCurrentException2();
            test.removeCurrentException3();
            test.get();

        }
        catch(CurrentNotSetException e)
        {
            e.printStackTrace();
        }

    }
}
