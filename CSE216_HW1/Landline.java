import java.util.ArrayList;
import java.util.Scanner;

public class Landline extends OldLandline{
    // "is a" old landline with
    // the additional functionality that anyone calling it may choose to leave a message
    // in case the intended recipient is busy on another phone call

    // it "has a" data type(s) to store messages
    // String storedMessage = null;
    // MSG_STATUS messageStatus = null;
    enum MSG_STATUS {READ, UNREAD}

    public class Message {

        private String text;
        private MSG_STATUS msgStatus;

        public Message(String s, MSG_STATUS m) {
            text = s;
            msgStatus = m;
        }

        // getters and setters
        public String getText() {
            return text;
        }

        public MSG_STATUS getMsgStatus() {
            return msgStatus;
        }

        public void setMsgStatus(MSG_STATUS ms) {
            this.msgStatus = ms;
        }

    }

    private ArrayList<Message> storedMessages = new ArrayList<>();

    public Landline(String s, long pn) {
        super(s, pn);
    }

    // a new message left on Landline
    public void leaveMessage(String s) { // new message left on Landline is initially always UNREAD
        Message msgNew = new Message(s, MSG_STATUS.UNREAD);
        storedMessages.add(msgNew);
    }

    @Override
    public void receive(Phone from){ // isBusy(), false when not busy, currentCall = null
        if(!this.isBusy()) { // check in case
            this.setCurrentCall(from);
        } else { // this line is busy
            // have option to leave a message
            System.out.println(from.getOwner() + " is unable to call " + this.getOwner() + ". Line is currently busy.");
            System.out.print("Does " + from.getOwner() + " want to leave a message? [y/n] ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            if(choice.equals("y")) {
                String s = sc.nextLine();
                this.leaveMessage(s);
                System.out.println(from.getOwner() + " has left a message for " + this.getOwner() + ".");
            } else if(choice.equals("n")) {
                System.out.println("No message was left.");
            } else { // wrong input
                System.out.println("Incorrect choice, you need to type either \"y\" or \"n\".");
                System.out.println("No message was left.");
            }
        }
    }

    // has instance method to iterate through
    // i) all messages
    // ii) or only unread messages
    public void readMessages() {
        // iteratively prints all messages
        for(int i = 0; i < storedMessages.size(); i++) {
            System.out.println(storedMessages.get(i).getText());
            if(storedMessages.get(i).getMsgStatus() == MSG_STATUS.UNREAD) {
                storedMessages.get(i).setMsgStatus(MSG_STATUS.READ);
            }
        }
    }

    // adding a new message on a Landline must be a constant time operation on the data type used to store the messages

    // the messages must be iterable in the order in which they were received.
    // That is, if A leaves a message m1 for B,
    // then the iteration through the messages must print m1 before m2
    public void readMessages(MSG_STATUS m) {
        // takes a parameter and iteratively prints all the relevant messages
        if(m == MSG_STATUS.READ) {
            for(int i = 0; i < storedMessages.size(); i++) {
                if(storedMessages.get(i).getMsgStatus() == MSG_STATUS.READ) {
                    System.out.println(storedMessages.get(i).getText());
                } // only print messages that are enum READ
            }
        } else if(m == MSG_STATUS.UNREAD) {
            for(int i = 0; i < storedMessages.size(); i++) {
                if(storedMessages.get(i).getMsgStatus() == MSG_STATUS.UNREAD) {
                    System.out.println(storedMessages.get(i).getText());
                    storedMessages.get(i).setMsgStatus(MSG_STATUS.READ);
                } // only print messages that are enum UNREAD
            }
        }
    }

}
