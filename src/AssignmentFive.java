
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Scanner;

public class AssignmentFive {

    //set the percentage calculation based in the years of service
    //0 – 5    4.5%
    static float retPercentage_5_years = (float) 0.045;
    //6 – 10  12.5%
    static float retPercentage_10_Years = (float) 0.125;
    //11- 15  30.85%
    static float retPercentage_15_Years = (float) 0.3085;
    //16 – 20 45.9%
    static float retPercentage_20_Years = (float) 0.459;
    //over 20 65.90%
    static float retPercentageOver_21_Years = (float) 0.6590;

    //Strict node
    static class listnode{
        String lastName, firstName;
        int start_month;
        int start_day;
        int start_year;  // 4 digits
        int end_month;  // 0 if still active
        int end_day;     //0 if still active
        int end_year;    // 0 if still active
        float annual_salary;
        String dept_code;  //    Note: may be entered in either upper or lower case (two characters)
        String email_address; //new String[25];
        listnode next;

        // constructor
        public listnode(String last_Name, String first_Name, int start_Month, int start_Day, int start_Year,
                        int end_Month, int end_Day, int end_Year, String dept_Code, float annual_Salary, String email_Address){
            lastName = last_Name;
            firstName = first_Name;
            start_month = start_Month;
            start_day= start_Day;
            start_year= start_Year;
            end_month=end_Month;
            end_day= end_Day;
            end_year= end_Year;
            dept_code=dept_Code;
            annual_salary= annual_Salary;
            email_address=email_Address;
            next =null;
        }

    }
    private static listnode head;


    // Method to insert a new node
    public void insert(String lastName, String firstName, int start_month, int start_day, int start_year,
                              int end_month, int end_day, int end_year, String dept_code, float annual_salary, String email_address) {

        // Create a new node with given data
        listnode new_node = new listnode(lastName, firstName, start_month, start_day, start_year,
                end_month, end_day, end_year, dept_code, annual_salary, email_address);


        // If the Linked List is empty,
        //If list is empty, both head will point to new node
        if (head == null) {
            head = new_node;
        }
        //Check if the lastName new node is sorted in our linked list, if it is allocated as the new head of the linked list
        else if (new_node.lastName.compareTo(head.lastName) < 0) {

                new_node.next = head;
                head = new_node;
        } else {
               listnode currentNode =head;
               // Search until reach the element that allocate the lastName new node in the linked list
               while (currentNode.next != null && currentNode.next.lastName.compareTo(new_node.lastName) < 0)
                    currentNode = currentNode.next;

                new_node.next = currentNode.next;
                currentNode.next = new_node;
        }

    }


    //Check if the lastName or firstName contains digit
    private static boolean checkName(String name) {
        String temp ="";
        // Loop looking for character "c" in the word
        for (Character c :name.toCharArray()) {
            //if character is a letter return the letter, if not temp contains digit
            if (Character.isLetter(c))
                temp +=c;
            else if(Character.isDigit(c))
                temp+=c;
        }
        //if temp contains digit return false
        if(temp.matches(".*\\d.*"))
            return false;
            //if temp is just letter return true
        else
            return true;
    }

    //Check how many days has each month
    private static int checkDaysMonth(int month, boolean leap) {
        switch (month){
            //Jan, Mar, May, Jul, Aug, Oct, Dec - 31 days
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            //Fev. Common year - 28 days , Leap year 29 days
            case 2:
                if(leap == true)
                    return 29;
                return 28;
            //Apr, Jun, Set, Nov - 30 days
            case 4: case 6: case 9: case 11:
                return 30;
        }
        return 0;
    }

