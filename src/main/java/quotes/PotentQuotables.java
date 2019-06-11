package quotes;


import com.google.common.base.Utf8;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();



        String quoteText = result.substring(result.indexOf(":") + 1,result.indexOf(", \"quoteAuthor"));
        String author = result.substring(result.indexOf("quoteAuthor") + 14,result.indexOf("\", \"senderName"));

        Quote quote = new Quote(author,quoteText);
        Gson gson = new GsonBuilder().serializeNulls().create();
        Path path = FileSystems.getDefault().getPath("assets", "recentquotes.json");
        append(path,gson.toJson(quote));

        return quote;
    }

    public static void append(Path path, String json) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String output = "";
        StringBuilder stringBuilder = new StringBuilder();

        while((output = reader.readLine()) != null){
            if(output.endsWith("]")){
                stringBuilder.append(",");
                stringBuilder.append(json);
                stringBuilder.append("\n");
                stringBuilder.append("]");
                break;
            }else{
                stringBuilder.append(output);
                stringBuilder.append("\n");
            }

        }

        BufferedWriter newFileUpdated = new BufferedWriter(new FileWriter(path.toFile(), false));
        newFileUpdated.write(stringBuilder.toString());
        newFileUpdated.close();


    }

}
