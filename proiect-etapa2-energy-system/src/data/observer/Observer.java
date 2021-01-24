package data.observer;

import actions.ProducerChanges;

/**
 * interfata pentru un observator
 */
public interface Observer {
    /**
     * observatorul este anuntat de noile schimbari
     */
    void update(ProducerChanges changes);
}
