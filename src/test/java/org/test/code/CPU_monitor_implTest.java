package org.test.code;

import org.junit.*;
import static org.junit.Assert.*;

public class CPU_monitor_implTest {
    private CPU_monitor cpu_monitor;
    @Before
    public void setUp() throws Exception {
        cpu_monitor = new CPU_monitor_impl();
    }

    @Test
    public void getAll_CPU_usage() throws Exception {
        try {
            assertFalse(cpu_monitor.getAll_CPU_usage().toString().equals("{}"));
        } catch (Exception e) {
            assertEquals("Exception",true, false);
        }
    }

    @Test
    public void get_count_of_core(){
        try {
            assertNotEquals(-1, cpu_monitor.get_count_of_core());
        } catch (Exception e) {
            assertEquals("Exception", true, false);
        }

    }



}