package proyecto2_edd.dataStructures.IDataStructures;

public interface IList {
    public Object insertBegin(Object element);
    public Object insertFinal(Object element);
    public Object insertInIndex(Object element, int index);
    public Object deleteBegin();
    public Object deleteFinal();
    public Object deleteInIndex(int index);
    public void deleteAll();
    public boolean isEmpty();
}

