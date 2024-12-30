import java.util.ArrayList;

public class Admin
    {
        private String adminName;
        private String adminPassword;
        private static ArrayList<Transaction> transactionList = new ArrayList<>();

        public Admin(String adminName, String adminPassword)
        {
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

        public void setAdminPassword(String adminPassword)
        {
            this.adminPassword = adminPassword;
        }

        public static ArrayList<Transaction> getTransactionArrayList()
        {
            return transactionList;
        }

    }



