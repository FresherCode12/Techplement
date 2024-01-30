import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class Employee {
  
    int id;
    String name;
    double salary;
    Employee(int id,String name,double salary){
        this.id=id;
        this.name = name;
        this.salary = salary;
    }
    public int getId(){
        return id;
    }

    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name = name;
    }

    public double getsalary(){
        return salary;
    }
    public void setsalary(double salary){
        this.salary = salary;

    }
    public void display(){
        //System.out.println("Employee Details");
        System.out.println(" \nEmployee ID: "+ id +"\nname       : " + name + "\nSalary    : " + salary ); 
    }
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + '}';
    }
    
}

class EmployeeManagementSystem {
        static ArrayList<Employee> employees = new ArrayList<>();
        public static void main(String[] args) throws IOException{
            Extractfromfile();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n\nEmployee Management System\n");
                System.out.println("1. Add Employee");
                System.out.println("2. View all the Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Search Employee");
                System.out.println("6. Exit");
    
                System.out.print("\nEnter your choice: ");
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                    addEmployee(scanner);     
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        updateEmployee(scanner);
                        break;
                    case 4:
                        deleteEmployee(scanner);
                        break;
                    case 5:
                        searchEmployee(scanner);
                        break;
                    case 6:
                        saveEmployeesToFile();
                        System.out.println("\nThank you");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.\n");
                }
            }
        }
    private static void addEmployee(Scanner scanner){
        try{
         System.out.println("\nEnter Employee ID:");
         int id=scanner.nextInt();
         Employee employee1 = findEmployeeById(id);

         if (employee1 != null) {
             System.out.println("\nEmployee id already found");
             return;
         } 
        
         System.out.println("\nEnter Employee Name:");
         String name=scanner.next();

         System.out.println("\nEnter Employee Salary");
         double salary=scanner.nextDouble();

         Employee employee=new Employee(id, name, salary);
         employees.add(employee);
        }
        catch(java.util.InputMismatchException e){
            System.out.println("Please enter a valid number for id");
        }

    }
    public static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\nNo employees found.");
        } else {
            System.out.println("\nList of Employees:");
            for (Employee employee : employees) {
                employee.display();
            }
        }
    }
    public static void updateEmployee(Scanner scanner) {
        try{  System.out.print("Enter the ID of the employee to be updated: ");
        int id = scanner.nextInt();
       Employee employee=findEmployeeById(id);
            if (employee==null) {
                System.out.println("The given ID does not exist .");
            }
            else{
                System.out.println("\nSelect what you want to update\n" +
                "1.Name\n2.Salary");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                    System.out.println("Enter new Name:");
                    String name = scanner.next();
                    employee.setname(name);
                    break;
                    case 2:
                    System.out.println("Enter new Salary:");
                    double salary = scanner.nextDouble();
                    employee.setsalary(salary);
                    break;
                    default:
                    System.out.println("Invalid Choice!");
                    
                }
            }  }catch(Exception e){
                System.out.println("Please enter a valid input");
            }
        
        }
    public static void deleteEmployee(Scanner scanner){
        System.out.print("Enter the ID of the employee to be deleted: ");
        int id =scanner.nextInt();
        Employee employee=findEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            employees.remove(employee);
            System.out.println("Employee deleted successfully!");
        }

    }
    public static void searchEmployee(Scanner scanner){
        System.out.print("Enter employee ID to search: ");
        int id = scanner.nextInt();

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("\nEmployee found: ");
             employee.display();
        }
    }
    private static Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    public static void Extractfromfile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double salary = Double.parseDouble(parts[2]);
                employees.add(new Employee(id, name, salary));
            }
        } catch (IOException e) {

        }
    } 
    public static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee.txt"))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getname() + "," + employee.getsalary());
                writer.newLine();
            }
        } catch (IOException e) {

        }
    } 
 }
        
 
       

