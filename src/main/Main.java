package main;

import importer.Input;

import java.io.IOException;

public class Main {

	public static final boolean DEBUG = false;
	public static int month = 0;
	public static final int CURRENTYEAR = 2010;
	public static String xlsfilename = "Fodelsedagar.xls";
	public static String UID = "username";
	public static String PWD = "password";
	public static int [] sheet = new int [importer.Input.numberOfYears];
	public static String [][] urls = new String [0] [0];
	/**
	 * @param args
	 * @author Mikael Falgard
	 * @version 2009-09-28
	 */
	public static void main(String[] args) throws IOException{
		try{
			month = Integer.parseInt(args[0]);
			if(month <= 0 || month > 12){
				System.out.println("Main.Wrong month number, Usage: Pick a month 1-12");
			}else{
				String m = Integer.toString(month);
				try{
					importer.Input.importInfo();
				}catch (Exception s){
					System.out.println("Main.Input error : " + s.getMessage());
				}

				urls = new String [importer.Input.numberOfYears] [importer.Input.numberOfMunicipals];
				
				for(int i = 0; i<Input.numberOfYears; i++){
					for(int j = 0; j <Input.numberOfMunicipals; j++){
						String y = Input.years[i];
						String c = Input.municipal[j];
						urls[i][j] = importer.Fetcher.search(c, y, m);
						System.out.println("URL to search : " + urls[i][j]);
					}

				}


				try{ 
					importer.Logon.Login();

				}catch(Exception e){
					System.out.println("Main.Failed during import : " + e.getMessage());
				}	

				String filename = importer.Input.setMonths(month) + xlsfilename; 
				String[] yr = new String[importer.Input.numberOfYears];

				for(int i = 0; i < importer.Input.numberOfYears; i++){
					int k = Integer.parseInt(importer.Input.years[i]);
					int tmpYr = CURRENTYEAR-k;
					yr[i] = "Fyller " + tmpYr;
				}
				//System.out.println(yr + filename);
				exporter.DocCreater.createDocument(yr, filename);



				if(main.Main.DEBUG == true){
					for(int i=0; i<=importer.Fetcher.numberIterations-1; i++){
						for(int k=0; k<=4; k++){
							System.out.println(importer.Fetcher.storage[i][k]);
						}
						System.out.println("");
					}
				}
			}
		}catch (NumberFormatException ex) {
			System.out.println("Input should be a the number of a month, eg: 10 for Oktober : " + ex.getMessage());
			return;
		}
		importer.Fetcher.showIterations();
	}

}


