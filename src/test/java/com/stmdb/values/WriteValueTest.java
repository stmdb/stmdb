package com.stmdb.values;

import java.util.HashMap;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class WriteValueTest {

    @Test
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void read() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        assertEquals(Boolean.TRUE, value.getBoolean("bool").get());
        assertEquals(Integer.valueOf(0), value.getInt("int").get());
        assertEquals(Long.valueOf(0L), value.getLong("long").get());
        assertEquals("hello world", value.getString("string").get());
        assertFalse(value.getString("empty").isPresent());
        assertNull(value.getString("missing"));
    }

    @Test
    public void missing() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        assertNull(value.getBoolean("missing"));
        assertNull(value.getInt("missing"));
        assertNull(value.getLong("missing"));
        assertNull(value.getString("missing"));
    }

    @Test
    public void typeOf() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        assertEquals(Boolean.class, value.getType("bool"));
        assertEquals(Integer.class, value.getType("int"));
        assertEquals(Long.class, value.getType("long"));
        assertEquals(String.class, value.getType("string"));
        assertEquals(Object.class, value.getType("empty"));
        assertNull(value.getType("missing"));
    }

    @Test(expected = ClassCastException.class)
    public void illegalCast() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        value.getString("bool");
    }

    @Test
    public void write() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        value.setBoolean("bool", Boolean.FALSE);
        value.setInt("int", 1);
        value.setLong("long", 1L);
        value.setString("string", "foobar");
        assertEquals(Boolean.FALSE, value.getBoolean("bool").get());
        assertEquals(Integer.valueOf(1), value.getInt("int").get());
        assertEquals(Long.valueOf(1L), value.getLong("long").get());
        assertEquals("foobar", value.getString("string").get());
    }

}
