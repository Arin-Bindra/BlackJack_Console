import java.util.*;

/*
 * Arin Bindra
 * 
 */

/**
* 
* The black jack game easy class extends the black jack class.
* 
**/

public class BlackJackGameEasy extends BlackJackGame{
    
    private Card card;
    private Map<Integer, Integer> pulledCards = new HashMap();
    
    public BlackJackGameEasy(CardDeck cardDeck)
    {
        super(cardDeck);
    }
    
    //----------------------------------------------------------------
    /**
    * 
    * The user hit function takes a new card object from the card deck.
    * 
    * The function checks if the pulled card allows the total value of cards to 
    * be 21 or under, and if not the card is discarded and a new card is pulled
    * from the deck until this condition can be met.
    * 
    **/

    @Override
    public void Userhit()
    {
        int value = 0;
        boolean correctCard = false;
        
        while(correctCard != true)
        {
            Card cardPossible = this.getCardDeck().newCard();
            value = cardPossible.getValue();
            
            if(value == 1)
            {
                if(this.getUserCardValue() + 11 <= 21)
                {
                    correctCard = true;
                    this.card = cardPossible;
                    value = 11;
                }
                
                if(this.getUserCardValue() + 1 <= 21)
                {
                    correctCard = true;
                    this.card = cardPossible;
                }
            }
            
            if(value != 1)
            {
                if(this.getUserCardValue() + value <= 21)
                {
                    correctCard = true;
                    this.card = cardPossible;
                }
            }
        }
        
        this.setUserCardDifference(value);
        this.setUserCardvalues(value);
        this.setUserCards(this.card);
    }
    
    //----------------------------------------------------------------
    /**
     *
     * The hit chance function checks from which cards have been pulled to see
     * which cards are left over in the deck, and from there calculates and prints
     * the percentage of pulling a new card which will bring the total value
     * of the users cards to 21 or under.
     * 
     **/
    
    
    @Override
    public void hitChance()
    {
        this.pulledCards = this.getCardDeck().getCardOccurance();
        double cardsPulled = 0.0;
        double cardsUnder = 0.0;
        double percentageD;
        int percentageI;
        
        for(int i = 1; i < 14; i++)
        {
            if((this.getUserCardValue() + i) <= 21)
            {
                cardsUnder = cardsUnder + (32 - this.pulledCards.get(i));
                cardsPulled = cardsPulled + this.pulledCards.get(i);
            }
        }
        
        percentageD = (cardsUnder / cardsPulled)*100;
        percentageI = (int)percentageD;
        
        System.out.println("There is a " + percentageI + "% chance of hitting a card which will bring the total value to 21 or under.");
    }
}
