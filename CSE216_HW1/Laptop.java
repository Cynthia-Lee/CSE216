import java.util.ArrayList;

public class Laptop implements Computer {
    // does not have the functionality of any Phone
    // has two additional variables

    private String brand;
    private String hostname; // incorporates the brand name, the laptop owner's name and the type of computer
    // Ex. John is owner of Dell laptop, hostname = John's Dell laptop
    private State laptopState = State.OFF;
    private int screenSize;
    private int ram;
    private int processorSpeed;
    ArrayList<String> storedGames = new ArrayList<>();
    // can have as many games progammer specifies, max 20?

    // hostname, brand, screen size, ram, processor speed, enum State
    public Laptop (String host, String brnd, int screen, int r, int ps) {
        hostname = host;
        brand = brnd;
        screenSize = screen;
        ram = r;
        processorSpeed = ps;
    }

    public Laptop (String host, String brnd, int screen, int r, int ps, State st) {
        hostname = host;
        brand = brnd;
        screenSize = screen;
        ram = r;
        processorSpeed = ps;
        laptopState = st;
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
        return laptopState;
    }

    // getters for host and brand
    public String getHostname(){
        return hostname;
    }

    public String getBrand(){
        return brand;
    }

    // exactly the same as SmartPhone, with same exception handling
    public void setState(String to){
        // If the parameter is not one of "on", "ON", "off", or "OFF", the setState(String to)
        // method must throw exception
        if(to.equalsIgnoreCase("on")) {
            laptopState = State.ON;
        } else if(to.equalsIgnoreCase("off")) {
            laptopState = State.OFF;
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
        if(storedGames.size() == 15) {
            System.out.println("Cannot install more than 15 games.");
        } else if(laptopState == State.OFF) { // cannot install if phone is off
            System.out.println("Cannot install " + gameName + " on " + this.hostname + ". Laptop is off.");
        } else { // 0 - 5 games
            if (storedGames.contains(gameName)) {

            } else {
                System.out.println("Installing " + gameName + " on " + this.getHostname() + ".");
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
        if(laptopState == State.OFF) {
            System.out.println("Cannot play " + gameName + " on " + this.hostname + ". Laptop is off.");
        } else {
            if (this.hasGame(gameName)) {
                System.out.println(this.getHostname() + " is playing " + gameName + ".");
            } else {
                System.out.println("Cannot play " + gameName + " on " + this.getHostname() + ". Install it first.");
            }
        }
    }

}
