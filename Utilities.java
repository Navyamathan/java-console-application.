import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// class to manage the seats grid
class Utilities
{
    // method to calculate the seatGrid from String to a properly added seatsGrid HashMap
    public static HashMap<Character, ArrayList<String>>  calculateGrid(int seatCount, String seatsGrid)
    {
        HashMap<Character, ArrayList<String>> hashMap = new HashMap<>(); // create a hashMap to store the calculated seatsGrid
        char character = 'A';
        String[] splitGrid = seatsGrid.split("\\*"); // use split methode to separate * from the grid and each one separately
        long[] seats = new long[splitGrid.length]; // long array to store the split grids orderly
        long sumOfSeatsInRow= 0;

        // for loop to convert grids String to long one by one
        for(int i = 0; i < splitGrid.length; i++)
        {
            seats[i] = Long.parseLong(splitGrid[i]);
            sumOfSeatsInRow += seats[i];
        }

        // checks if the calculated seats is equal to seatCount
        if(seatCount % sumOfSeatsInRow != 0) // if return null
        {
            System.out.println("Enter the Valid Grid!");
            return null;
        }
        else
        {
            long newTemp = seatCount/sumOfSeatsInRow; // calculate how many iteration it could be
            for(int iteration=0; iteration < newTemp; iteration++) // for loop for the total iterations
            {
                hashMap.put(character, new ArrayList<>()); // add key to the hashMap - (seat row)
                for (int i = 0; i < splitGrid.length; i++) // for loop for the grids
                {
                    for (int j = 0; j < seats[i]; j++) // for loop to add seats
                    {
                        hashMap.get(character).add("-"); // add values to the hashMap - (seats -)
                    }
                    if(i < splitGrid.length-1)
                    {
                        hashMap.get(character).add("<space>"); // add values to the hashMap - (spaces <space>)
                    }
                }
                character++;
            }
            System.out.println("Seats Grid Added Successfully!");
            return hashMap;
        }
    }

  // method to print seats
    public static void printSeats(HashMap<Character, ArrayList<String>> seats)
    {
        // loop to print the seats in an order way by entrySet function
        for(Map.Entry<Character,ArrayList<String>> tempSeats : seats.entrySet())
        {
            System.out.println(" "+tempSeats.getKey()+":"+tempSeats.getValue());
        }
    }


}
