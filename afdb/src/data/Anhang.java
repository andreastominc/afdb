package data;

import java.sql.Blob;
import java.util.Date;

public class Anhang {
	
	private int anhangId;
	private Blob datei;
	private String name;
	private Date hinzugefuegtAm;
	
	public Anhang()
	{
		
	}
	public Anhang(int anhangId, Blob datei, String name, Date hinzugefuegtAm) {
		super();
		this.anhangId = anhangId;
		this.datei = datei;
		this.name = name;
		this.hinzugefuegtAm = hinzugefuegtAm;
	}
	public int getAnhangId() {
		return anhangId;
	}
	public void setAnhangId(int anhangId) {
		this.anhangId = anhangId;
	}
	public Blob getDatei() {
		return datei;
	}
	public void setDatei(Blob datei) {
		this.datei = datei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getHinzugefuegtAm() {
		return hinzugefuegtAm;
	}
	public void setHinzugefuegtAm(Date hinzugefuegtAm) {
		this.hinzugefuegtAm = hinzugefuegtAm;
	}
	
	
	
	

}
