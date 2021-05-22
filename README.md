# Simple-POS-Java-Point-of-Sale-Project
Simple Java-based point of sale application. 

This is a simple java-based POS application that allows the handling of sessions, shifts, shopping carts, and store item registry.  It allows the printing of reciepts, cash ups, serialization of sessions the user wants to continue adding to later, and user-editable configuration files.

When launched, the application displays a new Session page that is optimised for touch-screen use.  The user may select "continue shift" which will recover a serialized session instance made prior, or they may select "new shift" which will create a new shift instance.  The shift instance contains a list of all "carts" or transactions.

![new-session-page](https://i.ibb.co/0hWmYTw/Session-Start-Page.jpg)
![new-session-page](https://i.ibb.co/0t0CZS6/CartPage.jpg)
![new-session-page](https://i.ibb.co/ZLXzvZ5/Configuration-Options.jpg)
![new-session-page](https://i.ibb.co/Qps5gfz/force-Refund-Page.jpg)
![new-session-page](https://i.ibb.co/THzD2sT/Reciept-Storage.jpg)
![new-session-page](https://i.ibb.co/0hWmYTw/Session-Start-Page.jpg)
![new-session-page](https://i.ibb.co/5xSMVQD/Shift-Operations-Page.jpg)


The next interface is the shift operations interface.  This interface allows the user to open a new "cart", perform a refund by negating cart ID, force a cash-only refund, or serialise the current shift.  Serializaion of the current shift will save a serial file with the current date and time in the program directory.  

The new cart interface is the most detailed interface of the application.  It handles the addition of categorised items to the current cart, the editing of said items, and the processing of payment.  

Once completing a cart (After completing payment), a reciept window is displayed.  This reciept has a customisable layout done by editing the file TEMPLATE_RECIEPT.html.  The reciept is stored as a HTML file inside a folder labeled with the date of issue. It may be viewed in any web browser at a later date by navigating to the correct folder.  The company details listed in the reciept are also configurable by the COMPANYDETAILS.config file.  

No reciept is printed for the issue of a refund or a forced-refund.  For a refund, the cart ID is requried.  This is a numeric code given to every cart in a shift.  It can be found printed on reciept.  Even if a reciept is not printed, the HTML file will be stored in the program directory inside a folder with the date the transaction was processed.  

The forced refund option does not remove the previous transaction from the shift, but instead creates a new transaction with negative value.  This may only be processed via cash. 

The closing of the shift via the button on the top right will display a shift "cash up" on screen.  This may be printed or skipped.  If skipped, it may still be retrieved at a later date by navigating to the HTML file stored in the program folder that corresponds to the shift.  This HTML file will be labeled with the shift start date and start time.  Further, the shift "cash up" will show the total EFTPOS, cash, and customer credit total.  Customer credit is not a feature currently implemented. 

