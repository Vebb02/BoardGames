package no.vebb.boardgames.hearts;

import java.util.List;
import java.util.ArrayList;

import no.vebb.boardgames.cards.*;

public class HeartsPlayer {
	private List<Card> hand;
	private List<Card> collected;

	private int numberOfPlayers;
	private int points;

	public HeartsPlayer(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		reset();
	}

	private void reset() {
		hand = new ArrayList<>();
		collected = new ArrayList<>();
	}

	public void deal(Card card) {
		hand.add(card);
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public int getPoints() {
		return points;
	}

	public void collect(List<Card> toCollect) {
		collected.addAll(toCollect);
	}

	public List<Card> getCollected() {
		return new ArrayList<>(collected);
	}

	public Card playCard(List<Card> playedCards) {
		// Temporary implementation
		return hand.remove(0);
	}
}
