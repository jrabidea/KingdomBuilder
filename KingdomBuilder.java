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

	public static void main(String[] args){

		System.out.println("*******************\n" + "KINGDOM BUILDER\n" + "*******************\n");

		mainMenu();
		
	}

	private static void mainMenu(){

		Boolean retryMenu = true;
		System.out.println("\n*****************************\n" + "         " +  "Main Menu"
			+ "\n*****************************\n");
		System.out.println("       ** New_Game **\n\n" + "       ** Load_Game **\n\n" + 
			"       ** Quit_Game ** ");

		while(retryMenu){
			retryMenu = false;
			System.out.println("\n\nEnter an option: ");
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
			System.out.println("      ** New_Kingdom **\n\n" + "       ** Main_Menu **\n");
			while(retryNewGame){
				retryNewGame = false;
				System.out.println("\n\nEnter an option: ");
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
						System.out.println("      ** New_Kingdom **\n\n" + "       ** Main_Menu **\n");
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

		System.out.println("\nGenerating plots....");
		System.out.println("\n******************************\n" + "          " + playerKingdomName + 
			"\n******************************\n");
		System.out.println("         ** Plots **\n");
		System.out.println("       ** Resources **\n");
		System.out.println("         ** Menu **\n");

		Boolean retryOption = true;

		while(retryOption){
			System.out.println("\n\nEnter an option: ");
			selectedOption = input.next();
			retryOption = false;
			if(selectedOption.equals("Plots")){
				System.out.println("COMING SOON!");
				retryOption = false;
			}
			else if(selectedOption.equals("Resources")){
				System.out.println("\nCOMING SOON!");
				retryOption = false;
			}
			else if(selectedOption.equals("Menu")){
				retryOption = false;
				gameMenu();
			}
			else{
				System.out.println("Incorrect option. Try again.");
				System.out.println("\n******************************\n" + "          " + playerKingdomName + 
					"\n******************************\n");
				System.out.println("         ** Plots **\n");
				System.out.println("       ** Resources **\n");
				System.out.println("         ** Menu **\n");
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
		System.out.println("        ** Quit **\n");

		while(inGameMenu){
			System.out.println("\n\nEnter an option: ");
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
				System.out.println("        ** Quit **\n");
			}

		}


	}


}