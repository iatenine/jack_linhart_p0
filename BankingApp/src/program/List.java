package program;

// Implement Link List to hold dynamic collection of Accounts

public class List<T> {
    final private int step = 5;
    private int tail = 0;
    private T[] list = (T[]) new Object[step];

    // Add an account
    public void push(T account){
        if(tail == list.length - 1)
            grow();
        list[tail] = account;
        tail++;
    }

    public T pop(){
        if(tail > 0)
            tail--;
        return list[tail];
    }

    // Remove account if found
    public T removeItem(int index){
        if(index < 0 || index > list.length - 1)
            return null;
        T a = list[index];
        list[index] = null;
        return a;
    }

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

    private void grow(){
        T[] temp = list;
        list = (T[]) new Object[temp.length + step];
        System.arraycopy(temp, 0, list, 0, temp.length);
    }
}
