import java.util.Arrays;

public class MyArrayList<E> {

    private Object[] data;      // массив объектов
    private int size;           // кол-во элементов в коллекции
    private int capacity = 10;  // значение ёмкости массива при стандартном значении

    public MyArrayList(int capacity) {         // конструктор создаёт коллекцию с заданной ёмкостью
        this.capacity = capacity;
        this.data = new Object[capacity];
        size = 0;

    }

    public MyArrayList() {                     // конструктор создаёт коллекцию со стандартным значением ёмкости
        size = 0;
        data = new Object[capacity];
    }

    public int size() {                       // возвращает кол-во элементов в коллекции
        return size;
    }

    @Override
    public String toString() {                // преобразует коллекцию в строковый вид
        String outStr = "";
        for (int i = 0; i < size; i++) {
            outStr += data[i];
            if (i != size - 1) {
                outStr += ", ";
            }
        }
        return "MyArrayList " + outStr;
    }

    private void ensureCapacity(int size) {  // закрытый метод, проверяет, достаточноли резерва памяти для хранения указанного в параметре кол-ва элементов
        if (size > capacity) {
            Object copData[] = new Object[(int) ((capacity * 1.5) + 1)];
            for (int i = 0; i < capacity; i++) {
                copData[i] = data[i];
            }
            data = copData;
            capacity = (int) ((capacity * 1.5) + 1);
            this.size = size;
        }
    }

    public void pushBack(E e) {             // добавление элемента в конец массива
        ensureCapacity(size++);
        data[capacity--] = e;
    }

    public void popFront() {                // удаление первого элемента из массива
        data[0] = null;
    }

    public void pushFront(E e) {            // добавление нового элемента в начало массива
        data[0] = e;
    }

    public void insert(E e, int index) {    // вставка нового элемента в массив по указанному индексу
        if (index < capacity && index > 0) {
            data[index] = e;
        } else {
            throw new NullPointerException();
        }
    }

    public void removeAt(int index) {      // удаление одного элемента по указанному индексу
        if (index < capacity && index > 0) {
            data[index] = null;
        } else {
            throw new NullPointerException();
        }
    }

    public void remove(E e) {              // удаление одного элемента, значение которого совпадает со значением переданного параметра
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(e)) {
                data[i] = null;
                break;
            }
        }
    }

    public void removeAll(E e) {          // удаление всех элементов, значения которых совпадает со значением переданного параметра
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(e)) {
                data[i] = null;
            }
        }
    }

    public void popBack() {              // удаление последнего элемента из массива
        data[capacity--] = null;
    }

    public void clear() {                // обнуление массива – всем элементам массива по индексам от 0 до size-1 присвоить значение null, полю size присвоить значение 0
        if (size > 0) {
            data = null;
            capacity = 0;
            size = 0;
        }
    }

    public boolean isEmpty() {          // метод возвращает true, если size = 0, и false в обратном случае
        return size == 0;
    }

    public void trimToSize() {          // метод подгоняет значение capacity под size, естественно с перевыделением памяти
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
        for (int i = 0; i < capacity; i++) {
            copData[i] = data[i];
        }
        data = copData;
    }

    public int indexOf(E e) {         // линейный поиск слева направо первого вхождения в массив указанного значения
        for (int i = 0; i < capacity; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(E e) {    // линейный поиск справа налево вхождения в массив указанного значения
        for (int i = capacity - 1; i >= 0; i--) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public void reverse() {         // изменение порядка следования элементов в массиве на противоположный
        Object copData[] = new Object[capacity];
        for (int i = capacity - 1; i >= 0; i--) {
            copData[i] = data[capacity - i++];
        }
        data = copData;
    }

    public void shuffle() {        // случайное перемешивание элементов массива
        Object copData[] = new Object[capacity];
        int randomInt[] = new int[capacity];
        int chek = 0;
        int randomIndex = (int) Math.random() * capacity;
        for (int i = 0; i < capacity; i++) {
            while (chek >= 0) {
                randomIndex = (int) Math.random() * capacity;
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

    public boolean equals(MyArrayList<E> list) { // Метод сравнивает массивы нетолько по количеству элементов, но и по их содержимому
        return list.capacity == capacity && list.size == size && list.data == data;
    }

    public Object getElementAt(int index) {      // возврат копии элемента массива по указанному индексу, с проверкой на выход за пределы массива
        if (index < capacity && index > 0) {
            return data[index];
        } else {
            throw new NullPointerException();
        }
    }

    public MyArrayList<E> clone() {              //  метод создает точную копию MyArrayList и возвращает ссылку на эту копию
        MyArrayList<E> copList = new MyArrayList<>();
        copList.data = Arrays.copyOf(data, size);
        return copList;
    }

}
