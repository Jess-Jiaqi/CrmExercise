public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public void enterMainMenu() {
        boolean isFlag = true;

        while (isFlag) {
            System.out.println("\n---------CRM System---------");
            System.out.println("\t1 Add Customer");
            System.out.println("\t2 Update Customer");
            System.out.println("\t3 Delete Customer");
            System.out.println("\t4 Customer List");
            System.out.println("\t5 Exit");
            System.out.print("Please choose(1-5): ");

            char menu = CMUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    updateCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.print("Are you sure you want to exit?(Y/N) ");
                    char isExit = CMUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        isFlag = false;
                    }
            }
        }

    }

    private void addNewCustomer() {
        System.out.println("---------------Add Customer---------------");
        System.out.print("Name:");
        String name = CMUtility.readString(40);
        System.out.print("Gender:");
        char gender = CMUtility.readChar();
        System.out.print("Age:");
        int age = CMUtility.readInt();
        System.out.print("Phone:");
        String phone = CMUtility.readString(10);
        System.out.print("Email:");
        String email = CMUtility.readString(64);

        Customer customer = new Customer(name, email, gender, age, phone);
        boolean isSucceed = customerList.addCustomer(customer);

        if (isSucceed) {
            System.out.println("----------Succeed!----------");
        } else {
            System.out.println("----------Fail! Customer list is full!----------");
        }
    }

    private void updateCustomer() {
        System.out.println("----------Update Info----------");
        Customer cust;
        int number;
        for (; ; ) {
            System.out.print("Please enter customer No. (to exit enter -1): ");
            number = CMUtility.readInt();

            if (number == -1) {
                return;
            }
            cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("Can't find this customer!");
            } else {
                break;
            }
        }
        System.out.print("Name(" + cust.getName() + "):");
        String name = CMUtility.readString(40, cust.getName());
        System.out.print("Gender(" + cust.getGender() + "):");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.print("Age(" + cust.getAge() + "):");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("Phone(" + cust.getPhone() + "):");
        String phone = CMUtility.readString(10, cust.getPhone());
        System.out.print("Email(" + cust.getEmail() + "):");
        String email = CMUtility.readString(64, cust.getEmail());

        Customer updateCust = new Customer(name, email, gender, age, phone);

        boolean isUpdated = customerList.updateCustomer(number - 1, updateCust);
        if (isUpdated) {
            System.out.println("----------Updated!----------");
        }
    }

    private void deleteCustomer() {
        System.out.println("----------Delete Customer----------");
        int number;
        for (; ; ) {
            System.out.print("Please enter customer No. (to exit enter -1): ");
            number = CMUtility.readInt();

            if (number == -1) {
                return;
            }

            Customer cust = customerList.getCustomer(number - 1);
            if (cust == null) {
                System.out.println("Can't find this customer!");
            } else {
                break;
            }

            System.out.print("Are you sure you want to delete this customer?(Y/N) ");
            char isDelete = CMUtility.readConfirmSelection();
            if (isDelete == 'Y') {
                boolean isDeleled = customerList.deleteCustomer(number - 1);
                if (isDeleled) {
                    System.out.println("----------Deleted!----------");
                }
            } else {
                return;
            }
        }
    }

    private void listAllCustomers() {
        System.out.println("---------------Customer List---------------");
        int total = customerList.getTotal();
        if (total == 0) {
            System.out.println("No customer yet!");
        } else {
            System.out.println("No.\tName\t\tGender\t\tAge\t\tPhone\t\t\tEmail");
            Customer[] custs = customerList.getAllCustomers();
            for (int i = 0; i < custs.length; i++) {
                Customer cust = custs[i];
                System.out.println((i + 1) + "\t" + cust.getName() + "\t\t" + cust.getGender() +
                        "\t\t\t" + cust.getAge() + "\t\t" + cust.getPhone() + "\t\t" + cust.getEmail());
            }
        }


        System.out.println("---------------End---------------");
    }

    public static void main(String[] args) {
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }

}
