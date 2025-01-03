public class Helper // Top Level Class
{
    public static void main(String[] args)throws CloneNotSupportedException // this exception comes cause of we use Clone built-in function in the project
    {
        AtmActions atmActions = new AtmActions(); // creating an object for AtmActions
       atmActions.start(); // call start function by using AtmActions object
    }
}