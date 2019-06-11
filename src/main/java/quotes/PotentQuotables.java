package quotes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

public class PotentQuotables {

    public PotentQuotables(){}

    public static Quote requestQuote() throws IOException {
        URL url = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
        String result = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
            System.out.println(result);
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();

        String quoteText = result.substring(result.indexOf(":") + 1,result.indexOf(", \"quoteAuthor"));
        System.out.println(quoteText);
        String author = result.substring(result.indexOf("quoteAuthor") + 14,result.indexOf("\", \"senderName"));
        System.out.println(author);

        Gson gson = new GsonBuilder().serializeNulls().create();
        Quote quote = gson.fromJson(result, Quote.class );


        return quote;
    }

}
