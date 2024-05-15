import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    private class Node {
        E val;
        Node next;

        Node(E val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node first;

    public LinkedList() {
        first = null;
    }

    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        }
        else {
            String s = "[";
            Node n = first;
            while (n.next != null) {
                s += n.val + ", ";
                n = n.next;
            }
            assert n != null && n.next == null;
            s += n.val + "]";
            return s;
        }
    }

    @Override
    public void add(E item) {
        Node n = new Node(item);
        if (first == null) {
            first = n;
        }
        else {
            assert first != null;
            Node tail = first;
            while (tail.next != null) {
                tail = tail.next;
            }
            assert tail != null && tail.next == null;
            tail.next = n;
        }
    }

    @Override
    public void clear() {
        first = null;
    }


    @Override
    public boolean contains(E item) {
        Node n = first;
        while (n != null) {
            if (n.val.equals(item)) {
                return true;
            }
            n = n.next;
        }
        assert n == null;
        return false;
    }

    @Override
    public E get(int i) {
        Node n = first;
        while(i > 0) {
            n = n.next;
            i--;
        }
        return n.val;
    }

    @Override
    public int indexOf(E item) {
        Node n = first;
        int i = 0;
        while(!n.val.equals(item)) {
            n = n.next;
            ++i;
        }
        if (n.val.equals(item)) {
            return i;
        }
        else return -1;
    }

    @Override
    public void set(int i, E item) {
        Node n = first;
        while (i>0) {
            n = n.next;
            --i;
        }
        n.val = item;
    }
    @Override
    public void removeAt(int i) {
        if (i == 0) {
            first = first.next;
        }
        else {
            Node p = first;
            Node n = first.next;
            int i1 = 1;
            while (i1 != i) {
                p = p.next;
                n = n.next;
                ++i1;
            }
            p.next = n.next;
        }
    }

    @Override
    public int size() {
        int s = 0;
        Node n = first;
        while (n != null) {
            ++s;
            n = n.next;
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList that = (LinkedList) o;
        Node thisNode = this.first;
        Node thatNode = that.first;
        while (thisNode != null && thatNode != null) {
            if (!thisNode.val.equals(thatNode.val)) {
                return false;
            }
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        if (thisNode == thatNode) return true;
        else                      return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node n = first;

            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public E next() {
                if(hasNext()){
                    E data = n.val;
                    n = n.next;
                    return data;
                }
                return null;
            }

        };
    }
}