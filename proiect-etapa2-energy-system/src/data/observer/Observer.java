package data.observer;

import actions.ProducerChanges;

import java.util.ArrayList;

public interface Observer {
    public void update(ProducerChanges changes);
}
