package quotes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static quotes.App.getQuotesData;

public class PotentQuotableTest {

    @Test(expected = IOException.class)
    public void testRequestQuoteException() throws IOException {

        Path path = FileSystems.getDefault().getPath("assets", "fakeFile.json");
        PotentQuotables.append(path,"");

    }

    @Test
    public void testRequestQuote() throws IOException {
        String[] strings = null;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(strings);

        assertTrue(outContent.toString().contains("Author") && outContent.toString().contains("Quote"));

    }

    @Test
    public void testAppendAddedQuoteToFile() throws IOException {

        Quote res = PotentQuotables.requestQuote();

        Path path = FileSystems.getDefault().getPath("assets", "recentquotes.json");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        String output = "";
        StringBuilder stringBuilder = new StringBuilder();

        while((output = reader.readLine()) != null){
            stringBuilder.append(output);
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        String string = gson.toJson(res);
        assertTrue(stringBuilder.toString().contains(string));

    }
}
