package quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class FromJson {
    String author;
    String text;
    public FromJson(String author , String quotes){
    this.author = author;
    this.text = quotes;

    }

    public static void getData()throws FileNotFoundException {
        System.out.println("hello in the json lab");

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("../quotes/recentquotes.json"));

        Type jsonCasting = new TypeToken<List<FromJson>>() {
        }.getType();

        List<FromJson> jsonList = gson.fromJson(reader, jsonCasting);

        Random random = new Random();
        int randoms = random.nextInt(jsonList.size());

        System.out.println(jsonList.get(randoms).toString());

    }



        @Override
    public String toString() {
        return "FromJson{" +
                "author='" + author + '\'' +
                ", quotes='" + text + '\'' +
                '}';
    }
}
