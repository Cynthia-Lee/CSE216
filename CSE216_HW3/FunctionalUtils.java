import java.util.*;
import java.util.stream.Collectors;

public class FunctionalUtils {

    /**
     * @param strings: the input collection of <code>String</code>s.
     * @return a collection of <code>String</code>s that start with a
     * capital letter (i.e., ‘A’ through ‘Z’).
     */
    public static Collection<String> capitalized(Collection<String> strings) {
        return strings.stream().filter(p -> Character.isUpperCase(p.charAt(0))).collect(Collectors.toList());
    }

    /**
     * Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
     *
     * @param strings: the given collection of <code>String</code>s.
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     *                  If <code>true</code>, then the element encountered earlier in
     *                  the iteration is returned, otherwise the element encountered
     *                  later is returned.
     * @return the longest <code>String</code> in the given collection,
     * where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start) {

        return from_start ? strings.stream().reduce((x, y) -> x.length() >= y.length() ? x : y).orElse(null) : strings.stream().reduce((x, y) -> x.length() > y.length() ? x : y).orElse(null);

        /*try {
            if (strings.isEmpty()) {
                return null;
            }

            if (from_start) { // from start = true (earlier)
                // if it is true, return x, if it is false, return y
                return strings.stream().reduce((x, y) -> x.length() >= y.length() ? x : y).get();
            } else { // from start = false (later one)
                return strings.stream().reduce((x, y) -> x.length() > y.length() ? x : y).get();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
        */
    }

    /**
     * Find and return the least element from a collection of given elements that are comparable.
     * @param items: the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     *                  If <code>true</code>, then the element encountered earlier in
     *                  the iteration is returned, otherwise the element encountered
     *                  later is returned.
     * @param <T>: the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) {
        // In this function, the single method chain can return a java.util.Optional<T>. Therefore, you must
        // write additional code to convert that object to an object of type T (handling any potential exceptions
        // in the process).

        return from_start ? items.stream().reduce((x, y) -> x.compareTo(y) <= 0 ? x : y).orElse(null) : items.stream().reduce((x, y) -> x.compareTo(y) < 0 ? x : y).orElse(null);

        /*try {
            if (items.isEmpty()) {
                return null;
            }

            if (from_start) { // from start = true (earlier)
                return items.stream().reduce((x, y) -> x.compareTo(y) <= 0 ? x : y).get();
            } else { // from start = false (later one)
                return items.stream().reduce((x, y) -> x.compareTo(y) < 0 ? x : y).get();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
        */
    }

    /**
     * Flattens a map to a stream of <code>String</code>s, where each element in the list
     * is formatted as "key -> value".
     *
     * @param aMap the specified input map.
     * @param <K> the type parameter of keys in <code>aMap</code>.
     * @param <V> the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap) {
        // converted map to a set to a stream
        return aMap.entrySet().stream().map(p -> p.getKey() + " -> " + p.getValue()).collect(Collectors.toList());
    }
}

