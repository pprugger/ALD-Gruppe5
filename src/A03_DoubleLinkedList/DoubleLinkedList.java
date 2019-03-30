package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{

    public Node<T> first;
    public Node<T> last;
    public Node<T> current;

    /**
     * Einfuegen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {

        //If no node exists, create a new one and set previous and next node to null
        if (first == null)
        {
            first = new Node<T>(a);
            first.setPrevious(null);
            first.setNext(null);
            last = first;
        }
        else
        {
            //if at least one node exists, insert after that node
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
    public void reset()
    {
        //reset the internal state, point current to first element
        current = first;

    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast()
    {
        //reset the internal state, point current to last element
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {

        // only return first if it isn't null
    	if( first != null) {return first;}

        return null;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {

        // only return last if it isn't null
        if( last != null) {return last;}
    	return null;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

        //return null if current is not set (null)
        if ( current == null) { return null;}

        //create temporary node so we can set current to the next element
        Node<T> temp = current;
        current = current.getNext();

        //return temp data, temp will get cleaned by garbage collector
        return temp.getData();
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

        //return null if current is not set (null)
        if ( current == null) { return null;}

        //create temporary node so we can set current to the previous element
        Node<T> temp = current;
        current = current.getPrevious();

        //return temp data, temp will get cleaned by garbage collector
        return temp.getData();
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {

        //return if current is not set (null)
        if (current == null ) { return; }

        //return if there is no next element
        if (current.getNext() == null ) { return; }

        //else set current to next element
        current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        //return if current is not set (null)
        if (current == null ) { return; }

        //return if there is no previous element
        if (current.getPrevious()== null ) { return; }

        //else set current to previous element
        current = current.getPrevious();
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        //return data if current is not null, else throw exception

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

        //we start counting at 1, so count is 1
        int count = 1;
        Node<T> temp = first;

        while (count < pos)
        {
            //if we reach the end of the list, return null
            if(temp.getNext() == null ) { return null;}
            temp = temp.getNext();

        }
        //else return data
        return temp.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {

        //we start counting at 1, so count is 1
        int count = 1;

        //create three temporary nodes, which will be cleaned up by the garbage collector
        Node<T> temp = first;
        //Node<T> temp_previous = null;
        //Node<T> temp_next = null;

        if(temp == null) { return; }  //if temp is null the list is empty, so there is nothing to remove, return then

        if((temp == first) & (temp == last))   //Only one element left in List, set all elements to null and return
        {
            first = null;
            last = null;
            temp = null;
            return;
        }

        while (count < pos)
        {
            //iterate through the list until we found the right list element
            //if we reach the end of the list, then return

            if(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            else { return; }
            count++;
        }

        //Special case: temp is the first list element
        if( temp == first)
        {
                //we have more than one element in the list
                //set first to the next element
                //set the previous element to null
                //delete the reference to temp and return
                first = first.getNext();
                first.setPrevious(null);
                temp = null;
                return;
        }

        if ( temp == last)
        {
            //we have more than one element in the list
            //set last to the element before
            //set the next element to null
            //delete the reference to temp and return
            last = last.getPrevious();
            last.setNext(null);
            temp = null;
            return;
        }

        //Get the previous element of temp and set the next link to the next element of temp
        temp.getPrevious().setNext(temp.getNext());

        //Get the next element of temp and set the previous link to the previous element of temp
        temp.getNext().setPrevious(temp.getPrevious());

        //Also possible with temporary Nodes
        //This nodes are defined up but commented out

        //temp_previous = temp.getPrevious();
        //temp_next = temp.getNext();
        //temp_previous.setNext(temp_next);
        //temp_next.setPrevious(temp_previous);

        //If we remove the current element set current to null
        if(temp == current) { current = null;}
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        //Node<T> temp_previous;
        //Node<T> temp_next;

        if(current == null) { return; } //Current not set, return

        if(current == first & current == last)   //Only one element left in List, set all elements to null and return
        {
            first = null;
            last = null;
            current = null;
            return;
        }

        //Special case current is first element
        if( current == first)
        {
            //Here we have at least two elements in the list
            //We delete the first element
            first = first.getNext();
            first.setPrevious(null);

            //Because we know for sure that we have at least two elements, we dont have to check here if
            //the next element is null
            //Also current.getPrevious will allways be null, because we delete the first element
            current = current.getNext();
            return;
        }

        //Special case current is last element
        if ( current == last)
        {
            //Here we have at least two elements in the list
            //We delete the last element
            last = last.getPrevious();
            last.setNext(null);

            //Because we know for sure that we have at least two elements, we dont have to check here if
            //the previous element is null
            //Also current.getNext will allways be null, because we delete the last element
            current = current.getPrevious();
            return;
        }

        //Get the previous element of current and set the link to the next element to the element after current
        current.getPrevious().setNext(current.getNext());

        //Get the next element of current and set the link to the previous element to the element before current
        current.getNext().setPrevious(current.getPrevious());

        //Also possible with temporary Nodes
        //This nodes are defined up but commented out

        /////////////////Start commented codeblock

        //temp_previous = current.getPrevious();
        //temp_next = current.getNext();
        //temp_previous.setNext(temp_next);
        //temp_next.setPrevious(temp_previous);

        //if(temp_next == null)
        //{
        //    current = current.getPrevious();
        //    return;
        //}
        //else
        //{
        //    current = current.getNext();
        //    return;
        //}

        /////////////////END commented codeblock

        //If the next element is null, set current to the previous element
        //Else set current to the next element
        if(current.getNext() == null)
        {
            current = current.getPrevious();
            return;
        }
        else
        {
            current = current.getNext();
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

        if(current == null) { return; } //return if current is not set

        temp_next = current.getNext();  //get the element after current

        newNode.setNext(temp_next);   //set the next element of the new Node
        newNode.setPrevious(current); // set the previous element of the new node to current

        //we can only set the previous here if we are not at the end of the list
        //if temp_next is null, then we are at the end of the list
        if(temp_next != null) { temp_next.setPrevious(newNode);}

        //set the next node of current to the new node
        current.setNext(newNode);

        //move the position of current to the new node
        current = newNode;
    }
}
