package game;
import java.util.Scanner;


public class Main extends gamemanager {
	
	public Main (int roomCount) {
		super(roomCount); 
	}
	
	private static gamemanager manager = new gamemanager(5);
	static int gameOperating= 1; 
	
	public static void main (String[] args) {
		
		manager.init();
		readyplayer1 player = new readyplayer1();
		player.setCurrentRoom(manager.startingRoom); 
		
		while (gameOperating == 1) { 
			
			Main.printRooms(player);
			String[] Input= Main.collectInput();
			Main.parse(Input,player); 
		}
		
		if (gameOperating == 0) {
			System.out.println("Game over! Want to play again?");
		}
	}
	
	private static void printRooms(readyplayer1 player) {
		player.getcurrentRoom();
		System.out.println(player.currentRoom.getName() + "\n");
		System.out.println(player.currentRoom.getLongDescription() + "\n");
	}
	
	private static String[] collectInput() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("to start the game please say where you want to go by providing one of the four cardinal directions (North, East, South, West)");
		
		String Input= scanner.nextLine();
		String[] strArr = Input.split(" ");
		
		if (gameOperating == 0) {
			
			scanner.close();
		}
		return strArr; 
			
	}
	
	public static void parse (String[] Input, readyplayer1 player) {
		if (Input[0].equals("Quit game")) {
			gameOperating= 0; 
		}
		switch(Input[0]){
		case "Start" :
			Main.moving(Input, player);
			break;
		case "Backward" :
			Main.moving(Input, player);
			break;
		case "left" :
			Main.moving(Input, player);
			break;
		case "right" :
			Main.moving(Input, player);
			break;
		default:
			System.out.println("Sorry! You used the wrong word to move. Please pick one of the four responses to move" + "\n" + "Start, Move, Go, Forward");
			break;
		}
	}
		public static void moving1(String[] Input, readyplayer1 player) {
			if (player.currentRoom.equals(manager.rooms[0])) {
				switch (Input[1]) {
				case "North":
					System.out.println("you are heading to the man cave");
					player.setCurrentRoom(manager.rooms[1]);
					break;
				case "East":
					System.out.println("You are heading to the sun room");
					player.setCurrentRoom(manager.rooms[2]);
					break;
				case "South":
					System.out.println("you are heading to the dining room");
					player.setCurrentRoom(manager.rooms[3]);
					break;
				case "West":
					System.out.println("you are heading to the laundry room");
					player.setCurrentRoom(manager.rooms[4]);
					break;
				default:
					System.out.println("Wrong input! Please use one of the cardinal directions (North, East, South, West)");
					break;
				}
			}else if (player.currentRoom.equals(manager.rooms[1])) {
				switch (Input[1]) {
				
				case "North":
					System.out.println("you are heading to the man cave");
					player.setCurrentRoom(manager.rooms[1]);
					break;
				case "East":
					System.out.println("Sorry! You cant go that way");
					player.setCurrentRoom(manager.rooms[2]);
					break;
				case "South":
					System.out.println("you are heading to the dining room");
					player.setCurrentRoom(manager.rooms[3]);
					break;
				case "West":
					System.out.println("Sorry! You cant go that way");
					player.setCurrentRoom(manager.rooms[4]);
					break;
				default:
					System.out.println("Wrong input! Please use go south to leave the man cave and continue your tour");
					break;
				}
				
			}else if (player.currentRoom.equals(manager.rooms[2])) {
				switch (Input[1]) {
				
				case "North":
					System.out.println("you can see through the northern glass wall that there is another room. However you can not go that way");
					player.setCurrentRoom(manager.rooms[1]);
					break;
				case "East":
					System.out.println("you are heading to the sun room");
					player.setCurrentRoom(manager.rooms[2]);
					break;
				case "South":
					System.out.println("you can see through the southern glass wall that there is another room. However you can not go that way");
					player.setCurrentRoom(manager.rooms[3]);
					break;
				case "West":
					System.out.println("you are heading to the laundry room");
					player.setCurrentRoom(manager.rooms[4]);
					break;
				default:
					System.out.println("Wrong input! Please use go west to leave the sun room and continue your tour");
					break;
				}
			}else if (player.currentRoom.equals(manager.rooms[3])) {
				switch(Input[1]) {
				case "North":
					System.out.println("You can not go that way. There is just a wall");
					player.setCurrentRoom(manager.rooms[1]);
					break;
				case "East":
					System.out.println("you are heading to the sun room");
					player.setCurrentRoom(manager.rooms[2]);
					break;
				case "South":
					System.out.println("You can not go that way. There is just a wall");
					player.setCurrentRoom(manager.rooms[3]);
					break;
				case "West":
					System.out.println("you are heading to the laundry room");
					player.setCurrentRoom(manager.rooms[4]);
					break;
				default:
					System.out.println("Wrong input! Please use go East to leave the Laundry room and continue your tour");
					break;
					
				}
			}else if (player.currentRoom.equals(manager.rooms[4])) {
				switch (Input[1]) {
				
				case "North":
					System.out.println("you are heading to the man cave");
					player.setCurrentRoom(manager.rooms[1]);
					break;
				case "East":
					System.out.println("You can not leave that way but theres a kitchen located on that east wall");
					player.setCurrentRoom(manager.rooms[2]);
					break;
				case "South":
					System.out.println("you are heading to the dining room");
					player.setCurrentRoom(manager.rooms[3]);
					break;
				case "West":
					System.out.println("you can not go that way");
					player.setCurrentRoom(manager.rooms[4]);
					break;
				default:
					System.out.println("Wrong input! Please use go north to leave the dining room and continue your tour");
					break;
				}
			}
		}

	private static void moving(String[] Input, readyplayer1 player) {
		// TODO Auto-generated method stub
		
	}
}
