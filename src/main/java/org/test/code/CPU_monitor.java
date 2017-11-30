package org.test.code;

import java.io.IOException;
import java.util.Map;

public interface CPU_monitor {
    Map<String, Double> getAll_CPU_usage() throws IOException;

    int get_count_of_core() throws IOException;
}
