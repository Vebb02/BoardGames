package no.vebb.boardgames.krig;

import no.vebb.boardgames.cards.*;

import java.util.LinkedList;
import java.util.Queue;

public class KrigPlayer {
	private Queue<Card> cards = new LinkedList<>();
	private String name;

	public KrigPlayer(String name) {
		this.name = name;
	}

	public Card useCard() {
		return cards.poll();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public boolean hasCards() {
		return !cards.isEmpty();
	}

	public String toString() {
		return name;
	}

	public int numberOfCards() {
		return cards.size();
	}
}
