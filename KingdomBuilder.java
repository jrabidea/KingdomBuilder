import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class KingdomBuilder{

	public static Boolean newGame = false;
	public static Boolean loadGame = false;
	public static String selectedOption; 
	public static Scanner input = new Scanner(System.in);
	public static String error;

	public static void main(String[] args){

		String playerKingdomName;

		System.out.println("*******************\n" + "KINGDOM BUILDER\n" + "*******************\n");

		mainMenu();
		newGameStart();
		loadGameStart();
	}

	private static void mainMenu(){

		Boolean retryMenu = true;

		System.out.println("\n-- Main Menu --");
		System.out.println("** New_Game **\n" + "** Load_Game **\n");

		while(retryMenu){
			retryMenu = false;
			System.out.println("Select an option: ");
			selectedOption = input.next();
			try{
				if(selectedOption.equals("New_Game")){
					newGame = true;
					retryMenu = false;
				}
				else if(selectedOption.equals("Load_Game")){
					loadGame = true;
					retryMenu = false;
					newGame = false;
				}
				else{
					System.out.println("\nIncorrect menu option selected. Please try again.");
					System.out.println("-- Main Menu --");
					System.out.println("** New_Game **\n" + "** Load_Game **\n");
					retryMenu = true;
				}
			}catch(NullPointerException e){
				String message = e.getMessage();
				System.out.println("There was a problem with the menu selection");
			}
		}

	}

	private static void newGameStart(){

		while(newGame){
			Boolean retryNewGame = true;
			System.out.println("\n-- New Game --");
			System.out.println("** New_Kingdom **\n" + "** Main_Menu **\n");
			while(retryNewGame){
				retryNewGame = false;
				System.out.println("Select an option: ");
				selectedOption = input.next();
				try{
					if(selectedOption.equals("New_Kingdom")){
						System.out.println("\nCOMING SOON!");
						retryNewGame = false;
						newGame = false;
					}
					else if(selectedOption.equals("Main_Menu")){
						newGame = false;
						retryNewGame = false;
						mainMenu();
					}
					else{
						System.out.println("\nIncorrect menu option selected. Please try again.");
						System.out.println("\n-- New Game --");
						System.out.println("** New_Kingdom **\n" + "** Main_Menu **\n");
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
		while(loadGame){
			System.out.println("\nCOMING SOON!");
			loadGame = false;
		}
	}
}