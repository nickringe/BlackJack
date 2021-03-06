import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome to Nick's Java BlackJack table!");
		System.out.println("Bets are $25 -- BlackJack pays 3:2");
		System.out.println("Please enter your name: ");
		Scanner scnr = new Scanner(System.in);
		String userName = scnr.nextLine();
		int userBet = 25;
		boolean userCont = true;
		try {
			Player player1 = new Player(userName, userBet);
			Dealer dealer = new Dealer();
			while (userCont == true) {
				if (player1.getBank() < 25) {
					System.out.println("You don't have enough money for that!");
					userCont = false;
					break;
				} else {
					System.out.println("Ok, " + userName + ". You started this round with $" + player1.getBank()
							+ " in your bank.");
					player1.setCardCount(0);
					dealer.setCardCount(0);

					ArrayList<Card> firstDeck = new ArrayList<>();
					player1.playerBet();
					Thread.sleep(1500);
					System.out.println("Now you have $" + player1.getBank() + " since you've bet $" + userBet);

					Methods.fillDeck(firstDeck);
					Methods.deal(firstDeck, player1, dealer);
					Methods.play(firstDeck, player1, dealer);
					if (player1.getBust() == false) {
						Methods.dealerPlay(firstDeck, player1, dealer);
					} else {
						System.out.println("You lost your bet of $" + player1.getBet());
						System.out.println("You have $" + player1.getBank() + " in your bank.");
					}

					if (Methods.playAgain(player1, dealer) == true) {
						userCont = true;
					} else {
						userCont = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Invalid Input. Please try again. Error: Main.tryCatch...");
			userCont = true;
			scnr.next();
		}

		scnr.close();
		System.out.println("Thanks for playing!");
	}

}
