package util;

/**
 * Holds a dynamically resized array of any object
 * @param <T>
 * Object type to be held in list
 */

public class List<T> {
    public Node<T> head = null;
    public Node<T> tail = null;
    public int length = 0;

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if(head == null) {
            //If the list is empty, both the head and tail will point to the first Node
            head = newNode;
            tail = newNode;
        } else {
            //First update the current tail's next reference to this new element/Node.
            tail.next = newNode;
            //Then update the tail to reference this new Node.
            tail = newNode;
        }
        length++;
    }

    public T pop(){
        if(length == 0)
            return null;
        length--;
        if(head == tail) {
            T ret = head.data;
            head = null;
            tail = null;
            return ret;
        }
        Node<T> newTail = head;
        while(newTail.next != tail){
            newTail = newTail.next;
        }
        T ret = tail.data;
        tail = newTail;
        return ret;
    }

    public T getAtIndex(int index){
        if(index > length-1 || index < 0)
            return null;
        Node<T> ret = head;
        for (int i = 0; i < index; i++) {
            ret = ret.next;
        }
        return ret.data;
    }

    public String toString() {
        String result = "(";
        Node<T> current = head;

        while(current != null) {
            result += current.data;
            if(current != tail) {
                result += ", ";
            }
            current = current.next;
        }

        result += ")";
        return result;
    }

}
