package MyInterfaces;

import java.util.Comparator;

/**
 * Интерфейс для предоставления реализации собственных списков
 * @param <T> - тип элементов в списке
 */
public interface ListInterface<T> extends Iterable<T> {
    /**
     * Метод добавления элемента
     * @param element - элемент для добавления
     */
    public void add(T element);

    /**
     * Метод добавления элемента в определённое место списка
     * @param element - элемент для добавления
     * @param index - место для добавления
     */
    public void add(T element, int index);

    /**
     * Метод получения элемента из определённого места в списке
     * @param index - место для добавления
     */
    public T get(int index);

    /**
     * Метод удаления элемента из определённого места в списке
     * @param index - место для удаления
     */
    public void delete(int index);

    /**
     * Метод удаления всех элементов из списка
     */
    public void deleteAll();

    /**
     * Метод получения количества элементов в списке
     */
    public int size();

    /**
     * Метод сортировки элементов в списке
     */
    public void sort(Comparator<T> comparator);
}
