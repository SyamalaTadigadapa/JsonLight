package examples;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeMap;
import parse.JsonParse;
import types.JsonMap;
import types.JsonNumber;
import types.JsonObject;
import types.JsonString;

public class ParseExamples {
    private JsonObject json;
    private JsonObject modelJson=null;

    public ParseExamples(String jsonPath) throws IOException{
        Path path = Paths.get(jsonPath);
        json = new JsonParse(path).get();
    }
    
    public ParseExamples(String jsonPath, String modelJsonPath) throws IOException{
        Path path = Paths.get(jsonPath);
        Path modelPath = Paths.get(modelJsonPath);
        json = new JsonParse(path).get();
        modelJson = new JsonParse(modelPath).get();
    }

    private void printElements(String key){
        // this is for a single key. You can even give key that has a map as value.
        System.out.print("The value of the key: "+key);
        System.out.println(" "+json.get(key).value());
    }

    private void printElements(String[] keys){
        //calling this for nested elements
        JsonObject job = null;
        for(String key: keys){
            //because, the first key should be read from json, then we get a jsob object and job references to that json object
            //now the second key is within the job not json so we search for second key in job and we assign the new
            //json object to job and so on.
            if(job == null){
                job = json.get(key);
            }
            else{
                job = job.get(key);
            }

        }
        System.out.print("The value of this nested key: "+Arrays.toString(keys));
        System.out.println(" "+job.value());
    }

    private void addElement(String key, Double value){
        // you can add any type
        ((JsonMap)json).add(new JsonString(key), (JsonObject)(new JsonNumber(value)));
    }

    private void addElement(String key, JsonObject value){
        // you can add any type
        ((JsonMap)json).add(new JsonString(key), value);
    }
    private void checkMap(){
        //print keys
        // you can iterate the json even if you don;t know the keys
        // the json root, when you do jsonparse.get(), you get json object, when you do json.value you get map.
        // you can iterate keys, get value of the key and again check the type of the value. 
        TreeMap<Object ,Object> mapper = (TreeMap<Object,Object>)json.value();
        System.out.println("The keys of the json are");
        for(Object key: mapper.keySet()){
            System.out.print(key+" ");
        }
    }

    public static void main(String[] args)throws IOException {
        ParseExamples pcj = new ParseExamples("D:\\tetris-python\\Game\\assets\\screens\\game_screen.json");
        pcj.printElements("next_shapes_container");
        pcj.printElements(new String[]{"next_shapes_container","color"});
        pcj.addElement("music_volume", 32.4); //single element

        String author = """
                        {
                                "age": 30,
                                "gender": "male",
                                "member": "yes",
                                "name": "subba rao"
                            }""";
        pcj.addElement("author", new JsonParse(author).get()); //nested elements
        pcj.checkMap();
    }
    
}
