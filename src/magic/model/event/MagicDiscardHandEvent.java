package magic.model.event;

import magic.model.MagicCard;
import magic.model.MagicCardList;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicSource;
import magic.model.action.MagicDiscardCardAction;

public class MagicDiscardHandEvent extends MagicEvent {

    public MagicDiscardHandEvent(MagicSource source) {
        super(
            source,
            source.getController(),
            EVENT_ACTION,
            "PN discards his or her hand."
        );
    }
    
    private static final MagicEventAction EVENT_ACTION=new MagicEventAction() {
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final MagicCardList hand = new MagicCardList(player.getHand());
            for (final MagicCard card : hand) {
                game.doAction(new MagicDiscardCardAction(player,card));
            }

        }
    };
}
