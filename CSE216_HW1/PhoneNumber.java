public class PhoneNumber {
    // wrapper containing a final long value, which is a 10-digit phone number

    private final long number;

    // constructor must ensure that an instance of this class is always a 10-digit number
    // if the constructor is provided anything else, it must throw a runtime exception
    public PhoneNumber(long n) {
        // number divided by 10^9 must be greater and equal to 1, less than 10 (will drop decimals)
        // int check = (int) (n / 1000000000);
        // check >= 1 && check < 10
        String num = Long.toString(n);
        if(num.length() == 10) { // it is a 10 digit number
            number = n;
        } else if(num.length() > 10 || num.length() < 10) {
            throw new IllegalArgumentException("Phone number must be 10 digits. (valid positive 10 digit long number)");
        // } else if(n < 0) { // negative number
            // throw new IllegalArgumentException("Phone number must not be a negative number.");
        } else { // throw runtime exception
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    //getter
    public long getNumber(){
        return number;
    }

}
