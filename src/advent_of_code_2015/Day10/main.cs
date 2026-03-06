using System.Text;

class Program
{
    static void Main()
    {
        string currentString = "1113222113";
        for (int i = 0; i < 40; i++)
        {
            currentString = LookAndSay(currentString);
        }

        Console.WriteLine(currentString.Length);
    }

    private static string LookAndSay(string input)
    {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        
        for (int j = 1; j < input.Length; j++)
        {
            if (input[j] != input[j - 1])
            {
                sb.Append(count);
                sb.Append(input[j - 1]);
                count = 1;
                
            } else
            {
                count++;
            }
        }

        //post processing
        sb.Append(count);
        sb.Append(input[input.Length - 1]);
        return sb.ToString();
    }
}