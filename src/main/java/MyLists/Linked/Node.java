package MyLists.Linked;
/**
 * Узел, состоящий из элемента и ссылки на следующий узел
 * @param <T> - тип данных в узле
 */

public class Node<T> {
    /**
     * Элемент заданного типа
     */
    private T element;

    /**
     * Ссылка на следующий узел
     */
    private Node<T> next;

    /**
     * Конструктор по умолчанию, принимающий:
     * @param element - элемент
     * @param next - ссылку на следующий узел
     */
    public Node(T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Метод получения элемента
     */
    public T getElement() {
        return element;
    }

    /**
     * Метод получения узла
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Метод установки узла
     * @param next - узел для установки
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Метод установки элемента
     * @param element - элемент для установки
     */
    public void setElement(T element) {
        this.element = element;
    }
}
