/**
 * Данный класс представляет собой динамический список, в основе которого лежит двумерный массив.
 * Первый уровень массива представляет собой индексы, которые соответсвуют элементам из нижнего уровня.
 * Второй уровень представляет собой элементы.
 * Реализация с помошью двумерного массива была сделана для хранения null-значений.
 * Элемент может являться null, индекс - нет. Если индекс равен null, значит такого элемента не существует.
 */

public class MyArrayList {

    /** Вместимость списка по умолчанию */
    private final int defaultCapacity = 10;

    /** Уровень расположения индексов элементов */
    private final int levelOfIndexes = 0;

    /** Уровень расположения элементов */
    private final int levelOfElements = 1;

    /** Количество уровней в коллекции */
    private final int countOfLevels = 2;

    /** Вместимость списка */
    private int capacity;

    /** Количество заполненного пространства */
    private int filledCapacity = 0;

    /** Двумерный массив для заполнения данными */
    private Object[][] elements;

    /** Конструктор по умолчанию */
    public MyArrayList() {
        elements = new Object[countOfLevels][defaultCapacity];
        capacity = defaultCapacity;
    }

    /** Метод добавления елемента */
    public void add(Object element) {
        elements[levelOfIndexes][filledCapacity] = filledCapacity;
        elements[levelOfElements][filledCapacity] = element;
        filledCapacity++;
        expandArray();
    }

    /** Метод добавления элемента по индексу */
    public void add(Object element, int index) {
        if(index > capacity) {
            throw new IndexOutOfBoundsException();
        } else {
            elements[levelOfIndexes][index] = index;
            elements[levelOfElements][index] = element;
            filledCapacity++;
            expandArray();
        }
    }

    /** Метод получения элемента по индексу */
    public Object get(int index) {
        checkExistingOfElement(index);
        return elements[levelOfElements][index];
    }

    /** Метод удаления элемента по индексу */
    public void delete(int index) {
        checkExistingOfElement(index);

        for (int i = index; i < filledCapacity; i++) {
            elements[levelOfIndexes][i - 1] = elements[levelOfElements][i];
            elements[levelOfElements][i - 1] = elements[levelOfElements][i];
        }

        filledCapacity--;
    }

    /** Метод удаления всех элементов */
    public void deleteAll() {
        elements = new Object[countOfLevels][defaultCapacity];
        capacity = defaultCapacity;
    }

    /** Метод расширения списка */
    private void expandArray() {
        if(filledCapacity == capacity) {
            int newCapacity = getNewCapacity();
            Object[][] newElements = new Object[countOfLevels][newCapacity];
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[i].length; j++) {
                    newElements[i][j] = elements[i][j];
                }
            }
            capacity = newCapacity;
            elements = newElements;
        }
    }

    /** Метод получения большей ёмкости списка */
    private int getNewCapacity() {
        if(capacity % 2 == 0) {
            return capacity + (capacity / 2);
        } else {
            return capacity + (capacity / 2) + 1;
        }
    }

    /** Метод проверки на существование элемента в списке */
    private void checkExistingOfElement(int index) {
        if(elements[levelOfIndexes][index] == null) throw new NullPointerException();
    }

    /** Метод получения количества элементов в списке */
    public int filledCapacity() {
        return filledCapacity;
    }
}
