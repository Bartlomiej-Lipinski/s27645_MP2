package Ekstensja;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ObjectPlus implements Serializable {
    private static Map<Class<? extends ObjectPlus>, List<ObjectPlus>> allExtents = new HashMap<>();

    public ObjectPlus(){
        Class<? extends ObjectPlus> theClass = this.getClass();
        allExtents.computeIfAbsent(theClass,k -> new ArrayList<>()).add(this);
    }
    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }
    public static void readExtents(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        allExtents = (HashMap) stream.readObject();
    }
    public static <T> Iterable<T> getExtent(Class<T> type) throws
            ClassNotFoundException{
        if(allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }
        throw new ClassNotFoundException(
                String.format("%s. Stored extents: %s",
                        type.toString(),
                        allExtents.keySet()));
    }
    public static void showExtent(Class theClass) throws Exception {
        List<ObjectPlus> extent = null;
        if(allExtents.containsKey(theClass)) {
            // Extent of thisclassalreadyexist
            extent = allExtents.get(theClass);
        }
        else{
            throw new Exception("Unknownclass " + theClass);
        }
        System.out.println("Extentof the class: " + theClass.getSimpleName());
        for(Object obj: extent) {
            System.out.println(obj);
        }
    }
}
