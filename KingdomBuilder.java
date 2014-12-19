import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class KingdomBuilder{

	public static Boolean loadGame = false;
	public static Boolean newGame = false;
	public static Boolean inGameMenu = false;
	public static Boolean gameStart;
	public static String selectedOption; 
	public static Scanner input = new Scanner(System.in);
	public static String error;
	public static String playerKingdomName = "";
	public static Boolean myKingdomMenu = false;
	public static int day = 0;
	public static int month = 0;
	public static int year = 0;
	public static double food = 0;
	public static double wood = 0;
	public static double gold = 0;
	public static int turns = 0;
	public static float energy = 0;
	public static int population = 0;
	public static float kingdomSize = 0;


	public static void main(String[] args){

		System.out.println("******************************\n" + "       KINGDOM BUILDER\n" + "******************************\n");

		mainMenu();
		
	}

	private static void mainMenu(){

		Boolean retryMenu = true;
		System.out.println("\n*****************************\n" + "         " +  "Main Menu"
			+ "\n*****************************\n");
		System.out.println("       ** New_Game **\n\n" + "       ** Load_Game **\n\n" + 
			"       ** Quit_Game **\n\n ");

		while(retryMenu){
			retryMenu = false;
			selectedOption = input.next();
			try{
				if(selectedOption.equals("New_Game")){
					retryMenu = false;
					newGameStart();
				}
				else if(selectedOption.equals("Load_Game")){
					loadGameStart();
					newGame = false;
					retryMenu = false;
				}
				else if(selectedOption.equals("Quit_Game")){
					retryMenu = false;
				}
				else{
					System.out.println("\nIncorrect menu option selected. Please try again.");
					System.out.println("\n*****************************\n" + "         " +  "Main Menu"
										+ "\n*****************************\n");
					System.out.println("       ** New_Game **\n\n" + "       ** Load_Game **\n\n" + 
										"       ** Quit_Game ** ");
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
			System.out.println("\n****************************\n" +  "          New Game" + "\n****************************\n");
			System.out.println("      ** New_Kingdom **\n\n" + "       ** Main_Menu **\n\n");
			while(retryNewGame){
				retryNewGame = false;
				selectedOption = input.next();
				try{
					if(selectedOption.equals("New_Kingdom")){
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
					else if(selectedOption.equals("Main_Menu")){
						newGame = false;
						retryNewGame = false;
						mainMenu();
					}
					else{
						System.out.println("\nIncorrect menu option selected. Please try again.");
						System.out.println("\n****************************\n" +  "          New Game" + "\n****************************\n");
						System.out.println("      ** New_Kingdom **\n\n" + "       ** Main_Menu **\n\n");
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
		loadGame = true;
		while(loadGame){
			System.out.println("\nCOMING SOON!");
			loadGame = false;
		}
	}

	private static void playGame(){

		
		System.out.println("\n******************************\n" + "          " + playerKingdomName + 
			"\n******************************\n");
		System.out.println("      ** My_Kingdom **\n");
		System.out.println("         ** Menu **\n\n");

		Boolean retryOption = true;

		while(retryOption){
			selectedOption = input.next();
			retryOption = false;
			if(selectedOption.equals("My_Kingdom")){
				retryOption = false;
				myKingdomMenu();
			}
			else if(selectedOption.equals("Menu")){
				retryOption = false;
				gameMenu();
			}
			else{
				System.out.println("Incorrect option. Try again.");
				System.out.println("\n******************************\n" + "          " + playerKingdomName + 
					"\n******************************\n");
				System.out.println("      ** My_Kingdom **\n");
				System.out.println("         ** Menu **\n\n");
				retryOption = true;
			}
		}
	}

	private static void gameMenu(){

		inGameMenu = true;

		System.out.println("\n******************************\n" + "        " + playerKingdomName + 
		"\n******************************\n");
		System.out.println("        -- Menu --\n ");
		System.out.println("    ** Return_To_Game **\n");
		System.out.println("        ** Save **\n");
		System.out.println("        ** Quit **\n\n");

		while(inGameMenu){
			selectedOption = input.next();
			if(selectedOption.equals("Return_To_Game")){
				playGame();
				inGameMenu = false;
			}
			else if(selectedOption.equals("Save")){
				System.out.println("\nCOMING SOON!");
				inGameMenu = false;
			}
			else if(selectedOption.equals("Quit")){
				System.out.println("\nQuitting the game..");
				inGameMenu = false;
			}
			else{
				System.out.println("\nIncorrect option. Try again.");
				System.out.println("\n******************************\n" + "        " + playerKingdomName + 
			"\n******************************\n");
				System.out.println("        -- Menu --\n ");
				System.out.println("    ** Return_To_Game **\n");
				System.out.println("        ** Save **\n");
				System.out.println("        ** Quit **\n\n");
				inGameMenu = true;
			}

		}

	}

	private static void myKingdomMenu(){

		myKingdomMenu = true;		
		while(myKingdomMenu){
			System.out.println("\n******************************\n" + "         My Kingdom" + "\n******************************\n");
			System.out.println("Day: " + day + " Month: " + month +  " Year: " + year);
			System.out.println("Turns: " + turns + "\n\n");
			System.out.println("       ** Actions **\n");
			System.out.println("      ** Resources **\n");
			System.out.println("        ** Stats **\n");
			System.out.println("        ** Back **\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("Actions")){
				myKingdomMenu = false;
			}
			else if(selectedOption.equals("Resources")){
				myKingdomMenu = false;
			}
			else if(selectedOption.equals("Stats")){
				myKingdomMenu = false;
				statsMenu();
			}
			else if(selectedOption.equals("Back")){
				myKingdomMenu = false;
				playGame();
			}
			else{
				myKingdomMenu = true;
			System.out.println("Incorrect option. Try again\n");
			System.out.println("\n******************************\n" + "         My Kingdom" + "\n******************************\n");
			System.out.println("       ** Actions **\n");
			System.out.println("      ** Resources **\n");
			System.out.println("        ** Stats **\n");
			System.out.println("         ** Back **\n\n");
			}	
		}

	}

	private static void statsMenu(){

		Boolean inStatsMenu = true;

		while(inStatsMenu){
			System.out.println("\n******************************\n" + "          " + playerKingdomName + 
			"\n******************************\n");

			System.out.println("Day: " + day + " Month: " + month +  " Year: " + year);
			System.out.println("Population: " + population + "\n" + "Kingdom Size: " + kingdomSize);
			System.out.println("Gold: " + gold); 
			System.out.println("Food: " + food);
			System.out.println("Wood: " + wood +"\n\n");
			System.out.println("       ** Back **\n\n");
			selectedOption = input.next();
			if(selectedOption.equals("Back")){
				inStatsMenu = false;
				myKingdomMenu();
			}
			else{
				inStatsMenu = true;
			}
		}
	}
}