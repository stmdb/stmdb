package com.stmdb.values;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;

public class ReadValue {

    @Nonnull
    final ImmutableMap<String, Optional<?>> store;

    public ReadValue() {
        this.store = ImmutableMap.of();
    }

    @VisibleForTesting
    ReadValue(@Nonnull ImmutableMap<String, Optional<?>> store) {
        this.store = store;
    }

    public ReadValue(@Nonnull WriteValue parent) {
        ImmutableMap.Builder<String, Optional<?>> builder = ImmutableMap.builder();
        builder.putAll(parent.store);
        builder.putAll(parent.scratch);
        this.store = builder.build();
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
        Optional<?> opt = store.get(key);
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
        Optional<?> opt = store.get(key);
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
        Optional<?> opt = store.get(key);
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
        Optional<?> opt = store.get(key);
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
        Optional<?> opt = store.get(key);
        if (opt == null) {
            return null;
        }
        if (opt.isPresent()) {
            return opt.get().getClass();
        } else {
            return Object.class;
        }
    }

}
