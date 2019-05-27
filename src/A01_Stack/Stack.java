package A01_Stack;


public class Stack<T> {
    private Node<T> first;

    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     *
     * @throws StackEmptyException
     */


    public T pop() throws StackEmptyException {
        if (first == null) {
            throw new StackEmptyException();
        }
        Node<T> reserve;
        reserve = first;
        first = first.getNext();
        return reserve.getData();

    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     *
     * @param i data
     */
    public void push(T i) {
        Node<T> reserve = first;
        Node<T> newFirst = new Node<>(i);
        first = newFirst;
        first.setNext(reserve);
    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     *
     * @return
     */
    public int getCount() {

        if (first != null) {
            int counter = 1;
            Node<T> reserve = first.getNext();
            if (reserve != null) {
                counter++;
                while ((reserve = reserve.getNext()) != null) {
                    counter++;
                }
            }
            return counter;
        }
        return 0;
    }
}
