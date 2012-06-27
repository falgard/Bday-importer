package exporter;

import java.io.*;

import jxl.*;
import java.util.*;
import jxl.Workbook;
import jxl.write.*;

public class DocCreater {

	public static void createDocument(String[] sheetLabelArray, String filename){
		try
		{
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filename), ws);

			for(int i = 0; i < importer.Input.numberOfYears; i++){
				int sheetStart = 0;
				int sheetBreak = main.Main.sheet[i];

				if(i == 0){
					sheetStart = 0;
				}else{
					sheetStart = main.Main.sheet[i-1];
				}

				String sheetLabel = sheetLabelArray[i];
				WritableSheet s = workbook.createSheet(sheetLabel, i);
				writeDataSheet(s, i, sheetStart, sheetBreak);
			}
			workbook.write();
			workbook.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("IO fail : " + e.getMessage());
		}
		catch (WriteException w)
		{
			w.printStackTrace();
			System.out.println("Write Exception : " + w.getMessage());
		}finally{
			System.out.println("");
			System.out.println("Done writing to excel sheet.");
			System.out.println("");
			//workbook.close(); close workbook somehow if try doesn't work out. 
		}
	}

	/**
	 * 
	 * @param s
	 * @param year
	 * @param x - current sheet number 
	 * @throws WriteException
	 */
	private static void writeDataSheet(WritableSheet s, int year, int sheetStart, int sheetBreak) throws WriteException{

		/* Format the Font */
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat cf = new WritableCellFormat(wf);
		cf.setWrap(true);


		for(int j=0; j < sheetBreak; j++){
			if(j == 0){
				s.setColumnView(j, 40);
			}else if(j == 1){
				s.setColumnView(j,30);
			}else if (j == 2){
				s.setColumnView(j,20);
			}else{} 
				
		}

			for(int i = sheetStart; i < sheetBreak; i++){
				
				int k = i - sheetStart;
				
				/* Labels with the actual info*/
				Label l = new Label(0,k,importer.Fetcher.storage[i][0],cf);
				Label l2 = new Label(1,k,importer.Fetcher.storage[i][1],cf);
				Label l3 = new Label(2,k,importer.Fetcher.storage[i][2],cf);
				//Label l4 = new Label(3,i,importer.Fetcher.storage[i][3],cf);
				//Label l5 = new Label(4,i,importer.Fetcher.storage[i][4],cf); //debugging
				s.addCell(l);
				s.addCell(l2);
				s.addCell(l3);
				//s.addCell(l4);
				//s.addCell(l5); //debugging
			}
		}
	}
