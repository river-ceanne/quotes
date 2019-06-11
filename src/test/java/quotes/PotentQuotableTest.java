package quotes;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static quotes.App.getQuotesData;

public class PotentQuotableTest {

    @Test(expected = IOException.class)
    public void testRequestQuoteException() throws IOException {

        Path path = FileSystems.getDefault().getPath("assets", "fakeFile.json");
        ArrayList<String> x = getQuotesData(path);

    }

    @Test
    public void testRequestQuote() throws IOException {
        String[] strings = null;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(strings);

        assertTrue(outContent.toString().contains("Author") && outContent.toString().contains("Quote"));

    }
}
