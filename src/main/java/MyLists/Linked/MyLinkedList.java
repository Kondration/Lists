package MyLists.Linked;

import MyInterfaces.ListInterface;

import java.util.Comparator;
/**
    Коллекция связный список
    @param <T> - тип элементов в коллекции
 */
public class MyLinkedList<T> implements ListInterface<T> {

    /**
     * Количество элементов в списке
     */
    private int size = 0;

    /**
     * Первый элемент
     */
    private Node<T> first;

    /**
     * Последний элемент
     */
    private Node<T> last;

    /**
     * Конструктор по умолчанию
     */
    public MyLinkedList() {

    }

    /**
     * Метод добавления элемента в конец списка
     * @param element - элемент для добавления
     */
    @Override
    public void add(T element) {
        if(size == 0) {
            first = new Node<>(element, null);
        } else if(size == 1) {
            last = new Node<>(element, null);
            first = new Node<>(first.getElement(), last);
        } else {
            Node<T> nextElement = new Node<>(element, null);
            last.setNext(nextElement);
            last = nextElement;
        }
        size++;
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
        Node<T> iterable = first;
        if(index == size) {
            add(element);
        } else {
            for (int i = 0; i < index; i++) {
                if(i == index - 1) {
                    iterable.setNext(new Node<T>(iterable.getElement(), iterable.getNext()));
                    iterable.setElement(element);
                    break;
                }
                iterable = iterable.getNext();
            }
        }
        size++;
    }

    /**
     * Метод добавления элемента в начало списка
     */
    public void addFirst(T element) {
        if(size == 0) {
            first = new Node<>(element, null);
        } else if(size == 1){
            last = first;
            first = new Node<>(element, last);
        } else {
            first = new Node<>(element, first);
        }
        size++;
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
        Node<T> iterable = first;
        if(index == size - 1) {
            iterable = last;
        } else {
            for (int i = 0; i < index; i++) {
                iterable = iterable.getNext();
                if(i == index - 1) {
                    break;
                }
            }
        }
        return iterable.getElement();
    }

    /**
     * Метод получения элемента из начала списка
     */
    public T getFirst() {
        return first.getElement();
    }

    /**
     * Метод получения элемента из конца списка
     */
    public T getLast() {
        return last.getElement();
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
        Node<T> iterable = first;
        for (int i = 0; i < index; i++) {
            if(i == index - 2) {
                iterable = new Node<>(iterable.getElement(), iterable.getNext().getNext());
                break;
            }
            iterable = iterable.getNext();
        }
        size--;
    }

    /**
     * Метод удаления всех элементов из списка
     */
    @Override
    public void deleteAll() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Метод сортировки элементов в порядке возрастания
     * @param comparator - сортировщик элементов
     */
    @Override
    public void sort(Comparator<T> comparator) {
        Node<T> biggestElement;
        Node<T> compareElement;
        int decrement = size;
        for (int i = 0; i < size; i++) {
            biggestElement = null;
            compareElement = null;
            for (int j = 0; j < decrement; j++) {
                if(biggestElement == null) {
                    biggestElement = first;
                }
                if (compareElement == null) {
                    compareElement = first;
                }
                if(comparator.compare(biggestElement.getElement(), compareElement.getElement()) == -1) {
                    biggestElement = compareElement;
                } else if(comparator.compare(biggestElement.getElement(), compareElement.getElement()) == 1) {
                    biggestElement.setNext(compareElement.getNext());
                    compareElement.setNext(biggestElement);
                }
                if(j != decrement - 1) {
                    compareElement = compareElement.getNext();
                }
            }
            decrement--;
        }
    }

    /**
     * Метод количества элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для добавления элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForAdd(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot add an element to position " + index + ". Max index for adding is " + size);
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot add an element to position " + index + ". Index is a negative number");
        }
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для удаления элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForDelete(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot delete an element to position " + index + ". Max index for deleting is " + size);
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot delete an element to position " + index + ". Index is a negative number");
        }
    }

    /**
     * Метод проверки индекса
     * @param index - место в списке для получения элемента
     * @throws IndexOutOfBoundsException - @param index больше размера списка
     * @throws IllegalArgumentException - @param index отрицательный
     */
    private void checkIndexForGet(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot get an element from position " + index + ". Max index for getting is " + (size - 1));
        } else if (index < 0) {
            throw new IllegalArgumentException("Cannot get an element from position " + index + ". Index is a negative number");
        }
    }

}
