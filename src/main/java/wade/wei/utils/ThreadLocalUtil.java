package wade.wei.utils;

import lombok.Getter;

/**
 * @author Administrator
 */
public class ThreadLocalUtil {
    private final static ThreadLocal<Long> startTime = new ThreadLocal<>();

    public static void add(Long time) {
        startTime.set(time);
    }

    public static Long getTime() {
        return startTime.get();
    }

    public static void remove() {
        startTime.remove();
    }
}
