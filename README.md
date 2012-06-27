Bday-importer
=============

------------
---README---
------------

Birthdaysimporter 1.2
---------------------
2009-11-23 MF
  -Fixed bug in Fetcher.java 
2009-09-27 MF
	-Betaversion up and runing
________________________________

----------------------
|Author:             |
| Mikael Falgard     |
| sup3rgnu@gmail.com |
----------------------
-----------
| 1.INTRO |
-----------
	1.1 Lorem Ipsum
	---------------	

	1.2 Getting Started
        --------------------
Fill in the years and municipals that you want to fetch birthdays for, in the importer.Input.INPUT_FILE. Note: It's important that they are in that order, and that atleast one of each category is entered. Then just enter the wanted month as an main argument with the format 1-12 for desired month. 

------------------------------------
| 2.IMPORTANT NOTES BEFORE RUNNING |
------------------------------------

	2.1 Confirm values at the following places:
	-------------------------------------------

		main.Main.CURRENTYEAR = Obviously the current year
		main.Main.UID = Username for www.birthday.se
		main.Main.PWD = Password for www.birthday.se
		importer.Input.numberOfMunicipals = Should match # municipals in importer.Input.INPUT_FILE
		importer.Input.numberOfYears = Should match # years in importer.Input.INPUT_FILE

	2.2 Restrictions
	----------------

This version will only run on the Swedish version of www.birthday.se, because the parser only works for Swedish in the current version. Also it will only fetch as many people as are specified in importer.Fetcher.NUMBEROFPEOPLE which at default is set to '1000' in the current version. Fulhack indeed, but that's how it's gonna go down for now. 

	2.3 External JARS
	-----------------

		2.3.1 Usage
			Make sure all jars under lib folder is in classpath. 
			
		2.3.2 List of jars
			jxl.jar
			jexcelapi_2_6_10.zip
			htmlunit-2.6.jar
			...
			TODO: Fill in rest of jars. 
-------------
| 3.CREDITS |
-------------

	3.1 Contributions
	-----------------
		Thanks to Kai Xie for helping me sorting out htmlunit.
-------------
| 4.UPDATES |
-------------

	4.1 Latest major fixes 
	----------------
Version 1.2 - Removed bug in Fetcher.java, didn't fetch from all municipals. Removed % from %c= in string url (line 38) which messed up the characters.

Version 1.1 - Generic numberOfYears and numberOfMunicipals in importer.Input. Which means that it doesn't matter if that value matches the one in the INPUT_FILE. The program will fix it automagically.
	
