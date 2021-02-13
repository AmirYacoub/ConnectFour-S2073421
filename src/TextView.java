
/**
 * This file is to be completed by you.
 *
 * @author S2073421
 */
public final class TextView
{
	public TextView()
	{
	
	}
	
	public final void displayNewGameMessage()
	{
		System.out.println("  ---- NEW GAME STARTED ----");
	}
	public final void turn(char player) {
		if (player == 'R')
			System.out.println("Red player's turn");
		else
			System.out.println("Blue player's turn");
	}


	public final String askForPlay()
	{
		System.out.println("Who would you like to play? (PvP or Bot)");
		return InputUtil.readStringFromUser();
	}

	public final String askForDiff()
	{
		System.out.println("Choose Difficulty. (Easy or Medium or Hard)");
		return InputUtil.readStringFromUser();
	}

	public final int askForConnectX()
	{
		System.out.print("Select a number of connections for a win: ");
		return InputUtil.readIntFromUser();
	}

	public final char askForRematch()
	{
		System.out.println("Would you like to play again? (Y/N)");
		return InputUtil.readCharFromUser();
	}

	public final int askForCols()
	{
		System.out.print("Select a number of columns: ");
		return InputUtil.readIntFromUser();
	}

	public final int askForRows()
	{
		System.out.print("Select a number of rows: ");
		return InputUtil.readIntFromUser();
	}
	
	public final int askForMove()
	{
		System.out.println("Enter 0 to forfeit the game.");
		System.out.print("Select a free column: ");
		return InputUtil.readIntFromUser();
	}
	
	public final void displayBoard(Model model)
	{
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		char[][] board = model.getBoard();
		// Get your board representation.
		
		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(4 * nrCols+1);
		
		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();
		
		// You can add to the String using its append method.
		sb.append("  ");
		int c = 1;
		while (c < nrCols+1)
		{
			sb.append(c);
			sb.append("   ");
			c++;
		}
		sb.append("\n");


		sb.append(rowDivider);
		sb.append("\n");

		// Then print out the assembled String.

		for (char[] a: board)
		{
			for (char i: a){
				sb.append( "| ");
				sb.append(i);
				sb.append(" ");
			}
			sb.append("|");
			sb.append("\n");
			sb.append(rowDivider);
			sb.append("\n");
		}

		System.out.println(sb);

	}
}
