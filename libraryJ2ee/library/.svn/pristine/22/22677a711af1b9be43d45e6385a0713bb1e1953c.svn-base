package it.univaq.mwt.j2ee.library.presentation.titles;

import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.RequestGrid;
import it.univaq.mwt.j2ee.library.business.ResponseGrid;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;
import it.univaq.mwt.j2ee.library.common.JsonUtility;
import it.univaq.mwt.j2ee.library.common.ResponseUtility;
import it.univaq.mwt.j2ee.library.presentation.common.RequestGridForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

public class TitlesAction extends MappingDispatchAction {

	public ActionForward views(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward findAllTitlesPaginated(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RequestGridForm form = (RequestGridForm) actionForm;
		RequestGrid requestGrid = new RequestGrid();
		BeanUtils.copyProperties(requestGrid, form);

		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance();
		TitleService service = factory.getTitleService();

		ResponseGrid responseGrid = service.findAllTitlesPaginated(requestGrid);

		String json = JsonUtility.writeValueAsString(responseGrid);
		ResponseUtility.generateJsonResponse(response, json);

		return null;
	}


	public ActionForward insert(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		TitleForm form = (TitleForm) actionForm;
		Title title = new Title();
		BeanUtils.copyProperties(title, form);
		TitleKind titleKind = new TitleKind(form.getTitleKindId());
		title.setTitleKind(titleKind);

		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance();
		TitleService service = factory.getTitleService();

		service.createTitle(title);
		return mapping.findForward("success");
	}

	public ActionForward updateStart(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TitleForm form = (TitleForm) actionForm;
		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance();
		TitleService service = factory.getTitleService();

		Title title = service.findTitleByPK(form.getId());
		BeanUtils.copyProperties(form, title);
		form.setTitleKindId(title.getTitleKind().getId());
		return mapping.findForward("form");
	}

	public ActionForward update(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		TitleForm form = (TitleForm) actionForm;
		Title title = new Title();
		BeanUtils.copyProperties(title, form);
		TitleKind titleKind = new TitleKind(form.getTitleKindId());
		title.setTitleKind(titleKind);

		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance();
		TitleService service = factory.getTitleService();

		service.updateTitle(title);
		return mapping.findForward("success");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm actionForm, HttpServletRequest req, HttpServletResponse response) throws Exception {
		TitleForm form = (TitleForm) actionForm;
		Title title = new Title();
		BeanUtils.copyProperties(title, form);
		TitleKind titleKind = new TitleKind(form.getTitleKindId());
		title.setTitleKind(titleKind);

		LibraryBusinessFactory factory = LibraryBusinessFactory.getInstance();
		TitleService service = factory.getTitleService();

		service.deleteTitle(title);
		return mapping.findForward("success");
	}

}
