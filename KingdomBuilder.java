import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.lang.IndexOutOfBoundsException;
import java.io.BufferedReader;



public class KingdomBuilder{

	public static Boolean loadGameMenu = false;
	public static Boolean newGame = false;
	public static Boolean inGameMenu = false;
	public static Boolean gameStart;
	public static Boolean timerStart = true;
	public static Boolean inActionsMenu = false;
	public static Boolean inStatsMenu = false;
	public static Boolean inSave = false;
	public static Boolean gatheringWood = false;
	public static Boolean eating = false;
	public static String selectedOption; 
	public static Scanner input = new Scanner(System.in);
	public static String error;
	public static String playerKingdomName = "";
	public static Boolean myKingdomMenu = false;
	public static int day = 0;
	public static int timeGathering = 0;
	public static String dataString = "";
	public static int month = 0;
	public static int year = 0;
	public static int hours = 0;
	public static int woodGathered = 0;
	public static float food = 100;
	public static float wood = 0;
	public static float gold = 0;
	public static float energy = 100;
	public static int population = 0;
	public static float kingdomSize = 0;
	public static int seconds = 0;
	public static int minutes = 0;
	public static int updateMenuCount = 0;
	public static Timer timer = new Timer();
	public static Timer actionTimer = new Timer();
	public static float health = 100;
	public static int actionSeconds = 0;
	public static int actionHours = 0;
	public static int foodGathered = 0;
	public static Boolean refreshMenu = false;
	public static Boolean hunting = false;
	public static String fileName = "";




	public static void main(String[] args){

		System.out.println("******************************\n" + "       KINGDOM BUILDER\n" + "******************************\n");

		mainMenu();
		playGame();
	}

	private static void mainMenu(){

		Boolean retryMenu = true;
		System.out.println("-- Main Menu --\n ");
		System.out.println("'1': New Game\n");
		System.out.println("'2': Load Game\n");
		System.out.println("'3': Quit Game\n\n");

		while(retryMenu){
			retryMenu = false;
			selectedOption = input.next();
			try{
				if(selectedOption.equals("1")){
					retryMenu = false;
					newGameStart();
				}
				else if(selectedOption.equals("2")){
					loadGameStart();
					newGame = false;
					retryMenu = false;
				}
				else if(selectedOption.equals("3")){
					retryMenu = false;
					timer.cancel();
					timer.purge();
				}
				else{
					System.out.println("\nIncorrect menu option selected. Please try again.");
					retryMenu = true;
				}
			}catch(NullPointerException e){
				String message = e.getMessage();
				System.out.println("There was a problem with the menu selection");
			}
		}
	}

	private static void newGameStart(){
		newGame = true;
		while(newGame){
			Boolean retryNewGame = true;
			System.out.println(" -- New Game --\n ");
			System.out.println("'1': New Kingdom\n");
			System.out.println("'2': Main Menu\n\n");
			while(retryNewGame){
				retryNewGame = false;
				selectedOption = input.next();
				try{
					if(selectedOption.equals("1")){
						System.out.println("\nEnter Kingdom Name: ");
						try{
							playerKingdomName = input.next();
							retryNewGame = false;
							newGame = false;
							playGame();
						}catch(NullPointerException d){
							error = d.getMessage();
							System.out.println("Kingdom name broke!!");
						}
					}
					else if(selectedOption.equals("2")){
						newGame = false;
						retryNewGame = false;
						mainMenu();
					}
					else{
						System.out.println("\nIncorrect menu option selected. Please try again.");
						retryNewGame = true;
					}
				}catch(NullPointerException s){
					error = s.getMessage();
					System.out.println("New Game broke!");
				}
			}
		}

		newGame = false;
	}

