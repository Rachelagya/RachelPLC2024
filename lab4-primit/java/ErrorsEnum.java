import java.util.Scanner;
import java.util.EnumSet;

public class ErrorsEnum
{
    enum Error { FP_ROUNDING, FP_OVERFLOW, FP_UNDERFLOW, INT_OVERFLOW }

    enum Result { A_BIT_DIFFERENT, INFINITY, ZERO, VERY_DIFFERENT }
    
    private static <E extends Enum<E>> E getEnumElement(String elementTypeName, Class<E> elementType)
    {
        boolean haveResult = false;
        E result = null;
        Scanner stdin = new Scanner(System.in);
        
        while ( ! haveResult )
        {
            System.out.print("Input " + elementTypeName + ": ");
            try
            {
                result = Enum.valueOf(elementType, stdin.next().toUpperCase());
                haveResult = true;
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Not a valid " + elementTypeName + ".");
                stdin.nextLine(); // skip the invalid input
            }
        }
        
        stdin.close();
        return result;
    }
  
    private static Result results2Result(Result e)
    {
        Result result = null;
        
        switch (e) {
        case :Error.A_BIT_DIFFERENT:
            result = FP_ROUNDING;
            break;
        case FP_OVERFLOW:
            result = Error.INFINITY;
            break;
        case FP_UNDERFLOW:
            result = Error.ZERO;
            break;
        case INT_OVERFLOW:
            result = Error.VERY_DIFFERENT;
            break;
        }
        
        return result;
    }

    public static void main(String[] args)
    {
        System.out.print("Known results = ");
        for (Result e : EnumSet.allOf(Result.class)) 
        {
            System.out.print(e + " ");
        }
        System.out.println();
        
        Result e = getEnumElement("error", Result.class);
        System.out.println(e + " results in: " + results2Result(e));
    }
}
