package design_patterns.cor;

// https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
// https://www.digitalocean.com/community/tutorials/chain-of-responsibility-design-pattern-in-java

// The chain-of-responsibility pattern is structurally nearly identical to the decorator pattern,
// the difference being that for the decorator, all classes handle the request, while for the chain of responsibility,
// exactly one of the classes in the chain handles the request. This is a strict definition of the Responsibility
// concept in the GoF book. However, many implementations (such as loggers below, or UI event handling, or servlet filters in Java, etc.) allow several elements in the chain to take responsibility.

public class COR {
    public static void main(String[] args) {
        var dispenser = new ATMDispenserHandler().dispenserHandler;
        System.out.printf("Balance amount that can't be dispensed: %s", dispenser.handleAmount(5312));

        System.out.println();
    }
}

interface DispenserHandler {
    public abstract int handleAmount(int amount);

}

class ATMDispenserHandler {
    DispenserHandler dispenserHandler;
    ATMDispenserHandler() {
        init();
    }

    public static int handleNext(int amount, DispenserHandler next) {
        if(next != null) return next.handleAmount(amount);
        return amount;
    }

    private void init() {
        dispenserHandler = new Dispenser100(new Dispenser50(new Dispenser10(null)));
    }

    static class Dispenser50 implements DispenserHandler {
        DispenserHandler next = null;

        Dispenser50(DispenserHandler next) {
            this.next = next;
        }

        @Override
        public int handleAmount(int amount) {
            int count = 0;
            while(amount >= 50) {
                amount -= 50;
                count += 1;
            }
            System.out.printf("50 Dispenser handled %d notes%n", count);
            return handleNext(amount, next);
        }
    }

    static class Dispenser10 implements DispenserHandler {
        DispenserHandler next = null;

        Dispenser10(DispenserHandler next) {
            this.next = next;
        }

        @Override
        public int handleAmount(int amount) {
            int count = 0;
            while(amount >= 10) {
                amount -= 10;
                count += 1;
            }
            System.out.printf("10 Dispenser handled %d notes%n", count);
            return handleNext(amount, next);
        }
    }

    static class Dispenser100 implements DispenserHandler {
        DispenserHandler next = null;

        Dispenser100(DispenserHandler next) {
            this.next = next;
        }

        @Override
        public int handleAmount(int amount) {
            int count = 0;
            while(amount >= 100) {
                amount -= 100;
                count += 1;
            }
            System.out.printf("100 Dispenser handled %d notes%n", count);
            return handleNext(amount, next);
        }
    }
}