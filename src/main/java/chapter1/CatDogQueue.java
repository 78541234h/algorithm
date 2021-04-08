package chapter1;

import java.util.LinkedList;
import java.util.Queue;

public class CatDogQueue {
    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    public class Cat extends Pet {
        public Cat() {
            super("Cat");
        }
    }

    public class Dog extends Pet {
        public Dog() {
            super("Dog");
        }
    }

    private class PetExtension {
        private Pet pet;
        private long count;

        private PetExtension(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }
    }

    private long count;

    private Queue<PetExtension> catQ;
    private Queue<PetExtension> dogQ;

    public CatDogQueue() {
        catQ = new LinkedList<>();
        dogQ = new LinkedList<>();
        count = 0;
    }

    public void add(Pet pet) {
        PetExtension petExt = new PetExtension(pet, count++);
        if (petExt.pet.getType().equals("Cat"))
            catQ.add(petExt);
        else if (petExt.pet.getType().equals("Dog"))
            dogQ.add(petExt);
        else
            throw new RuntimeException("error, not dog or cat");
    }

    public Dog pollDog() {
        if (dogQ.isEmpty())
            throw new RuntimeException("error, queue is emtpy");
        return (Dog) dogQ.poll().pet;
    }

    public Cat pollCat() {
        if (catQ.isEmpty())
            throw new RuntimeException("error, queue is empty");
        return (Cat) catQ.poll().pet;
    }

    public Pet pollAll() {
        if (!catQ.isEmpty() && !dogQ.isEmpty()) {
            if (catQ.peek().count > dogQ.peek().count)
                return dogQ.poll().pet;
            else
                return catQ.poll().pet;
        } else if (!catQ.isEmpty()) {
            return catQ.poll().pet;
        } else if (!dogQ.isEmpty()) {
            return dogQ.poll().pet;
        } else {
            throw new RuntimeException("error, queue is empty");
        }
    }

    public boolean isEmpty() {
        return catQ.isEmpty() && dogQ.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQ.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQ.isEmpty();
    }
}
