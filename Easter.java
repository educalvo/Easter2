package nl.ru.ai.easter;

public class Easter
{
  static boolean isLeapYear(int year)
  {
    return ((year%4 == 0) && (!((year%100 == 0) && !(year%400 == 0))));
  }

  static int numberOfDaysInMonth(int year, Month month)
  {
	
    switch (month) {
    case FEBRUARY:
    	if (isLeapYear(year))
    		return 29;
    	return 28;
    case JANUARY:
    case MARCH:
    case MAY:
    case JULY:
    case AUGUST:
    case OCTOBER:
    case DECEMBER:
    return 31;
    case APRIL:
    case JUNE:
    case SEPTEMBER:
    case NOVEMBER:
    return 30;
    default:
    return 0;
    }
  }

  static Month easterMonth(int year)
  {
    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int l = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * l) / 451;

    return Month.month((h + l - 7 * m + 114) / 31);
  }

  static int easterDay(int year)
  {
	int a = year % 19;
	int b = year / 100;
	int c = year % 100;
	int d = b / 4;
	int e = b % 4;
	int f = (b + 8) / 25;
	int g = (b - f + 1) / 3;
	int h = (19 * a + b - d - g + 15) % 30;
	int i = c / 4;
	int k = c % 4;
	int l = (32 + 2 * e + 2 * i - h - k) % 7;
	int m = (a + 11 * h + 22 * l) / 451;

	return (((h + l - 7 * m + 114) % 31) + 1);
  }

  static int dayNumberInYear(int day, Month month, int year)
  {
	int days = day;
	for (int i = month.number() - 1; i > 0; i--) {
		days += numberOfDaysInMonth(year, Month.month(i));
	}
	return days;
  }

  static Month monthInYearOfDayNumber(int dayNumber, int year)
  {
    int month = 0;
    for (int i = dayNumber; i > 0; i = i - numberOfDaysInMonth(year, Month.month(month))) {
    	month++;
    }
    return Month.month(month);
  }

  static int dayInMonthOfDayNumber(int dayNumber, int year)
  {
    int month = 0;
    int i = dayNumber;
    for (i = dayNumber; i > numberOfDaysInMonth(year, Month.month(month + 1)); i = i - numberOfDaysInMonth(year, Month.month(month))) {
    	month++;
	}
    return i;
  }

  static void showHolyDays(int year)
  {
	dayInRelationToEaster(-49, year);
    System.out.println("For the year " + year + ":");
    System.out.println("Carnival is on " + monthInRelationToEaster(-49, year) + " " + dayInRelationToEaster(-49, year));
    System.out.println("Good Friday is on " + monthInRelationToEaster(-2, year) + " " + dayInRelationToEaster(-2, year));
    System.out.println("Easter is on " + easterMonth(year) + " " + easterDay(year));
    System.out.println("Ascension Day is on " + monthInRelationToEaster(39, year) + " " + dayInRelationToEaster(39, year));
    System.out.println("Whitsuntide is on " + monthInRelationToEaster(49, year) + " " + dayInRelationToEaster(49, year));
  }

  static int dayInRelationToEaster(int dayDifference, int year) {
	return dayInMonthOfDayNumber(dayNumberInYear(easterDay(year), easterMonth(year), year) + dayDifference, year);
  }

static Month monthInRelationToEaster(int dayDifference, int year) {
	return monthInYearOfDayNumber(dayNumberInYear(easterDay(year), easterMonth(year), year) + dayDifference, year);
  }

public static void main(String[] arguments)
  {
	 System.out.println(Month.month(1));
	 System.out.println(monthInYearOfDayNumber(35, 1999));
	 System.out.println(dayInMonthOfDayNumber(361, 1999));
	 showHolyDays(1999);
  }
}
