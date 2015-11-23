package it.univaq.mwt.j2ee.library.presentation.titles.action;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InsertStartTitleAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance(); 
		TitleService service = factory.getTitleService();

		try {
			List<TitleKind> titleKinds = service.findAllTitleKinds();
			request.setAttribute("titleKinds", titleKinds);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("form");
	}
	
	

}
