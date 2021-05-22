# Simple-POS-Java-Point-of-Sale-Project
Simple Java-based point of sale application. 

This is a simple java-based POS application that allows the handling of sessions, shifts, shopping carts, and store item registry.  It allows the printing of reciepts, cash ups, serialization of sessions the user wants to continue adding to later, and user-editable configuration files.

### Contents 
* [1. The Session Page](#session-page)
* [2. The Shift Operations Page](#operations-page)
* [3. Opening a new Cart](#new-cart)
* [4. Refund Page](#refund-page)
* [5. Closing shift and obtaining a "cash up"](#cash-up)
* [6. Using configuration files](#config)

## 1. The Session Page 

When launched, the application displays a new Session page that is optimised for touch-screen use.  The user may select "continue shift" which will recover a serialized session instance made prior, or they may select "new shift" which will create a new shift instance.  The shift instance contains a list of all "carts" or transactions.

![new-session-page](https://i.ibb.co/0hWmYTw/Session-Start-Page.jpg)

## 2. The Shift Operations Page 

The next interface is the shift operations interface.  This interface allows the user to open a new "cart", perform a refund by negating cart ID, force a cash-only refund, or serialise the current shift. Serializaion of the current shift will save a serial file with the current date and time in the program directory.  It also allows the user to view the current cash and EFTPOS totals of the shift. 

![shift-operations-page](https://i.ibb.co/5xSMVQD/Shift-Operations-Page.jpg)
![shift-status](https://i.ibb.co/JqNfdcM/shift-status.jpg)

## 3. Opening a new Cart 

The new cart interface is the most detailed interface of the application.  It handles the addition of categorised items to the current cart, the editing of said items, and the processing of payment.  

![new-session-page](https://i.ibb.co/0t0CZS6/CartPage.jpg)

### Reciepts: Printing & storage 

Once completing a cart (After completing payment), a reciept window is displayed.  This reciept has a customisable layout done by editing the file TEMPLATE_RECIEPT.html. 

![reciept](https://i.ibb.co/cTXR4HS/reciept.jpg)

The reciept is stored as a HTML file inside a folder labeled with the date of issue. It may be viewed in any web browser at a later date by navigating to the correct folder.  

![reciept-folders](https://i.ibb.co/M24HGhc/folders.jpg)
![reciept-files](https://i.ibb.co/THzD2sT/Reciept-Storage.jpg)

## 4. Refund Page 

### Refunding by cart ID 

No reciept is printed for the issue of a refund or a forced-refund.  For a refund, the cart ID is requried.  This is a numeric code given to every cart in a shift.  It can be found printed on reciept.  Even if a reciept is not printed, the HTML file will be stored in the program directory inside a folder with the date the transaction was processed.  

![refund-page](https://i.ibb.co/tLvFqCK/refund.jpg)

### Forcing a cash refund 

The forced refund option does not remove the previous transaction from the shift, but instead creates a new transaction with negative value.  This may only be processed via cash. 

![forced-refund-page](https://i.ibb.co/Qps5gfz/force-Refund-Page.jpg)

## 5. Closing a shift and obtaining a "cash up" 

The closing of the shift via the button on the top right will display a shift "cash up" on screen.  This may be printed or skipped.  If skipped, it may still be retrieved at a later date by navigating to the HTML file stored in the program folder that corresponds to the shift.  This HTML file will be labeled with the shift start date and start time.  Further, the shift "cash up" will show the total EFTPOS, cash, and customer credit total.  Customer credit is not a feature currently implemented. 

![cash-up](https://i.ibb.co/VSVmZ3W/cash-up.jpg)

## 6. Using configuration files 

The following files allow the configuration of item categories and company details to customise the application to suit you.  

![configuration-options-page](https://i.ibb.co/ZLXzvZ5/Configuration-Options.jpg)

The company details configuration file is self-explanatory and requires only swapping out default values. The first line is the company name, the second is the ABN (Australian Business Number), and the third is the site of operation (Normally an address).

![configuration-company](https://i.ibb.co/cwZ2JGN/company-details.jpg)

The dictionary file is more complicated. It allows the customisation of the categories seen in the "cart" screen.  The entries are formatted as follows:
" KEYCODE : Default description : default price : default quantity ".  When the user selects a keycode from the "cart" screen, these entries are automatically applied to the cart by default.  
Further, the entries with a "\*" symbol before them indicate a category title.  These appear at the top of the tabbed-pane.  Any entry above the category title, and after the previous category title fall within said category. 

![configuration-dictionary](https://i.ibb.co/7Q6d7Yt/dictionary.jpg)
![dictionary-pane](https://i.ibb.co/JmQY2km/category-pane.jpg)
