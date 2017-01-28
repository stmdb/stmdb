package com.stmdb.values;

import java.util.HashMap;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;

public class WriteValue {

    @Nonnull
    final ImmutableMap<String, Optional<?>> store;

    @Nonnull
    final HashMap<String, Optional<?>> scratch;

    public WriteValue() {
        this.store = ImmutableMap.of();
        this.scratch = new HashMap<>();
    }

    @VisibleForTesting
    WriteValue(@Nonnull ImmutableMap<String, Optional<?>> store,
               @Nonnull HashMap<String, Optional<?>> scratch) {
        this.store = store;
        this.scratch = scratch;
    }

    public WriteValue(@Nonnull ReadValue parent) {
        this.store = parent.store;
        this.scratch = new HashMap<>();
    }

    @Nullable
    private Optional<?> getValue(@Nonnull String key) {
        Optional<?> opt = scratch.get(key);
        if (opt == null) {
            return store.get(key);
        } else {
            return opt;
        }
    }

    /**
     * Get Boolean associated with key.
     * Return null if key is not present.
     *
     * @param key non-null name
     * @return null, empty Optional instance, or non-empty Optional instance
     *
     * @throws ClassCastException if value cannot be cast to Boolean
     */
    public Optional<Boolean> getBoolean(@Nonnull String key) {
        Optional<?> opt = getValue(key);
        if (opt == null) {
            return null;
        } else {
            return opt.map(x -> (Boolean) x);
        }
    }

    /**
     * Get Integer associated with key.
     * Return null if key is not present.
     *
     * @param key non-null name
     * @return null, empty Optional instance, or non-empty Optional instance
     *
     * @throws ClassCastException if value cannot be cast to Integer
     */
    public Optional<Integer> getInt(@Nonnull String key) {
        Optional<?> opt = getValue(key);
        if (opt == null) {
            return null;
        } else {
            return opt.map(x -> (Integer) x);
        }
    }

    /**
     * Get Long associated with key.
     * Return null if key is not present.
     *
     * @param key non-null name
     * @return null, empty Optional instance, or non-empty Optional instance
     *
     * @throws ClassCastException if value cannot be cast to Long
     */
    public Optional<Long> getLong(@Nonnull String key) {
        Optional<?> opt = getValue(key);
        if (opt == null) {
            return null;
        } else {
            return opt.map(x -> (Long) x);
        }
    }

    /**
     * Get String associated with key.
     * Return null if key is not present.
     *
     * @param key non-null name
     * @return null, empty Optional instance, or non-empty Optional instance
     *
     * @throws ClassCastException if value cannot be cast to String
     */
    public Optional<String> getString(@Nonnull String key) {
        Optional<?> opt = getValue(key);
        if (opt == null) {
            return null;
        } else {
            return opt.map(x -> (String) x);
        }
    }

    /**
     * Get class of value associated with key.
     * Return null if key is not present. Returns
     * Object.class if the key stores an null value.
     *
     * @param key non-null name
     * @return null, Object.class, or one of the storage types
     *
     * @throws ClassCastException if value cannot be cast to String
     */
    @SuppressWarnings("OptionalIsPresent")
    public Class<?> getType(@Nonnull String key) {
        Optional<?> opt = getValue(key);
        if (opt == null) {
            return null;
        }
        if (opt.isPresent()) {
            return opt.get().getClass();
        } else {
            return Object.class;
        }
    }

    /**
     * Writes a (key, value) pair into the object where value is a Boolean.
     *
     */
    public void setBoolean(@Nonnull String key, @Nullable Boolean value) {
        scratch.put(key, Optional.ofNullable(value));
    }

    /**
     * Writes a (key, value) pair into the object where value is an Integer.
     *
     */
    public void setInt(@Nonnull String key, @Nullable Integer value) {
        scratch.put(key, Optional.ofNullable(value));
    }

    /**
     * Writes a (key, value) pair into the object where value is a Long.
     *
     */
    public void setLong(@Nonnull String key, @Nullable Long value) {
        scratch.put(key, Optional.ofNullable(value));
    }

    /**
     * Writes a (key, value) pair into the object where value is a String.
     *
     */
    public void setString(@Nonnull String key, @Nullable String value) {
        scratch.put(key, Optional.ofNullable(value));
    }

}