    // check for leap year
    private static boolean checkLeapYear(int year) {
        // year to be checked
        boolean leap = false;
        // if the year is divided by 4
        if (year % 4 == 0) {
            // if the year is century
            if (year % 100 == 0) {
                // year that is divisible by 100 is a leap year only if it is also divisible by 400
                //if is %400 is leap year
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            // if the year is not century
            else
                leap = true;
        }
        else
            leap = false;

        return leap;
    }

    //calculate the retirement salary
    private static float checkRetirementSalary(int end_year, int start_year, float annual_salary,float ret_5_years,float ret_10_years, float ret_15_years, float ret_20_years,float ret_over_21) {

        if(end_year-start_year>=0 && end_year-start_year<=5)
            return (float) (annual_salary*ret_5_years);

        else if(end_year-start_year>=6 && end_year-start_year<=10)
            return  (float) (annual_salary*ret_10_years);

        else if(end_year-start_year>=11 && end_year-start_year<=15)
            return (float) (annual_salary*ret_15_years);

        else if(end_year-start_year>=16 && end_year-start_year<=20)
            return (float) (annual_salary*ret_20_years);

        else if(end_year-start_year>20)
            return (float) (annual_salary*ret_over_21);

        return 0;
    }

    //Verify the department code
    private static String departmentCode(String code) {

        if(code.toUpperCase().equals("AC"))
            return "Accounting";
        else if(code.toUpperCase().equals("MD"))
            return "Medical";
        else if(code.toUpperCase().equals("EG"))
            return "Engineering";
        else if(code.toUpperCase().equals("TE"))
            return "Technical";
        else if(code.toUpperCase().equals("PP"))
            return "Physical plant";
        else if(code.toUpperCase().equals("ST"))
            return "Staff";
        else if(code.toUpperCase().equals("LW"))
            return "Legal";
        else if(code.toUpperCase().equals("RD"))
            return "Research";
        else if(code.toUpperCase().equals("TT"))
            return "Testing";
        else if(code.toUpperCase().equals("QC"))
            return "Quality Control";

            //if the department code doesn't matches return empty
        else
            return "";

    }

    //Format the date to a short date notation
    public static String getMonth(int month, int day, int year) {
        switch (month){
            case 1:
                return "Jan."+day+","+year;
            case 2:
                return "Feb."+day+","+year;
            case 3:
                return "Mar."+day+","+year;
            case 4:
                return "Apr."+day+","+year;
            case 5:
                return "May."+day+","+year;
            case 6:
                return "Jun."+day+","+year;
            case 7:
                return "Jul."+day+","+year;
            case 8:
                return "Aug."+day+","+year;
            case 9:
                return "Set."+day+","+year;
            case 10:
                return "Oct."+day+","+year;
            case 11:
                return "Nov."+day+","+year;
            case 12:
                return "Dec."+day+","+year;
        }
        return "";
    }

    //Create the output file
    static void WriteToFile(AssignmentFive arr, int countTotalError, int countline, float retPercentage_5_years, float retPercentage_10_Years, float retPercentage_15_Years, float retPercentage_20_Years, float retPercentageOver_21_Years) {
        //format the date and time in the report
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //check the date and time for the report
        LocalDateTime now = LocalDateTime.now();
        //format the currency
        DecimalFormat moneyFormat = new DecimalFormat("$0,000.00");
        float total_salary=0;
        float total_month_salary =0;
        try {
            // Get the file
            File file = new File("report_out.rpt");
            if (file.exists()) {
                RandomAccessFile raf = new RandomAccessFile(file, "rw"); //Open the file for reading and writing
                raf.setLength(0); //set the length of the character sequence equal to 0
            }
            FileWriter fw = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(fw);

            //Create new format for the report
            Formatter formatter = new Formatter();
            //Convert all the data to string, including the current date and time
            printer.append(String.valueOf(formatter.format("%50s %20s %15s", "Employee Report", " ", dtf.format(now))));
            printer.append('\n');

            formatter = new Formatter();
            printer.append(String.valueOf(formatter.format("%10s %15s %15s %15s %15s %15s %15s", "Last", "First","Date of","Annual","Department","Years","Predicted/monthly")));
            printer.append('\n');

            formatter = new Formatter();
            printer.append(String.valueOf(formatter.format("%10s %15s %15s %15s %15s %15s %15s", "Name", "Initial","Hire","Salary","Title","of Service","Retirement")));
            printer.append('\n');
            printer.append('\n');

            listnode currNode = head;
            int position=0;
            //Print the records ordered by last name into the output file
            while(currNode!=null){
                //Create new format for the report
                formatter = new Formatter();
                //Print all the objects from the node into the output file
                printer.append(String.valueOf(formatter.format("%10s %15s %15s %15s %15s %15s %15s", currNode.lastName, currNode.firstName.charAt(0) + ".", getMonth(currNode.start_month, currNode.start_day, currNode.start_year), moneyFormat.format(currNode.annual_salary),
                        departmentCode(currNode.dept_code), nearestYear(currNode.end_year,currNode.end_month,currNode.start_year,currNode.start_month), moneyFormat.format(checkRetirementSalary(currNode.end_year, currNode.start_year, currNode.annual_salary,
                                retPercentage_5_years,retPercentage_10_Years,retPercentage_15_Years
                                ,retPercentage_20_Years,retPercentageOver_21_Years) / 12))));
                printer.append('\n');

                total_salary+= currNode.annual_salary;
                total_month_salary +=checkRetirementSalary(currNode.end_year,currNode.start_year,currNode.annual_salary,retPercentage_5_years,retPercentage_10_Years,retPercentage_15_Years
                        ,retPercentage_20_Years,retPercentageOver_21_Years)/12;


                deleteNode(currNode);
                currNode = currNode.next;
            }

            formatter = new Formatter();
            printer.append('\n');
            printer.append(String.valueOf(formatter.format("%10s %15s %15s %15s %15s %15s %15s", "Total", "      ","    ",moneyFormat.format(total_salary),"     ","         ",moneyFormat.format(total_month_salary))));
            printer.append('\n');

            formatter = new Formatter();
            printer.append('\n');
            printer.append(String.valueOf(formatter.format("%10s %d", "Total record read ", countline)));

            formatter = new Formatter();
            printer.append('\n');
            printer.append(String.valueOf(formatter.format("%10s %d", "Total records processed ", countline-countTotalError)));

            formatter = new Formatter();
            printer.append('\n');
            printer.append(String.valueOf(formatter.format("%10s %d", "Total error records ", countTotalError)));

            printer.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    //Print into the error output
    private static void errorOutput(String errorRecord, int countline, int countTotalError) {

        try {
            // Get the file
            File file = new File("error.rpt");
            FileWriter fw = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(fw);
            //Create new format for the error output
            Formatter formatter = new Formatter();
            if (countTotalError == 0) {
                //Print the record line and th error type
                printer.append(String.valueOf(formatter.format("%5s%d %50s","record " ,countline, errorRecord)));
                printer.append('\n');
            }
            else{
                //Print the record line and th error type
                printer.append('\n');
                printer.append(String.valueOf(formatter.format("%5s%d","Total number of error record " ,countTotalError-1)));
            }

            printer.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    static listnode deleteNode(listnode head)
    {

        if (head == null)
            return null;

        // Move the head pointer to the next node
        head = head.next;

        return head;
    }

    // service rounded to the nearest year
    private static Object nearestYear(int end_year, int end_month, int start_year, int start_month) {
        //if the employer retired before Jun, the nearest year is decreased by one
        if(end_month - start_month>=6) end_year =end_year+1;

        return end_year-start_year;

    }

    public static void main(String[] args) throws IOException {



        //Create a node to store all the data
        AssignmentFive employersRecord = new AssignmentFive();

        //count the current line (record the line to the error file)
        int countline=0;
        //count the current index in the array
        int countArrChecker=0;
        //count total error (obs. starts with 1 to manipulate the conditional in method errorOutput)
        int countTotalError=1;

        //Create error file
        File errorFile= new File("error.rpt");
        //if the file exist, erase its content
        if (errorFile.exists()) {
            RandomAccessFile raf = new RandomAccessFile(errorFile, "rw"); //Open the file for reading and writing
            raf.setLength(0); //set the length of the character sequence equal to 0
        }
        FileWriter fw = new FileWriter(errorFile, true);
        PrintWriter printer = new PrintWriter(fw);
        //Create new format for the error report
        Formatter formatter = new Formatter();
        //Print the reference topics for the error report
        printer.append(String.valueOf(formatter.format("%5s %35s", "Error file", "Error Messages")));
        printer.append('\n');
        printer.append('\n');
        printer.close();

        // Open and read the Input file
        File file = new File("employee_3_18_21.txt");
        // Scanner all the file
        Scanner scannerFile = new Scanner(file);

        //While until have lines in the file
        while (scannerFile.hasNext()) {

            //Store all the data line by line into variable to populate the node
            //Each variable will be an object in the node
            String lastName = scannerFile.next();
            String FirstName = scannerFile.next();
            int start_month = Integer.parseInt(scannerFile.next());
            int start_day = Integer.parseInt(scannerFile.next());
            int start_year = Integer.parseInt(scannerFile.next());
            int end_month = Integer.parseInt(scannerFile.next());
            int end_day = Integer.parseInt(scannerFile.next());
            int end_year = Integer.parseInt(scannerFile.next());
            float annual_salary = Float.parseFloat(scannerFile.next());
            String dept_codes = scannerFile.next();
            String email_address = scannerFile.next();

            //Count the current line for the error report
            countline++;

            // if the employer is still working, the end date is today's date
            LocalDate currentdate = LocalDate.now();
            //get today`s year, month and day
            int currentYear = currentdate.getYear();
            int currentMonth = currentdate.getMonthValue();
            int currentDay = currentdate.getDayOfMonth();
            //set today`s year, month and day
            if(end_year==0 || end_month==0 || end_day==0){
                end_year=currentYear;
                end_month = currentMonth;
                end_day = currentDay;
            }

            //Check the errors name, invalid date, annual salary = 0, unknown department code
            //if does not contains errors
            if (checkName(lastName) != false && checkName(FirstName) != false && start_month != 0 && start_day != 0 && start_year != 0 && annual_salary != 0
                    && start_year < end_year && start_month <= 12 && end_month <= 12 && start_day <= checkDaysMonth(start_month, checkLeapYear(start_year)) && end_day <= checkDaysMonth(end_month, checkLeapYear(end_year))
                    && departmentCode(dept_codes) != ""){
                //Record the data into the listnode
                employersRecord.insert(lastName, FirstName, start_month, start_day, start_year, end_month, end_day, end_year, dept_codes, annual_salary, email_address);
                countArrChecker++;
            }

            //if contains errors
            else {
                //Error in the name
                if (checkName(lastName) == false || checkName(FirstName) == false)
                    errorOutput("<name error  " + lastName + " " + FirstName, countline, 0);

                    //Error in the start date (0/0/0)
                else if (start_month == 0 || start_day == 0 || start_year == 0)
                    errorOutput("<start date error  " + start_month + "/" + start_day + "/" + start_year, countline, 0);

                    //Error in the month >12
                else if (start_month >= 13 || end_month >= 13)
                    errorOutput("<month error  start/end " + start_month + "/" + end_month, countline, 0);

                    //Error in the date (end year is less than start year)
                else if (start_year >= end_year)
                    errorOutput("<year error start/end " + start_year + "/" + end_year, countline, 0);

                    //Error in the amount of days according to each month (e.g. 32 days in Jan)
                else if (start_day > checkDaysMonth(start_month, checkLeapYear(start_year)) || end_day > checkDaysMonth(end_month, checkLeapYear(start_year)))
                    errorOutput("<day error start/end " + start_month+"/"+start_day + " " + end_month+"/"+end_day, countline, 0);

                    //Error annual salary = 0
                else if (annual_salary == 0)
                    errorOutput("<annual salary error  " + annual_salary, countline, 0);

                    //Error department code doesn't exist
                else if (departmentCode(dept_codes) == "")
                    errorOutput("<dept code error  " + dept_codes, countline, 0);

                countTotalError++;

            }

        }

        //Call the function to write the report_output
        WriteToFile(employersRecord,countTotalError-1,countline,
                retPercentage_5_years,retPercentage_10_Years,retPercentage_15_Years
                ,retPercentage_20_Years,retPercentageOver_21_Years);
        //Call the function to write the total error in the error report
        errorOutput("",0,countTotalError);

    }


}
