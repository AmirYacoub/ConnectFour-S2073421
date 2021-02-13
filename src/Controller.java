/**
 * This file is to be completed by you.
 *
 * @author S2073421
 */
public final class Controller
{
    private final Model model;
    private final TextView view;

    private boolean winStatus = false;
    private int playerMove;

    public Controller(Model model, TextView view)
    {
        this.model = model;
        this.view = view;
    }

    public void humanPlay(char player)
    {
        view.displayBoard(model);
        view.turn(player);
        playerMove = view.askForMove();
        if (playerMove != 0)
        {
            while (!model.isMoveValid(playerMove - 1))
            {
                System.out.print("Invalid move, please pick a new column: ");
                playerMove = InputUtil.readIntFromUser();
            }
            model.makeMove(playerMove - 1, player);
            if (model.checkWin(playerMove - 1, player))
            {
                view.displayBoard(model);
                winStatus = true;
                if (player == 'R')
                    System.out.println("Red Player won!");
                else
                    System.out.println("Blue Player won!");
            }
        } else
        {
            winStatus = true;
            if (player == 'R')
            {
                System.out.print("Blue Player won!");
            } else
            {
                System.out.println("Red Player won!");
            }
        }
    }

    public void easyBot()
    {
        playerMove = (int) (1 + Math.random() * model.getNrCols());

        while (!model.isMoveValid(playerMove - 1))
        {
            playerMove = (int) (1 + Math.random() * model.getNrCols());
        }
        model.makeMove(playerMove - 1, 'B');
        System.out.println("Blue player placed his piece in Column #" + playerMove);
        if (model.checkWin(playerMove - 1, 'B'))
        {
            view.displayBoard(model);
            System.out.println("Blue Player won!");
            winStatus = true;
        }
    }

    public void mediumBot()
    {
        playerMove = model.checkForWinningMove('B');
        if (playerMove != -1)
        {
            model.makeMove(playerMove - 1, 'B');
            System.out.println("Blue player placed his piece in Column #" + playerMove);
            winStatus = true;
        }
        else
        {
            easyBot();
        }
    }

    public void hardBot()
    {
        playerMove = model.checkForWinningMove('B');
        if (playerMove != -1)
        {
            model.makeMove(playerMove - 1, 'B');
            System.out.println("Blue player placed his piece in column #" + playerMove);
            if (model.checkWin(playerMove - 1, 'B'))
            {
                view.displayBoard(model);
                System.out.println("Blue Player won!");
                winStatus=true;
            }
        }
        else
        {
            playerMove = model.checkForWinningMove('R');
            if (playerMove != -1)
            {
                model.makeMove(playerMove - 1, 'B');
                System.out.println("Blue player placed his piece in column #" + playerMove);
            }
            else
            {
                easyBot();
            }
        }
    }
    public void endGame()
    {
        if (model.isBoardFull())
        {
            System.out.print("Board is full! ");
        }
        System.out.println("Game Over.");
        char ans = view.askForRematch();
        if (ans == 'Y' || ans == 'y')
        {
            model.clearBoard();
            winStatus=false;
            startSession();
        } else
            System.out.println("Thank you for playing");

    }

    public void startSession()
    {
        int rows = view.askForRows();
        int cols = view.askForCols();
        int connectX = view.askForConnectX();
        while (connectX > rows && connectX > cols)
        {
            System.out.println("Board is too small, Please pick new dimensions and new number of connections");
            rows = view.askForRows();
            cols = view.askForCols();
            connectX = view.askForConnectX();
        }
        String against = view.askForPlay();
        while (!(against.equals("Bot") || against.equals("PvP") || against.equals("bot") || against.equals("pvp")))
        {
            System.out.println("Invalid answer, please pick again. ");
            against = InputUtil.readStringFromUser();
        }

        model.setNrRows(rows);
        model.setNrCols(cols);
        model.setBoard(rows, cols);
        model.setConnectX(connectX);



        // Against Bot

        if (against.equals("Bot") || against.equals("bot"))
        {
            String diff = view.askForDiff();
            while (!(diff.equals("easy") || diff.equals("Easy") || diff.equals("Medium") || diff.equals("medium")
                    || diff.equals("Hard") || diff.equals("hard")))
            {
                System.out.println("Invalid answer, please pick again. ");
                diff = InputUtil.readStringFromUser();
            }

            //Easy Difficulty Game

            if (diff.equals("easy") || diff.equals("Easy"))
            {
                model.clearBoard();
                view.displayNewGameMessage();
                while (!model.isBoardFull() && !winStatus)
                {
                    humanPlay('R');
                    //without this, if player R gets a win, player B still gets to play again before the loop ends.
                    if (winStatus)
                        break;
                    easyBot();
                }
                endGame();
            }

            //Medium Difficulty Game
            else if (diff.equals("medium") || diff.equals("Medium"))
            {
                view.displayNewGameMessage();
                model.clearBoard();
                while (!model.isBoardFull() && !winStatus)
                {
                    humanPlay('R');
                    if (winStatus)
                        break;
                    mediumBot();
                }
                endGame();
            }

            //Hard Difficulty Game
            else
            {
                model.clearBoard();
                view.displayNewGameMessage();
                while (!model.isBoardFull()&&!winStatus)
                {
                    humanPlay('R');
                    if (winStatus)
                        break;
                    hardBot();
                }
                endGame();
            }
        }

        // Against Human
        else
        {
            model.clearBoard();
            view.displayNewGameMessage();
            while (!model.isBoardFull() && !winStatus)
            {
                humanPlay('R');
                if (winStatus)
                    break;
                humanPlay('B');
            }
            endGame();
        }
    }
}


