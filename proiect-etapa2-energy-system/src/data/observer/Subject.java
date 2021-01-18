package data.observer;

import actions.ProducerChanges;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Subject {
     public void attach(Observer o);
    public void dettach(Observer o);
    public void notifyUpdate(ProducerChanges changes);
}
