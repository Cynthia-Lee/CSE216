import java.util.ArrayList;

public class SmartPhone extends Landline implements Computer{
    // adds more functionality to Landline
    // obeys the behavior defined by the Computer interface type

    private State smartPhoneState = State.OFF;
    // SmartPhone is able to store up to 5 games, accessing any particular game must be
    //  a constant-time operation
    // arraylist
    ArrayList<String> storedGames = new ArrayList<>(); // max it can have is 5
    private int screenSize;
    private int ram;
    private int processorSpeed;

    // owner, phone number,
    // screen size, ram, processor speed, enum State
    public SmartPhone(String s, long pn, int screen, int r, int ps) {
        super(s, pn);
        screenSize = screen;
        ram = r;
        processorSpeed = ps;
        // smartPhoneState = State.OFF;
    }

    public SmartPhone(String s, long pn, int screen, int r, int ps, State st) {
        super(s, pn);
        screenSize = screen;
        ram = r;
        processorSpeed = ps;
        smartPhoneState = st;
    }

    public int getScreenSize(){
        return screenSize;
    }

    public int getRAM(){
        return ram;
    }

    public int getProcessorSpeeed(){
        return processorSpeed;
    }

    public State getState(){
        return smartPhoneState;
    }

    // If the parameter is not one of "on", "ON", "off", or "OFF", the setState(String to)
    // method must throw exception
    public void setState(String to) {
        if(to.equalsIgnoreCase("on")) {
            smartPhoneState = State.ON;
        } else if(to.equalsIgnoreCase("off")) {
            // if phone turns off and is in a call, ends the call (cannot call)
            if(this.getCurrentCall() != null) {
                System.out.print(this.getOwner() + "'s smart phone is turned off, so ");
                this.end();
            }
            smartPhoneState = State.OFF;
        } else {
            throw new IllegalArgumentException("State entered is not valid.");
        }
    }

    public void installGame(String gameName){
        // if a game is already installed on the smart phone, calling this method
        // should realize this in constant-time and not reinstall the game
        // this method should silently ignore the reinstallation
        // you do not have to print anything if the game is already installed
        // can use an arraylist because it is always length 5
        if(storedGames.size() == 5) {
            System.out.println("Cannot install more than 5 games on " + this.getOwner() + "'s smart phone.");
        } else if(smartPhoneState == State.OFF) { // cannot install if phone is off
            System.out.println("Cannot install game on " + this.getOwner() + "'s smart phone. Smart phone is off.");
        } else { // 0 - 5 games
            if (storedGames.contains(gameName)) {

            } else {
                System.out.println("Installing " + gameName + " on " + this.getOwner() + "'s smart phone.");
                storedGames.add(gameName);
            }
        }
    }

    public boolean hasGame(String gameName){
        if(storedGames.contains(gameName)) {
            return true;
        } else {
            return false;
        }
    }

    public void playGame(String gameName){
        if(smartPhoneState == State.OFF) {
            System.out.println("Cannot play game on " + this.getOwner() + "'s smart phone. Smart phone is off.");
        } else {
            if(this.hasGame(gameName)) {
                System.out.println(this.getOwner() + " is playing " + gameName + ".");
            } else {
                System.out.println("Cannot play " + gameName + " on " + this.getOwner() + "'s smart phone. Install it first.");
            }
        }
    }

    // take account if phone is off
    @Override
    public void call(Phone phone) {
        if(smartPhoneState == State.OFF) {
            // this phone should be busy then
            System.out.println(this.getOwner() + "'s smart phone is off. Unable to make call.");
        }
        super.call(phone);

    }

    @Override
    public void receive(Phone from) {
        if(smartPhoneState == State.OFF) {
            // this phone should be busy then
            System.out.println(this.getOwner() + "'s smart phone is off. Unable to receive call.");
        }

        super.receive(from);
    }

    // busy now also includes if phone is off
    @Override
    public boolean isBusy(){
        boolean busy = true;
        if (smartPhoneState == State.OFF) { // phone is off, should be busy, currentCall also should be set to null if On to Off
            // if phone is off, it is busy
            // cannot be in a call, ends the call
            return true; // is busy, will always be busy when off
        } else { // phone is on, operates normally
            if (this.getCurrentCall() == null) {
                busy = false;
            }
        }
        return busy;
    }

    @Override
    public void readMessages() {
        if(smartPhoneState == State.OFF) {
            System.out.println(this.getOwner() + "'s smart phone is off. Unable to read messages.");
        } else {
            super.readMessages();
        }
    }

    @Override
    public void readMessages(MSG_STATUS m) {
        if(smartPhoneState == State.OFF) {
            System.out.println(this.getOwner() + "'s smart phone is off. Unable to read messages.");
        } else {
            super.readMessages(m);
        }
    }

    @Override
    public void end() { // this person is ending the call
        if (smartPhoneState == State.OFF){

        } else { // phone is on
            super.end();
        }
    }
}