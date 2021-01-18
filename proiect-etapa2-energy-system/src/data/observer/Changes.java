package data.observer;

import actions.ProducerChanges;
import data.Distributors;

import java.util.ArrayList;
import java.util.Observable;

public class Changes implements Subject {
    ArrayList<ProducerChanges> changes = new ArrayList<>();
    ArrayList<Observer> observers = new ArrayList<>();
    @Override
    public void attach(Observer o) {
            observers.add(o);
    }

    @Override
    public void dettach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyUpdate(ProducerChanges change) {
        changes.add(change);
        for(Observer o : observers){
            o.update(change);
        }
    }
    //ArrayList<Distributors> distributors = new ArrayList<>();


   /* @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }*/
}
