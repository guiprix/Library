package it.univaq.mwt.j2ee.library.presentation.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dispatch(req, resp);
	}
	
	private void dispatch(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String view = process(req, resp);
		if (view.startsWith("redirect:")) {
			resp.sendRedirect(req.getContextPath() + view.substring(9));
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);	
		}
	}
	
	protected abstract String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

}
