package data.observer;

import actions.ProducerChanges;

/**
 * interfata pentru Observable
 */
public interface Subject {

    /**
     * adauga un observator
     */
    void attach(Observer o);

    /**
     * elimina un observator
     */
    void dettach(Observer o);

    /**
     * notifica observatorii despre schimbarile
     * producatorilor
     */
    void notifyUpdate(ProducerChanges changes);
}
