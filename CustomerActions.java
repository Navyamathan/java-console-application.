import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CustomerActions
{
    public Customer loginProcess(Scanner s)
    {
        Customer status;
        System.out.println("1. SIGN UP\n2. LOGIN\n3. EXIT");
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(s.nextLine());
        if(choice==1)
        {
            status = signUp(s);
            return status;
        }
        else if(choice==2)
        {
            status = login(s);
            return status;
        }
        else if(choice==3)
        {
            return null;
        }
        else
        {
            System.out.println("Enter The Valid Choice!");
        }
        return null;
    }

    public Customer login(Scanner s)
    {
        int attempts = 0;
        System.out.println();
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");

        for (Customer tempAccount : BookMyShow.getCustomerArrayList())
        {
            while (attempts < 3)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String accNo = s.nextLine();
                if (accNo.equals(tempAccount.getUserName()))
                {
                    System.out.print("Enter Password: ");
                    String pin = s.nextLine();
                    if (pin.equals(tempAccount.getPassword()))
                    {
                        return tempAccount;
                    }
                    else
                    {
                        attempts++;
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3)
                        {
                            Customer ob = new Customer(null, null,null);
                            return ob;
                        }
                    }
                }
                else
                {
                    return null;
                }

            }
        }
        return null;
    }

    public Customer signUp(Scanner s)
    {
        String userName;
        String password;
        String location;
        System.out.println();
        System.out.println("To Sign Up Enter The Following Details...");
        System.out.println();
        System.out.print("Enter User Name: ");
        userName = s.nextLine();
        System.out.print("Enter Password: ");
        password = s.nextLine();
        System.out.print("Enter Location: ");
        location = s.nextLine();
        Customer temp = new Customer(userName,password,location);
        BookMyShow.getCustomerArrayList().add(temp);
        return temp;
    }

    public void customerAction(Scanner s,Customer currentCustomer)
    {
        System.out.println();
        System.out.println("YOU ENTER THE CUSTOMER BOARD...");
        while (true)
        {
            System.out.println();
            System.out.println("1. TICKET BOOKING \n2.EXIT");
            System.out.print("Enter Your Choice: ");
            int choice = Integer.parseInt(s.nextLine());
            System.out.println();
            if (choice == 1)
            {
                availableMovies(s,currentCustomer);
            }
            else if(choice == 2)
            {
                break;
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    public void availableMovies(Scanner s,Customer currentCustomer)
    {
        ArrayList<String> printedMovies = new ArrayList<>();
        boolean movieFound = false;
        LocalDate currentDate = LocalDate.now();
        System.out.println("Available Movies...");
        for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
        {
            for(Movie tempMovie : tempMovieList)
            {
                if(tempMovie.getShow().getLocation().equals(currentCustomer.getLocation()) && tempMovie.getShow().getDate().equals(currentDate))
                {
                    if(!printedMovies.contains(tempMovie.getMovieName()))
                    {
                        System.out.println("* "+tempMovie.getMovieName());
                        printedMovies.add(tempMovie.getMovieName());
                        movieFound = true;
                    }
                }
            }
        }
        if(!movieFound)
        {
            System.out.println("No Available Movie!");
        }
        while(true)
        {
            System.out.println();
            System.out.println("Do You Want To Change Your Location Or Date?");
            System.out.print("yes/no: ");
            String choice = s.nextLine();
            if(choice.equals("yes"))
            {
               int flag = changeDateOrLocation(s,currentCustomer);
               if(flag!=0)
               {
                   System.out.println("Enter The Selected Movie: ");
                   String selectedMovie = s.nextLine();
                   ticketBooking(s,selectedMovie);
               }
               break;
            }
            else if(choice.equals("no"))
            {
                if(movieFound)
                {
                    System.out.println("Enter The Selected Movie: ");
                    String selectedMovie = s.nextLine();
                    ticketBooking(s,selectedMovie);
                }
                break;
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    public int changeDateOrLocation(Scanner s,Customer currentCustomer)
    {
        System.out.println();
        System.out.println("1. CHANGE DATE\n2. CHANGE LOCATION\n3. CHANGE BOTH");
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(s.nextLine());
        boolean movieFound = false;
        ArrayList<String> printedMovies = new ArrayList<>();
        if(choice == 1)
        {
            System.out.print("Enter The Date: ");
            String date = s.nextLine();
            LocalDate localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
            System.out.println("Available Movies...");
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    if(tempMovie.getShow().getLocation().equals(currentCustomer.getLocation()) && tempMovie.getShow().getDate().equals(localDate))
                    {
                        if(!printedMovies.contains(tempMovie.getMovieName()))
                        {
                            System.out.println("* "+tempMovie.getMovieName());
                            printedMovies.add(tempMovie.getMovieName());
                            movieFound = true;
                        }
                    }
                }
            }
            if(!movieFound)
            {
                System.out.println("No Available Movie!");
                return 0;
            }
            return 1;
        }
        else if(choice == 2) {
            LocalDate currentDate = LocalDate.now();
            System.out.print("Enter The Location: ");
            String changeLocation = s.nextLine();
            System.out.println("Available Movies...");
            for (ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values()) {
                for (Movie tempMovie : tempMovieList) {
                    if (tempMovie.getShow().getLocation().equals(changeLocation) && tempMovie.getShow().getDate().equals(currentDate)) {
                        if (!printedMovies.contains(tempMovie.getMovieName())) {
                            System.out.println("* " + tempMovie.getMovieName());
                            printedMovies.add(tempMovie.getMovieName());
                            movieFound = true;
                        }
                    }
                }
            }
            if (!movieFound)
            {
                System.out.println("No Available Movie!");
                return 0;
            }
            return 1;
        }
        else if(choice == 3)
        {
            System.out.print("Enter The Date: ");
            String date = s.nextLine();
            LocalDate localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
            System.out.print("Enter The Location: ");
            String location = s.nextLine();
            System.out.println("Available Movies...");
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    if(tempMovie.getShow().getLocation().equals(location) && tempMovie.getShow().getDate().equals(localDate))
                    {
                        if(!printedMovies.contains(tempMovie.getMovieName()))
                        {
                            System.out.println("* "+tempMovie.getMovieName());
                            printedMovies.add(tempMovie.getMovieName());
                            movieFound = true;
                        }
                    }
                }
            }
            if(!movieFound)
            {
                System.out.println("No Available Movie!");
                return 0;
            }
            return 1;
        }
        else
        {
            System.out.println("You Enter The InValid Choice!");
            return 0;
        }
    }

    public void ticketBooking(Scanner s, String selectedMovie)
    {
        label : while(true)
        {
            ArrayList<String> printedTheatres = new ArrayList<>();
            boolean theatreFound = false;
            boolean showsFound = false;
            System.out.println("Available Theatres...");
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    if(tempMovie.getMovieName().equals(selectedMovie))
                    {
                        if(!printedTheatres.contains(tempMovie.getTheatre().getTheatreName()))
                        {
                            System.out.println("* " + tempMovie.getTheatre().getTheatreName());
                            printedTheatres.add(tempMovie.getTheatre().getTheatreName());
                            theatreFound = true;
                        }
                    }
                }
            }
            if(!theatreFound)
            {
                System.out.println("No Available Movie!");
                break;
            }

            System.out.print("Enter The Theatre You Want: ");
            String theatreName = s.nextLine();
            System.out.println("Available Show...");
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    if(tempMovie.getTheatre().getTheatreName().equals(theatreName))
                    {
                        System.out.println("* "+tempMovie.getScreen().getScreenName());
                        System.out.println("* "+tempMovie.getShow().getStartingTime()+" - "+tempMovie.getShow().getEndingTime());
                        System.out.println("--------------------------");
                        showsFound = true;
                    }
                }
            }
            if(!showsFound)
            {
                System.out.println("No Available Show!");
                break;
            }

            while(true)
            {
                System.out.print("Enter The Timing You Want: ");
                String timing= s.nextLine();
                System.out.print("Enter The Screen You Want: ");
                String screenNane= s.nextLine();
                System.out.println("Available Show");
                for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
                {
                    for(Movie tempMovie : tempMovieList)
                    {
                        String tempTiming =tempMovie.getShow().getStartingTime()+" - "+tempMovie.getShow().getEndingTime();
                        if(tempTiming.equals(timing)&&screenNane.equals(tempMovie.getScreen().getScreenName()))
                        {
                            System.out.println("Available seats...");
                            System.out.println(tempMovie.getShow().getSeatsGrid());
                            while (true)
                            {
                                int status = selectSeats(s,tempMovie.getScreen().getSeatsCount(),tempMovie.getScreen().getGrid(),tempMovie.getShow().getSeatsGrid());
                                if(status == 1)
                                {
                                    System.out.println(tempMovie.getShow().getSeatsGrid());
                                    System.out.println("Ticket Booked Successfully!");
                                    break label;
                                }
                                else
                                {
                                    System.out.println("Enter A Valid Seat Numbers!");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Enter The Valid Data!");
                        }
                    }
                }
            }
        }
    }

    public int selectSeats( Scanner s,int seatCount, String grid, HashMap<Character, ArrayList<String>> seatsGrid)
    {
        HashMap<Character,ArrayList<String>> updatedSeats = new HashMap<>();
        HashMap<Character,ArrayList<String>> tempSeats = new HashMap<>();
        System.out.println();
        System.out.print("Enter The How Many Seats You Want To Book: ");
        int seatsCount = Integer.parseInt(s.nextLine());
        String[] seatNumbers = new String[seatsCount];
        for(int i=0; i<seatsCount; i++)
        {
            System.out.print("Enter "+(i+1)+" Seat Number: ");
            seatNumbers[i] = s.nextLine();

            String seatNumber = seatNumbers[i];
            char seatRow = '0';
            int seatColumn = -1;
            for(int j=0;j<seatNumber.length();j++)
            {
                char tempCharacter = seatNumber.charAt(j);
                if(Character.isLetter(tempCharacter))
                {
                    if (seatsGrid.containsKey(tempCharacter))
                    {
                        seatRow = tempCharacter;
                    }
                }
                else if(Character.isDigit(tempCharacter))
                {
                    int tempSeatColumn = Character.getNumericValue(tempCharacter);
                    if(tempSeatColumn > 0 && tempSeatColumn <= seatCount)
                    {
                        seatColumn =tempSeatColumn;
                    }
                }
            }
            if(seatRow!='0' && seatColumn!=-1)
            {
                if(seatsGrid.containsKey(seatRow))
                {
                    ArrayList<String> tempSeatsList;
                    if(tempSeats.containsKey(seatRow))
                    {
                        tempSeatsList = tempSeats.get(seatRow);

                        String[] splitGrid = grid.split("\\*");
                        long[] seats = new long[splitGrid.length];
                        long[] userPointOfViewSeats = new long[splitGrid.length];
                        for(int n = 0; n < splitGrid.length; n++)
                        {
                            seats[n] = Long.parseLong(splitGrid[n]);
                        }

                        long tempValue=0;
                        for(int index1=0; index1<seats.length; index1++)
                        {
                            tempValue += seats[index1];
                            userPointOfViewSeats[index1] = tempValue;
                        }

                        if( seatColumn <= userPointOfViewSeats[0])
                        {
                            int seatIndex = seatColumn - 1;
                            if(!tempSeatsList.get(seatIndex).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                        else if(seatColumn > userPointOfViewSeats[0] && seatColumn <= userPointOfViewSeats[1])
                        {
                            if(!tempSeatsList.get(seatColumn).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                        else if(seatColumn > userPointOfViewSeats[1] && seatColumn <= userPointOfViewSeats[2])
                        {
                            int seatIndex = seatColumn + 1;
                            if(!tempSeatsList.get(seatIndex).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                    }
                    else
                    {
                        tempSeatsList = new ArrayList<>(seatsGrid.get(seatRow));

                        String[] splitGrid = grid.split("\\*");
                        long[] seats = new long[splitGrid.length];
                        long[] userPointOfViewSeats = new long[splitGrid.length];
                        for(int n = 0; n < splitGrid.length; n++)
                        {
                            seats[n] = Long.parseLong(splitGrid[n]);
                        }

                        long tempValue=0;
                        for(int index1=0; index1<seats.length; index1++)
                        {
                            tempValue += seats[index1];
                            userPointOfViewSeats[index1] = tempValue;
                        }

                        if( seatColumn <= userPointOfViewSeats[0])
                        {
                            int seatIndex = seatColumn - 1;
                            if(!tempSeatsList.get(seatIndex).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                        else if(seatColumn > userPointOfViewSeats[0] && seatColumn <= userPointOfViewSeats[1])
                        {
                            if(!tempSeatsList.get(seatColumn).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                        else if(seatColumn > userPointOfViewSeats[1] && seatColumn <= userPointOfViewSeats[2])
                        {
                            int seatIndex = seatColumn + 1;
                            if(!tempSeatsList.get(seatIndex).equals("×"))
                            {
                                tempSeatsList.set(seatColumn, "×");
                            }
                            else
                            {
                                System.out.println("This Seat Is Already Booked");
                            }
                        }
                        tempSeats.put(seatRow, tempSeatsList);
                    }
                    updatedSeats.put(seatRow,tempSeatsList);
                }
            }
        }
        if(!updatedSeats.isEmpty())
        {
            for(Map.Entry<Character,ArrayList<String>> entry : updatedSeats.entrySet())
            {
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            System.out.println("Do You Want Book The Tickets?");
            System.out.print("(yes/no): ");
            String choice = s.nextLine();
            if(choice.equals("yes"))
            {
                for(Map.Entry<Character,ArrayList<String>> entry : updatedSeats.entrySet())
                {
                    Character key = entry.getKey();
                    if(seatsGrid.containsKey(key))
                    {
                        seatsGrid.put(key,entry.getValue());
                    }
                }
                return 1;
            }
        }
        return 0;
    }

}
