package it.univaq.mwt.j2ee.library1;

import it.univaq.mwt.j2ee.library1.business.BusinessException;
import it.univaq.mwt.j2ee.library1.business.TitleService;
import it.univaq.mwt.j2ee.library1.business.impl.JDBCTitleService;
import it.univaq.mwt.j2ee.library1.business.model.Title;
import it.univaq.mwt.j2ee.library1.business.model.TitleKind;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Library1 {

public static void main(String[] args) throws BusinessException {

		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				
				System.out.println("Gestione Biblioteca 1.0");
				System.out.println("Scegliere le voci di menu");
				System.out.println("1. visualizza titoli");
				System.out.println("2. creazione titoli");
				System.out.println("3. modifica titolo");
				System.out.println("4. cancellazione titolo");
				System.out.println("5. esci");

				String line = reader.readLine();
				int menu = Integer.parseInt(line);
						
				switch (menu) {
				case 1:
					
					break;
				case 2:
					System.out.println("inserisci il nome");
					String name = reader.readLine();
					System.out.println("inserisci l'autore");
					String author = reader.readLine();
					System.out.println("inserisci descrizione");
					String description = reader.readLine();
					System.out.println("inserisci isbn");
					String isbn = reader.readLine();
					System.out.println("anno pubblicazione");
					int publicationYear = Integer.parseInt(reader.readLine());
					System.out.println("inserisci il tipo(1. libro 2. rivista 3.periodico 4.quotidiano)");
					String titleKind = reader.readLine();//TODO: to handle
					TitleKind tk = new TitleKind(Long.parseLong(titleKind), " ", " ");
					System.out.println("editore");
					String editor = reader.readLine();
					Title title = new Title(name, author, description,isbn, publicationYear, editor, tk);
					JDBCTitleService service = new JDBCTitleService();
					service.createTitle(title);
				
					break;
				case 3:
	
					break;
				case 4:
	
					break;
				case 5:

					return;
				}
			}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}


