package it.univaq.mwt.j2ee.library.business;

import java.util.List;

import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;


public interface TitleService {
	
	ResponseGrid<Title> findAllTitlesPaginated(RequestGrid requestGrid) throws BusinessException;

	//prende requestGrid in ingresso e mi da il responseGrid

	List<TitleKind> findAllTitleKinds() throws BusinessException;
	
	void create(Title title) throws BusinessException;

	Title findTitleByPk(Long id) throws BusinessException;

	void update(Title title) throws BusinessException;

	void delete(Title title) throws BusinessException;
	
}
