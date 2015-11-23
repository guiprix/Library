package it.univaq.mwt.j2ee.library.business;



public abstract class LibraryBusinessFactory {
	
	private static LibraryBusinessFactory instance;
	
	public static LibraryBusinessFactory getInstance() {
		return instance;
	}
	
	public static void setInstance(LibraryBusinessFactory factory) {
		instance = factory;
	}
	
	
	
	public abstract TitleService getTitleService();	
	
	public abstract SecurityService getSecurityService();
	

}
