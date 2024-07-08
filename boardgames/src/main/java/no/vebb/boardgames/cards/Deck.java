package no.vebb.boardgames.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> cards;
	private Random rnd = new Random();

	public Deck() {
		reset();
	}

	public void reset() {
		cards = new ArrayList<>();
		addCards();
		scrambleCards();
	}

	private void addCards() {
		for (CardSuit suit : CardSuit.values()) {
			for (CardRank rank : CardRank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	private void scrambleCards() {
		for (int i = cardsLeft() - 1; i > 0; i--) {
			int j = rnd.nextInt(i + 1);
			Collections.swap(cards, i, j);
		}
	}

	public int cardsLeft() {
		return cards.size();
	}

	public Card draw() {
		if (cardsLeft() == 0) {
			return null;
		}
		return cards.remove(cardsLeft() - 1);
	}
}
