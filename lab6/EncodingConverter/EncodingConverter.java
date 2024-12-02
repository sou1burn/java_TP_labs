package EncodingConverter;

import java.io.*;
import java.nio.charset.Charset;

public class EncodingConverter {
    public static void convert(String[] args)
    {

        if (args.length != 4)
        {
            System.out.println("Usage: java EncodingConverter <inputFile> <outputFile> <inputEncoding> <outputEncoding>");
            return;
        }

        String in = args[0];
        String out = args[1];
        String baseEncoding = args[2];
        String outEncoding = args[3];

        if (!Charset.isSupported(baseEncoding) || !Charset.isSupported(outEncoding))
        {
            System.out.println("Invalid encoding specified. Please use valid encodings.");
            return;
        }

        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), baseEncoding));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), outEncoding))
        )
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File successfully converted from " + baseEncoding + " to " + outEncoding);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("I/O error occurred: " + e.getMessage());
        }


    }
}
