package it.univaq.mwt.j2ee.library.business;

import java.util.List;

import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

public interface TitleService {

	void createTitle(Title title) throws BusinessException;

	List<Title> findAllTitles() throws BusinessException;
	
	Title findTitleByPK(Long id) throws BusinessException;
	
	void updateTitle(Title title) throws BusinessException;

	List<TitleKind> findAllTitleKinds() throws BusinessException;

	ResponseGrid findAllTitlesPaginated(RequestGrid requestGrid) throws BusinessException;

	void deleteTitle(Title title) throws BusinessException;
}
