/**
* The HungryNibbleMonster program is a game
* where a monster acts happy when fed one nibble (hex digit)
* and will keep asking for more nibble until the game ends
* or its hunger level surpasses 40 and the player loses.
*
*
* @author  Nidhi Lakshmanan, CS 2011-03
* @author  Jack Bentley, CS 2011-03
*/

import java.util.Scanner;
import java.applet.*;
import java.awt.Graphics;

public class Nibble extends Applet{
    public static void main(String[] args) {
      //create random hunger levels
      double lowerHunger = Math.random()*10 + 10;
      double upperHunger = Math.random()*10 + 10;
      double numHunger = Math.random()*10 + 10;

      //create immunity boolean variables
      boolean numImmune = false;
      boolean upperImmune = false;
      boolean lowerImmune = false;

      int difficulty;
      int bombCount = 5;

      Scanner bob = new Scanner(System.in);
      char input;

      //provide explanation of game if necessary.
      System.out.println("Need an explanation of the game? (Y/N): ");
      input = bob.nextLine().charAt(0);

      if (input == 'Y' || input == 'y') {
        System.out.println("Here's how the game works:\nYou will have to feed this monster and keep track of his three gauges of hunger - lowercase letters (a-e), uppercase letters (A-E), and numbers (0-8).\nTo lower the hunger in a particular category, simply input a character of the desired type when prompted.\nHowever, each type of the monster's hunger will increase a random amount each turn.\nYou can do an extra special move if you use one more than the maximum of each of the categories - 9 for numbers and F or f for the letters.\nThis special move is a bomb, and it detonates a large amount of the hunger in a category, and it also prevents it from increasing for the next turn.\nThe catch, however, is that you only get 5 bombs a game, and once you run out, you have no more, so you must use your bombs wisely.\nIf any of the categories reach 40 hunger, you lose the game.\nSimply survive for a certain number of rounds, depending on the difficulty, and you win.");
      }

      //specify difficulty
      System.out.println("Which difficulty would you like to play at? Easy(10 turns), Normal(20 turns), or Hard(30 turns)? (1/2/3): ");
      difficulty = bob.nextInt();

      //for loop runs until difficulty is reached
      for (int i = 0; i < difficulty*10; i++) {

          System.out.println("\n\n\nNumber Hunger: " + numHunger + "\nCapital Letter Hunger: " + upperHunger + "\nLowercase Letter Hunger: " + lowerHunger + "\nBombs Left: " + bombCount);

          System.out.print(":E monster hungry!!!\nfeed nibble :o ");
          input = bob.next().charAt(0);

          //monster reacts to regular nibbles
          if (input >= '0' && input <= '8') {
            System.out.println("nom nom nom\nNumber Hunger reduced by 10!");
            numHunger-=10;
          }
          else if (input >= 'A' && input <= 'E') {
            System.out.println("YUM!\nCapital Letter Hunger reduced by 10!");
            upperHunger-=10;
          }
          else if (input >= 'a' && input <= 'e') {
            System.out.println("yum!\nLowercase Letter Hunger reduced by 10!");
            lowerHunger-=10;
          }
          //monster reacts to special nibbles, use of bomb and immunity
          else if (input == '9') {
            if (bombCount > 0) {
              System.out.println("Bomb Detonating!\nNumber Hunger reduced by 20 and next increase prevented!");
              numHunger -= 20;
              numImmune = true;
              bombCount--;
            } else {
              System.out.println("No more Bombs!");
            }
          }
          else if (input == 'F') {
            if (bombCount > 0) {
              System.out.println("Bomb Detonating!\nCapital Letter Hunger reduced by 20 and next increase prevented!");
              upperHunger -= 20;
              upperImmune = true;
              bombCount--;
            } else {
              System.out.println("No more Bombs!");
            }
          }
          else if (input == 'f') {
            if (bombCount > 0) {
              System.out.println("Bomb Detonating!\nLowercase Letter Hunger reduced by 20 and next increase prevented!");
              lowerHunger -= 20;
              lowerImmune = true;
              bombCount--;
            } else {
              System.out.println("No more Bombs!");
            }
          }
          //monster reacts to characters that are not nibbles
          else {
            System.out.println("barffffff! :o=" + input + "\nAll Hunger increased by 5!");
            numHunger+=5; upperHunger+=5; lowerHunger+=5;
          }

          //player loses if hunger surpasses 40
          if (numHunger >= 40 || upperHunger >= 40 || lowerHunger >= 40) {
            System.out.println("You Lose!!!");
            System.exit(0);
          }

          //hunger increases if immunity is false
          if (!numImmune)
            numHunger+=Math.random()*10;
          if (!upperImmune)
            upperHunger+=Math.random()*10;
          if (!lowerImmune)
            lowerHunger+=Math.random()*10;

          //all immunity reset to false
          numImmune = false;
          upperImmune = false;
          lowerImmune = false;
      }

      //congradulates user
      System.out.println("You Won!");
    }
}
