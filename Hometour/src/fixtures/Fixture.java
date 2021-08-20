package fixtures;

public abstract class Fixture {
	
	private String name;
	private String shortDescription;
	private String longDescription; 

	public Fixture(String name, String shortDescription, String longDescription) {
		
		this.name= name;
		this.shortDescription = shortDescription; 
		this.longDescription = longDescription;
		
	}
		
	public String nameinput() {
		return this.name;
	
	}
		
	public String shortDescriptioninput() {
		return this.shortDescription;
		
	}
	public String longDescriptioninput() {
		return this.longDescription;
	}
}
