using System.Runtime.CompilerServices;
using System.Text;

class Program
{
    static void Main()
    {   
        string password = "cqjxxyzz";
        // while (!IsValidPassword(password))
        // {
        password = IncrementPassword(password);
        while (!IsValidPassword(password))
        {
            password = IncrementPassword(password);
        }
        //}

        Console.WriteLine(password);
    }

    private static bool IsValidPassword(string password)
    {
        //rule 1: can't contain i, o, l
        if (password.Contains('i') || password.Contains('o')  || password.Contains('l') )
        {
            return false;
        }

        // Rule 2: Must have one increasing straight of 3 letters
        bool hasStraight = false;
        for (int i = 0; i < password.Length - 2; i++)
        {
            if (password[i + 1] == password[i] + 1 && password[i + 2] == password[i] + 2)
            {
                hasStraight = true;
                break;
            }
        }

        if (!hasStraight) return false;

        // Rule 3: Must have two non-overlapping pairs
        int pairCount = 0;
        for (int i = 0; i < password.Length - 1; i++)
        {
            if (password[i] == password[i + 1])
            {
                pairCount++;
                i++; // skip next to avoid overlapping
            }
        }

        return pairCount >= 2;
    }

    private static string IncrementPassword(string password)
    {
        char[] chars = password.ToCharArray();
        int i = chars.Length - 1;
        
        while (i >= 0)
        {
            if (chars[i] == 'z')
            {
                chars[i] = 'a';
                i--;
            }
            else
            {
                chars[i]++;
                break;
            }
        }

        return new string(chars);
    }
}