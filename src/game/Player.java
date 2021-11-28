package game;

import org.newdawn.slick.*;
import utils.Utils;

import java.util.ArrayList;

public class Player extends Asset{

    private int direction;
    private boolean moving;

    private Animation currAnimation;
    private ArrayList<Animation> idleAnimation;
    private ArrayList<Animation> walkAnimation;

    public Player() throws SlickException {
        x = Utils.SCREEN_WIDTH/2 - Utils.SPRITE_WIDTH/2;
        y = Utils.SCREEN_HEIGHT/2 - Utils.SPRITE_HEIGHT/2;

        direction = Utils.FRONT;
        moving = false;

        idleAnimation = new ArrayList<>();
        walkAnimation = new ArrayList<>();

        //add in idle animations
        idleAnimation.add(new Animation(new SpriteSheet(Utils.IDLE_L,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.IDLE_DURATION));
        idleAnimation.add(new Animation(new SpriteSheet(Utils.IDLE_R,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.IDLE_DURATION));
        idleAnimation.add(new Animation(new SpriteSheet(Utils.IDLE_F,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.IDLE_DURATION));
        idleAnimation.add(new Animation(new SpriteSheet(Utils.IDLE_B,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.IDLE_DURATION));

        //add in walk animations
        walkAnimation.add(new Animation(new SpriteSheet(Utils.WALK_L,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.WALK_DURATION));
        walkAnimation.add(new Animation(new SpriteSheet(Utils.WALK_R,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.WALK_DURATION));
        walkAnimation.add(new Animation(new SpriteSheet(Utils.WALK_F,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.WALK_DURATION));
        walkAnimation.add(new Animation(new SpriteSheet(Utils.WALK_B,Utils.SPRITE_WIDTH, Utils.SPRITE_HEIGHT),Utils.WALK_DURATION));

        currAnimation = idleAnimation.get(direction);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        Input input = gameContainer.getInput();

        moving = false;
        //detect movement
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            x -= Utils.PLAYER_VELOCITY;
            direction = Utils.LEFT;
            moving = true;
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)){
            x += Utils.PLAYER_VELOCITY;
            direction = Utils.RIGHT;
            moving = true;
        }

        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)){
            y -= Utils.PLAYER_VELOCITY;
            direction = Utils.BACK;
            moving = true;
        }

        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
        {
            y += Utils.PLAYER_VELOCITY;
            direction = Utils.FRONT;
            moving = true;
        }

        //keep player inside screen boundaries
        if( x > Utils.SCREEN_WIDTH - Utils.SPRITE_WIDTH)
            x = Utils.SCREEN_WIDTH - Utils.SPRITE_WIDTH;
        if(x < 0)
            x = 0;
        if(y > Utils.SCREEN_HEIGHT - Utils.SPRITE_HEIGHT)
            y = Utils.SCREEN_HEIGHT - Utils.SPRITE_HEIGHT;
        if(y < 0)
            y = 0;


        //determine which animation to use
        if(!moving)
        {
            switch(direction)
            {
                case Utils.LEFT:
                    currAnimation = idleAnimation.get(Utils.LEFT);
                    break;
                case Utils.RIGHT:
                    currAnimation = idleAnimation.get(Utils.RIGHT);
                    break;
                case Utils.BACK:
                    currAnimation = idleAnimation.get(Utils.BACK);
                    break;
                default:
                    currAnimation = idleAnimation.get(Utils.FRONT);
                    break;
            }
        } else {
            switch(direction)
            {
                case Utils.LEFT:
                    currAnimation = walkAnimation.get(Utils.LEFT);
                    break;
                case Utils.RIGHT:
                    currAnimation = walkAnimation.get(Utils.RIGHT);
                    break;
                case Utils.BACK:
                    currAnimation = walkAnimation.get(Utils.BACK);
                    break;
                default:
                    currAnimation = walkAnimation.get(Utils.FRONT);
                    break;
            }
        }
        currAnimation.update(delta);
    }

    @Override
    public void render(Graphics graphics) {
        currAnimation.draw(x,y);
    }
}