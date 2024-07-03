package no.vebb.boardgames.krig;

import no.vebb.boardgames.cards.*;

import java.util.List;
import java.util.ArrayList;

public class KrigMain {
	private Player player1;
	private Card card1;
	private Player player2;
	private Card card2;
	private List<Card> cardsOnTable;

	public void play() {
		reset();
		while (move()) {

		}
		System.out.println(player1 + " had " + card1);
		System.out.println(player2 + " had " + card2);
		
		for (Card card : cardsOnTable) {
			System.out.println(card);
		}
		System.out.println(player1.numberOfCards());
		System.out.println(player2.numberOfCards());
	}

	private void reset() {
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		cardsOnTable = new ArrayList<>();
		Deck deck = new Deck();
		while (deck.cardsLeft() > 0) {
			player1.addCard(deck.draw());
			player2.addCard(deck.draw());
		}
	}

	private boolean draw() {
		if (!playerHasCards())
			return false;
		card1 = player1.useCard();
		cardsOnTable.add(card1);
		card2 = player2.useCard();
		cardsOnTable.add(card2);
		System.out.println(player1 + " used " + card1);
		System.out.println(player2 + " used " + card2);
		return true;
	}

	private void drawThree() {
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

	private boolean move() {
		System.out.println(player1.numberOfCards());
		System.out.println(player2.numberOfCards());
		if (!draw()) {
			if (player1.hasCards()) {
				giveCards(player1);
			} else {
				giveCards(player2);
			}
			return false;
		}
		if (tie()) {
			drawThree();
			return true;
		}
		int comp = card1.compareTo(card2);
		if (comp == 1) {
			giveCards(player1);
		} else if (comp == -1) {
			giveCards(player2);
		} else {
			return false;
		}
		return true;
	}

	private boolean playerHasCards() {
		return player1.hasCards() && player2.hasCards();
	}

	private boolean tie() {
		return card1.compareTo(card2) == 0;
	}

	private void giveCards(Player player) {
		for (Card card : cardsOnTable) {
			player.addCard(card);
			System.out.println(player + " got " + card);
		}
		cardsOnTable.clear();
	}

}
