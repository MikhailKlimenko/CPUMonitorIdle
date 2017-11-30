package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CPU_monitor_impl implements CPU_monitor {

    private static final int IDLE_COLUMN_INDEX = 11;

    // Карта загруженности процессора
    private static Map<String, Double> get_CPU_usage_map() throws IOException {
       Map<String, Double> cpu_usage_map = new HashMap<>();

        try {
            Process process = Runtime.getRuntime().exec("mpstat -P ALL 1 1");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String read = null;
                String[] buffer = null;

                reader.readLine();
                reader.readLine();
                reader.readLine();
                while ((read = reader.readLine()) != null && read.length() > 1) {
                    buffer = read.replaceAll(",", ".").split("\\s+");
                    //   Округление до 2 знаков после запятой
                    Double round_usage_core = (double) Math.round((100 - Double.parseDouble(buffer[IDLE_COLUMN_INDEX])) * 100) / 100;
                    cpu_usage_map.put(buffer[1], round_usage_core);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cpu_usage_map;
    }


    @Override
    public Map<String, Double> getAll_CPU_usage() throws IOException {
        return get_CPU_usage_map();
    }

    @Override
    public int get_count_of_core() throws IOException {
        return get_CPU_usage_map().size() - 1;
    }

    @Override
    public String toString() {
        try {
            return get_CPU_usage_map().toString();
        } catch (IOException e) {}
        return null;
    }
}