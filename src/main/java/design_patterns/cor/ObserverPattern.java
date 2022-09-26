package design_patterns.cor;

import java.util.ArrayList;
import java.util.List;


//  https://en.wikipedia.org/wiki/Observer_pattern

public class ObserverPattern {
    public static void main(String[] args) {
        User1 user1 = new User1();
        User2 user2 = new User2();

        Keyboard keyboard = new Keyboard();
        keyboard.attachObserver(user1);
        keyboard.attachObserver(user2);

        keyboard.typeCharacter('x');
    }
}

interface Observer<D> {
    public void update(D data);
}

interface Observable<T extends Observer<D>, D> {
    public void notify(D data);
    public void attachObserver(T obj);
    public void detachObserver(T obj);
}

class Keyboard implements Observable<Observer<Character>, Character> {

    List<Observer<Character>> observers = new ArrayList<>();

    @Override

    public void notify(Character data) {
        observers.forEach(characterObserver -> characterObserver.update(data));
    }

    @Override
    public void attachObserver(Observer<Character> obj) {
        observers.add(obj);
    }

    @Override
    public void detachObserver(Observer<Character> obj) {
        observers.remove(obj);
    }

    public void typeCharacter(Character c) {
        // do something with c and then
        notify(c);
    }
}


class User1 implements Observer<Character> {

    @Override
    public void update(Character data) {
        System.out.printf("here is the update from user1: %s \n", data);
    }
}

class User2 implements Observer<Character> {

    @Override
    public void update(Character data) {
        System.out.printf("here is the update from user2: %s \n", data);
    }
}