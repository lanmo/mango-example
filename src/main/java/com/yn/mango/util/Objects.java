package com.yn.mango.util;

import java.util.Arrays;

/**
 * Created by yangnan on 16/11/4.
 */
public class Objects {

    public static int hashCode(Object... objs) {
        return Arrays.hashCode(objs);
    }

    public static boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }
}
