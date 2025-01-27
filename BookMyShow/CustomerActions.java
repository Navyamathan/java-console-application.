import java.time.LocalDate;
import java.util.*;

// creating a class for the Admin Actions
class CustomerActions
{
    //this function manage the log in process by displaying the choices and execute based on the choices the user give
    public static Customer loginProcess(Scanner s)
    {
        Customer status;
        System.out.println("1. SIGN UP\n2. LOGIN\n3. EXIT"); // print the choices
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(s.nextLine()); // get the choice
        if(choice==1)
        {
            status = signUp(s); // calls the singUp function (user will select signUp when user don't ever log in)
            return status;
        }
        else if(choice==2)
        {
            status = login(s); // calls the login function (user will select login when user have already login)
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
    public static Customer login(Scanner s) // get the parameters values when this function call (this function also returns the object Customer)
    {
        int attempts = 0; // variable to manage the attempts of login process
        System.out.println();
        System.out.println("WELCOME TO CUSTOMER LOGIN SYSTEM...");

        for (Customer tempAccount : BookMyShow.getCustomerArrayList())  // for loop for checking the username and password one by one in the Customer arraylist
        {
            while (attempts < 3) // this loop will exit when attempts is greater than 3
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String accNo = s.nextLine(); // get the userName
                if (accNo.equals(tempAccount.getUserName())) // checks if the userName is Customer ArrayList
                {
                    System.out.print("Enter Password: ");
                    String pin = s.nextLine(); // get the password
                    if (pin.equals(tempAccount.getPassword())) // checks if the password is Customer ArrayList
                    {
                        return tempAccount; // return the Customer object
                    }
                    else // if the password is wrong
                    {
                        attempts++; // increment the attempts
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3) // if attempts is equal to 3
                        {
                            Customer ob = new Customer(null, null,null); // create a new Customer object a values of null
                            return ob; // return the empty object
                        }
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
    public static Customer signUp(Scanner s)
    {
        String userName;
        String password;
        String location;
        System.out.println();
        System.out.println("To Sign Up Enter The Following Details...");
        System.out.println();
        System.out.print("Enter User Name: ");
        userName = s.nextLine(); // get the userName
        System.out.print("Enter Password: ");
        password = s.nextLine(); // get the password
        System.out.print("Enter Location: ");
        location = s.nextLine(); // get the customer location
        // after get all the customer, create a Customer object type variable and store customer details in it
        Customer temp = new Customer(userName,password,location);
        BookMyShow.getCustomerArrayList().add(temp); // add temp into Customer ArrayList
        return temp; // return that Customer object type variable
    }

    // function to display the choices and execute based on the choices the user give
    public static void customerAction(Scanner s,Customer currentCustomer)
    {
        availableMovies(s,currentCustomer);
    }

    // method to print available movies
    public static void availableMovies(Scanner s,Customer currentCustomer)
    {
        ArrayList<String> printedMovies = new ArrayList<>(); // stores the already printed data to later check that data are not repeatedly printed
        boolean movieFound = false;
        LocalDate currentDate = LocalDate.now(); // gets the current date
        System.out.println();
        for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values()) // loop for get the movie HashMap's value which is movie arrayList
        {
            for(Movie tempMovie : tempMovieList) // loop for get the movie Arraylist one by one to get all the shows in the movie arrayList
            {
                // checks if the customer location and current date is equals to the data in the tempMovie
                if(tempMovie.getShow().getLocation().equals(currentCustomer.getLocation()) && tempMovie.getShow().getDate().equals(currentDate))
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
            String choice = s.nextLine(); // get the user choice
            if(choice.equals("yes")) // if your choice is yes then the changeDateOrLocation will the called
            {
               int flag = changeDateOrLocation(s,currentCustomer); // call the changeDateOrLocation method and store the integer value in the flag variable that the function returns
               if(flag!=0) // if flag is not equal to one means that the available movies are printed already
               {
                   System.out.println();
                   System.out.print("Enter The Movie You Want To Book: ");
                   String selectedMovie = s.nextLine(); // get the movie name which user want from the printed available movies

                   for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
                   {
                       for (Movie tempMovie : tempMovieList)
                       {
                           // checks if the given selected movie name in the tempMovie Arraylist
                           if (tempMovie.getMovieName().equals(selectedMovie))
                           {
                               ticketBooking(s, tempMovieList); // and call the ticketBooking function for future process
                               break;
                           }
                       }
                   }
               }
            }
            else if(choice.equals("no"))
            {
                if(movieFound)
                {
                    System.out.println();
                    System.out.print("Enter The Movie You Want To Book: ");
                    String selectedMovie = s.nextLine(); // get the movie name which user want from the printed available movies
                    for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
                    {
                        for(Movie tempMovie : tempMovieList)
                        {
                            // checks if the given selected movie name in the tempMovie Arraylist
                            if (tempMovie.getMovieName().equals(selectedMovie))
                            {
                                ticketBooking(s, tempMovieList); // and call the ticketBooking function for future process
                                break;
                            }
                        }
                    }
                }
                break; // break the while loop
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    // method to change the customer's location and date if needed
    public static int changeDateOrLocation(Scanner s,Customer currentCustomer)
    {
        System.out.println();
        System.out.println("1. CHANGE DATE\n2. CHANGE LOCATION\n3. CHANGE BOTH"); // print the choices
        System.out.print("Enter Your Choice: ");
        int choice = Integer.parseInt(s.nextLine()); // get the choice
        boolean movieFound = false;
        ArrayList<String> printedMovies = new ArrayList<>(); // stores the already printed data to later check that data are not repeatedly printed
        if(choice == 1)
        {
            System.out.println();
            System.out.print("Enter The Date: ");
            String date = s.nextLine(); // get the date that customer wants to change
            LocalDate localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
            System.out.println();
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    // checks if the customer location and current date is equals to the data in the tempMovie
                    if(tempMovie.getShow().getLocation().equals(currentCustomer.getLocation()) && tempMovie.getShow().getDate().equals(localDate))
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
                System.out.println("No Available Movie!");
                return 0;
            }
            return 1;
        }
        else if(choice == 2)
        {
            System.out.println();
            LocalDate currentDate = LocalDate.now(); // gets the current date
            System.out.print("Enter The Location: ");
            String changeLocation = s.nextLine(); // get the location that customer wants to change
            System.out.println();
            for (ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for (Movie tempMovie : tempMovieList)
                {
                    // checks if the customer location and current date is equals to the data in the tempMovie
                    if (tempMovie.getShow().getLocation().equals(changeLocation) && tempMovie.getShow().getDate().equals(currentDate))
                    {
                        if(!movieFound)
                        {
                            System.out.println("Available Movies...");
                            movieFound =true;
                        }
                        // checks that current movie name is not already exist in the printedMovies arrayList(because we store printed movies in this arrayList)
                        if (!printedMovies.contains(tempMovie.getMovieName()))
                        {
                            System.out.println("* " + tempMovie.getMovieName()); // print the movie name(which is not already printed)
                            printedMovies.add(tempMovie.getMovieName()); // after print the movie name it will add to the printedMovie arrayList to check it later
                        }
                    }
                }
            }
            if (!movieFound) // if movieFound is false then  in customer's location and date there is no available movies
            {
                System.out.println("No Available Movie!");
                return 0;
            }
            return 1;
        }
        else if(choice == 3)
        {
            System.out.println();
            System.out.print("Enter The Date: ");
            String date = s.nextLine(); // get the date that customer wants to change
            LocalDate localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
            System.out.print("Enter The Location: ");
            String location = s.nextLine(); // get the location that customer wants to change
            System.out.println();
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for(Movie tempMovie : tempMovieList)
                {
                    if(!movieFound)
                    {
                        System.out.println("Available Movies...");
                        movieFound =true;
                    }
                    // checks if the customer location and current date is equals to the data in the tempMovie
                    if(tempMovie.getShow().getLocation().equals(location) && tempMovie.getShow().getDate().equals(localDate))
                    {
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

    // method for ticket booking
    public static void ticketBooking(Scanner s, ArrayList<Movie> selectedMovie)
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
                    if (!theatreFound) {
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
            String theatreName = s.nextLine(); // get the theatre name that user want from the printed available theatres
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
                    System.out.println("* "+tempMovie.getShow().getStartingTime()+" - "+tempMovie.getShow().getEndingTime()); // print the show's startingTime and endingTime (which is not already printed)
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
                String timing= s.nextLine(); // get the showtime that user want
                for(Movie tempMovie : selectedMovie)
                {
                    // here store the current showtime as a String to compare with the user choose showtime
                    String tempTiming =tempMovie.getShow().getStartingTime()+" - "+tempMovie.getShow().getEndingTime();
                    // checks if the user given timing and screen name is in the tempMovie
                    if(tempTiming.equals(timing))
                    {
                        enteredShowIsCorrect = true;
                        System.out.println("Available seats: ");
                        Utilities.printSeats(tempMovie.getShow().getSeatsGrid()); // prints the available seats

                        while (true)
                        {
                            System.out.println();
                            System.out.print("Enter The How Many Seats You Want To Book: ");
                            int seatsCount = Integer.parseInt(s.nextLine()); // get how many ticket the customer want to book
                            if (seatsCount < 0)
                            {
                                System.out.println("Enter The Valid Seat Count!");
                                continue;
                            }
                            String[] seatNumbers = new String[seatsCount]; // store the seat Numbers that customer gave
                            for (int i = 0; i < seatsCount; i++)  // for loop to get the seat numbers
                            {
                                System.out.print("Enter " + (i + 1) + " Seat Number: ");
                                seatNumbers[i] = s.nextLine(); // get the seat numbers
                            }
                            // call selectedSeats function to calculate and identify and update the given seatNumbers in a proper way (and also stores the value that the function returns)
                            int status = selectSeats(s, tempMovie.getScreen().getGrid(), tempMovie.getShow().getSeatsGrid(), seatNumbers);
                            if (status == 1) // if status is equal to 1
                            {
                                Utilities.printSeats(tempMovie.getShow().getSeatsGrid());
                                System.out.println("Ticket Booked Successfully!");
                                break label;
                            }
                            else if (status == -1) // if status is equal to -1
                            {
                                System.out.println("This Seats Is Already Booked!");
                            }
                            else if(status == -2)
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
                if(!enteredShowIsCorrect)
                {
                    System.out.println("Enter A Valid Show!");
                }
            }
        }
    }

    // method to calculate,identify and update the given seatNumbers in a proper way in a seatsGrid HashMap
    public static int selectSeats( Scanner s,String grid, HashMap<Character, ArrayList<String>> seatsGrid, String[] seatNumber)
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

        // this for loop iterates each seatNumbers one by one to calculate
        for(String tempSeatNumber : seatNumber)
        {
            // separate the seat row and numbers of a tempSeats by CharAt() to get the seat row (character) and use subString() to get the seat number (int)
            char seatRow = tempSeatNumber.charAt(0);
            int seat_number = Integer.parseInt(tempSeatNumber.substring(1));

            // checks if the generated seat row is in the seatsGrid
            if (seatsGrid.containsKey(seatRow))
            {
                String[] splitGrid = grid.split("\\*"); // use split methode to separate * from the grid and each one separately
                long[] seats = new long[splitGrid.length];
                // for loop to convert grids String to long one by one
                for (int k = 0; k < splitGrid.length; k++)
                {
                    seats[k] = Long.parseLong(splitGrid[k]);
                }

                long[] userPointOfViewSeats = new long[splitGrid.length];
                long tempValue = 0;
                for (int l = 0; l < seats.length; l++) // for loop to get the user point of view seat numbers in a long[]  to use it later for ticket booking
                {
                    tempValue += seats[l];
                    userPointOfViewSeats[l] = tempValue;
                }

                if (seat_number < 0 || seat_number > tempValue) // checks if the seat_number is greater than zero or seat_number greater than the tempValues to verify that seat_number is valid or not
                {
                    return 0;
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
                if (selectedSeat.get(index).equals("×")) // if × find then it is booked seat
                {
                    return -1;
                }
                else
                {
                    selectedSeat.set(index, "×"); // set the seat index as × to book the tickets
                }
                duplicateSeats.put(seatRow,selectedSeat); // after checked by many condition is it still true then it add to the hashMap duplicateSets
            }
        }
        if(!duplicateSeats.isEmpty()) // checks if the duplicateSeats is not empty because than only we can understand that it is edited or not( if edited(!null) it is valid -- if not edited(null) it is in valid )
        {
            // for loop to print edited duplicateSeats by entrySet method
            for(Map.Entry<Character,ArrayList<String>> entry : duplicateSeats.entrySet())
            {
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            while(true)
            {
                System.out.println("Do You Want Book The Tickets?");
                System.out.print("(yes/no): ");
                String choice = s.nextLine(); // get a choice to finally verify that the user want to book tickets
                if(choice.equalsIgnoreCase("yes"))
                {
                    // for loop to store changes of duplicateSeats updated to seatsGrid by entrySet method
                    for(Map.Entry<Character,ArrayList<String>> entry : duplicateSeats.entrySet())
                    {
                        Character key = entry.getKey();
                        if(seatsGrid.containsKey(key))
                        {
                            seatsGrid.put(key,entry.getValue());
                        }
                    }
                    return 1; // if successfully updated return 1
                }
                else if(choice.equalsIgnoreCase("no"))
                {
                    return -2;
                }
                else
                {
                    System.out.println("Enter The Valid Data!");
                }
            }
        }
        return 0;
    }

}
