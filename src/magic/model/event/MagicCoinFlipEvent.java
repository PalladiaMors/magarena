package magic.model.event;

import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicRandom;
import magic.model.MagicSource;
import magic.model.action.MagicAction;

public class MagicCoinFlipEvent extends MagicEvent {

    public MagicCoinFlipEvent(final MagicSource source, final boolean aheads, final MagicPlayer player,final MagicAction aWinAction, final MagicAction aLoseAction) {
        super(
            source,
            player, 
            EventAction(aWinAction, aLoseAction, aheads),
            "PN flips a coin."
        );
    }

    private static final MagicEventAction EventAction(final MagicAction winAction, final MagicAction loseAction, final boolean heads) {
        return new MagicEventAction() {
            @Override
            public void executeEvent(final MagicGame game, final MagicEvent event) {
                //True = Heads
                final MagicPlayer player = event.getPlayer();
                boolean coinFace = false;
                boolean playerFace = heads;
                final MagicRandom rng = new MagicRandom(event.getStateId());
                    if (rng.nextInt(2)==0){
                        game.logAppendMessage(player, "Coin was Heads.");
                        coinFace = true;
                        //Heads trigger
                    } else {
                        game.logAppendMessage(player, "Coin was Tails.");
                        //Tails trigger
                    }
                if (playerFace==coinFace) {
                    game.logAppendMessage(player, player.getName() + " wins.");
                    if (winAction !=null) {
                       game.doAction(winAction);
                       //Win trigger
                    }
                } else if (loseAction !=null) {
                    game.doAction(loseAction);
                    //Lose trigger
                }
            }
        };
    }
}
