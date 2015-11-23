package it.univaq.mwt.j2ee.library.presentation;

import java.util.List;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.RequestGrid;
import it.univaq.mwt.j2ee.library.business.ResponseGrid;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/titles")
public class TitlesController  {
	
	@Autowired
	private TitleService service;
	@Autowired
	private TitleValidator validator;// per iniettare questa classe devo creare una classe TitleValidator e annotarla come con titleservice
	// la nnotazione e @Component , le cerca anche questa qnd scansianoa tt il  packg presentation
	
	@RequestMapping("/views.do")
	public String views(){
		
		return "titles.views";//queata stringa e' il tiles dove deve andaare a risolvere la vista
	}

	
	@RequestMapping("/findAllTitlesPaginated.do")
	@ResponseBody 
	public ResponseGrid<Title> findAllTitlesPaginated(@ModelAttribute RequestGrid requestGrid) throws BusinessException{
		//TitleService service = LibraryBusinessFactory.getInstance().getTitleService();
		ResponseGrid<Title> result = service.findAllTitlesPaginated(requestGrid);
		//ConversionUtility.toJason(result,response);
		return result;
	}
	
	@RequestMapping("/create_start.do")
	public String createStart(Model model) throws BusinessException{//il model e un interfaccia
		//di spring come il dizionario della richiesta
		model.addAttribute("title", new Title());
		//" title "e il modelAttribute della title_form
		
		//come sopra e' il model attribute di titleKind
		List<TitleKind>titleKinds = service.findAllTitleKinds();
		model.addAttribute("TitleKinds", titleKinds);
		return "titles.createform";//questo va frwd al e il tile nella classe tiles-defs.xml
	//che mi manda alla jsp che mi fa vedere i campi
	}
	@RequestMapping(value="/create.do", method= RequestMethod.POST) //qnd inserisco un titolo sonon in POST e glielo devo dire espressamente a differenza del get
	public String create(@ModelAttribute Title title, BindingResult bindingResult) throws BusinessException{
		//chiamo il metodo create del service - 
		//bindingResult e la validazione o meglio prende gli errori di conversione dalla form al modello
		
		validator.validate(title, bindingResult);//vedi autowired sopra di validator all oggetto validate gli passo title e binding result,
		
		//se ci sono errori in bindingResultinfatti faccio l if:
		if(bindingResult.hasErrors()){
			return"titles.createform";//se ci sonon errori true allora lom rimando al tiles della form torna a create form
		}
		service.create(title);//nell interfaccia titleService metto il metodo create
		// che viene impelmentata nel JDBC Title Service
		
		return "redirect:/titles/views.do";//vado invece dei tiles lo mando alla vista dei titoli direttamente
	}
		
	@RequestMapping("/update_start.do")
	public String updateStart(@RequestParam("id") Long id,  Model model) throws BusinessException{
		Title title = service.findTitleByPk(id);
		model.addAttribute("title", title);
		return "titles.updateform";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String update(@ModelAttribute Title title, BindingResult bindingResult) throws BusinessException {
		validator.validate(title, bindingResult);
		if (bindingResult.hasErrors()) {
			return "titles.updateform";
		}
		service.update(title);
		
		return "redirect:/titles/views.do";
	}
	
	@RequestMapping("/delete_start.do")
	public String deleteStart(@RequestParam("id") Long id,  Model model) throws BusinessException{
		Title title = service.findTitleByPk(id);
		model.addAttribute("title", title);
		return "titles.deleteform";
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String delete(@ModelAttribute Title title) throws BusinessException {

		service.delete(title);
		
		return "redirect:/titles/views.do";
	}
	
	
	@ModelAttribute //con questa notazione legata al lmetodo si chiama prima questao metodo di tut gli altri nella classe
	//quindi riempoiamo il modello con l attribuo cosi la select e sempre presente nella form
	public void findAllTitles(Model model) throws BusinessException {
		List<TitleKind> titleKinds =service.findAllTitleKinds();
		
		//riempo il modello che viene cpmpilato prima di create.do e anche prima degli altri metodi views etc che pero non serve
		model.addAttribute("titleKinds", titleKinds);
	}

}