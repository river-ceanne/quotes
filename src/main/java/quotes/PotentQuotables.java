package quotes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
//        return result;
        return new Quote();
    }

}
