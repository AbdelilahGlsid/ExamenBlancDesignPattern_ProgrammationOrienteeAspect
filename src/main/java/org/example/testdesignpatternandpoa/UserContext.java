package org.example.testdesignpatternandpoa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserContext {
    private static final ThreadLocal<Set<String>> userRoles = ThreadLocal.withInitial(HashSet::new);

    public static void setRoles(String... roles) {
        userRoles.get().addAll(Arrays.asList(roles));
    }

    public static boolean hasRoles(String[] requiredRoles) {
        Set<String> currentRoles = userRoles.get();
        for (String role : requiredRoles) {
            if (currentRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    public static void clear() {
        userRoles.remove();
    }
}
