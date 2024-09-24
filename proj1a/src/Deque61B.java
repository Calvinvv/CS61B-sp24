import java.util.List;

/**
 * 由 hug 于 2017 年 2 月 4 日创建。方法按建议的顺序提供
 * 他们应该完成。
 */
public interface Deque61B<T> {

    /**
     * 将 {@code x} 添加到双端队列的前面。假设 {@code x} 永远不会为 null。
     *
     * @param x 项添加
     */
    void addFirst(T x);

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    void addLast(T x);

    /**
     * 返回 deque 的 List 副本。不改变 deque。
     *
     * @return deque 的新列表副本。
     */
    List<T> toList();

    /**
     * 如果 deque 为空，则返回。不改变 deque。
     *
     * 如果双端队列没有元素，则@return {@code true}，否则为 {@code false}。
     */
    boolean isEmpty();

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    int size();

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeFirst();

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeLast();

    /**
     * Deque61B 抽象数据类型通常没有 get 方法，
     * 但是我们包含了这个额外的操作，以便为您提供一些
     * 额外的编程练习。迭代获取元素。返回
     * 如果 index 超出范围，则为 null。不改变 deque。
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T get(int index);

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     ** 从技术上讲，此方法不应出现在界面中，但它就在这里
     * 使测试更好。以递归方式获取一个元素。如果满足以下条件，则返回 null
     * index 超出范围。不改变 deque。
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    T getRecursive(int index);
}
