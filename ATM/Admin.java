import java.util.ArrayList;

// creating a pojo Admin class to maintain and manage the admin data

class Admin
    {
        // I also put the variable in private for a security porpoise (by put variables in private the other classes can't aware of this variable existance)
        private String adminName;  // create a variable to store admin name (I don't initialize the variable because it is an instance variable)
        private String adminPassword; //  create a variable to store admin password
        private static ArrayList<Transaction> transactionList = new ArrayList<>(); //  creating an arraylist to store admin transactions

        public Admin(String adminName, String adminPassword) //  create a constructor to assign a value to the variable by passing a parameter
        {
            // using this keyword to move the courser to the variable in the same reference class(because it is a non static)
            this.adminName = adminName;
            this.adminPassword = adminPassword;
        }

        public String getAdminName()
        {
            return adminName;
        }

        public String getAdminPassword()
        {
            return adminPassword;
        }

        public static ArrayList<Transaction> getTransactionArrayList()
        {
            return transactionList;
        }

    }




