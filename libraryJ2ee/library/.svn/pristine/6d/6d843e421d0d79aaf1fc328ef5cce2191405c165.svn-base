package it.univaq.mwt.j2ee.library.presentation.titles.servlet;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;
import it.univaq.mwt.j2ee.library.presentation.common.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTitleServlet extends BaseServlet {

	
	protected String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String isbn = req.getParameter("isbn");
		int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
		String editor = req.getParameter("editor");
		Long titleKindId = Long.parseLong(req.getParameter("titleKind"));
		TitleKind titleKind = new TitleKind(titleKindId);
		Title title = new Title(id, name, author, description, isbn, publicationYear, editor, titleKind);
		
		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance(); 
		TitleService service = factory.getTitleService();

		try {
			service.updateTitle(title);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return "redirect:/titles/views.do";
		
	}	
}