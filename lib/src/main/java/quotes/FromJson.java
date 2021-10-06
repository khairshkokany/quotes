package quotes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class FromJson {
    String author;
    String text;
    String quoteText;
    String quoteAuthor;
    public FromJson(String author , String quotes , String quoteTest ,String quoteAuthor  ){
    this.author = author;
    this.text = quotes;
    this.quoteAuthor = quoteAuthor;
    this.quoteText = quoteTest;
    }

    public static void getData() throws IOException {
String url = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setConnectTimeout();
    int codeRes = 0 ;
       try {
          codeRes = connection.getResponseCode();
       } catch (Exception exception) {
           System.out.println(exception.getMessage());
       }

        if (codeRes == HttpURLConnection.HTTP_OK) {




            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
//            System.out.println(data);
            bufferedReader.close();
            Gson gson = new Gson();
            FromJson randomQuotes = gson.fromJson(data, FromJson.class);
            String authorString = randomQuotes.quoteAuthor;
            String quoteString = randomQuotes.quoteText;
            System.out.println(authorString);
            System.out.println(quoteString);

            FromJson addJson = new FromJson(authorString,quoteString,null,null);
            Writer writer = new FileWriter("../quotes/recentquotes.json",true) ;
            gson.toJson(addJson,writer);
            System.out.println("this is the writer method " + addJson);
            writer.close();


        }else {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("../quotes/recentquotes.json"));

            Type jsonCasting = new TypeToken<List<FromJson>>() {}.getType();

            List<FromJson> jsonList = gson.fromJson(reader, jsonCasting);

            Random random = new Random();
            int randoms = random.nextInt(jsonList.size());

            System.out.println(jsonList.get(randoms).toString());
        }


    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    @Override
    public String toString() {
        return "FromJson{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", quoteText='" + quoteText + '\'' +
                ", quoteAuthor='" + quoteAuthor + '\'' +
                '}';
    }
}
