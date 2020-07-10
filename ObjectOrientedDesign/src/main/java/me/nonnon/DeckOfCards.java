package me.nonnon;

import java.util.ArrayList;

public class DeckOfCards {
	// Design the data structures for a generic deck of cards. Explain how you
	// would subclass the data structures to implement blackjack.
	
	public enum Suit{
		Club(0), Diamond(1), Heart(2), Spade(3); // Arguments passed to constructor
		private int value;
		private Suit(int v) { value = v; } // Constructor, runs 4 times only
		public int getValue() { return value;}
		public static Suit getSuitFromValue(int value) {
			for (Suit s : Suit.values()) {
				if (s.getValue() == value) {
					return s;
				}
			}
			return null;
		}
	}
	
	public class Deck <T extends Card>{
		private ArrayList<T> cards; // all cards
		private int dealtIndex = 0; // marks first undealt card
		
		public void setDeckOfCards(ArrayList<T> deckOfCards) { }
		
		public void shuffle() { }
		public int remainingCards() {
			return cards.size() - dealtIndex;
		}	
		public T[] dealHand(int number) { return null; }
		public T dealCard() { return null; }
	}
	
	public abstract class Card {
		private boolean available = true;
		
		// number or face on card - number 2 through 10, then 11 to 14
		protected int faceValue;
		protected Suit suit;
		
		public Card(int c, Suit s) {
			faceValue = c;
			suit = s;
		}
		
		public abstract int value(); // method to be fleshed out in inherited class
		public Suit suit() { return suit; }
		
		// Checks if card is available to be given out
		public boolean isAvailable( ) { return available; }
		public void markUnavailable() { available = false; }
		public void markAvailable() { available = true; }		
	}
	
	public class Hand <T extends Card> {
		protected ArrayList<T> cards = new ArrayList<T>();
		
		public int score() {
			int score = 0;
			for (T card : cards) {
				score += card.value();
			}
			return score;
		}
		public void addCard(T card) {
			cards.add(card);
		}
	}
	
	public class BlackJackHand extends Hand<BlackJackCard> {
		/* There are multiple possible scores for ablackjack hand, since aces have
		 * multiple values. Return the highest possible score that's under 21, or the
		 * lowest score that's over. */
		
	}
	
	public class BlackJackCard extends Card {
		public BlackJackCard(int c, Suit s) { super(c, s); }
		public int value() {
			if (isAce()) return 1;
			else if (faceValue >= 11 && faceValue <= 13) return 10;
			else return faceValue;
		}
		
		public int minValue() {
			if (isAce()) return 1;
			else return value();
		}
		
		public int maxValue() {
			if (isAce()) return 11;
			else return value();			
		}
		
		public boolean isAce() {
			return faceValue == 1;
		}
		
		public boolean isFaceCard() {
			return faceValue >= 11 && faceValue <= 13;
		}
	}		

}
