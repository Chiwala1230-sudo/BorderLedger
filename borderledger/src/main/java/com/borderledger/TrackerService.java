package com.borderledger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class TrackerService {
    
    private Map<String, Trader> traders;
    private final Gson gson;
    private final String fileName = "borderledger_data.json";

    public TrackerService() {
        this.traders = new HashMap<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadData();
    }

    public void addTrader(Trader trader) {
        traders.put(trader.getId(), trader);
        saveData();
    }

    public Trader getTrader(String id) {
        return traders.get(id);
    }

    public Map<String, Trader> getAllTraders() {
        return traders;
    }

    public void deleteTrader(String id) {
        traders.remove(id);
        saveData();
    }

    private void saveData() {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(traders, writer);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private void loadData() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> rawMap = gson.fromJson(reader, type);
            
            if (rawMap != null) {
                for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
                    // Convert the generic Object back into an InformalTrader
                    JsonObject jsonObject = gson.toJsonTree(entry.getValue()).getAsJsonObject();
                    Trader t = gson.fromJson(jsonObject, InformalTrader.class);
                    traders.put(entry.getKey(), t);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}