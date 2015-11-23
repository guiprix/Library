package it.univaq.mwt.j2ee.library.presentation.titles.servlet;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.presentation.common.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewsTitlesServlet extends BaseServlet {

	protected String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance(); 
			TitleService service = factory.getTitleService();
			List<Title> titles = service.findAllTitles();
			req.setAttribute("titles", titles);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/titles/titleviews.jsp";
	}	

}
