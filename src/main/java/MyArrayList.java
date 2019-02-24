import java.util.Arrays;

public class MyArrayList <E> {

    private Object[] data;
    private int size;
    private int capacity = 10;

    public MyArrayList(int capacity){

        this.capacity = capacity;
        this.data = new Object[capacity];
        size = 0;

    }

    public MyArrayList(){
        size = 0;
        data = new Object[capacity];
    }

    public int size(){ return size; }

    @Override
    public String toString() {
        String outStr = "";
        for (int i = 0; i < size; i++){
            outStr += data[i];
            if (i != size -1){
                outStr += ", ";
            }
        }
        return "MyArrayList " + outStr;
    }

    private void ensureCapacity(int size){
        if (size > capacity){
            Object copData[] = new Object[(int)((capacity * 1.5) + 1)];
            for(int i = 0; i < capacity; i++) {
                copData[i] = data[i];
            }
            data = copData;
            capacity = (int)((capacity * 1.5) + 1);
            this.size = size;
        }
    }

    public void pushBack(E e){
        ensureCapacity(size++);
        data[capacity--] = e;
    }

    public void popFront(){
        data[0] = null;
    }

    public void pushFront(E e){
        data[0] = e;
    }

    public void insert(E e, int index){
        if (index < capacity && index > 0){
            data[index] = e;
        } else {
            throw new  NullPointerException();
        }
    }

    public void removeAt(int index){
        if (index < capacity && index > 0){
            data[index] = null;
        } else {
            throw new  NullPointerException();
        }
    }

    public void remove(E e){
        for (int i = 0; i < capacity; i++){
            if (data[i].equals(e)){
                data[i] = null;
                break;
            }
        }
    }

    public void removeAll(E e){
        for (int i = 0; i < capacity; i++){
            if (data[i].equals(e)){
                data[i] = null;
            }
        }
    }

    public void popBack(){
        data[capacity--] = null;
    }

    public void  clear(){
        data = null;
        capacity = 0;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void trimToSize() {
        int index = -2;
        int count = capacity - size;
        while (count != 0) {
            for (int i = 0; i < capacity; i++) {
                if (data[i] == null) {
                    index = i;
                } else {
                    data[index] = data[i];
                    data[i] = null;
                    count--;
                }
            }
        }
        capacity = size;
        Object copData[] = new Object[capacity];
        for(int i = 0; i < capacity; i++) {
            copData[i] = data[i];
        }
        data = copData;
    }

    public int indexOf(E e){
        for (int i = 0; i < capacity; i ++){
            if (data[i] == e){
                return i;
            }
        }
        return  -1;
    }

    public int lastIndexOf(E e){
        for (int i = capacity - 1; i >= 0; i--){
            if (data[i] == e){
                return i;
            }
        }
        return -1;
    }

    public void reverse(){
        Object copData[] = new Object[capacity];
        for (int i = capacity - 1; i >= 0; i--){
            copData[i] = data[capacity - i++];
        }
        data = copData;
    }

    public void shuffle(){
        Object copData[] = new Object[capacity];
        int randomInt[] = new int[capacity];
        int chek = 0;
        int randomIndex = (int)Math.random() * capacity;
        for (int i = 0; i < capacity; i++) {
            while(chek >= 0) {
                randomIndex = (int)Math.random() * capacity;
                for (int z = 0; i < capacity; z++) {
                    if (randomInt[z] == randomIndex) {
                        chek = z;
                        break;
                    } else {
                        chek = -1;
                    }
                }
            }
            copData[i] = data[randomIndex];
            randomInt[i] = randomIndex;
        }
        data = copData;
    }

    public boolean equals(MyArrayList<E> list){
        return list.capacity == capacity && list.size == size && list.data == data;
    }

    public Object getElementAt(int index){
        if (index < capacity && index > 0) {
            return data[index];
        } else {
            throw new NullPointerException();
        }
    }

    public MyArrayList<E> clone(){
        MyArrayList<E> copList = new MyArrayList<>();
        copList.data = Arrays.copyOf(data, size);
        return copList;
    }

}
