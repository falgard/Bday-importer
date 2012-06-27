package importer;

import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.BrowserVersion;
//import org.apache.log4j.Logger;
//import org.apache.log4j.BasicConfigurator;

public class Logon {


	//private static Logger logger = Logger.getLogger(Logon.class);

	public void submittingForm() throws Exception {
		try{
			final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);
			webClient.setThrowExceptionOnScriptError(false);
			// Get the first page
			final HtmlPage logonPage = webClient.getPage("http://www.birthday.se/logga-in/");
			//        logonPage.setStrictErrorChecking(false);
			//System.out.println(logonPage.getPage().asText());
			// See what we have in the logon page
			//        logger.debug(logonPage.getTextContent());

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = logonPage.getFormByName("aspnetForm");
			final HtmlTextInput userNameInput = form.getInputByName("ctl00$cphContent$txtUsername");

			userNameInput.setValueAttribute(main.Main.UID);
			final HtmlPasswordInput passwordInput = form.getInputByName("ctl00$cphContent$txtPassword");
			passwordInput.setValueAttribute(main.Main.PWD);
			final HtmlSubmitInput button = form.getInputByName("ctl00$cphContent$btnSubmit");
			@SuppressWarnings("unused")
			final HtmlPage pageAfterLogon = button.click();

			System.out.println("Logged in");
			System.out.println("");
			//System.out.println(pageAfterLogon.asText());
			for(int i = 0; i<Input.numberOfYears; i++){
				for(int j = 0; j <Input.numberOfMunicipals; j++){
					final HtmlPage searchPage = webClient.getPage(main.Main.urls[i][j]);
					String txt = searchPage.asText();
					//System.out.println(main.Main.urls[i][j]); //Debugging kinda
					importer.Fetcher.stringSplitter(txt, importer.Input.years[i]);
					try{
						int k = 2;
						while(importer.Fetcher.nextPage == true){
							String nextUrl = main.Main.urls[i][j] + "&page=" + k;
							final HtmlPage nextPage = webClient.getPage(nextUrl);
							String txt2 = nextPage.asText();
							//System.out.println(nextUrl); //Debugging kinda
							importer.Fetcher.stringSplitter(txt2, importer.Input.years[i]);
							k++;
						}
					}catch(Exception no){
						System.out.println("Next page not found : " +no.getMessage());
					}
					importer.Fetcher.nextPage = true;

				}
				main.Main.sheet[i] = importer.Fetcher.showIterations();
				System.out.println("sheet " + i + " : "  + main.Main.sheet[i]);
				System.out.println("");				
			}
		}catch(Exception l){
			System.out.println("Login failed : " +l.getMessage());
		}
	}

	public static void Login() throws Exception {
		// BasicConfigurator.configure();
		new Logon().submittingForm();
	}
}
