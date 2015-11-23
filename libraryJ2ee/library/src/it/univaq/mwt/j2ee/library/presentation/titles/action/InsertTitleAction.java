package it.univaq.mwt.j2ee.library.presentation.titles.action;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InsertTitleAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse response) throws Exception {
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String description = req.getParameter("description");
		String isbn = req.getParameter("isbn");
		int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
		String editor = req.getParameter("editor");
		Long titleKindId = Long.parseLong(req.getParameter("titleKind"));
		TitleKind titleKind = new TitleKind(titleKindId);
		Title title = new Title(name, author, description, isbn, publicationYear, editor, titleKind);
		
		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance(); 
		TitleService service = factory.getTitleService();

		try {
			service.createTitle(title);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
	
	

}
