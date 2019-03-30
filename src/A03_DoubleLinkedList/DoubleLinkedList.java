package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{

    public Node<T> first;
    public Node<T> last;
    public Node<T> current;

    /**
     * Einf?gen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {

        if (first == null)
        {
            first = new Node<T>(a);
            first.setPrevious(null);
            first.setNext(null);
            last = first;
        }
        else
        {
            Node<T> newNode = new Node<T>(a);
            newNode.setNext(null);
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {

        current = first;

    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {

        current = last;

    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
    	
    	if( first != null) {return first;}

        return null;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {

        if( last != null) {return last;}
    	return null;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

        if ( current == null) { return null;}

        Node<T> temp = current;
        current = current.getNext();

        return temp.getData();
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

        if ( current == null) { return null;}
        Node<T> temp = current;
        current = current.getPrevious();

        return temp.getData();
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {

        if (current == null ) { return; }
        if (current.getNext() == null ) { return; }
        current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        if (current == null ) { return; }
        if (current.getPrevious()== null ) { return; }
        current = current.getPrevious();
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current != null)
        {
            return current.getData();
        }
        else
        {
            throw new CurrentNotSetException();
        }
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        int count = 1;
        Node<T> temp = first;

        while (count < pos)
        {

            if(temp.getNext() == null ) { return null;}
            temp = temp.getNext();

        }

        return temp.getData();

    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {

        int count = 1;
        Node<T> temp = first;
        Node<T> temp_previous = null;
        Node<T> temp_next = null;

        if(temp == null) { return; }  //list empty

        while (count < pos)
        {
            if(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            count++;

        }

        if( temp == first)
        {
            //Delete the first element
            first = first.getNext();
            first.setPrevious(null);
            temp = null;
            return;
        }

        if ( temp == last)
        {
            last = last.getPrevious();
            last.setNext(null);
            temp = null;
            return;
        }


       temp_previous = temp.getPrevious();
       temp_next = temp.getNext();
       temp_previous.setNext(temp_next);
       temp_next.setPrevious(temp_previous);


        if(temp == current) { current = null;}
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        Node<T> temp_previous;
        Node<T> temp_next;

        if(current == null) { return; } //Current not set

        if(current == first & current == last)   //Only one element left in List, set all elements to null
        {
            first = null;
            last = null;
            current = null;
            return;
        }

        if( current == first)
        {
            //Delete the first element
            first = first.getNext();
            first.setPrevious(null);

            if(current.getNext() != null)
            {
                current = current.getNext();
            }
            else
            {
                current = current.getPrevious();
            }
            return;
        }

        if ( current == last)
        {
            last = last.getPrevious();
            last.setNext(null);

            if(current.getNext() != null)
            {
                current = current.getNext();
            }
            else
            {
                current = current.getPrevious();
            }
            return;
        }


        temp_previous = current.getPrevious();
        temp_next = current.getNext();

        temp_previous.setNext(temp_next);
        temp_next.setPrevious(temp_previous);

        if(temp_next == null)
        {
            current = current.getPrevious();
            return;
        }
        else
        {
            current = current.getNext();
            temp_next.setPrevious(temp_previous);
            return;
        }
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        Node<T> newNode = new Node<T>(a);
        Node<T> temp_next = null;

        if(current != null) { temp_next = current.getNext();}

        newNode.setNext(temp_next);
        newNode.setPrevious(current);

        if(temp_next != null) { temp_next.setPrevious(newNode);}

        if(current != null) { current.setNext(newNode); }

        current = newNode;

    }
}