	private static void loadGameStart(){
		loadGameMenu = true;
		Boolean retry = false;
		Boolean loadGame = true;
		String loadImport = "";
		String[] importSplit = {};
		Boolean setData = false;
		int setInt = 0;
		float setFloat = 0;
		int q = 1;
		int r = 7;
		int[] saveData = {seconds, hours, day, month, year, population};
		float[] saveData2 = {energy, food, kingdomSize, wood, gold};

		while(loadGameMenu){
			System.out.println(" \n-- Load Game --\n");
			System.out.println("'1': Load\n");
			System.out.println("'2': Back\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("1")){
				retry = true;
				while(retry){
					if(loadGame){
						retry = false;
                		System.out.println("\nEnter the file name you want to load: ");
               		 	fileName = input.next() + ".txt"; 
               		 	try{
               		 		BufferedReader reader = new BufferedReader(new FileReader(fileName));
               		 		String line = null;
               		 		while((line = reader.readLine()) != null){
               		 			loadImport = line;
               		 		}

               		 	}catch(FileNotFoundException e){
               		 			error = e.getMessage();
               		 			System.out.println("File not found. Please try again!");
               		 			retry = true;
               		 	}catch(IOException t){
               		 			error = t.getMessage();
               		 	}
               		}
				}

				System.out.println("Loading file....");

				importSplit = loadImport.split(" ");
				setData = true;
				while(setData){
					playerKingdomName = importSplit[0];
					setInt = Integer.parseInt(importSplit[1]);
					seconds = setInt;
					setInt = Integer.parseInt(importSplit[2]);
					hours = setInt;
					setInt = Integer.parseInt(importSplit[3]);
					day = setInt;
					setInt = Integer.parseInt(importSplit[4]);
					month = setInt;
					setInt = Integer.parseInt(importSplit[5]);
					year = setInt;
					setInt = Integer.parseInt(importSplit[6]);
					population = setInt;
					setFloat = Float.parseFloat(importSplit[7]);
					energy = setFloat;
					setFloat = Float.parseFloat(importSplit[8]);
					food = setFloat;
					setFloat = Float.parseFloat(importSplit[9]);
					kingdomSize = setFloat;
					setFloat = Float.parseFloat(importSplit[10]);
					wood = setFloat;
					setFloat = Float.parseFloat(importSplit[11]);
					gold = setFloat;

					setData = false;
				}
				playGame();
			}
		
			else if(selectedOption.equals("2")){
				loadGameMenu = false;
				mainMenu();
			}

			else{
				System.out.println("Incorrect option. Try again\n");
				loadGameMenu = true;
			}
		}
	}
	
	private static void playGame(){
		timer();	
		
		System.out.println(" \n-- " + playerKingdomName + " --\n ");
		System.out.println("'1': My Kingdom\n");
		System.out.println("'2': Menu\n\n");

		Boolean retryOption = true;

		while(retryOption){
			selectedOption = input.next();
			retryOption = false;
			if(selectedOption.equals("1")){
				retryOption = false;
				myKingdomMenu();
			}
			else if(selectedOption.equals("2")){
				retryOption = false;
				gameMenu();
			}
			else{
				System.out.println("Incorrect option. Try again.");
				retryOption = true;
			}
		}
	}

	private static void gameMenu(){

		inGameMenu = true;

		System.out.println(" \n-- " + playerKingdomName + " -- ");
		System.out.println(" \n-- Menu --\n ");
		System.out.println("'1': Return To Game\n");
		System.out.println("'2': Save\n");
		System.out.println("'3': Quit\n\n");

		while(inGameMenu){
			selectedOption = input.next();
			if(selectedOption.equals("1")){
				playGame();
				inGameMenu = false;
			}
			else if(selectedOption.equals("2")){
				inGameMenu = false;
				saveGame();
			}
			else if(selectedOption.equals("3")){
				System.out.println("\nQuitting the game..");
				inGameMenu = false;
			}
			else{
				System.out.println("\nIncorrect option. Try again.");
				inGameMenu = true;
			}

		}
	}

	private static void myKingdomMenu(){

		myKingdomMenu = true;	
		while(myKingdomMenu){
			System.out.println(" -- My Kingdom --\n ");
			System.out.println("'1': Actions\n");
			System.out.println("'2': Stats\n");
			System.out.println("'3': Back\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("1")){
				myKingdomMenu = false;
				actionsMenu();
			}
			else if(selectedOption.equals("2")){
				myKingdomMenu = false;
				statsMenu();
			}
			else if(selectedOption.equals("3")){
				myKingdomMenu = false;
				playGame();
			}
			else{
				myKingdomMenu = true;
			System.out.println("Incorrect option. Try again\n");
			}	
		}
	}

	private static void statsMenu(){

		inStatsMenu = true;
		while(inStatsMenu){
			System.out.println(" \n-- " + playerKingdomName + " --\n");
			System.out.println("Hour: " + hours + " Day: " + day + " Month: " + month +  " Year: " + year);
			System.out.println("Player: " + "Health: " + health + " Energy: " + energy);
			System.out.println("Population: " + population + "\n" + "Kingdom Size: " + kingdomSize);
			System.out.println("Gold: " + gold); 
			System.out.println("Food: " + food);
			System.out.println("Wood: " + wood +"\n\n");
			System.out.println("'1': Back\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("1")){
				inStatsMenu = false;
				myKingdomMenu();
			}
			else{
				System.out.println("Incorrect option. Try again\n");
				inStatsMenu = true;
			}
		}
	}

