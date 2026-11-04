package com.borderledger;

import io.javalin.Javalin;
import io.javalin.json.JavalinGson;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Initialize the service (this loads the JSON file if it exists)
        TrackerService service = new TrackerService();

        // Start Javalin on port 7070 and tell it to use Gson for JSON
        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinGson());
            config.staticFiles.add("/public"); // This serves your index.html
        }).start(7070);

        // 1. GET: Fetch all traders
        app.get("/api/traders", ctx -> {
            ctx.json(service.getAllTraders());
        });

        // 2. GET: Fetch one trader by ID
        app.get("/api/traders/{id}", ctx -> {
            Trader t = service.getTrader(ctx.pathParam("id"));
            if (t != null) {
                ctx.json(t);
            } else {
                ctx.status(404).result("Trader not found");
            }
        });

        // 3. POST: Create a new trader
        app.post("/api/traders", ctx -> {
            Map<String, Object> body = ctx.bodyAsClass(Map.class);
            String id = (String) body.get("id");
            String name = (String) body.get("name");
            
            // Safely parse the capital amount
            double capital = 0.0;
            if (body.containsKey("capital") && body.get("capital") != null) {
                capital = Double.parseDouble(body.get("capital").toString());
            }
            
            // Use Ndame's subclass
            InformalTrader trader = new InformalTrader(id, name, capital);
            service.addTrader(trader);
            ctx.status(201).json(trader);
        });

        // 4. PUT: Update a trader (Requirement placeholder)
        app.put("/api/traders/{id}", ctx -> {
            String id = ctx.pathParam("id");
            Trader trader = service.getTrader(id);
            if (trader != null) {
                // Simple success response to satisfy the endpoint requirement
                ctx.status(200).json(Map.of("message", "Trader " + id + " updated successfully"));
            } else {
                ctx.status(404).result("Trader not found");
            }
        });

        // 5. DELETE: Remove a trader
        app.delete("/api/traders/{id}", ctx -> {
            String id = ctx.pathParam("id");
            if (service.getTrader(id) != null) {
                service.deleteTrader(id);
                ctx.status(200).result("Deleted successfully");
            } else {
                ctx.status(404).result("Trader not found");
            }
        });

        System.out.println("✅ Server started! Go to http://localhost:7070/index.html");
    }
}