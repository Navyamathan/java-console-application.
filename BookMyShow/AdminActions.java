import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

// creating a class for the Admin Actions
class AdminActions
{
    //this function manage the log in process
    public static Admin adminLogin() // get the parameters values when this function call (this function also returns the object Admin)
    {
        System.out.println("WELCOME TO ADMIN LOGIN SYSTEM...");

        for (Admin tempAccount : BookMyShow.getAdminArrayList())  // for loop for checking the username and password one by one in the Admin arraylist
        {
            while(true)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String username = BookMyShowActions.s.nextLine(); // get the userName
                if (username.equals(tempAccount.getUserName())) // checks if the userName is admin ArrayList
                {
                    System.out.print("Enter Password: ");
                    String password = BookMyShowActions.s.nextLine(); // get the password
                    if (password.equals(tempAccount.getPassword())) // checks if the password is admin ArrayList
                    {
                        return tempAccount; // return the Admin object
                    }
                    else // if the password is wrong
                    {
                        System.out.println("Enter The Valid Password!");
                    }
                }
                else // if userName is wrong then else will be executed
                {
                    return null; // return object null
                }
            }
        }
        return null; //return null as a default
    }

    //this function display the choices and execute based on the choices the user give
    public static void adminAction()
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true)
        {
            System.out.println();
            System.out.println("1. ADD THEATRE \n2. VIEW THEATRE DETAILS \n3. ADD MOVIE \n4. VIEW MOVIE \n5. EXIT"); // print the choice
            System.out.print("Enter Your Choice: ");
            int choice = Integer.parseInt(BookMyShowActions.s.nextLine()); // get the choice
            if (choice == 1)
            {
                addTheatre(); // call the addTheatre method to add a theatres in a theatre array list
            }
            else if(choice == 2)
            {
                viewTheatre(); // call the viewTheatre method to view all the added theatres
            }
            else if (choice == 3)
            {
                addMovie(); // call the addMovie method to add a movies in a movie array list
            }
            else if (choice == 4)
            {
                viewMovie(); // call the viewMovie method to view all the added Movies
            }
            else if (choice == 5)
            {
                break; // if the user choice is 5 then break the loop and exit
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    // method for add a theatre and screen to the theatre and screen HashMap
    public static void addTheatre()
    {
        String theatreName;
        int screenCount;
        System.out.println();
        System.out.println("To Add Theatres Enter The Following Details...");
        System.out.println();

        while(true) // loop to check theatre name
        {
            System.out.print("Enter Theater Name: ");
            theatreName = BookMyShowActions.s.nextLine(); // gets the theatre name
            if (BookMyShow.getTheatreHashMap().containsKey(theatreName))
            {
                System.out.println("This Theatre Is Already Exist, Enter The Valid Theatre Name!");
            }
            else
            {
                break;
            }
        }

        System.out.print("Enter Location Of The Theater: ");
        String theatreLocation = BookMyShowActions.s.nextLine(); // gets the theatre location

        while(true) // loop to check screen count
        {
            System.out.print("Enter Theater Screen Count: ");
            screenCount = Integer.parseInt(BookMyShowActions.s.nextLine()); // gets the screen count
            if(screenCount <= 0)
            {
                System.out.println("Enter The Valid Screen Count!");
            }
            else
            {
                break;
            }
        }

        Theatre theatre = new Theatre(theatreName, theatreLocation, screenCount); // create a new theatre object and put the theatre data in it
        BookMyShow.getTheatreHashMap().put(theatreName, theatre); // add a created object to the theatre HashMap

        for (int i = 1; i <= screenCount; i++) // for loop to get the data for screen and store the screen data into the screen HashMap
        {
            String screenName;
            int seatsCount;
            String seatsGrid;
            System.out.println();
            System.out.println("Enter Details About Screen Number " + i + "...");

            while(true) // loop to check screen name
            {
                System.out.print("Enter Screen Name: "); // gets the screen name
                screenName = BookMyShowActions.s.nextLine();
                if(theatre.getScreenHashMap().containsKey(screenName))
                {
                    System.out.println("This Screen Is Already Exist, Enter The Valid Screen Name!");
                }
                else
                {
                    break;
                }
            }

            while(true) // loop to check seat number
            {
                System.out.print("Enter The Number Of Seats: ");
                seatsCount = Integer.parseInt(BookMyShowActions.s.nextLine()); // gets the number of seats
                if(seatsCount <= 0)
                {
                    System.out.println("Enter The Valid Seat Count!");
                }
                else
                {
                    break;
                }
            }

            while (true) // while loop for get the seats grid repeatedly until user enter the valid seats grid
            {
                System.out.print("Enter Seats Grid: ");
                seatsGrid = BookMyShowActions.s.nextLine(); // gets the seats grid as a string
                HashMap<Character, ArrayList<String>> seats = Utilities.calculateGrid(seatsCount, seatsGrid); // call the calculateGrid function to calculate the String grid to valid seatsGrid HashMap
                if (seats != null) // checks if the seats is not null
                {
                    theatre.getScreenHashMap().put(screenName, new Screen(screenName, seatsCount, seatsGrid, seats)); // put the key and values for screen HashMap
                    break; // breaks the while loop
                }
            }
        }
        System.out.println();
        System.out.println("Theatre Added Successfully!");
    }

    // method to print all the theatres and its screens
    public static void viewTheatre()
    {
        System.out.println("...Details Of Theatre...");
        for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values()) // loop to manage the theatre HashMap one by one to print the theatre one by one
        {
            System.out.println();
            System.out.println("Theatre Name: "+tempTheatre.getTheatreName());
            System.out.println("Theatre Location: "+tempTheatre.getTheatreLocation());
            System.out.println("Screen Count :"+tempTheatre.getScreenCount());
            int i=1;
            for(Screen tempScreen : tempTheatre.getScreenHashMap().values()) // loop to manage the screen HashMap one by one to print the screen one by one
            {
                System.out.println();
                System.out.println("Details of Screen "+i+"...");
                System.out.println("Screen Name: "+tempScreen.getScreenName());
                System.out.println("Seats Count: "+tempScreen.getSeatsCount());
                System.out.println("Seats Arrangements: ");
                Utilities.printSeats(tempScreen.getSeatsGrid());
                i++;
            }
        }
    }

    // method to get movies details and store it into the movie HashMap
    public static void addMovie()
    {
        Screen screen = null;
        Theatre theatre = null;
        Show show;
        HashMap<Character,ArrayList<String>> showSeatsGrid = null;
        String location;
        LocalDate localDate;
        String theatreName;
        String screenName;
        boolean theatreFound = false;
        System.out.println();
        System.out.println("Enter the Following Details to Add Movie...");
        System.out.println();
        System.out.print("Enter The Movie Name: ");
        String movieName = BookMyShowActions.s.nextLine(); // get the movie name
        System.out.print("Enter The Date( dd_MM_yyyy ): ");
        String date = BookMyShowActions.s.nextLine(); // get the movie date
        localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
        System.out.print("Enter The Duration( HH:mm ): ");
        String duration = BookMyShowActions.s.nextLine(); // get the duration of the movie
        LocalTime localTime = LocalTime.parse(duration,BookMyShow.getLocalTimeFormatter());
        System.out.print("Enter the Price: ");
        long price = Long.parseLong(BookMyShowActions.s.nextLine()); // get the movie price

        while(true) // while loop will break when the theatreFound is true
        {
            System.out.print("Enter The Location: ");
            location = BookMyShowActions.s.nextLine(); // get the location

            for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values()) // for loop to print all available theatre based on the given location
            {
                if(tempTheatre.getTheatreLocation().equals(location)) // checks that given location is matches any theatre location that stored in theatre HashMap
                {
                    if(!theatreFound)
                    {
                        System.out.println("Available Theatres...");
                        theatreFound =true;
                    }
                    System.out.println("* "+tempTheatre.getTheatreName()); // prints the available theatres
                }
            }
            if(theatreFound) // if theatreFound is true then the there are some available theatres
            {
                break;
            }
            else // if theatreFound is false
            {
                System.out.println("There Is No Available Theatre In The Location You Select");
            }
        }

        while(true) // loop runs until the theatre are found
        {
            System.out.print("Enter The Name Of The Theatre You Want To Add: ");
            theatreName = BookMyShowActions.s.nextLine(); // get the theatre which is user want in the printed available theatre
            if (BookMyShow.getTheatreHashMap().containsKey(theatreName)) // check the theatre name is in the theatre HashMap
            {
                System.out.println("Available Screens... ");
                theatre = BookMyShow.getTheatreHashMap().get(theatreName); // get the theatre HashMap's value and store it in the theatre object variable to get the screen
                for (String tempScreen : theatre.getScreenHashMap().keySet()) // loop for print the available screen on by one by the theatre we get
                {
                    System.out.println("* "+tempScreen);
                }
            }
            if(theatre != null) // if the theatre is not null then the theatre have a value and by that we can print available theatres
            {
                break; // breaks the while loop
            }
            else // if theatre is null it don't have any values then we cant print the available screens
            {
                System.out.println("Enter The Valid Theatre Name!");
            }
        }

        while(true) // loop runs until the screen are found
        {
            System.out.print("Enter The Name Of The Screen You Want To Add: ");
            screenName = BookMyShowActions.s.nextLine(); // get the name of the screen that user want in the printed available screens

            if (theatre.getScreenHashMap().containsKey(screenName)) // checks if the screen name found in the screen HashMap
            {
                screen = theatre.getScreenHashMap().get(screenName); // get the screen from the hashMap and stores it in the screen object
                showSeatsGrid = screen.getSeatsGrid(); // get the seats grid from the screen and stores it in the showSeatsGrid variable
            }

            if(screen != null) // if the screen is not null then the screen have a value and by that we can print available screens
            {
                break; // breaks the while loop
            }
            else // if screen is null it don't have any values then we cant go to the screen process future
            {
                System.out.println("Enter The Valid Screen Name!");
            }
        }

        while(true) // loop runs util the given show data is not already exist
        {
            boolean sameScreenFlag = false;
            boolean differentScreenFlag = false;
            System.out.print("Enter The Show Starting Time( HH:mm ): ");
            String startingTime = BookMyShowActions.s.nextLine(); // get the show starting time
            LocalTime localStartingTime = LocalTime.parse(startingTime,BookMyShow.getLocalTimeFormatter()); // convert the given string time into LocalTime (which is built in function)
            LocalTime localEndingTime = localStartingTime.plusHours(localTime.getHour()).plusMinutes(localTime.getMinute()).plusMinutes(30); // calculate the endTime by the given startingTime

            // for loop to check that the same screen don't have a show in same time range and there is any other screen starts in the same starting time as the user given starting time
            for (Show tempShow : screen.getShowHashSet())
            {
                if (tempShow.getDate().equals(localDate)) // checks is the date of the show already exist and date of the show admin provide is equal or not
                {
                    if (tempShow.getScreenName().equals(screenName)) // checks that in the given screen is there is any other show is already exist in that same screen
                    {
                        if (tempShow.getStartingTime().isBefore(localEndingTime) && tempShow.getEndingTime().isAfter(localStartingTime)) // checks the time range by using isBefore and isAfter method
                        {
                            sameScreenFlag = true;
                        }
                    }
                    if (!tempShow.getScreenName().equals(screenName))
                    {
                        if (localStartingTime.equals(tempShow.getStartingTime())) // checks that the starting time with the show already exist but in different screen
                        {
                            differentScreenFlag = true;
                        }
                    }
                }
            }

            if(sameScreenFlag) // if flag is true then the show is already exist int this time range
            {
                System.out.println("The Show Is Already Exist In Given Time Range, Enter A Valid Starting Time!");
            }
            else if(differentScreenFlag) // if flag is true when there is any other screen starts in the same starting time as the user given starting time
            {
                System.out.println("The Time You Provided Is The starting Time Of Another Screen's Show, Enter The Valid Starting Time!");
            }
            else // if flag false then there is no show is existed in that time so we can add shw data in the show arrayList
            {
                show = new Show(localDate,localStartingTime, localEndingTime,screenName,showSeatsGrid,price); // in show object store the show data
                screen.getShowHashSet().add(show); // add show object in show arrayList
                break; // breaks the while loop
            }
        }

        Movie tempMovie = new Movie(movieName,localDate,localTime,location,theatre,screen,show); // create a movie abject variable and store movie data we get all along in this object
        // this is true when the given movie name is already exist so here we don't have to create a new arrayList we just have to get the existing arrayList add the tempMovie in it
        if(BookMyShow.getMovieHashMap().containsKey(movieName))
        {
            BookMyShow.getMovieHashMap().get(movieName).add(tempMovie);
            System.out.println("Movie Added Successfully!");
        }
        // this block will execute when the given movie name is not already exist so here we have to create a new arrayList and add the tempMovie in it
        else
        {
            BookMyShow.getMovieHashMap().put(movieName,new ArrayList<>());
            BookMyShow.getMovieHashMap().get(movieName).add(tempMovie);
            System.out.println("Movie Added Successfully!");
        }
    }

    // method is to print all movie data in the movie HashMap
    public static void viewMovie()
    {
        System.out.println();
        System.out.println("...The Details Of Movies...");
        for(String tempMovieKey : BookMyShow.getMovieHashMap().keySet()) // loop for get the movie HashMap's value which is movie arrayList
        {
            System.out.println();
            System.out.println("Movie Name: " + tempMovieKey);
            System.out.println("----------------------------");
            for(Movie tempMovie : BookMyShow.getMovieHashMap().get(tempMovieKey)) // loop for get the movie Arraylist one by one to get all the shows in the movie arrayList
            {
                System.out.println();
                System.out.println("Location: " + tempMovie.getLocation());
                System.out.println("Date: " + tempMovie.getDate());
                System.out.println("Duration: " + tempMovie.getDuration());
                System.out.println("Price: " + tempMovie.getShow().getPrice());
                System.out.println("Theatre: " + tempMovie.getTheatre().getTheatreName());
                System.out.println("Screen: " + tempMovie.getScreen().getScreenName());
                System.out.println("Show Starting Time: " + tempMovie.getShow().getStartingTime());
                System.out.println("Show Ending Time: " + tempMovie.getShow().getEndingTime());
            }
        }
    }

}




