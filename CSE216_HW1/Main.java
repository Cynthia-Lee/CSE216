public class Main {

    public static void main(String[] args) {
        /*
        OldLandline julie = new OldLandline("Julie",9018772324L);
        OldLandline test = new OldLandline("Test",1000000000L);
        OldLandline ann = new OldLandline("Ann", 9999999999L);

        julie.call(test); //test should receive
        julie.call(test);
        test.call(ann);
        test.call(julie);
        julie.end();
        test.call(julie);
        ann.call(julie);
        julie.end();
        ann.call(julie);
        */

        Landline jack = new Landline("Jack", 6312028899L);
        Landline john = new Landline("John", 3028113434L);
        OldLandline julie = new OldLandline("Julie", 9018772324L);
        /*
        jack.call(john);
        julie.call(john);
        john.end();
        julie.call(john); // julie is on the phone with john
        john.call(jack); // Landline calling Landline while in a call already, print nothing
        julie.end();

        // test Landline call OldLandline

        // test message printing out
        System.out.println("---");
        john.call(julie);
        jack.call(john);
        john.end();

        // test the unread and read messages
        System.out.println("---");
        System.out.println("Read READ messages test:");
        john.readMessages(Landline.MSG_STATUS.READ);
        System.out.println("Read UNREAD messages test:");
        john.readMessages(Landline.MSG_STATUS.UNREAD);
        System.out.println("Now the unread messages are read.");
        System.out.println("Reading all messages");
        john.readMessages();
        System.out.println("Read READ messages test:");
        john.readMessages(Landline.MSG_STATUS.READ);
        */

        // testing SmartPhone
        System.out.println("---");
        // SmartPhone paul = new SmartPhone("Paul", 2024449019, ...);
        // intall fornite, install angry birds, cannot play minecraft, play fortnite
        // screen size, ram, processor speed, enum State
        SmartPhone paul = new SmartPhone("Paul", 2024449019, 5, 7, 4, Computer.State.ON);
        paul.installGame("fortnite");
        paul.installGame("angry birds");
        paul.installGame("fortnite"); // prints nothing
        paul.playGame("minecraft");
        paul.playGame("fortnite");
        /*
        System.out.println("---");
        paul.playGame("angry birds");
        paul.setState("off");
        paul.call(john);
        john.call(paul);
        System.out.println("-");
        john.call(julie); // test if john is not in a call
        john.end();
        julie.call(paul);

        System.out.println("Read messages test:");
        paul.readMessages(Landline.MSG_STATUS.UNREAD);
        System.out.println("test phone turned on");
        paul.setState("on");
        paul.readMessages(Landline.MSG_STATUS.READ);
        System.out.println("-");
        paul.readMessages(Landline.MSG_STATUS.UNREAD);
        */
        // need to test if phone is on and in a call, then turn off and what happens
        System.out.println("---");
        julie.call(paul);
        john.call(paul);
        jack.call(julie);
        julie.end();
        paul.call(john); // in call
        paul.setState("off"); // paul ends call
        paul.call(julie);
        jack.call(john); // jack should be able to make call
        john.call(julie); // prints nothing, cause john is in a call already
        paul.installGame("jump");
        paul.playGame("angry birds");

    }

}
