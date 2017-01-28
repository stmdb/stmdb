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
    public void init() {
        WriteValue value = new WriteValue();
        assertEquals(0, value.store.size());
        assertEquals(0, value.scratch.size());
    }

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

    @Test
    public void remove() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue value = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        value.remove("bool");
        value.remove("int");
        value.remove("long");
        value.remove("string");
        value.remove("empty");
        assertNull(value.getBoolean("bool"));
        assertNull(value.getInt("int"));
        assertNull(value.getLong("long"));
        assertNull(value.getString("string"));
        assertNull(value.getString("empty"));
    }

    @Test
    public void freeze() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        WriteValue write = new WriteValue(ImmutableMap.copyOf(store), new HashMap<>());
        write.setBoolean("bool", Boolean.FALSE);
        write.setInt("int", 1);
        write.setLong("long", 1L);
        write.setString("string", "foobar");
        write.remove("empty");
        ReadValue read = new ReadValue(write);
        assertEquals(Boolean.FALSE, read.getBoolean("bool").get());
        assertEquals(Integer.valueOf(1), read.getInt("int").get());
        assertEquals(Long.valueOf(1L), read.getLong("long").get());
        assertEquals("foobar", read.getString("string").get());
        assertNull(read.getString("empty"));
        assertNull(read.getString("missing"));
    }

    @Test
    public void thaw() {
        HashMap<String, Optional<?>> store = new HashMap<>();
        store.put("bool", Optional.of(Boolean.TRUE));
        store.put("int", Optional.of(0));
        store.put("long", Optional.of(0L));
        store.put("string", Optional.of("hello world"));
        store.put("empty", Optional.empty());
        ReadValue read = new ReadValue(ImmutableMap.copyOf(store));
        WriteValue write = new WriteValue(read);
        write.setBoolean("bool", Boolean.FALSE);
        write.setInt("int", 1);
        write.setLong("long", 1L);
        write.setString("string", "foobar");
        assertEquals(Boolean.FALSE, write.getBoolean("bool").get());
        assertEquals(Integer.valueOf(1), write.getInt("int").get());
        assertEquals(Long.valueOf(1L), write.getLong("long").get());
        assertEquals("foobar", write.getString("string").get());
        assertFalse(write.getString("empty").isPresent());
        assertNull(write.getString("missing"));
    }
}
