/*
self-defined Array list through array data structure only,with generic
 */
public class ArrayList<T> {
    T[] space = (T[])new Object[4];
    int size = 0;

    public void add(T t){

        if(size < space.length) {
            space[size] = t;
        }else {//increase size
            T[] temp = (T[])new Object[space.length];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = space[i];
            }
            space = (T[])new Object[temp.length*2];
            for (int i = 0; i < temp.length; i++) {
                space[i] = temp[i];
            }
            space[size] = t;
        }
        size++;
    }

    public int size(){//return size
        return size;
    }

    public T get(int index){
        return space[index];
    }

    public T pop(){
        T popedValue = space[size - 1];
        space[size - 1] = null;
        size--;
        if(size <= (space.length/2)){//reduce size
            T[] temp = (T[])new Object[space.length/2];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = space[i];
            }
            space = (T[])new Object[space.length/2];
            for (int i = 0; i < temp.length; i++) {
                space[i] = temp[i];
            }
        }
        return popedValue;
    }

    public void insert(int index, T t){
        if(index < size) {
            size++;
            if (size < space.length) {
                T temp = null;
                for (int i = index; i < size; i++) {
                    temp = space[i];
                    space[i] = t;
                    t = temp;
                }
            } else {//increase size
                T[] temp = (T[])new Object[space.length];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = space[i];
                }
                space = (T[])new Object[temp.length * 2];
                for (int i = 0; i < temp.length; i++) {
                    space[i] = temp[i];
                }
                T temp2 = null;
                for (int i = index; i < size; i++) {
                    temp2 = space[i];
                    space[i] = t;
                    t = temp2;
                }
            }
        }
    }

    public void remove(int index){
        if(index < size) {
            for (int i = index; i < size - 1; i++) {
                space[i] = space[i + 1];
            }
            space[size - 1] = null;
            size--;
            if (size < (space.length / 2)) {//reduce size
                T[] temp = (T[])new Object[space.length / 2];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = space[i];
                }
                space = (T[])new Object[space.length / 2];
                for (int i = 0; i < temp.length; i++) {
                    space[i] = temp[i];
                }
            }
        }
    }
}
