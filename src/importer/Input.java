package importer;

import java.io.*;
import java.util.Scanner;

public class Input {

	public static final String INPUT_FILE = "inputfile.txt";
	public static int numberOfMunicipals = 10; //Default = 10 it's not recommended to run greater numbers. See README.
	public static int numberOfYears = 10; //Default = 10 it's not recommended to run greater numbers. See README.
	public static final String[] months = new String[13];
	public static String[] municipal = new String[numberOfMunicipals];
	public static String[] years = new String[numberOfYears];
	/**
	 * Fetches wanted years and municipal's from file, and stores them in their arrays. 
	 * @throws IOException
	 */
	public static void importInfo(){
		Scanner s = null;

		try {
			s = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));

			int i = 0;
			int k = 0;
			String [] tmpYears = new String [10];
			String [] tmpMunicipal = new String [10];

			while (s.hasNext()) {
				if(s.hasNextInt()){
					tmpYears[i] = s.next();
					i++;
				}else{
					tmpMunicipal[k] = s.next();
					k++;
				}
			}
			
			numberOfYears = i;
			numberOfMunicipals = k;
			
			municipal = new String[numberOfMunicipals];
			years = new String[numberOfYears];
			
			for(int l = 0; l< numberOfYears; l++ ){
				years[l] = tmpYears[l];
				//System.out.println(years[l]);
			}
			
			for(int m = 0; m < numberOfMunicipals; m++ ){
				municipal[m] = tmpMunicipal[m];
				//System.out.println(municipal[m]);
			}
		}

		catch(FileNotFoundException e){
			System.err.println("File not found " + INPUT_FILE);

		}finally {
			if (s != null) {
				s.close();
			}
		}

		if(main.Main.DEBUG == true){
			for(int i = 0; i <= numberOfYears-1; i++){
				//System.out.println(years[i]);
			}
			for(int i = 0; i <= numberOfMunicipals-1; i++){
				//System.out.println(municipal[i]);
			}
		}
	}

	/**
	 * @param recives monthNumber from main args[] 
	 * @return chosen month as a string to main, eg "Oktober" if monthNumber is = 10
	 */
	public static String setMonths(int month){

		months[0] = "";
		months[1] = "Januari";
		months[2] = "Februari";
		months[3] = "Mars";
		months[4] = "April";
		months[5] = "Maj";
		months[6] = "Juni";
		months[7] = "Juli";
		months[8] = "Augusti";
		months[9] = "September";
		months[10] = "Oktober";
		months[11] = "November";
		months[12] = "December";

		if(main.Main.DEBUG == true){
			System.out.println(month);
			System.out.println(months[month]);
		}
		return months[month]; 
	}
}
