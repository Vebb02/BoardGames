package no.vebb.boardgames.krig;

import no.vebb.boardgames.cards.*;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class KrigMain {
	private KrigPlayer player1;
	private Card card1;
	private KrigPlayer player2;
	private Card card2;
	private List<Card> cardsOnTable;

	public void play() {
		reset();
		while (playersHasCards()) {
			doTurn();
			pause();
		}

		giveCards(getPlayerWithCards());
	}

	private void reset() {
		player1 = new KrigPlayer("Player 1");
		player2 = new KrigPlayer("Player 2");
		cardsOnTable = new ArrayList<>();
		Deck deck = new Deck();
		while (deck.cardsLeft() > 0) {
			player1.addCard(deck.draw());
			player2.addCard(deck.draw());
		}
	}

	private boolean doTurn() {
		if (!draw()) {
			if (player1.hasCards()) {
				giveCards(player1);
			} else {
				giveCards(player2);
			}
			return false;
		}
		if (tie()) {
			krig();
			return true;
		}
		KrigPlayer turnWinner = getWinningPlayer();
		if (turnWinner == null) {
			return false;
		}
		giveCards(turnWinner);
		return true;
	}

	private void printStatus() {
		System.out.println(player1 + " used " + card1);
		System.out.println(player2 + " used " + card2);
		System.out.println(player1.numberOfCards());
		System.out.println(player2.numberOfCards());
	}

	private void pause() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean draw() {
		if (!playersHasCards())
		return false;
		card1 = player1.useCard();
		cardsOnTable.add(card1);
		card2 = player2.useCard();
		cardsOnTable.add(card2);
		printStatus();
		return true;
	}

	private void krig() {
		System.out.println("Krig on:");
		printStatus();
		for (int i = 0; i < 3; i++) {
			Card card1 = player1.useCard();
			if (card1 != null) {
				this.card1 = card1;
				cardsOnTable.add(card1);
			}
			Card card2 = player2.useCard();
			if (card2 != null) {
				this.card2 = card2;
				cardsOnTable.add(card2);
			}
		}
	}

	private KrigPlayer getPlayerWithCards() {
		return player1.hasCards() ? player1 : player2;
	}

	private KrigPlayer getWinningPlayer() {
		int comp = card1.compareTo(card2);
		if (comp == 1) {
			return player1;
		}
		if (comp == -1) {
			return player2;
		}
		return null;
	}

	private boolean playersHasCards() {
		return player1.hasCards() && player2.hasCards();
	}

	private boolean tie() {
		return card1.compareTo(card2) == 0;
	}

	private void giveCards(KrigPlayer player) {
		for (Card card : cardsOnTable) {
			player.addCard(card);
			System.out.println(player + " got " + card);
		}
		cardsOnTable.clear();
	}

}
