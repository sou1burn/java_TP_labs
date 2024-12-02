package FormattedInput;

import java.util.Scanner;

public class FormattedInput
{
    public static Object[] scanf(String format)
    {
        Scanner scanner = new Scanner(System.in);
        return parse(format, scanner.nextLine());
    }

    public static Object[] sscanf(String format, String input) 
    {
        return parse(format, input);
    }

    protected static Object[] parse(String format, String input)
    {   
        String[] formatTokens = format.split(" ");
        String[] inputTokens = input.split(" ");
        
        if (formatTokens.length != inputTokens.length)
        {
            throw new IllegalArgumentException("Input does not match format.");
        }

        Object[] results = new Object[formatTokens.length];

        for (int i = 0; i < formatTokens.length; i++)
        {
            switch (formatTokens[i])
            {
                case "%d":
                    results[i] = Integer.parseInt(inputTokens[i]);
                    break;
                case "%f":
                    results[i] = Double.parseDouble(inputTokens[i]);
                    break;
                case "%s":
                    results[i] = inputTokens[i];
                    break;
                case "%c":
                    if (inputTokens[i].length() != 1)
                    {
                        throw new IllegalArgumentException("Input does not match format for %c.");
                    }
                    results[i] = inputTokens[i].charAt(0);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported format specifier: " + formatTokens[i]);
            }
        }

        return results;
    }


}