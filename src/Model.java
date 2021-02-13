/**
 * This file is to be completed by you.
 *
 * @author S2073421
 */
public final class Model
{
	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 7 rows and 6 columns.

	private static char[][] board;

	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int connectX;
	
	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model()
	{
		//Since we want the players to set their own dimensions, we cant have any preset values
	}


	public void setNrCols(int nrCols)
	{
		this.nrCols = nrCols;
	}

	public void setNrRows(int nrRows)
	{
		this.nrRows = nrRows;
	}

	public void setBoard(int rows, int cols)
	{
		board = new char[rows][cols];
	}

	public void setConnectX(int connectX)
	{
		this.connectX = connectX;
	}

	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================

	public void clearBoard()
	{
			for (int i=0; i<nrRows; i++)
			{
				for (int j = 0; j<nrCols; j++)
				{
					//Player 1 is R, Player 2 is B, space is the blank spot.
					board[i][j]= ' ';
				}
			}
	}

	public boolean isColumnFull(int move)
	{
		if (move < nrCols)
		{
			for (int i = nrRows - 1; i > -1; i--)
			{
				if (board[i][move] == ' ')
					return false;
			}
		}
		return true;
	}
	public boolean isBoardFull()
	{
		for (char[] chars : board)
		{
			for (char aChar : chars)
			{
				if (aChar == ' ')
				{
					return false;
				}
			}
		}
	return true;
	}


	public boolean isMoveValid(int move)
	{
		if (move > nrCols || move < 0 || isColumnFull(move))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	//Changing x, going up the rows looking for the first row with 'E'
	//values of x is then reset every time makeMove is called upon.

	public int x;
	public void makeMove(int move,char player)
	{
		x = nrRows -1;
		while (board[x][move] != ' ')
		{
			x = x-1;
		}
			board[x][move] = player;
	}




	public boolean checkHorizontal(int move, char player)
	{
		int countLeft =0;
		int countRight = 0;

		int tempMove = move;
		while (tempMove!= 0 && board[x][tempMove-1] == player)
		{
			countLeft++;
			tempMove--;
		}

		tempMove = move;
		while (tempMove!= nrCols-1 && board[x][tempMove+1] == player)
		{
			countRight++;
			tempMove++;
		}
		return countRight + countLeft + 1 >= connectX;
	}

	public boolean checkVertical(int move, char player)
	{
		int countUp = 0;
		int countDown = 0;

		int tempX = x;
		while (tempX!= nrRows-1 && board[tempX+1][move] == player)
		{
			countUp++;
			tempX++;
		}

		tempX = x;
		while (tempX!= 0 && board[tempX-1][move] == player)
		{
			countDown++;
			tempX--;
		}
		return countDown + countUp + 1 >= connectX;
	}

	public boolean checkDiagonal(int move, char player)
	{
		int countDownRight = 0;
		int countDownLeft = 0;
		int countUpRight = 0;
		int countUpLeft = 0;

		int tempX = x;
		int tempMove = move;

		while (tempX!= nrRows-1 && tempMove!= nrCols-1 && board[tempX+1][tempMove+1] == player)
		{
			countUpRight++;
			tempX++;
			tempMove++;
		}

		tempMove = move;
		tempX = x;
		while (tempX!= nrRows-1 && tempMove!= 0 && board[tempX+1][tempMove-1] == player)
		{
			countUpLeft++;
			tempX++;
			tempMove--;
		}

		tempMove = move;
		tempX = x;
		while (tempX!= 0 && tempMove!= 0 && board[tempX-1][tempMove-1] == player)
		{
			countDownLeft++;
			tempX--;
			tempMove--;
		}

		tempMove = move;
		tempX = x;
		while (tempX!= 0 && tempMove!= nrCols-1 && board[tempX-1][tempMove+1] == player)
		{
			countDownRight++;
			tempX--;
			tempMove++;
		}
		return (countDownLeft+countUpRight+1 >= connectX || countDownRight+countUpLeft+1 >= connectX);
	}


	public boolean checkWin(int move, char player)
	{
		return checkHorizontal(move, player) || checkVertical(move, player) || checkDiagonal(move, player);
	}

	public int checkForWinningMove(char player)
	{
		for (int i = 0; i < nrCols; i++)
		{
			if(checkWin(i,player))
				return i+1;
		}
		return -1;
	}

	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================

	public int getNrRows()
	{
		return nrRows;
	}

	public int getNrCols()
	{
		return nrCols;
	}

	public char[][] getBoard()
	{
		return board;
	}
}
