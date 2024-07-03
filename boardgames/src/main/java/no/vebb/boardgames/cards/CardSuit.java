package no.vebb.boardgames.cards;

public enum CardSuit {
	SPADES(CardColor.BLACK),
	CLUBS(CardColor.BLACK),
	HEARTS(CardColor.RED),
	DIAMONDS(CardColor.RED);

	public final CardColor color;

	private CardSuit(CardColor color) {
		this.color = color;
	}
}
