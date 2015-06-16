package zx.soft.mongodb.sample;

import java.util.Date;

import org.bson.Document;

import zx.soft.utils.json.JsonUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class SimpleCURD {

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("hefei17", 27017);
		MongoDatabase mongoDatabase = mongo.getDatabase("videos");
		MongoCollection<Document> table = mongoDatabase.getCollection("oa");
		System.out.println(mongoDatabase.getName());
		MongoIterable<String> tables = mongoDatabase.listCollectionNames();
		for (String coll : tables) {
			System.out.println(coll);
		}

		Document document = new Document();
		document.put("name", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());
		table.insertOne(document);

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "mkyong");
		FindIterable<Document> cursor = table.find(searchQuery);
		for (Document d : cursor) {
			System.out.println(JsonUtils.toJson(d));
		}
		MongoIterable<String> dbs = mongo.listDatabaseNames();
		for (String db : dbs) {
			System.out.println(db);
		}
		mongo.close();
	}

}
