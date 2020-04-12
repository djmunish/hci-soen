#include <iostream>

using namespace std;


int main()
{
    int firstNumber, secondNumber, sumOfTwoNumbers;
    
    cout << "Enter two integers: ";

#ifdef DEBUG
    cout<<"DEBUG";
#else
        cout<<"NOT DEBUG";


#endif

    // sum of two numbers in stored in variable sumOfTwoNumbers


    return 0;
}