# hci-soen

Self-Adjusting Smart Interface for Program Development using GCC Compiler

This project is to design and implement a self-adjusting smart graphical interface for C++ program development using the GCC compiler (let’s call it SmartGCC). Your interface is to be designed for three types of users:
• Novice user learning programming: need to use compiler options, linking options, execute options and debugging options.
• Typical Programmer: need to use code generation and code optimization options in addition to the options used by novice users.
• Expert Developer: need to use the developer options in addition to the options used by typical programmers.


The SmartGCC interface should have two windows, one showing the program the user is working on and the other showing the result/output from GCC, in addition to all the available options for the user. When SmartGCC is installed, it prompts the user to select the user type and initializes the interface with the menus and commands for the selected user type. The interface for all types of users should also contain a menu item called “All Options” containing all the options that any user can select. Once the user selects the required options, SmartGCC should execute the program using the selected options by calling GCC through an appropriate command line and display the results. Your interface should be made self-adjusting in the sense that when a user performs a command/task from the “All Options” menu which is not included in the interface for his/her user type, the interface will include that command within appropriate menus from that time. Thus, eventually all the commands a user has used in the past will be available in the appropriate menus and can be used efficiently. You should also implement a proof of concept prototype of SmartGCC using GCC in the background.


