import java.util.Comparator;

public class Orderings {
    // ensure that these objects can be ordered in various ways so that they can be used
    // in real-world applications
    // each implementation must be a static inner class

    // Order phones by their numbers (increasing)
    public static class PhoneNumberComparator implements Comparator<Phone>{
        // need compare method because implemented Comparator
        @Override
        public int compare(Phone p1, Phone p2) {
            // return (int)(o1.number().getNumber() - o2.number().getNumber());
            if(p1.number().getNumber() > p2.number().getNumber()) { // >
                return 1;
            } else if(p1.number().getNumber() < p2.number().getNumber()) { // <
                return -1;
            } else { // ==
                return 0;
            }
        }
        // phones we have are: OldLandline, Landline, SmartPhone
    }

    // Order phones by their owner names (alphabetically, case-insensitive).
    public static class OwnerNameComparator implements Comparator<Phone>{
        // alphabetically, so start from A - Z, smaller to larger, increasing
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getOwner().compareToIgnoreCase(p2.getOwner());
        }

    }

    // Order computers by their screen size (increasing)
    public static class ScreenSizeComparator implements Comparator<Computer>{

        @Override
        public int compare(Computer c1, Computer c2) {
            return c1.getScreenSize() - c2.getScreenSize();
        }
        // computers we have are: Laptop, SmartPhone
    }

    // Order computers by their brand names (alphabetically, case-insensitive)
    public static class BrandNameComparator implements Comparator<Computer>{

        @Override
        public int compare(Computer c1, Computer c2){
            // computers we have are: Laptop, SmartPhone
            // SmartPhone doesn't have a brand
            // SmartPhones first (least), then Laptops
            if (c1 instanceof SmartPhone && c2 instanceof Laptop){
                // SmartPhone < Laptop
                return -1;
            } else if (c1 instanceof Laptop && c2 instanceof SmartPhone) {
                // Laptop > SmartPhone
                return 1;
            } else if (c1 instanceof Laptop && c2 instanceof Laptop) {
                return ((Laptop) c1).getBrand().compareToIgnoreCase(((Laptop) c2).getBrand());
            } else { // (c1 instanceof  SmartPhone && c2 instanceof SmartPhone)
                return 0;
            }
        }

    }

    // Order computers according to the amount of RAM (increasing)
    public static class RAMComparator implements Comparator<Computer>{

        @Override
        public int compare(Computer c1, Computer c2){
            return c1.getRAM() - c2.getRAM(); // both Laptop and SmartPhone have RAM
        }

    }

    // at the end of your implementation, you must be able to directly use
    // the Java Collections library for sorting (i.e., java.util.Collections#sort method)
}
