package com.gyc.roguelike.kernel.identity;

/**
 * A local runtime instance identifier.
 *
 * <p>This value is not globally unique by itself.
 * Full runtime identity is represented by {@link InstanceRef}.</p>
 *
 * @param <T> the runtime instance type
 */
public record LocalInstanceId<T>(long value) {

    public LocalInstanceId {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "LocalInstanceId value cannot be negative"
            );
        }
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
