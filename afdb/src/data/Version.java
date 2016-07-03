package data;

public class Version {
	private int versionId;
	private String bezeichnung;
	
	public Version() {
	}

	public Version(int verId, String bez) {
		this.versionId = verId;
		this.bezeichnung = bez;
	}

	public int getVersionId() {
		return versionId;
	}



	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}



	public String getBezeichnung() {
		return bezeichnung;
	}



	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}


	@Override
	//nötig für ComboBox Anzeige
	public String toString() {
		return bezeichnung;
	}
		


}
