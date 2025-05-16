package com.ooad6.ecommerce.repository;

import com.mongodb.client.*;
import com.ooad6.ecommerce.model.Items;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Component
public class ItemsSearchImpl implements ItemsSearch{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Items> findByText(String text) {
        final List<Items> itemsList = new ArrayList<>();
        MongoDatabase database = client.getDatabase("Ecommerce");
        MongoCollection<Document> collection = database.getCollection("Items");

        System.out.println("🔍 Searching for: " + text); // Debugging

        // Debug: Print all database items
        FindIterable<Document> allItems = collection.find();
        for (Document item : allItems) {
            System.out.println("📄 Item in DB: " + item.toJson());
        }

        // MongoDB Search Query
        FindIterable<Document> result = collection.find(
                new Document("$or", Arrays.asList(
                        new Document("Name", new Document("$regex", text).append("$options", "i")),
                        new Document("Description", new Document("$regex", text).append("$options", "i")),
                        new Document("Category", new Document("$regex", text).append("$options", "i"))
                ))
        ).limit(5);

        for (Document doc : result) {
            System.out.println("✅ Found: " + doc.toJson()); // Debugging
            itemsList.add(converter.read(Items.class, doc));
        }

        System.out.println("📢 Total results: " + itemsList.size()); // ✅ Debugging

        return itemsList;
    }

}

