package it.univaq.mwt.j2ee.library.presentation.titles;

import java.io.IOException;
import java.util.List;

import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;

public class TitleKindTilesAction implements Controller {

	@Override
	public void execute(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
		TitleService service = LibraryBusinessFactory.getInstance().getTitleService();
		List<TitleKind> titleKinds = service.findAllTitleKinds();
		request.setAttribute("titleKinds", titleKinds);
		
	}

	@Override
	public void perform(ComponentContext context, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
		
	}
}