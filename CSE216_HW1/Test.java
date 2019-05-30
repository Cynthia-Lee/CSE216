import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Landline jack = new Landline("Jack", 6312028899L);
        Landline john = new Landline("John", 3028113434L);
        OldLandline julie = new OldLandline("Julie", 9018772324L);

        // further constructor details
        SmartPhone paul = new SmartPhone("Paul", 2024449019L,4, 3, 3, Computer.State.ON);
        // will make paul's SmartPhone "on", default is "off"
        SmartPhone bob = new SmartPhone("Bob", 3314959034L, 5, 4, 6); // Bob's phone is off

        // testing if phone number is not valid (not 10 numbers)
        System.out.println("---Testing invalid phone numbers---");
        try {
            System.out.print("9 digit phone number: ");
            Landline casey = new Landline("Casey", 999999999L); // 9 digits
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            System.out.print("11 digit phone number: ");
            OldLandline henry = new OldLandline("Henry", 12345678911L); // 11 digits
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            System.out.print("Phone number is 0011111111L: ");
            OldLandline martha = new OldLandline("Martha", 0011111111L); // phone number cannot start with 0
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            System.out.print("Phone number is -1113339999L: ");
            OldLandline martha = new OldLandline("Martha", -1113339999L); // phone number cannot be negative
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n---Testing sample input---"); // testing sample input
        jack.call(john); // Jack is on the phone with John.
        julie.call(john); // Julie is unable to call John. Line is currently busy.
        // Ask Julie to leave a message
        john.end();
        julie.call(john); // Julie is on the phone with John.
        john.call(jack); // Since John is in a call already, print nothing (Landline and Landline call)
        julie.end();

        paul.installGame("fortnite");
        paul.installGame("angry birds");
        paul.installGame("fortnite");
        paul.playGame("minecraft");
        paul.playGame("fortnite");

        System.out.println("\n---Testing OldLandline---");
        OldLandline gary = new OldLandline("Gary", 4580293582L);
        // OldLandline calling OldLandline
        gary.call(julie);
        jack.call(julie);
        julie.end();
        john.call(gary);
        john.end();
        paul.call(gary);
        gary.end();

        System.out.println("\n---Testing Landline---");
        // was asked earlier in sample, John will have message if Julie left one
        Landline andy = new Landline("Andy", 8639682001L);
        john.call(paul);
        john.end();
        john.call(jack);
        andy.call(jack); // Jack has message from Andy
        andy.call(john); // John has message from Andy
        jack.end();
        andy.call(john);
        jack.call(john); // John has message from Jack
        paul.call(john);
        jack.end();
        john.end(); // John not in a call, and is not busy so it prints nothing
        System.out.print("\nPrinting out John's read messages: \n"); // should be nothing
        john.readMessages(Landline.MSG_STATUS.READ);
        System.out.print("\nPrinting out all John's unread messages: \n");
        john.readMessages(Landline.MSG_STATUS.UNREAD);
        System.out.print("\nPrinting out all John's read messages: \n");
        john.readMessages(Landline.MSG_STATUS.READ);
        System.out.print("\nPrinting out all Jacks's messages: \n");
        jack.readMessages();
        System.out.println();
        jack.call(paul);
        System.out.println("\nPrinting out all Jack's messages while in a call:");
        jack.readMessages(); // in my implementation, I allow Landlines to read messages while in a call

        System.out.println("\n---Testing SmartPhone---");
        // Paul's phone
        // testing ON and OFF state
        // when setting the state of SmartPhone, will not print anything as well

        // test installing games
        paul.installGame("fruit ninja");
        paul.installGame("candy crush");
        paul.installGame("minecraft");
        paul.installGame("i spy");

        // paul.setState("hello"); // throws IllegalArgumentException

        System.out.println("--Testing SmartPhone's ON and OFF states. " + paul.getOwner() + "'s phone is " + paul.getState() + ". And " + bob.getOwner() + "'s phone is " + bob.getState());
        // test playing game
        paul.playGame("i spy");
        paul.playGame("minecraft");
        paul.call(jack); // allows SmartPhone to play games and have calls at the same time
        paul.end();
        paul.call(bob); // Bob's phone is off, paul can leave a message
        // Bob's phone
        bob.call(paul);
        bob.installGame("subway surfer");
        System.out.println("\nPrinting out all Bob's messages when phone is off:");
        bob.readMessages();
        System.out.println("I will set Bob's phone to on.");
        bob.setState("on"); // can make a call
        bob.call(paul);
        System.out.println("\nPrinting out all Bob's messages while in a call:");
        bob.readMessages(); // in my implementation, I allow SmartPhones to read messages, install games and play games while in a call
        System.out.println();
        System.out.println("I will set Paul's phone to off.");
        paul.setState("off");
        paul.playGame("minecraft");
        bob.end(); // prints nothing, Bob wasn't in a call
        paul.end(); // prints nothing, Bob wasn't in a call
        paul.end(); // prints nothing, Bob wasn't in a call
        System.out.println("Testing if " + bob.getOwner() + "'s SmartPhone can be turned off twice or turned on twice: ");
        // if setState is called twice, it shouldn't have errors, and state shouldn't change
        bob.setState("off");
        bob.setState("off"); // should be off, no errors
        System.out.println(bob.getState());
        bob.setState("on");
        bob.setState("on"); // should be on, no errors
        System.out.println(bob.getState());
        // read messages on Bob's phone
        System.out.print("\nPrinting out all Bob's messages: \n");
        bob.readMessages();


        System.out.println("\n---Testing Laptop---");
        Laptop cynthia = new Laptop("Cynthia's Alienware laptop", "Alienware", 20, 900, 750);
        // laptop is off
        System.out.println(cynthia.getHostname());
        cynthia.installGame("Divinity 2");
        System.out.println("I will set Cynthia's laptop to on.");
        cynthia.setState("on");
        cynthia.playGame("Overcooked");
        cynthia.installGame("Divinity 2");
        cynthia.installGame("Overcooked");
        cynthia.installGame("Stardew Valley");
        cynthia.installGame("Overwatch");
        cynthia.installGame("Starbound");
        cynthia.installGame("Castle Crashers");
        cynthia.installGame("Battleblock Theater");
        cynthia.installGame("Life is Strange");
        cynthia.installGame("Don't Starve Together");
        cynthia.installGame("Katamari");
        cynthia.installGame("Portal 2");
        cynthia.installGame("Risk of Rain");
        cynthia.installGame("Terraria");
        cynthia.installGame("Uraveled Two");
        cynthia.installGame("Baba is You");
        cynthia.installGame("Minecraft"); // 16th
        cynthia.installGame("Undertale"); // 17th
        cynthia.playGame("Deltarune");
        cynthia.playGame("Divinity 2");
        System.out.println("I will set Cynthia's laptop to off.");
        cynthia.setState("off");
        cynthia.playGame("Katamari");
        System.out.println("Testing if " + cynthia.getHostname() + " can be turned off twice or turned on twice: ");
        // if setState is called twice, it shouldn't have errors, and state shouldn't change
        cynthia.setState("off");
        cynthia.setState("off"); // should be off, no errors
        System.out.println(cynthia.getState());
        cynthia.setState("on");
        cynthia.setState("on"); // should be on, no errors
        System.out.println(cynthia.getState());

        System.out.println("\n---Testing Ordering----");
        // the Java Collections library for sorting (i.e., java.util.Collections#sort method)
        // making arrays
        ArrayList<Phone> listPhones = new ArrayList<>(); // array of phones
        // jack, julie, paul, gary, andy, bob, john
        // LL, OL, SP, OL, OL, SP, LL
        ArrayList<Computer> listComputers = new ArrayList<>(); // array of computers
        // cynthia, bob, andrea, mike, paul
        // L, SP, L, L, SP
        Laptop andrea = new Laptop("Andreas's Lenovo laptop", "Lenovo", 14, 500, 350);
        Laptop mike = new Laptop("Mike's Acer laptop", "Acer", 18, 700, 800);

        listPhones.add(jack);
        listPhones.add(julie);
        listPhones.add(paul);
        listPhones.add(gary);
        listPhones.add(andy);
        listPhones.add(bob);
        listPhones.add(john);
        System.out.println("The length of phone the array is now " + listPhones.size());

        System.out.print("Ordering phones by phone numbers: ");
        Collections.sort(listPhones, new Orderings.PhoneNumberComparator());
        for (int i = 0; i < listPhones.size(); i++) {
            if(i == listPhones.size() - 1) {
                System.out.print(Long.toString(listPhones.get(i).number().getNumber()));
            } else {
                System.out.print(Long.toString(listPhones.get(i).number().getNumber()) + ",");
            }
        }

        System.out.print("\nOrdering phones by owner name: ");
        Collections.sort(listPhones, new Orderings.OwnerNameComparator());
        for (int i = 0; i < listPhones.size(); i++) {
            if(i == listPhones.size() - 1) {
                System.out.print(listPhones.get(i).getOwner());
            } else {
                System.out.print(listPhones.get(i).getOwner() + ", ");
            }
        }

        listComputers.add(cynthia);
        listComputers.add(bob);
        listComputers.add(andrea);
        listComputers.add(mike);
        listComputers.add(paul);
        // listComputers.add(john); // cannot add Landline because not type Computer
        System.out.println("\nThe length of computer the array is now " + listComputers.size());


        System.out.print("Ordering computers by screen size: "); // will print out every object in the computer array
        Collections.sort(listComputers, new Orderings.ScreenSizeComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                System.out.print(listComputers.get(i).getScreenSize());
            } else {
                System.out.print(listComputers.get(i).getScreenSize() + ", ");
            }
        }

        System.out.print("\nOrdering computers by brand: "); // will only print out the laptops in the computer array
        Collections.sort(listComputers, new Orderings.BrandNameComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                if(listComputers.get(i) instanceof Laptop) {
                    System.out.print(((Laptop) listComputers.get(i)).getBrand());
                }
            } else {
                if(listComputers.get(i) instanceof Laptop) {
                    System.out.print(((Laptop) listComputers.get(i)).getBrand() + ", ");
                }
            }
        }

        System.out.print("\nOrdering computers by amount of RAM: "); // will print out every object in the computer array
        Collections.sort(listComputers, new Orderings.RAMComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                System.out.print(listComputers.get(i).getRAM());
            } else {
                System.out.print(listComputers.get(i).getRAM() + ", ");
            }
        }

        System.out.println("\n\n---Testing when real and apparent types are different---");
        // apparent type = methods that are available/you can use
        // actual type = method version that will execute

        System.out.println("--Testing Phones--");
        // Phones
        // testing call(), end(), and readMessages()
        // for SmartPhones, testing setState(...), installGame(...), playGame(...), readMessages()
        OldLandline a = new Landline("A", 3451234444L); // OldLandline x = new Landline(...);
        Phone b = new Landline("B", 1984457607L); // Phone x = new Landline(...);
        Phone c = new OldLandline("C", 7789093421L); // Phone x = new OldLandline(...);
        Phone d = new SmartPhone("D", 4993569989L, 5, 16, 9); // Phone x = new SmartPhone(...);
        // other cases
        Landline e = new SmartPhone("E", 2112314672L, 7, 23, 19);// Landline x = new SmartPhone(...);
        OldLandline f = new SmartPhone("F", 7964762974L, 5, 15, 20); // OldLandline x = new SmartPhone(...);

        // Landline x = new Phone(); // doesn't work because Phone is an abstract class

        // This won't work because the narrower class is on the left side
        // Landline x = new OldLandline("X", 1000000001L); // incompatible types

        // Similarly, what happens to the ordering and the various methods like playGame(), etc. when
        // the real and apparent types of the computers are different?

        // testing a
        // "a" actual type = Landline, apparent type = OldLandline
        a.call(john); // jack is a Landline, john is a Landline
        jack.call(a); // "a" acts as a Landline because jack can leave a message
        // Landlines have messages, OldLandlines do not
        // "a" uses OldLandline's methods, but Landline's version of the method
        // a.call() uses OldLandline's method, it is not overridden
        // a.end() uses OldLandline's method, it is not overridden
        a.end();
        // a.readMessages(); // doesn't work because readMessages() is not defined in OldLandline, the apparent type
        // needs to be casted as a Landline
        System.out.println("\nPrinting out all A's messages:");
        ((Landline) a).readMessages();
        System.out.println();

        // testing b
        // "b" actual type = Landline, apparent type = Phone
        b.call(john);
        jack.call(b); // "b" acts as a Landline because jack can leave a message
        // "b" uses Phone's methods, but Landline's version of the method
        b.end();
        System.out.println("\nPrinting out all B's messages:");
        ((Landline) b).readMessages();
        System.out.println();

        // testing c
        // "c" actual type = OldLandline, apparent type = Phone
        c.call(john);
        jack.call(c); // "c" executes OldLandline's method version
        c.end(); // executes OldLandline's .end(), not Phone
        // c.readMessages(); // c cannot call this method

        // testing d
        // "d" actual type = SmartPhone, apparent type = Phone
        d.call(john);
        jack.call(d); // should be able to leave a message
        d.end();
        // d.setState("on"); // error, cannot execute because Phone doesn't have setState method
        // have to cast it
        ((SmartPhone) d).setState("on");
        ((SmartPhone) d).installGame("pokemon yellow");
        // d.insallGame("game game"); // error
        ((SmartPhone) d).playGame("pokemon yellow");
        ((SmartPhone) d).readMessages();

        // testing e
        // "e" actual type = SmartPhone, apparent type = Landline
        e.call(john);
        jack.call(e);
        e.end();
        // have to cast
        ((SmartPhone) e).setState("on");
        ((SmartPhone) e).installGame("brick breaker");
        ((SmartPhone) e).playGame("farm game");
        ((SmartPhone) e).playGame("brick breaker");
        e.call(john);
        // in my implementation, SmartPhones can call, play games and check messages at the same time
        System.out.println("\nPrinting out all E's messages:");
        e.readMessages();
        System.out.println("\nPrinting out all E's messages:");
        ((SmartPhone) e).readMessages(); // SmartPhone cast isn't needed, but still works
        System.out.println();
        e.end();

        // testing f
        // "f" actual type = SmartPhone, apparent type = OldLandline
        f.call(john);
        jack.call(e);
        f.end();
        // have to cast
        ((SmartPhone) f).setState("on");
        ((SmartPhone) f).installGame("collect cats");
        ((SmartPhone) f).playGame("pong");
        ((SmartPhone) f).playGame("collect cats");
        f.call(john);
        // f.readMessages(); // error, doesn't work, OldLandline does not have this method
        System.out.println("\nPrinting out all F's messages:");
        ((SmartPhone) e).readMessages();
        System.out.println();
        f.end();

        // Computers
        // testing setState(...), installGame(...), playGame(...)
        // for SmartPhones, call(), end(), readMessages()
        Computer g = new Laptop("G's Dell laptop", "Dell", 24, 770, 538);
        Computer h = new SmartPhone("H", 3331231234L, 8, 47, 55);

        // testing g
        // "g" actual type = Laptop, apparent type = Computer
        g.setState("on");
        g.installGame("Peggle"); // calling Laptop's method version
        g.playGame("Putt Putt goes to the moon");
        g.playGame("Peggle");
        ((Laptop) g).getHostname(); // needs to be casted, getHostname() is only defined in the Laptop class

        // testing h
        // "h" actual type = SmartPhone, apparent type = Computer
        h.setState("on");
        // have to case to a SmartPhone to use the following methods for Phone
        ((SmartPhone) h).call(john);
        jack.call(((SmartPhone) h));
        ((SmartPhone) h).end();
        ((SmartPhone) h).readMessages();
        // computer methods
        h.installGame("word play");
        h.playGame("word play");
        h.playGame("happy farm");

        System.out.println("\n--Testing Ordering--");
        // Phones
        listPhones.add(a);
        listPhones.add(b);
        listPhones.add(c);
        listPhones.add(d);
        listPhones.add(e);
        listPhones.add(f);
        // listPhones.add(cynthia); // cannot work for computer objects
        System.out.println("The length of phone the array is now " + listPhones.size());

        System.out.print("Ordering phones by phone numbers: ");
        Collections.sort(listPhones, new Orderings.PhoneNumberComparator());
        for (int i = 0; i < listPhones.size(); i++) {
            if(i == listPhones.size() - 1) {
                System.out.print(Long.toString(listPhones.get(i).number().getNumber()));
            } else {
                System.out.print(Long.toString(listPhones.get(i).number().getNumber()) + ",");
            }
        }

        System.out.print("\nOrdering phones by owner name: ");
        Collections.sort(listPhones, new Orderings.OwnerNameComparator());
        for (int i = 0; i < listPhones.size(); i++) {
            if(i == listPhones.size() - 1) {
                System.out.print(listPhones.get(i).getOwner());
            } else {
                System.out.print(listPhones.get(i).getOwner() + ", ");
            }
        }

        // Computers
        listComputers.add(g);
        listComputers.add(h);
        // listComputers.add(a); // cannot work for phone objects
        System.out.println("\nThe length of the computer array is now " + listComputers.size());

        System.out.print("Ordering computers by screen size: "); // will print all computer objects because all computers have a screen size
        Collections.sort(listComputers, new Orderings.ScreenSizeComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                System.out.print(listComputers.get(i).getScreenSize());
            } else {
                System.out.print(listComputers.get(i).getScreenSize() + ", ");
            }
        }

        System.out.print("\nOrdering computers by brand: "); // will print only laptop computer objects because only laptops have a brand, not smart phones
        Collections.sort(listComputers, new Orderings.BrandNameComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                if(listComputers.get(i) instanceof Laptop) {
                    System.out.print(((Laptop) listComputers.get(i)).getBrand());
                }
            } else {
                if(listComputers.get(i) instanceof Laptop) {
                    System.out.print(((Laptop) listComputers.get(i)).getBrand() + ", ");
                }
            }
        }

        System.out.print("\nOrdering computers by amount of RAM: "); // will print all computer objects because all computers have ram
        Collections.sort(listComputers, new Orderings.RAMComparator());
        for (int i = 0; i < listComputers.size(); i++) {
            if(i == listComputers.size() - 1) {
                System.out.print(listComputers.get(i).getRAM());
            } else {
                System.out.print(listComputers.get(i).getRAM() + ", ");
            }
        }
    }

}