	private static void timer(){

		timer.schedule(new TimerTask(){

			public void run(){

				seconds++;

				if(seconds == 20){
					hours++;
					if(hunting){
						actionHours++;
					}
					else if(gatheringWood){
						actionHours++;
						timeGathering++;
						if(actionHours == 5){
							actionHours = 0;
						}

					}

					health();
					seconds = 0;
				}

				if(hours == 24){
					day++;
					hours = 0;
				}
				try{				
					if(inStatsMenu){
						updateMenuCount++;
						if(updateMenuCount == 20){
							updateMenuCount = 0;
							statsMenu();
						}
					}
					else if(hunting){
						updateMenuCount++;
						if(updateMenuCount == 20){
							updateMenuCount = 0;
							huntingAction();
						}
					}
					else if(gatheringWood){
						updateMenuCount++;
						if(updateMenuCount == 20){
							updateMenuCount = 0;
							woodGatherAction();
						}
					}
					else if(eating){
						updateMenuCount++;
						if(updateMenuCount == 20){
							updateMenuCount = 0;
							eat();
						}
					}
				}catch(IndexOutOfBoundsException s){
					String error = s.getMessage();
				}

			}
		}, 1* 1000, 1* 1000);
	}

	public static void actionsMenu(){

		inActionsMenu = true;
		String actionOption = "";
		System.out.println(" \n-- " +  playerKingdomName + " --\n ");
		System.out.println(" -- Actions --\n ");
		while(inActionsMenu){
			if(energy > 0){
				System.out.println("'1': Hunt\n");
				System.out.println("'2': Gather Wood\n");
				System.out.println("'3': Scout\n");
				System.out.println("'4': Build\n");
				System.out.println("'5': Eat\n");
				System.out.println("'6': Back\n\n");
				actionOption = input.next();
				if(actionOption.equals("1")){
					huntingAction();
					inActionsMenu = false;
				}
				else if(actionOption.equals("2")){
					woodGatherAction();
					inActionsMenu = false;
				}
				else if(actionOption.equals("3")){
					
					inActionsMenu = false;
				}
				else if(actionOption.equals("4")){
					
					inActionsMenu  = false;
				}
				else if(actionOption.equals("5")){
					eat();
					inActionsMenu = false;
				}
				else if(actionOption.equals("6")){
					myKingdomMenu();
					inActionsMenu  = false;
				}
				else{

				 System.out.println("Incorrect option. Try again\n");
				}
			}
			else{
				System.out.println("You don't have any energy. You need to eat.\n");
				System.out.println(" \n-- " +  playerKingdomName + " --\n ");
				System.out.println(" -- Actions --\n ");
				System.out.println("'1': Eat\n");
				System.out.println("'2': Back\n\n");
				selectedOption = input.next();
				if(selectedOption.equals("1")){
					inActionsMenu = false;
					eat();
				}
				else if(selectedOption.equals("2")){
					inActionsMenu = false;
					myKingdomMenu();
				}
				else{
					System.out.println("Incorrect option. Try again\n");
				}

			}
		}
	}

	public static void eat(){
		eating = true;
		Boolean canEat = true;
		int maxEnergy = 100;
		float num = 0;
		while(eating){
			System.out.println(" \n-- Eat Menu -- ");
			System.out.println("Energy: " + energy + " Food: " + food + "\n");
			if(food == 0){
				System.out.println("You do not have any food.\n");
				canEat = false;
				System.out.println("'1': Back\n\n");
				selectedOption = input.next();
					if(selectedOption.equals("1")){
						eating = false;
						actionsMenu();
					}
					else{
						 System.out.println("Incorrect option. Try again\n");
					}

			}else{
		
				System.out.println("'1': Eat\n");
				System.out.println("'2': Back\n");
				
				selectedOption = input.next();
				if(selectedOption.equals("1")){
					System.out.println("\nHow much food do you want to eat?");
					selectedOption = input.next();
					float foodEaten = Float.parseFloat(selectedOption);
					if(foodEaten <= 100){
						
						energy = energy + foodEaten;
						
						if(energy > 100){
							System.out.println("\nThe amount of food eaten exceeds the maximum amount of energy.\n");
							energy = energy - foodEaten;	
						}
						else{
							food = food - foodEaten;
							if(food < 0){
								System.out.println("\nYou do no have enough food to eat that much!\n");
								food = food + foodEaten;
								energy = energy - foodEaten;
							}
						}
					}

					else{
						System.out.println("The maximum amount of food that can be eaten is 100");
					}
				}

				else if(selectedOption.equals("2")){
					eating = false;
					updateMenuCount = 0;
					actionsMenu();
				}
			}
		}
	}

	public static void health(){

		if(energy > 0){
			energy = energy - 1.5f;	
			if(hunting){
				energy = energy - 3.0f;
				if(energy < 0){
					energy = 0;
				}
			}
			else if(gatheringWood){
				energy = energy - 6.0f;
				if(energy < 0){
					energy = 0;
				}
			}
		}

		if(energy < 0){
			energy = 0;
		}
		
		if(energy == 0){
			health = health - 1.5f;
		}
	}

