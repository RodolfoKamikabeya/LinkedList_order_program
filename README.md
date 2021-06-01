# LinkedList_order_program

## This program is part of COT 5405 Design & Analysis Of Algorithms course. 

### Algorithms Design Project to order records using a list.

**Step 1.**  Write a c++ program to insert the records into an ordered (maintain order at all times)  list (max number records unknown )(ordered by Last_name  in alphabetical order). Use the following structure definition in your program.

        1:  Years if service  0 – 5    4.5% (based on annual salary) ,  6 – 10  12.5% ,   
        11- 15  30.85% , 16 – 20 45.9%,  over 20 65.90% annual retirement salary.   

        2: Department codes AC – accounting MD –medical  EG –engineering    TE – technical   
        PP -physical plant   ST – staff   LW –legal RD research TT – testing QC -quality control SC - service Center.


**Step 2.**  Validate the data in each record before storing it in the data structure, any record found to be in error should be written to a file called "error.dat" along with the reason for the error.
        
        Error file                                 Error Messages

        record 99                     < start date month error   13/22/89
        Total number of error record 99

 

### Sample report
<pre>
Employee Report                                           08/31/2021 

 

Last        First             Date of          Annual         Department      Years          Predicted/monthly

Name        Initial            Hire            salary         Title         of service*      Retirement

Smith       J.              Dec. 10, 1990    $45,000.00         Law             23            $9999.99

Williams    K.              an. 19, 1990     $23,500.00         Med              24           $9999.99

etc.


Total                                        $999,999.99                                       $9999.99

 

Total records read 999

Total records processed 999

Total error records 999

 

*service rounded  to the nearest year
</pre>
