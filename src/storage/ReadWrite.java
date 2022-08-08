package storage;

import java.util.ArrayList;
import java.util.List;

public interface ReadWrite<E> {
    ArrayList<E> readData(String pathname);
    void writeData(List<E> e, String pathname);
}
