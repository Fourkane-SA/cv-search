package fr.univ_lyon1.info.m1.cv_search.controller;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento m) {
        mementos.add(m);
    }

    /**
     * Delete the last Memento and return the new last memento
     * @version 1.0
     */
    public Memento getMemento() {
	int lastIndex = mementos.size() - 1;
	if (lastIndex > 0) {
	    mementos.remove(lastIndex);
	    lastIndex--;
	    return mementos.get(lastIndex);
	}
        return null;
    }
}