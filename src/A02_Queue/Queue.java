package A02_Queue;

public class Queue<T> {
    private Node<T> first;

    private Node<T> last;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     *
     * @throws QueueEmptyException
     */
    public T dequeue() throws QueueEmptyException {
        if (first == null) {
            throw new QueueEmptyException();
        }
        Node<T> reserve = first;
        if (first != last) {
            first = reserve.getNext();
        } else {
            first = null;
            last = null;
        }


        return reserve.getData();
    }


    /**
     * �bergebenen Integer am Ende der Queue anh�ngen.
     *
     * @param i Zahl
     */
    public void enqueue(T i) {
        if (first == null) {
            first = new Node<>(i);
            last = first;
        } else {
            Node<T> resserve = last;
            last = new Node<>(i);
            resserve.setNext(last);
        }

    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     *
     * @return
     */
    public int getCount() {
        if (first != null) {
            int counter = 1;


            if (first != last) {
                Node<T> reserve = first.getNext();
                counter++;
                while ((reserve =reserve.getNext()) != null) {
                    counter++;
                }
            }
            return counter;

        }

        return 0;

    }
}


