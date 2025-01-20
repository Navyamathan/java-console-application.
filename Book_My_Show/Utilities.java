
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Utilities
{
    public static HashMap<Character, ArrayList<String>>  calculateGrid(int seatCount, String seatsGrid)
    {
        HashMap<Character, ArrayList<String>> hashMap = new HashMap<>();
        char character = 'A';
        String[] splitGrid = seatsGrid.split("\\*");
        long[] seats = new long[splitGrid.length];
        long temp = 0;

        for(int i = 0; i < splitGrid.length; i++)
        {
            seats[i] = Long.parseLong(splitGrid[i]);
            temp += seats[i];
        }

        if(seatCount % temp != 0)
        {
            System.out.println("Enter the Valid Grid!");
            System.out.println();
            return null;
        }
        else
        {
            long newTemp = seatCount/temp;
            for(int iteration=0; iteration < newTemp; iteration++)
            {
                hashMap.put(character, new ArrayList<>());
                for (int i = 0; i < splitGrid.length; i++)
                {
                    for (int j = 0; j < seats[i]; j++)
                    {
                        hashMap.get(character).add("-");
                    }
                    hashMap.get(character).add("<space>");
                }
                character++;
            }
            System.out.println("Seats Grid Added Successfully!");
            return hashMap;
        }
    }

    public static void printSeats(HashMap<Character, ArrayList<String>> seats)
    {
        for(Map.Entry<Character,ArrayList<String>> tempSeats : seats.entrySet())
        {
            System.out.print(" "+tempSeats.getKey()+":"+tempSeats.getValue());
        }
    }


}
