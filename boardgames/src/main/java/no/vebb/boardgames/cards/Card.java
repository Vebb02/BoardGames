package no.vebb.boardgames.cards;

import java.util.Random;

public class Card implements Comparable<Card> {
	private final CardRank rank;
	private final CardSuit suit;

	public Card(CardRank rank, CardSuit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getValue() {
		return rank.value;
	}

	public CardColor getColor() {
		return suit.color;
	}

	public CardRank getRank() {
		return rank;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public String toString() {
		return rank.toString().toLowerCase() + " of " + suit.toString().toLowerCase();
	}

	public static Card getRandom() {
		Random rnd = new Random();
		CardRank[] ranks = CardRank.values();
		int index = rnd.nextInt(ranks.length);
		CardRank rank = ranks[index];
		CardSuit[] suits = CardSuit.values();
		index = rnd.nextInt(suits.length);
		CardSuit suit = suits[index];
		return new Card(rank, suit);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	@Override
	public int compareTo(Card o) {
		return Integer.compare(getValue(), o.getValue());
	}
}
