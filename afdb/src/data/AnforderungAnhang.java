package data;

import java.io.Serializable;

public class AnforderungAnhang implements Serializable{
	
	private Anforderung anforderung;
	private Anhang anhang;
	
	public AnforderungAnhang() {
		
	}

	public AnforderungAnhang(Anforderung anforderung, Anhang anhang) {
		this.anforderung = anforderung;
		this.anhang = anhang;
	}

	public Anforderung getAnforderung() {
		return anforderung;
	}

	public void setAnforderung(Anforderung anforderung) {
		this.anforderung = anforderung;
	}

	public Anhang getAnhang() {
		return anhang;
	}

	public void setAnhang(Anhang anhang) {
		this.anhang = anhang;
	}
	
	

}
