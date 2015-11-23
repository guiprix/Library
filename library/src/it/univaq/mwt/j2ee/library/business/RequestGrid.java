package it.univaq.mwt.j2ee.library.business;

public class RequestGrid implements java.io.Serializable {
	
	private Long iDisplayStart;
	private Long iDisplayLength;
	private String sEcho;
	private String sSearch;
	private String sortCol;
	private String sortDir;
	
	
	public RequestGrid() {
		super();
	}
	public RequestGrid(Long iDisplayStart, Long iDisplayLength, String sEcho, String sSearch, String sortCol, String sortDir) {
		super();
		this.iDisplayStart = iDisplayStart;
		this.iDisplayLength = iDisplayLength;
		this.sEcho = sEcho;
		this.sSearch = sSearch;
		this.sortCol = sortCol;
		this.sortDir = sortDir;
	}
	public Long getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(Long iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public Long getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(Long iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
}