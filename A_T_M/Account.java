import java.util.ArrayList;

class Account
{
    String id;  // create a variable to store admin and customer id (I don't initialize the variable because it is an instance variable)
    String password; //  create a variable to store admin and customer password

    public Account(String adminName, String adminPassword) //  create a constructor to assign a value to the variable by passing a parameter
    {
        // using this keyword to move the courser to the variable in the same reference class(because it is a non static)
        this.id = adminName;
        this.password = adminPassword;
    }

    public String getId()
    {
        return id ;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String pin)
    {
        this.password = pin;
    }


}
