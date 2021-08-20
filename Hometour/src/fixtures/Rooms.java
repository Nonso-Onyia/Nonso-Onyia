package fixtures;

public class Rooms extends Fixture {

	private Rooms[] exits= new Rooms[4];
	
	private Fixture[] Items; 
	
	public Rooms(String name, String shortDescription, String longDescription) {
		super (name, shortDescription, longDescription);
		
		
	}
	public Rooms[] getExits() {
		return this.exits;
	}
	public Rooms getExits(String direction) {
		int index= 0; 
		direction = direction.toUpperCase();
		
		switch(direction) {
		
		case "north":
			index = 0;
			break;
			
		case "east": 
			index = 1;
			break;
			
		case "south": 
			index = 2;
			break;
			
		case "west": 
			index = 3;
			break;
			 
		}
		if (index >= this.exits.length || this.exits[index] == null) {
			System.out.println("can not go in that direction");
			
			return this; 
		}
		return this.exits[index];
	    }
		public void setExits(Rooms[] exits) {
			this.exits = exits;
		}
		public void setExits(Rooms exits, int index) {
			this.exits[index]= exits;
		}
		public Fixture[] getItems() {
			return Items;
		}	
		public void setItems(Fixture[] Items, Fixture...items) {
			this.Items= Items;
	}
		public String getLongDescription() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
}
