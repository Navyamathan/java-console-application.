import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// creating a class for the Admin Actions
class CustomerActions
{
    //this function manage the log in process by displaying the choices and execute based on the choices the user give
    public static Customer loginProcess()
    {
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");
        System.out.println();
        Customer status;
        System.out.println("1. SIGN UP\n2. LOGIN\n3. EXIT"); // print the choices
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(BookMyShowActions.s.nextLine()); // get the choice
        if(choice==1)
        {
            status = signUp(); // calls the singUp function (user will select signUp when user don't ever log in)
            return status;
        }
        else if(choice==2)
        {
            status = login(); // calls the login function (user will select login when user have already login)
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

    //this function manage the log in process
    public static Customer login() // get the parameters values when this function call (this function also returns the object Customer)
    {
        System.out.println();
        System.out.println("To Login Enter The Following Details...");
        System.out.println();
        for (Customer tempAccount : BookMyShow.getCustomerArrayList())  // for loop for checking the username and password one by one in the Customer arraylist
        {
            while(true)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String accNo = BookMyShowActions.s.nextLine(); // get the userName
                if (accNo.equals(tempAccount.getUserName())) // checks if the userName is Customer ArrayList
                {
                    System.out.print("Enter Password: ");
                    String pin = BookMyShowActions.s.nextLine(); // get the password
                    if (pin.equals(tempAccount.getPassword())) // checks if the password is Customer ArrayList
                    {
                        return tempAccount; // return the Customer object
                    }
                    else // if the password is wrong
                    {
                        System.out.println("Enter The Valid Password!");
                    }
                }
                else // if userName is wrong then else will be executed
                {
                    return null;  // return object null
                }
            }
        }
        return null;  //return null as a default
    }

    //this function manage the log in process
    public static Customer signUp()
    {
        String userName;
        String password;
        String location;
        System.out.println();
        System.out.println("To Sign Up Enter The Following Details...");
        System.out.println();
        System.out.print("Enter User Name: ");
        userName = BookMyShowActions.s.nextLine(); // get the userName
        System.out.print("Enter Password: ");
        password = BookMyShowActions.s.nextLine(); // get the password
        System.out.print("Enter Location: ");
        location = BookMyShowActions.s.nextLine(); // get the customer location
        // after get all the customer, create a Customer object type variable and store customer details in it
        Customer temp = new Customer(userName,password,location);
        BookMyShow.getCustomerArrayList().add(temp); // add temp into Customer ArrayList
        return temp; // return that Customer object type variable
    }

    // function to display the choices and execute based on the choices the user give
    public static void customerAction(Customer currentCustomer)
    {
        while(true)
        {
            System.out.println();
            System.out.println("1. AVAILABLE MOVIES\n2. VIEW TICKETS\n3. EXIT"); // print the choices
            System.out.print("Enter Your Choice: ");
            int choice = Integer.parseInt(BookMyShowActions.s.nextLine()); // get the choice
            if(choice == 1)
            {
                availableMovies(currentCustomer); // call availableMovies method to print all the available movies and future process
            }
            else if(choice == 2)
            {
                viewTicket(currentCustomer); // call viewTickets method to view ticket booked details
            }
            else if(choice == 3)
            {
                break; // if the user choice is 3 then break the loop and exit
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    // method to print available movies
    public static void availableMovies(Customer currentCustomer)
    {
        LocalDate localDate = LocalDate.now();
        label : while(true)
        {
            ArrayList<String> printedMovies = new ArrayList<>(); // stores the already printed data to later check that data are not repeatedly printed
            boolean movieFound = false;
            System.out.println();
            for(ArrayList<Movie> tempMovieList : BookMyShow.getMovieHashMap().values()) // loop for get the movie HashMap's value which is movie arrayList
            {
                for(Movie tempMovie : tempMovieList) // loop for get the movie Arraylist one by one to get all the shows in the movie arrayList
                {
                    // checks if the customer location and current date is equals to the data in the tempMovie
                    if(tempMovie.getLocation().equals(currentCustomer.getLocation()) && tempMovie.getDate().equals(localDate))
                    {
                        if(!movieFound)
                        {
                            System.out.println("Available Movies...");
                            movieFound =true;
                        }
                        // checks that current movie name is not already exist in the printedMovies arrayList(because we store printed movies in this arrayList)
                        if(!printedMovies.contains(tempMovie.getMovieName()))
                        {
                            System.out.println("* "+tempMovie.getMovieName()); // print the movie name(which is not already printed)
                            printedMovies.add(tempMovie.getMovieName()); // after print the movie name it will add to the printedMovie arrayList to check it later
                        }
                    }
                }
            }
            if(!movieFound) // if movieFound is false then  in customer's location and date there is no available movies
            {
                System.out.println("No Available Movie In Your Current Location Or Date!");
            }

            while(true)
            {
                System.out.println();
                System.out.println("Do You Want To Change Your Location Or Date?");
                System.out.print("(yes/no): ");
                String choice = BookMyShowActions.s.nextLine(); // get the user choice
                if(choice.equalsIgnoreCase("yes")) // if user choice is yes then the changeDateOrLocation will be called and then call ticketBooking method
                {
                    LocalDate updatedDate = changeDateOrLocation(currentCustomer); // call the changeDateOrLocation method and store the integer value in the flag variable that the function returns
                    if(updatedDate!=null)
                    {
                       localDate = updatedDate;
                       continue label;
                    }
                    else
                    {
                        continue label;
                    }
                }
                else if(choice.equalsIgnoreCase("no")) // if user choice is no then it directly call the ticket booking method
                {
                    boolean selectedMovieIsCorrect = false;
                    if(movieFound)
                    {
                        while(true)
                        {
                            System.out.println();
                            System.out.print("Enter The Movie You Want To Book: ");
                            String selectedMovie = BookMyShowActions.s.nextLine(); // get the movie name which user want from the printed available movies
                            for(ArrayList<Movie> tempMovieList : BookMyShow.getMovieHashMap().values())
                            {
                                for(Movie tempMovie : tempMovieList)
                                {
                                    // checks if the given selected movie name in the tempMovie Arraylist
                                    if (tempMovie.getMovieName().equals(selectedMovie))
                                    {
                                        selectedMovieIsCorrect = true;
                                        ticketBooking(tempMovieList,currentCustomer); // and call the ticketBooking function for future process
                                        return;
                                    }
                                }
                            }
                            // checks that the user entered movie name is correct or not
                            if(!selectedMovieIsCorrect)
                            {
                                System.out.println("Enter The Valid Movie Name!");
                            }
                        }
                    }
                    break;
                }
                else
                {
                    System.out.println("Enter The Valid Choice!");
                }
            }
        }
    }

    // method to change the customer's location and date if needed
    public static LocalDate changeDateOrLocation(Customer currentCustomer)
    {
        System.out.println();
        System.out.println("1. CHANGE DATE\n2. CHANGE LOCATION\n3. CHANGE BOTH\n4. EXIT"); // print the choices
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(BookMyShowActions.s.nextLine()); // get the choice
        LocalDate currentDate = LocalDate.now(); // gets the current date
        LocalDate localDate;
        while(true)
        {
            if (choice == 1)
            {
                System.out.println();
                System.out.print("Enter The Date: ");
                String date = BookMyShowActions.s.nextLine(); // get the date that customer wants to change
                localDate = LocalDate.parse(date, BookMyShow.getLocalDateFormatter());
                if(localDate.isBefore(currentDate)) // checks if the user given date is not a past date
                {
                    System.out.println("You Enter The Past Date, Enter The Valid Date!");
                    continue;
                }
               return localDate;
            }
            else if (choice == 2)
            {
                ArrayList<String> printedLocation = new ArrayList<>();
                System.out.println();
                System.out.println("Available Locations...");
                // for loop print the available locations
                for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values())
                {
                    if(!printedLocation.contains(tempTheatre.getTheatreLocation()))
                    {
                        System.out.println("* "+tempTheatre.getTheatreLocation());
                        printedLocation.add(tempTheatre.getTheatreLocation());
                    }
                }
                System.out.print("Enter The Location: ");
                String changeLocation = BookMyShowActions.s.nextLine(); // get the location that customer wants to change
                // checks is the user entered location is in the printedLocation array list
                if(printedLocation.contains(changeLocation))
                {
                    currentCustomer.setLocation(changeLocation);
                    return null;
                }
                else
                {
                    System.out.println("There is No Theatre In the Location You Entered, Enter The Valid Location!");
                }
            }
            else if (choice == 3)
            {
                ArrayList<String> printedLocation = new ArrayList<>();
                System.out.println();
                System.out.print("Enter The Date: ");
                String date = BookMyShowActions.s.nextLine(); // get the date that customer wants to change
                localDate = LocalDate.parse(date, BookMyShow.getLocalDateFormatter());
                if(localDate.isBefore(currentDate)) // checks if the user given date is not a past date
                {
                    System.out.println("You Enter The Past Date, Enter The Valid Date!");
                    continue;
                }
                System.out.println("Available Locations...");
                // for loop print the available locations
                for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values())
                {
                    if(!printedLocation.contains(tempTheatre.getTheatreLocation()))
                    {
                        System.out.println("* "+tempTheatre.getTheatreLocation());
                        printedLocation.add(tempTheatre.getTheatreLocation());
                    }
                }
                System.out.print("Enter The Location: ");
                String changeLocation = BookMyShowActions.s.nextLine(); // get the location that customer wants to change
                // checks is the user entered location is in the printedLocation array list
                if(printedLocation.contains(changeLocation))
                {
                    currentCustomer.setLocation(changeLocation);
                    return localDate;
                }
                else
                {
                    System.out.println("There is No Theatre In the Location You Entered, Enter The Valid Location!");
                }
            }
            else if(choice == 4)
            {
                break;
            }
            else
            {
                System.out.println("You Enter The InValid Choice!");
            }
        }
        return null;
    }

    // method for ticket booking
    public static void ticketBooking(ArrayList<Movie> selectedMovie,Customer currentCustomer)
    {
        label : while(true)
        {
            ArrayList<String> printedTheatres = new ArrayList<>();
            boolean theatreFound = false;
            boolean showsFound = false;
            boolean enteredShowIsCorrect = false;

            for(Movie tempMovie :selectedMovie)
            {
                // checks that current theatre name is not already exist in the printedTheatres arrayList(because we store printed theatres in this arrayList)
                if (!printedTheatres.contains(tempMovie.getTheatre().getTheatreName()))
                {
                    if (!theatreFound)
                    {
                        System.out.println("Available Theatres...");
                        theatreFound = true;
                    }
                    System.out.println("* " + tempMovie.getTheatre().getTheatreName()); // print the theatre name(which is not already printed)
                    printedTheatres.add(tempMovie.getTheatre().getTheatreName()); // after print the movie name it will add to the printedMovie arrayList to check it later
                }
            }
            if(!theatreFound) // if theatreFound is false then in given movie is not play in ant theatres
            {
                System.out.println("Theatre Not Found!");
                break;
            }

            System.out.print("Enter The Theatre You Want: ");
            String theatreName = BookMyShowActions.s.nextLine(); // get the theatre name that user want from the printed available theatres
            for(Movie tempMovie :selectedMovie)
            {
                if(tempMovie.getTheatre().getTheatreName().equals(theatreName))
                {
                    if(!showsFound)
                    {
                        System.out.println("Available Shows...");
                        showsFound =true;
                    }
                    System.out.println("* "+tempMovie.getScreen().getScreenName());  // print the screen name(which is not already printed)
                    System.out.println("* "+tempMovie.getShow().getStartingTime()); // print the show's startingTime and endingTime (which is not already printed)
                    System.out.println("--------------------------");
                }
            }
            if(!showsFound) // if showFound is false then in given theatre have no shows
            {
                System.out.println("Enter Valid Theatre Name!");
                continue ;
            }

            while(true)
            {
                System.out.print("Enter The Timing You Want: ");
                String startingTime = BookMyShowActions.s.nextLine(); // get the show starting time
                LocalTime localStartingTime = LocalTime.parse(startingTime,BookMyShow.getLocalTimeFormatter());
                for(Movie tempMovie : selectedMovie)
                {
                    // checks if the user given timing and screen name is in the tempMovie
                    if(localStartingTime.equals(tempMovie.getShow().getStartingTime()))
                    {
                        enteredShowIsCorrect = true;
                        System.out.println("Available seats: ");
                        Utilities.printSeats(tempMovie.getShow().getSeatsGrid()); // prints the available seats

                        while (true)
                        {
                            System.out.println();
                            System.out.print("Enter The How Many Seats You Want To Book: ");
                            int seatsCount = Integer.parseInt(BookMyShowActions.s.nextLine()); // get how many ticket the customer want to book
                            if (seatsCount <= 0)
                            {
                                System.out.println("Enter The Valid Seat Count!");
                                continue;
                            }
                            // store the ticket data in a Ticket object for future process
                            Tickets tickets = new Tickets(tempMovie.getTheatre().getTheatreName(),tempMovie.getMovieName(),tempMovie.getTheatre().getTheatreLocation(),tempMovie.getScreen().getScreenName(),tempMovie.getShow().getStartingTime(),tempMovie.getShow().getPrice() * seatsCount);
                            // call the selectSeats method and store the value in the variable status that the function return
                            int status = selectSeats(tempMovie.getScreen().getSeatsCount(), seatsCount, tempMovie.getScreen().getGrid(), tempMovie.getShow().getSeatsGrid(),tickets);

                            // call selectedSeats function to calculate and identify and update the given seatNumbers in a proper way (and also stores the value that the function returns)
                            if (status == 1) // if status is equal to 1
                            {
                                currentCustomer.getTicketList().add(tickets);
                                System.out.println("Ticket Booked Successfully!");
                                return;
                            }
                            else if (status == -1)
                            {
                                break label;
                            }
                            else
                            {
                                System.out.println("Enter A Valid Seat Numbers!");
                            }
                        }
                    }
                }
                // check if the entered show time is correct or not
                if(!enteredShowIsCorrect)
                {
                    System.out.println("Enter A Valid Show!");
                }
            }
        }
    }

    // method to calculate,identify and update the given seatNumbers in a proper way in a seatsGrid HashMap
    public static int selectSeats( int screenSeatsCount,int seatsCount, String grid, HashMap<Character, ArrayList<String>> seatsGrid,Tickets tickets)
    {
        // create a HashMap to store the values grids like a duplicate one(we use this hashMap to first edit the changes in it and then ask the confirmation from the user and then add that changes in the duplicate to seats grid)
        HashMap<Character,ArrayList<String>> duplicateSeats = new HashMap<>();

        // for loop to store data of seatsGrid HashMap to duplicate by entrySet method
        for(Map.Entry<Character, ArrayList<String>> entry : seatsGrid.entrySet())
        {
            char key = entry.getKey(); // get the key
            ArrayList<String> tempValue = entry.getValue(); // get the value
            ArrayList<String> values = new ArrayList<>(tempValue); // store values of ArrayList in a deep copy way
            duplicateSeats.put(key,values); // store key and values in a duplicateSeats
        }

        ArrayList<String> seatNumbers = new ArrayList<>(); // store the all seat numbers that customer want to book
        int i=0;
        while (seatsCount > 0)
        {
            System.out.println();
            System.out.print("Enter Seat Number " + (i+1) + ": ");
            String seatNumber = BookMyShowActions.s.nextLine(); // get the seat number
           seatNumbers.add(seatNumber); // add seat number one by one in the seatNumbers array List

            // separate the seat row and numbers of a tempSeats by CharAt() to get the seat row (character) and use subString() to get the seat number (int)
            char seatRow = seatNumber.charAt(0);
            int seat_number = Integer.parseInt(seatNumber.substring(1));

            if (seat_number <= 0 || seat_number > screenSeatsCount) // checks if the seat_number is greater than zero or seat_number greater than the total seatsCount to verify that seat_number is valid or not
            {
                System.out.println("Enter The Valid Seat Number!");
                continue;
            }

            // checks if the generated seat row is in the seatsGrid
            if (seatsGrid.containsKey(seatRow))
            {
                String[] splitGrid = grid.split("\\*"); // use split methode to separate * from the grid and each one separately
                long[] seats = new long[splitGrid.length];
                // for loop to convert grids String to long one by one
                for (int k = 0; k < splitGrid.length; k++) {
                    seats[k] = Long.parseLong(splitGrid[k]);
                }

                long[] userPointOfViewSeats = new long[splitGrid.length];
                long tempValue = 0;
                for (int l = 0; l < seats.length; l++) // for loop to get the user point of view seat numbers in a long[]  to use it later for ticket booking
                {
                    tempValue += seats[l];
                    userPointOfViewSeats[l] = tempValue;
                }

                // condition check that how can index can calculate for seat_numbers based on the which position it placed at
                int index;
                if (seat_number <= userPointOfViewSeats[0])
                {
                    index = seat_number - 1;
                }
                else if (seat_number > userPointOfViewSeats[0] && seat_number <= userPointOfViewSeats[1])
                {
                    index = seat_number;
                }
                else
                {
                    index = seat_number + 1;
                }
                // after calculate the index here checks and change the seats by where it is booked seats or not for future process
                ArrayList<String> selectedSeat = seatsGrid.get(seatRow); // temp seats arraylist to store the changes
                if (selectedSeat.get(index).equals("x")) // if × find then it is booked seat
                {
                    System.out.println("This Seats Is Already Booked,Enter The Valid Seat Number!");
                    continue;
                }
                else
                {
                    selectedSeat.set(index, "x"); // set the seat index as × to book the tickets
                }
                duplicateSeats.put(seatRow, selectedSeat); // after checked by many condition is it still true then it add to the hashMap duplicateSets
                Utilities.printSeats(duplicateSeats);
            }
            seatsCount--;
            i++;
        }
        while(true)
        {
            System.out.println();
            System.out.println("Do You Want Book The Tickets?");
            System.out.print("(yes/no): ");
            String choice = BookMyShowActions.s.nextLine(); // get a choice to finally verify that the user want to book tickets
            if(choice.equalsIgnoreCase("yes")) // if user print yes to book then duplicate hashmap is make the changes(booked seats) in the original seatsGrid hashmap one by one
            {
                tickets.setBookedSeats(seatNumbers);
                // for loop to store changes of duplicateSeats updated to seatsGrid by entrySet method
                for(Map.Entry<Character,ArrayList<String>> entry : duplicateSeats.entrySet())
                {
                    Character key = entry.getKey();
                    if(seatsGrid.containsKey(key))
                    {
                        seatsGrid.put(key,entry.getValue());
                    }
                }
                System.out.println();
                System.out.println("Booked Seats: ");
                Utilities.printSeats(seatsGrid);
                return 1; // if successfully updated return 1
            }
            else if(choice.equalsIgnoreCase("no")) // if user print no to book seats then it returns -2
            {
                System.out.println("Ticket Booking Canceled!");
                return -1; //
            }
            else
            {
                System.out.println("Enter The Valid Data!");
            }
        }
    }

    // method to print the ticket booking details
    public static void viewTicket(Customer currentCustomer)
    {
        System.out.println();
        for(Tickets tempTicket : currentCustomer.getTicketList()) // loop to get the currentCustomers ticket ArrayList one by one
        {
            System.out.println("Theatre: "+tempTicket.getTheatreName());
            System.out.println("Movie: "+tempTicket.getMovieName());
            System.out.println("Location: "+tempTicket.getLocation());
            System.out.println("Screen: "+tempTicket.getScreenName());
            System.out.println("Show Time: "+tempTicket.getStartingTime());
            System.out.println("Ticket Price: "+tempTicket.getTicketPrice());
            System.out.print("Booked Seats: "+tempTicket.getBookedSeats());
            System.out.println();
        }
    }

}
