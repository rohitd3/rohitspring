package com.nighthawk.spring_portfolio.mvc.calendar;
import java.util.*;

// Prototype Implementation

public class APCalendar {
     
    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0){
            return true;
        }

        return false;
        }
    
    
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */


    public static int firstDayOfYear(int year) {
        int currentYearFirstDay = 0;
        // given that first day of year 2023 is Sunday, here it is being preset what day of the year it is
        // 0 is Sunday

        if (year == 2023) {
            currentYearFirstDay = 6; // therefore no change to first day

        } else if (year < 2023) {
            int yearsAway = 2023 - year;
            int leapYearsAway = numberOfLeapYears(year, 2023); // finding the number of leap years between 2000 and given year
            int nonLeapYearsAway = yearsAway - leapYearsAway; // finding the number of non-leap years between 2000 and given year
            int daysAway = (leapYearsAway * 366) + (nonLeapYearsAway * 365); // total number of days between 2000 and given year
            for (int i = 0; i < daysAway; i++) {
                currentYearFirstDay--;
                if (currentYearFirstDay == -1) {
                    currentYearFirstDay = 6; // resetting the day to saturday everytime it goes to -1
                }
            }
        } else if (year > 2023) {
            int yearsAway = year - 2023;
            int leapYearsAway = numberOfLeapYears(2023, year-1);
            int nonLeapYearsAway = yearsAway - leapYearsAway;
            int daysAway = (leapYearsAway * 366) + (nonLeapYearsAway * 365);
            for (int i = 0; i < daysAway; i++) {
                currentYearFirstDay++;
                if (currentYearFirstDay == 7) {
                    currentYearFirstDay = 0; // resetting to Sunday (0) everytime it goes to 7
                }
            }
        }
        
        return currentYearFirstDay;
    }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    public static int dayOfYear(int month, int day, int year) {
        int n = 0;
        int feb = 28;
        if (isLeapYear(year) == true) {
            feb = 29;
        }

        int[] months = {31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // this follows the year and each individual month with the number of days in each
        for (int i = 0; i < month - 1; i++) {
            n += months[i];
        }

        n += day;
        
        return n;
    }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
        int leapCount = 0;

        for (int i = year1; i <= year2; i++ ) {
            if (isLeapYear(i) == true) {
                leapCount++;
            }
        }

        return leapCount;
    }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        Calendar calenobj = Calendar.getInstance();
        calenobj.add(Calendar.YEAR, year);
        calenobj.add(Calendar.MONTH, month - 1); //month begins on index 0
        calenobj.add(Calendar.DAY_OF_WEEK, day);
        Map<String, Integer> map = new HashMap<String, Integer>(){{
            put("Sun",0);
            put("Mon",1);
            put("Tue",2);
            put("Wed",3);
            put("Thu",4);
            put("Fri",5);
            put("Sat",6);
        }};
        String currentDay = (calenobj.getTime()).toString();
        String days = currentDay.substring(0,3);
        System.out.println(days);
        return (map.get(days));
    }
    // With help from Everitt Cheng

    /** Tester method */
    // public static void main(String[] args) {
    //     // Private access modifiers
    //     System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
    //     System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));

    //     // Public access modifiers
    //     System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
    //     System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
    //     System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    // }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        // method 1
        System.out.println("Enter year:");
        int year1 = userInput.nextInt();
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(year1));

        // method 2
        System.out.println("Enter a year:");
        int year2 = userInput.nextInt();
        System.out.println("Enter a month:");
        int month2 = userInput.nextInt();
        System.out.println("Enter a day:");
        int day2 = userInput.nextInt();
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(month2, day2, year2));

        // method 3
        System.out.println("Enter year:");
        int year3 = userInput.nextInt();
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(year3));

        // method 4
        System.out.println("Enter year:");
        int year4 = userInput.nextInt();
        System.out.println("Enter another year that comes after the previous year:");
        int nextyear = userInput.nextInt();
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(year4, nextyear));

        // method 5
        System.out.println("Enter a year:");
        int year5 = userInput.nextInt();
        System.out.println("Enter a month:");
        int month5 = userInput.nextInt();
        System.out.println("Enter a day:");
        int day5 = userInput.nextInt();
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(month5, day5, year5));
    }



}