import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class AdminActions
{
    public Admin adminLogin(Scanner s)
    {
        int attempts = 0;
        System.out.println("WELCOME TO ADMIN LOGIN SYSTEM...");

        for (Admin tempAccount : BookMyShow.getAdminHashMap().values())
        {
            while (attempts < 3)
            {
                System.out.println();
                System.out.print("Enter User Name: ");
                String username = s.nextLine();
                if (username.equals(tempAccount.getUserName()))
                {
                    System.out.print("Enter Password: ");
                    String password = s.nextLine();
                    if (password.equals(tempAccount.getPassword()))
                    {
                        return tempAccount;
                    } else {
                        attempts++;
                        System.out.println();
                        System.out.println("Wrong Password!");
                        if (attempts == 3)
                        {
                            Admin ob = new Admin(null, null);
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

    public void adminAction(Scanner s)
    {
        System.out.println();
        System.out.println("YOU ENTER THE ADMIN BOARD...");
        while (true)
        {
            System.out.println();
            System.out.println("1. ADD THEATRE \n2. VIEW THEATRE DETAILS \n3. ADD MOVIE \n4. VIEW MOVIE \n5. EXIT");
            System.out.print("Enter Your Choice: ");
            int choice = s.nextInt();
            s.nextLine();
            System.out.println();
            if (choice == 1)
            {
                addTheatre(s);
            }
            else if(choice == 2)
            {
                viewTheatreDetails();
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

    public void addTheatre(Scanner s)
    {
        System.out.println("To Add Theatres Enter The Following Details...");
        System.out.println();
        System.out.print("Enter Theater Name: ");
        String theatreName = s.nextLine();
        System.out.print("Enter Location Of The Theater: ");
        String theatreLocation = s.nextLine();
        System.out.print("Enter Theater Screen Count: ");
        int screenCount = Integer.parseInt(s.nextLine());
        Theatre t = new Theatre(theatreName, theatreLocation, screenCount);
        BookMyShow.getTheatreHashMap().put(theatreName,t);
        for (int i = 1; i <= screenCount; i++)
        {
            System.out.println();
            System.out.println("Enter Details About Screen Number " + i + "...");
            System.out.print("Enter Screen Name: ");
            String screenName = s.nextLine();
            System.out.print("Enter The Number Of Seats: ");
            int seatsCount = Integer.parseInt(s.nextLine());
            String seatsGrid;
            while(true)
            {
                System.out.print("Enter Seats Grid: ");
                seatsGrid = s.nextLine();
                HashMap<Character,ArrayList<String>> seats = Utilities.calculateGrid(seatsCount,seatsGrid);
                if(seats!=null)
                {
                    t.getScreenHashMap().put(screenName, new Screen(screenName,seatsCount,seats));
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Theatre Added Successfully!");
    }

    public void viewTheatreDetails()
    {
        System.out.println("...Details Of Theatre...");
        System.out.println();
        for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values())
        {
            System.out.println("Theatre Name: "+tempTheatre.getTheatreName());
            System.out.println("Theatre Location: "+tempTheatre.getTheatreLocation());
            System.out.println("Screen Count :"+tempTheatre.getScreenCount());
            int i=1;
            for(Screen tempScreen : tempTheatre.getScreenHashMap().values())
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

    public void addMovie(Scanner s)
    {
        Screen screen = null;
        Theatre theatre = null;
        Show show;
        String location;
        LocalDate localDate;
        String theatreName;
        String screenName;
        boolean theatreFound = false;
        System.out.println("Enter the Following Details to Add Movie...");
        System.out.println();
        System.out.print("Enter The Movie Name: ");
        String movieName = s.nextLine();
        System.out.print("Enter The Date( dd_MM_yyyy ): ");
        String date = s.nextLine();
        localDate = LocalDate.parse(date,BookMyShow.getLocalDateFormatter());
        System.out.print("Enter The Duration( HH:mm ): ");
        String duration = s.nextLine();
        LocalTime localTime = LocalTime.parse(duration,BookMyShow.getLocalTimeFormatter());
        System.out.print("Enter the Price: ");
        long price = Long.parseLong(s.nextLine());

        while(true)
        {
            System.out.print("Enter The Location: ");
            location = s.nextLine();

            System.out.println("Available Theatres: ");
            for(Theatre tempTheatre : BookMyShow.getTheatreHashMap().values())
            {
                if(tempTheatre.getTheatreLocation().equals(location))
                {
                    System.out.println("* "+tempTheatre.getTheatreName());
                    theatreFound = true;
                }
            }
            if(theatreFound)
            {
                break;
            }
            else
            {
                System.out.println("There Is No Theatre In The Location You Select");
            }
        }

        while(true)
        {
            System.out.print("Enter The Name Of The Theatre You Want To Add: ");
            theatreName = s.nextLine();

            System.out.println("Available Screens: ");
            if (BookMyShow.getTheatreHashMap().containsKey(theatreName))
            {
                theatre = BookMyShow.getTheatreHashMap().get(theatreName);
                for (Screen tempScreen : theatre.getScreenHashMap().values())
                {
                    System.out.println("* "+tempScreen.getScreenName());
                }
            }
            if(theatre != null)
            {
                break;
            }
            else
            {
                System.out.println("Theatre Not Found!");
            }
        }

        while(true)
        {
            System.out.print("Enter The Name Of The Screen You Want To Add: ");
            screenName = s.nextLine();

            if (theatre.getScreenHashMap().containsKey(screenName))
            {
                screen = theatre.getScreenHashMap().get(screenName);
            }

            if(screen != null)
            {
                break;
            }
            else
            {
                System.out.println("Screen Not Found!");
            }
        }

        while(true)
        {
            boolean flag = false;
            System.out.print("Enter The Show Starting Time( HH:mm ): ");
            String startingTime = s.nextLine();
            LocalTime localStartingTime = LocalTime.parse(startingTime,BookMyShow.getLocalTimeFormatter());
            LocalTime localEndingTime = localStartingTime.plusHours(localTime.getHour()).plusMinutes(localTime.getMinute()).plusMinutes(30);
            for(Movie tempMovie : Theatre.getMovieHashMap().values())
            {
                if ((tempMovie.getDate().equals(localDate)) && (tempMovie.getTheatre().getTheatreName().equals(theatreName)) && (tempMovie.getScreen().getScreenName().equals(screenName)))
                {
                    if (!(localEndingTime.isBefore(tempMovie.getShow().getStartingTime()) || localStartingTime.isAfter(tempMovie.getShow().getEndingTime())))
                    {
                        flag = true;
                    }
                }
            }
            if(flag)
            {
                System.out.println("Show Is Already Exist In Given Time!");
            }
            else
            {
                show = new Show(localStartingTime, localEndingTime);
                Theatre.getShowHashMap().put(localDate,show);
                Theatre.getMovieHashMap().put(movieName, new Movie(movieName,localDate,localTime,price,location,theatre,screen,show));
                System.out.println("Movie Added Successfully!");
                break;
            }
        }
    }

    public void viewMovie()
    {
        System.out.println("...The Details Of Movies...");
        for(Movie tempMovie : Theatre.getMovieHashMap().values())
        {
            System.out.println();
            System.out.println("Movie Name: "+tempMovie.getMovieName());
            System.out.println("Date: "+tempMovie.getDate());
            System.out.println("Duration: "+tempMovie.getDuration());
            System.out.println("Price: "+tempMovie.getPrice());
            System.out.println("Location: "+tempMovie.getLocation());
            System.out.println("Theatre: "+tempMovie.getTheatre().getTheatreName());
            System.out.println("Screen: "+tempMovie.getScreen().getScreenName());
            System.out.println("Show Starting Time: "+tempMovie.getShow().getStartingTime());
            System.out.println("Show Ending Time: "+tempMovie.getShow().getEndingTime());
        }
    }

}




