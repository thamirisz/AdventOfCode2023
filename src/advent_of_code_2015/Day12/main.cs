using System;
using System.IO;
using System.Text.Json;

class Program
{
    static int Sum(JsonElement element)
    {
        int total = 0;

        switch (element.ValueKind)
        {
            case JsonValueKind.Number:
                total += element.GetInt32();
                break;

            case JsonValueKind.Array:
                foreach (var item in element.EnumerateArray())
                    total += Sum(item);
                break;

            case JsonValueKind.Object:
                foreach (var prop in element.EnumerateObject())
                {
                    if (prop.Value.ValueKind == JsonValueKind.String && prop.Value.GetString() == "red")
                        return 0;
                    else 
                        total += Sum(prop.Value);
                }
                   
                break;
        }

        return total;
    }

    static void Main()
    {
        string json = File.ReadAllText("input.txt");

        JsonDocument doc = JsonDocument.Parse(json);

        Console.WriteLine(Sum(doc.RootElement));
    }
}