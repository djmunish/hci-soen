#include <iostream>
 
 using namespace std;
 
 
 int main()
 {
     int firstNumber, secondNumber, sumOfTwoNumbers;
     
     cout << "Enter two integers: ";
 
 
     // sum of two numbers in stored in variable sumOfTwoNumbers

#ifdef DEBUG 
 int x, y ;
 cin >> x >> y;
     sumOfTwoNumbers = x * y;
     // Prints sum 
     cout  << sumOfTwoNumbers;
#else
 int x, y ;
 cin >> x >> y;
     sumOfTwoNumbers = x + y;
     // Prints sum 
     cout << x << " + " <<  y << " = " << sumOfTwoNumbers;
#endif


 
     return 0;
 }