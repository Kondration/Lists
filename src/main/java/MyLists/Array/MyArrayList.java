package MyLists.Array;

import MyInterfaces.ListInterface;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 Коллекция список на основе динамического массива
 @param <T> - тип элементов в коллекции
 */
public class MyArrayList<T> implements ListInterface<T> {

    /**
     * Начальная вместимость списка
     */
    private final int defaultCapacity = 10;

    /**
     * Вместимость списка
     */
    private int capacity = 0;

    /**
     * Количество элементов списка
     */
    private int elementsCount = 0;

    /**
     * Массив элементов списка
     */
    private T[] elements;

    /**
     * Указатель на элемент для итератора
     */
    private int iterable = 0;

    /**
     * Конструктор по умолчанию
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (T[]) new Object[defaultCapacity];
        capacity = defaultCapacity;
    }

    /**
     * Метод добавления элемента в конец списка
     * @param element - элемент для добавления
     */
    @Override
    public void add(T element) {
        checkCapacity();
        elements[elementsCount] = element;
        elementsCount++;
    }

    /**
     * Метод добавления элемента в определённое место списка
     * @param element - элемент для добавления
     * @param index - место для добавления
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    @Override
    public void add(T element, int index) {
        checkIndexForAdd(index);
        checkCapacity();
        for (int i = elementsCount; i > index ; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        elementsCount++;
    }

    /**
     * Метод получения элемента из определённого места
     * @param index - место в списке, откуда нужно получить элемент
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    @Override
    public T get(int index) {
        checkIndexForGet(index);
        return elements[index];
    }

    /**
     * Метод удаления элемента из определённого места
     * @param index - место в списке, откуда нужно удалить элемент
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    @Override
    public void delete(int index) {
        checkIndexForDelete(index);
        for (int i = index; i < elementsCount; i++) {
            elements[i - 1] = elements[i];
        }
        elementsCount--;
    }

    /**
     * Метод удаления всех элементов из списка
     */
    @SuppressWarnings("unchecked")
    @Override
    public void deleteAll() {
        elements = (T[]) new Object[defaultCapacity];
        capacity = defaultCapacity;
        elementsCount = 0;
    }

    /**
     * Метод сортировки элементов в порядке возрастания
     * @param comparator - сортировщик элементов
     */
    @Override
    public void sort(Comparator<T> comparator) {
        T biggestElement;
        int decrement = elementsCount;
        for (int i = 0; i < elementsCount; i++) {
            biggestElement = null;
            for (int j = 0; j < decrement; j++) {
                if(biggestElement == null) {
                    biggestElement = elements[j];
                    continue;
                }
                if(isFirstBiggest(biggestElement, elements[j], comparator)) {
                    elements[j - 1] = elements[j];
                    elements[j] = biggestElement;
                } else {
                    biggestElement = elements[j];
                }
            }
            decrement--;
        }
    }

    private boolean isFirstBiggest(T o1, T o2, Comparator<T> comparator) {
        return comparator.compare(o1, o2) == 1;
    }

    /**
     * Метод сравнения вместимости списка с количеством элементов
     */
    private void checkCapacity() {
        if(capacity == elementsCount) {
            expand();
        }
    }

    /**
     * Метод расширения вместимости списка
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        int newCapacity = getNewCapacity();
        T[] newElements = (T[]) new Object[newCapacity];
        for (int i = 0; i < elementsCount; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        capacity = newCapacity;
    }

    /**
     * Метод расчёта вместимости списка
     */
    private int getNewCapacity() {
        if (capacity % 2 == 0) {
            return capacity + capacity / 2;
        } else {
            return capacity + (capacity / 2) + 1;
        }
    }

    /**
     * Метод получения количества элементов в списке
     */
    public int size() {
        return elementsCount;
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для удаления элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForDelete(int index) {
        if (index >= elementsCount) {
            throw new IndexOutOfBoundsException("Cannot delete an element from position " + index + ". Max index for deleting is " + (elementsCount - 1));
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot delete an element from position " + index + ". Index is a negative number");
        }
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для получения элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForGet(int index) {
        if (index >= elementsCount) {
            throw new IndexOutOfBoundsException("Cannot get an element from position " + index + ". Max index for getting is " + (elementsCount - 1));
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot get an element from position " + index + ". Index is a negative number");
        }
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для добавления элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForAdd(int index) {
        if (index > elementsCount) {
            throw new IndexOutOfBoundsException("Cannot add an element to position " + index + ". Max index for adding is " + elementsCount);
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot add an element to position " + index + ". Index is a negative number");
        }
    }

    /**
     * Метод получения итератора
     *
     * @return Iterator - возвращает итератор по данной коллекции
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             * @return boolean - проверяет, существует ли следующий элемент в коллекции.
             */
            @Override
            public boolean hasNext() {
                if(iterable >= elementsCount) {
                    iterable = 0;
                    return false;
                }
                return elements[iterable] != null;
            }

            /**
             * @return T - возвращает элемент из коллекции и переключается на следующий.
             */
            @Override
            public T next() {
                return elements[iterable++];
            }
        };
    }

    /**
     * Метод проведения какой-либо операции над всеми элементами в коллекции
     * @param action - принимает потребителя, которому необходимы элементы из коллекции.
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        ListInterface.super.forEach(action);
    }

    /**
     * Метод разделения итерирования
     * @return Spliterator - возвращает базовый сплитератор
     */
    @Override
    public Spliterator<T> spliterator() {
        return ListInterface.super.spliterator();
    }
}
