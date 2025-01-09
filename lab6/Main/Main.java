package Main;

import FormattedInput.*;
import EncodingConverter.*;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Enter input matching format '%d %s %c':");

        Object[] values = FormattedInput.scanf("%d %s %c");

        String ss = "42323 dds d";

        Object[] valuess = FormattedInput.sscanf("%d %s %c", ss);
        System.out.println("Parsed values:");

        for (Object value : values)
        {
            System.out.println(value);
        }

        System.out.println("Parsed values from sscanf:");
        for (Object value : valuess) 
        {
            System.out.println(value);
        }
        
        String[] argc = {"in.txt", "out.txt", "us-ascii", "utf-16"};
        
        EncodingConverter.convert(argc);

    }    
}
