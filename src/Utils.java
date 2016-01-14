import java.util.*;

/**
 * By: Sebastian Nilsson
 * Date: 16-01-14
 * Project: Poker
 */
public class Utils {

    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }

        return sets;
    }

    public static <T> Set<Set<T>> powerSetsOf(Set<T> originalSet, int setSize) {

        Set<Set<T>> powerSet = Utils.powerSet(originalSet);
        Set<Set<T>> setsOf = new HashSet<>();

        for (Set<T> set : powerSet ) {

            if (set.size() == setSize) {
                setsOf.add(set);
            }
        }

        return setsOf;
    }

}
