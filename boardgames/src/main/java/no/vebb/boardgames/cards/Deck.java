package no.vebb.boardgames.cards;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private Random rnd = new Random();

	public Deck() {
		reset();
	}

	public void reset() {
		cards = new ArrayList<>();
		for (CardSuit suit : CardSuit.values()) {
			for (CardRank rank : CardRank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public int cardsLeft() {
		return cards.size();
	}

	public Card draw() {
		if (cardsLeft() == 0) {
			return null;
		}
		int i = rnd.nextInt(cardsLeft());
		return cards.remove(i);
	}
}
