package game;

import fixtures.Rooms;

public class gamemanager {
	public Rooms startingRoom;
	public Rooms[] rooms;
	
	public gamemanager(int roomCount) {
		rooms = new Rooms[roomCount]; 
	}
	
	public void init() {
		Rooms Corridor = new Rooms(
				"Corridor",
				"main walkway into the house",
				"You enter the house and walk into the Corridor."
				+ "To the north you find a large open room that appears to be a man cave" + "\n" + "To the east you see a glass paneled room, it appears to be a sun room. " +"\n" + "To the south on the right hand side of the door you see a medium sized open space that looks like a dining room" + "\n" + "To the west you seem a small room and heard the low rumbling of laundry machines" );
		        
		       this.rooms[0] = Corridor; 
		       this.startingRoom = Corridor;
		       
		  Rooms manCave = new Rooms(
						"manCave",
						"Welcome to the man cave",
						"this is a large room with sports memoribilia on the walls, a large televison, and a gaming monitor and PC set up in the corner. To the south a dining room table can be seen" );
		                 
		                this.rooms[1] = manCave;
		                
		  Rooms sunRoom = new Rooms(
						"sun room",
						"Welcome to the sun room ",
						"This is a large room filled with a wide assortment of plants. each unique, some complex and others simple. the light shines through from each side except west facing side of the room. on that side you see the dim lights of a laundry room" );
				                 
				        this.rooms[2] = sunRoom;
			
		  Rooms diningRoom = new Rooms(
						"dining room",
						"Welcome to the dining room",
						"this is a brightly lit room that radiates warmth. the room is filled with smells from a kitchen that is being used along the eastern wall" );
				                 
				         this.rooms[3] = diningRoom;
				         
		  Rooms laundryRoom = new Rooms(
						 "laundry room",
						 "Welcome to the laundry room",
						 "this is a humid small room, yet has a relaxing environment. The low humming of a washing machine and dryer in use. the air smells like fresh linen and dryer sheets" );
					                 
					      this.rooms[4] = laundryRoom;	         
	
	}
         public Rooms getStartingRoom() {
        	 return this.startingRoom; 
        	 
         }
         public Rooms[] getRooms() {
        	 return this.rooms;
         }
         public void setRooms(Rooms[] rooms) {
        	 this.rooms = rooms;
         }
}
