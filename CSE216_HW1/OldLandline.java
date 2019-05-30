public class OldLandline implements Phone{
    // is a phone that can only make or receive a phone call

    // more than one simultaneous calls cannot be made from an old landline
    // more than one simultaneous calls cannot be receive by an old landline
    // you may assume that if the receive of a call is not busy, then s/he will pick up their phone when it rings
    private String owner;
    private PhoneNumber number;
    private Phone currentCall = null; // so that the line isn't busy
    
    // constructor (Name, 10 digit long)
    public OldLandline(String s, long pn) {
        owner = s;
        number = new PhoneNumber(pn);
    }

    public String getOwner() {
        return owner;
    }

    public void call(Phone phone) { // isBusy(), false when not busy, currentCall = null
        if(!isBusy()) { // A not busy
            // if B is also not busy
            if(!phone.isBusy()) { // can make call
                currentCall = phone;
                phone.receive(this); // B not busy
                System.out.println(this.owner + " is on the phone with " + phone.getOwner() + ".");
            } else if(phone.isBusy()) { // if B is busy, check if it is a Landline
                if(phone instanceof Landline) { // if B is Landline
                    // let A leave a message for B
                    phone.receive(this); // Landline's receive method
                } else { // if B is not Landline, A unable to call B
                    System.out.println(this.owner + " is unable to call " + phone.getOwner() + ". Line is currently busy.");
                }
            }
        } else {
            // else if (isBusy()) { //A is busy, (B can be busy or not busy) print nothing
        }
    }

    public void end() { // this person is ending the call
        if(this.isBusy()) {
            System.out.println(this.owner + " has ended the call to " + currentCall.getOwner() + ".");
            currentCall.receiveEndSignal(this); // other person will have call ended
            currentCall = null; // this person ends call
        }
    }

    public void receive(Phone from){ // isBusy(), false when not busy, currentCall = null
        if(!isBusy()) { // check in case
            currentCall = from;
        }
    }

    public boolean isBusy(){
        boolean busy = true;
        if (currentCall == null) {
            busy = false;
        }
        return busy;
    }

    public void receiveEndSignal(Phone from){ //both people in the call will end call
        currentCall = null;
    }

    public PhoneNumber number() {
        return number;
    }

    // getters and setters for currentCall
    public Phone getCurrentCall() {
        return currentCall;
    }

    public void setCurrentCall(Phone p) {
        currentCall = p;
    }

}
