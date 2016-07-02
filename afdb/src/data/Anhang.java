package data;

import java.io.File;
import java.sql.Blob;
import java.util.Date;

public class Anhang {
	
	private Integer anhangId;
	private Blob datei;
	private String name;
	private Date hinzugefuegtAm;
	private Anforderung anforderung;
	private File file;
	
	public Anhang(){
	}
	
	public Anhang(Integer anhangId, Blob datei, String name, Date hinzugefuegtAm) {
		super();
		this.anhangId = anhangId;
		this.datei = datei;
		this.name = name;
		this.hinzugefuegtAm = hinzugefuegtAm;
	}
	public Integer getAnhangId() {
		return anhangId;
	}
	public void setAnhangId(Integer anhangId) {
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

	public Anforderung getAnforderung() {
		return anforderung;
	}

	public void setAnforderung(Anforderung anforderung) {
		this.anforderung = anforderung;
	}

	@Override
	public String toString() {
		//return "Anhang [anhangId=" + anhangId + ", name=" + name + ", hinzugefuegtAm="
		//		+ hinzugefuegtAm + ", anforderung=" + anforderung.getAnfId() + "]";
		return name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	
	
	
	



}
