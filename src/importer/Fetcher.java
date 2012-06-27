package importer;



public class Fetcher {

	public static final int NUMBEROFPEOPLE = 1000; //could be a problem that it's not dynamnic
	public static String[][] storage = new String[NUMBEROFPEOPLE][4];
	public static String[] tempStorage = new String[10];
	public static final String patternNotFound = "Hittade ingen träff på din sökning"; // If there's no matches to the search
	public static boolean nextPage = true;
	public static int numberIterations = 0; 
	public static int sheetNr = 0;



	/** Search with month, and first municipal, then next municipal and so on,  
	 * when all are done - repeat with next year.
	 * For every new search when year is changed, do sheetNr++;
	 * 
	 * When logged in use this url to perform searches:
	 * "http://www.birthday.se/sok/?c=%e5m%e5l&y=1959&m=1"
	 * 
	 * In this example we're searching for; From Åmål, born in Jan, 1959 
	 * 
	 * å=%e5
	 * ä=%e4
	 * ö=%f6
	 */
	public static String search(String c, String y, String m){

		c = c.replaceAll("å","%e5"); //Bug if capital letters? 
		c = c.replaceAll("ä","%e4");
		c = c.replaceAll("ö","%f6");

		//System.out.println(c); //Debugging

		String url = "http://www.birthday.se/sok/?c=" + c + "&y=" +y + "&m=" + m;
		//System.out.println(url);

		return url;
	}

	/**
	 * Splits a String retrieved from a website to substrings with the wanted information
	 * Ex. 1. Name Address Postalnr 
	 * @param tempStorage
	 */
	public static void stringSplitter(String txt, String year){

		if(txt.contains(patternNotFound) == true){
			nextPage = false;
			System.out.println("No matches for the given criteria number of iterations : " + numberIterations);
		}else{

			String[] tempSplit = new String[2];
			tempSplit = txt.split("stavning");

			String [] personSplit = tempSplit[1].split("Karta,");
			if(personSplit.length < 11){
				nextPage = false;
			}
			for(int i=0; i < personSplit.length-1; i++){
				try{
					personSplitter(personSplit[i], i, year);
				}catch(Exception f){
					System.out.println("Fetcher.Failed @ stringSplitter : " +f.getMessage());
				}
				//System.out.println(personSplit[i]);
			}

			for(int k=0; k<10; k++){	
				if(main.Main.DEBUG == true){
					System.out.println(personSplit[k]);
				}
			}
		}
	}

	/**
	 * Uses split to break up a string of input separated by
	 * commas and/or dots and/or <p>.
	 * @param stringToSplit
	 */
	public static void personSplitter(String stringToSplit, int number, String year){

		String pattern = "";

		if (number == 0){
			pattern = "din sökning";
		}else{
			//System.out.println(pattern);
			pattern = year; 
		}

		String[] tempSplit = new String[2];
		tempSplit = stringToSplit.split(pattern);

		//System.out.println(tempSplit[1]);

		String a = "\r\n|\r|\n";
		String b = "\\. ";
		String c = ", ";

		//String[] naStr = new String[2];
		String [] naStr = tempSplit[1].split(a);
		String [] name = naStr[1].split(b);
		String [] address = naStr[2].split(c);

		//String sheet = Integer.toString(sheetNr); 

		storage[numberIterations][0] = name[1];
		storage[numberIterations][1] = address[0];
		storage[numberIterations][2] = address[1];
		//storage[numberIterations][4] = sheet; //debugging
		
		
		if(address.length == 3){
			storage[numberIterations][2] = address[2]; //TODO: Possible bug
		}

		if(main.Main.DEBUG == false){ //Set to 'false' to see what's going on when running.
			for(int j=0; j<3; j++){
				System.out.println(storage[numberIterations][j]);
			}
			System.out.println("");
		}

		iterate();

	}

	public static void iterate()throws ArrayIndexOutOfBoundsException{
		try{
			numberIterations++;
			if(numberIterations == NUMBEROFPEOPLE){
			}
		}catch(Exception n){
			System.out.println("Fetcher.Out of bounds error. Limit of ppl reached in NUMBEROFPEOPLE : " +n.getMessage());
		}
	}

	public static int showIterations(){
		System.out.println("Number of iterations: " + numberIterations);
		return numberIterations;
	}


}
