import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// creating a class for the Admin Actions
class AdminActions
{
    //this function manage the log in process
    public static Admin adminLogin(Scanner s) // get the parameters values when this function call (this function also returns the object Admin)
    {
        int attempts = 0; // variable to manage the attempts of trying to log in
        System.out.println("WELCOME TO ADMIN LOGIN SYSTEM...");

        for (Admin tempAccount : BookMyShow.getAdminArrayList())  // for loop for checking the username and password one by one in the Admin arraylist
        {
            while (attempts < 3) // this loop will exit when attempts is greater than 3
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String username = s.nextLine(); // get the userName
                if (username.equals(tempAccount.getUserName())) // checks if the userName is admin ArrayList
                {
                    System.out.print("Enter Password: ");
                    String password = s.nextLine(); // get the password
                    if (password.equals(tempAccount.getPassword())) // checks if the password is admin ArrayList
                    {
                        return tempAccount; // return the Admin object
                    }
                    else // if the password is wrong
                    {
                        attempts++; // increment the attempts
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3) // if attempts is equal to 3
                        {
                            Admin ob = new Admin(null, null); // create a new Admin object a values of null
                            return ob; // return the empty object
                        }
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
    public static void adminAction(Scanner s)
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true)
        {
            System.out.println();
            System.out.println("1. ADD THEATRE \n2. VIEW THEATRE DETAILS \n3. ADD MOVIE \n4. VIEW MOVIE \n5. EXIT"); // print the choice
            System.out.print("Enter Your Choice: ");
            int choice = Integer.parseInt(s.nextLine()); // get the choice
            System.out.println();
            if (choice == 1)
            {
                addTheatre(s);
            }
            else if(choice == 2)
            {
                viewTheatre();
            }
            else if (choice == 3)
            {
                addMovie(s);
            }
            else if (choice == 4)
            {
                viewMovie();
            }
            else if (choice == 5)
            {
                break;
            }
            else
            {
                System.out.println("Enter The Valid Choice!");
            }
        }
    }

    // method for add a theatre and screen to the theatre and screen HashMap
    public static void addTheatre(Scanner s)
    {
        System.out.println("To Add Theatres Enter The Following Details...");
        System.out.println();
        System.out.print("Enter Theater Name: ");
        String theatreName = s.nextLine(); // gets the theatre name
        System.out.print("Enter Location Of The Theater: ");
        String theatreLocation = s.nextLine(); // gets the theatre location
        System.out.print("Enter Theater Screen Count: ");
        int screenCount = Integer.parseInt(s.nextLine()); // gets the screen count
        Theatre t = new Theatre(theatreName, theatreLocation, screenCount); // create a new theatre object and put the theatre data in it
        BookMyShow.getTheatreHashMap().put(theatreName,t); // add a created object to the theatre HashMap
        for (int i = 1; i <= screenCount; i++) // for loop to get the data for screen and store the screen data into the screen HashMap
        {
            System.out.println();
            System.out.println("Enter Details About Screen Number " + i + "...");
            System.out.print("Enter Screen Name: "); // gets the screen name
            String screenName = s.nextLine();
            System.out.print("Enter The Number Of Seats: ");
            int seatsCount = Integer.parseInt(s.nextLine()); // gets the number of seats
            String seatsGrid;
            while(true) // while loop for get the seats grid repeatedly until user enter the valid seats grid
            {
                System.out.print("Enter Seats Grid: ");
                seatsGrid = s.nextLine(); // gets the seats grid as a string
                HashMap<Character,ArrayList<String>> seats = Utilities.calculateGrid(seatsCount,seatsGrid); // call the calculateGrid function to calculate the String grid to valid seatsGrid HashMap
                if(seats!=null) // checks if the seats is not null
                {
                    t.getScreenHashMap().put(screenName, new Screen(screenName,seatsCount,seatsGrid,seats)); // put the key and values for screen HashMap
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
    public static void addMovie(Scanner s)
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
        System.out.println("Enter the Following Details to Add Movie...");
        System.out.println();
        System.out.print("Enter The Movie Name: ");
        String movieName = s.nextLine(); // get the movie name
        System.out.print("Enter The Date( dd_MM_yyyy ): ");
        String date = s.nextLine(); // get the movie date
        localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
        System.out.print("Enter The Duration( HH:mm ): ");
        String duration = s.nextLine(); // get the duration of the movie
        LocalTime localTime = LocalTime.parse(duration,BookMyShow.getLocalTimeFormatter());
        System.out.print("Enter the Price: ");
        long price = Long.parseLong(s.nextLine()); // get the movie price

        while(true) // while loop will break when the theatreFound is true
        {
            System.out.print("Enter The Location: ");
            location = s.nextLine(); // get the location

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
            theatreName = s.nextLine(); // get the theatre which is user want in the printed available theatre
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
            screenName = s.nextLine(); // get the name of the screen that user want in the printed available screens

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
            boolean flag = false;
            System.out.print("Enter The Show Starting Time( HH:mm ): ");
            String startingTime = s.nextLine(); // get the show starting time
            LocalTime localStartingTime = LocalTime.parse(startingTime,BookMyShow.getLocalTimeFormatter()); // convert the given string time into LocalTime (which is built in function)
            LocalTime localEndingTime = localStartingTime.plusHours(localTime.getHour()).plusMinutes(localTime.getMinute()).plusMinutes(30); // calculate the endTime by the given startingTime
            for (Show tempShow : screen.getShowHashSet())
            {
                if (!tempShow.getStartingTime().isAfter(localEndingTime) || !tempShow.getEndingTime().isBefore(localStartingTime))
                {
                    flag = true;
                }
            }
            for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values())
            {
                for (Movie tempMovie : tempMovieList)
                {
                    if(tempMovie.getTheatre().getTheatreName().equals(theatreName) && tempMovie.getShow().getDate().equals(localDate))
                    {
                        if (localStartingTime.equals(tempMovie.getShow().getStartingTime()))
                        {
                            flag = true;
                        }
                    }
                }
            }
            if(flag) // if flag is true then the show is already exist
            {
                System.out.println("Show Is Already Exist In Given Range!");
            }
            else // if flag false then there is no show is existed in that time so we can add shw data in the show arrayList
            {
                show = new Show(localDate,location,localStartingTime, localEndingTime,showSeatsGrid,price); // in show object store the show data
                screen.getShowHashSet().add(show); // add show object in show arrayList
                break; // breaks the while loop
            }
        }

        Movie tempMovie = new Movie(movieName,localTime,theatre,screen,show); // create a movie abject variable and store movie data we get all along in this object
        // this is true when the given movie name is already exist so here we don't have to create a new arrayList we just have to get the existing arrayList add the tempMovie in it
        if(Theatre.getMovieHashMap().containsKey(movieName))
        {
            Theatre.getMovieHashMap().get(movieName).add(tempMovie);
            System.out.println("Movie Added Successfully!");
        }
        // this block will execute when the given movie name is not already exist so here we have to create a new arrayList and add the tempMovie in it
        else
        {
            Theatre.getMovieHashMap().put(movieName,new ArrayList<>());
            Theatre.getMovieHashMap().get(movieName).add(tempMovie);
            System.out.println("Movie Added Successfully!");
        }
    }

    // method is to print all movie data in the movie HashMap
    public static void viewMovie()
    {
        System.out.println("...The Details Of Movies...");
        for(ArrayList<Movie> tempMovieList : Theatre.getMovieHashMap().values()) // loop for get the movie HashMap's value which is movie arrayList
        {
            for(Movie tempMovie : tempMovieList) // loop for get the movie Arraylist one by one to get all the shows in the movie arrayList
            {
                System.out.println();
                System.out.println("Movie Name: " + tempMovie.getMovieName());
                System.out.println("Location: " + tempMovie.getShow().getLocation());
                System.out.println("Duration: " + tempMovie.getDuration());
                System.out.println("Theatre: " + tempMovie.getTheatre().getTheatreName());
                System.out.println("Screen: " + tempMovie.getScreen().getScreenName());
                System.out.println("Date: " + tempMovie.getShow().getDate());
                System.out.println("Show Starting Time: " + tempMovie.getShow().getStartingTime());
                System.out.println("Show Ending Time: " + tempMovie.getShow().getEndingTime());
                System.out.println("Price: " + tempMovie.getShow().getPrice());
            }
        }
    }

}




