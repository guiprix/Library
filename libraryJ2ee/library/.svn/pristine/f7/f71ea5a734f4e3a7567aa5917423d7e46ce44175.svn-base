package it.univaq.mwt.j2ee.library.presentation.titles.servlet;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.impl.JDBCTitleService;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;
import it.univaq.mwt.j2ee.library.presentation.common.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertStartTitleServlet extends BaseServlet {
	
	protected String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance(); 
		TitleService service = factory.getTitleService();

		try {
			List<TitleKind> titleKinds = service.findAllTitleKinds();
			req.setAttribute("titleKinds", titleKinds);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/titles/inserttitle.jsp";
		
	}	
}
