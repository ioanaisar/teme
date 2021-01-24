package data.observer;

import actions.ProducerChanges;

import java.util.ArrayList;

/**
 * clasa ce reprezinta obiectil observabil
 */
public final class Changes implements Subject {
    /**
     * lista cu schimbarile ptroduse
     */
    private ArrayList<ProducerChanges> changes = new ArrayList<>();

    /**
     * lista cu observatorii
     */
    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * adauga un observator
     */
    @Override
    public void attach(final Observer o) {
        observers.add(o);
    }

    /**
     * sterge un observator
     */
    @Override
    public void dettach(final Observer o) {
        observers.remove(o);
    }

    /**
     * se anunta observatorii despre schimbarile produse
     * se adauga noua schimbare in lista
     */
    @Override
    public void notifyUpdate(final ProducerChanges change) {
        changes.add(change);
        for (Observer o : observers) {
            o.update(change);
        }
    }

    public ArrayList<ProducerChanges> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<ProducerChanges> changes) {
        this.changes = changes;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }
}