	public static void huntingAction(){

		hunting = true;
		Random random = new Random();
		while(hunting){
			int randomNum = random.nextInt((100 - 0) + 1) + 0;
			System.out.println(" \n\n-- Hunting -- ");
			System.out.println("Hour: " + hours + " Day: " + day + " Month: " + month +  " Year: " + year);
			System.out.println("Player: " + "Health: " + health + " Energy: " + energy);	
			System.out.println("Hours spent hunting: " + actionHours + " Food Gathered: " + foodGathered);
			System.out.println("\n'1': Stop Hunting\n");
	
			if(actionHours == 1){
				if(randomNum <= 03){
					foodGathered = foodGathered + 40;
				}
				else if(randomNum > 03 && randomNum <= 45){
					foodGathered = foodGathered + 1;
				}
			}
			else if(actionHours > 1 && actionHours <=5){
				if(randomNum <= 05){
					foodGathered = foodGathered + 35;
				}
				else if(randomNum > 06 && randomNum <= 50){
					foodGathered = foodGathered + 1;
				}
			}

			else if(actionHours > 5 && actionHours <=10){
				if(randomNum <= 07){
					foodGathered = foodGathered + 30;
				}
				else if(randomNum > 07 && randomNum <= 60){
					foodGathered = foodGathered +  1;
				}
			}

			else if(actionHours > 10 && actionHours <=15){
				if(randomNum <= 10){
					foodGathered = foodGathered + 25;
				}
				else if(randomNum > 10 && randomNum <= 70){
					foodGathered = foodGathered + 1;
				}
			}

			else if(actionHours > 15 && actionHours <=24){
				if(randomNum <= 12){
					foodGathered = foodGathered + 15;
				}
				else if(randomNum > 12 && randomNum <= 65){
					foodGathered = foodGathered + 1;
				}
			}
			selectedOption = input.next();

			if(selectedOption.equals("1")){
				hunting = false;
				food = food + foodGathered;
				foodGathered = 0;
				updateMenuCount = 0;
				actionHours = 0;
				actionsMenu();
			}

			else{
				System.out.println("Incorrect option. Try again\n");
				hunting = true;
			}
		}
	}	

	public static void saveGame(){

		inSave = true;
		Scanner writeNewFile = new Scanner(System.in);

		int[] saveData = {seconds, hours, day, month, year, population};
		float[] saveData2 = {energy, food, kingdomSize, wood, gold};

		while(inSave){

			System.out.println(" \n-- Save Game --\n ");
			System.out.println("'1': Save\n");
			System.out.println("'2': Back\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("1")){
				System.out.println("\nEnter save file name: ");
				fileName = writeNewFile.next() + ".txt";
				try{
					System.out.println("Saving......");
					FileWriter writer = new FileWriter(fileName);
					writer.append(playerKingdomName);
					writer.append(" ");
					for(int i = 0; i < saveData.length; i++){
						dataString = Integer.toString(saveData[i]);
						writer.append(dataString);
						writer.append(" ");
					}
					for(int i = 0; i < saveData2.length; i++){
						dataString = Float.toString(saveData2[i]);
						writer.append(dataString);
						writer.append(" ");
					}

					System.out.println("Saving successfully completed!");
					writer.flush();
					writer.close();
				}catch(IOException e){
					error = e.getMessage();
				}	
			}
			else if(selectedOption.equals("2")){
				inSave = false;
				gameMenu();
			}

			else{
				System.out.println("Incorrect option. Try again\n");
				inSave = true;
			}
		}
	}

	public static void woodGatherAction(){

		gatheringWood = true;
		Random random = new Random();
		int randomNum = 0;

		while(gatheringWood){
			System.out.println(" \n-- Wood Gathering --\n");
			System.out.println("Hour: " + hours + " Day: " + day + " Month: " + month +  " Year: " + year);
			System.out.println("Player: Energy: " + energy);
			System.out.println("Hours gathering wood: " + timeGathering + " Wood Gathered: " + woodGathered + "\n");
			System.out.println("'1': Stop Gathering Wood\n\n");

			if(actionHours < 5){
				randomNum = random.nextInt((100 - 0) + 1) + 0;
				System.out.println(randomNum);
				if(randomNum <= 35){
					woodGathered = woodGathered + 2;
				}

			}
			else if(actionHours == 5){
				randomNum = random.nextInt((10 - 0) + 1) + 0;
				woodGathered = woodGathered + randomNum;
			}
			else if(timeGathering > 5){
				randomNum = random.nextInt((100 - 0) + 1) + 0;
				woodGathered = (randomNum / 4) + woodGathered;
			}

			selectedOption = input.next();
			if(selectedOption.equals("1")){
				gatheringWood = false;
				actionHours = 0;
				timeGathering = 0;
				updateMenuCount = 0;
				wood = wood + woodGathered;
				actionsMenu();
			}
		}

	}

}