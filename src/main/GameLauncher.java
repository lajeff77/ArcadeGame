package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import states.GameState;
import utils.Utils;

public class GameLauncher extends StateBasedGame {

    public GameLauncher()
    {
        super("Arcade Game");
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new GameLauncher());
            app.setDisplayMode(Utils.SCREEN_WIDTH,Utils.SCREEN_HEIGHT, false);
            app.setTargetFrameRate(60);
            app.start();

        }catch(SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
    }
}

