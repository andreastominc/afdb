package data;

public class Version {
	private int versionId;
	private String bezeichnung;
	
	public Version() {
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
	public String toString() {
		return "Version [versionId=" + versionId + ", bezeichnung=" + bezeichnung + "]";
	}


}
