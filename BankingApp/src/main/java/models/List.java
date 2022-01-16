package models;

/**
 * Holds a dynamically resized array of any object
 * @param <T>
 * Object type to be held in list
 */

public class List<T> {
    final private int step = 5;
    private int tail = 0;
    private T[] list = (T[]) new Object[step];

    /**
     * Add an item to the tail of the list
     * @param item
     * item to be added to the list
     */
    public void push(T item){
        if(tail == list.length - 1)
            grow();
        list[tail] = item;
        tail++;
    }

    /**
     * Remove and return the tail item of the list
     * @return
     * Removed item
     */
    public T pop(){
        if(tail > 0)
            tail--;
        return list[tail];
    }

    /**
     * Remove item at a specified index
     * @param index
     * Index of item to remove
     * @return
     * Removed item
     */
    public T removeItem(int index){
        if(index < 0 || index > list.length - 1)
            return null;
        T a = list[index];
        list[index] = null;
        return a;
    }

    /**
     * Get all items
     * @return
     * Array of all stored items
     */
    public T[] getItems(){
        int blocks = 0;
        for (T a: list) {
            if(a != null)
                blocks++;
        }
        int nextIndex = 0;
        T[] a = (T[]) new Object[blocks];
        for (T add: list) {
            if(add == null)
                continue;
            a[nextIndex++] = add;
        }
        return a;
    }

    /**
     * Grow list's size
     */
    private void grow(){
        T[] temp = list;
        list = (T[]) new Object[temp.length + step];
        System.arraycopy(temp, 0, list, 0, temp.length);
    }
}
