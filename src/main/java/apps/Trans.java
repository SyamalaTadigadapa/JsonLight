package apps;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parse.JsonParse;
import types.JsonArray;
import types.JsonMap;
import types.JsonObject;
import types.JsonString;

public class Trans {
	private Map<String, String> transmap;
	private JsonObject trans;

	public Trans(String file) throws Exception {
		trans = new JsonParse(Path.of(file)).get();
		transmap = new HashMap<>();
		process();
	}

	private void storeList(String name) {
		int i = 0;
		String path = "translations." + name;
		List<JsonObject> li = ((JsonArray) (trans.get(path))).value();
		for (JsonObject o : li) {
			String key = name + "-" + i++;
			transmap.put(key, (String) (o.value()));
			System.out.println(key + " -> " + transmap.get(key));
		}
	}

	private void storeMisc() {
		for (Entry<JsonString, JsonObject> e : ((JsonMap) (trans.get("translations.miscellaneous"))).value()
				.entrySet()) {
			String key = e.getKey().value();
			String val = (String) (e.getValue().value());
			transmap.put(key, val);
			System.out.println(key + " -> " + transmap.get(key));
		}
	}

	private void process() {
		storeList("tithi");
		storeList("vaara");
		storeList("nakshtra");
		storeList("yoga");
		storeList("karana");
		storeList("rasi");
		storeList("panchaka");
		storeMisc();
	}

	public static void main(String[] args) throws Exception {
		String file = "C:\\Users\\ADMIN\\OneDrive\\Desktop\\PanchangamJsonModel\\english.trans.json";
		Trans tr = new Trans(file);
	}

}
