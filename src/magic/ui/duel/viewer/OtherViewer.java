package magic.ui.duel.viewer;

import magic.model.MagicCardList;
import magic.ui.GameController;
import magic.ui.theme.Theme;

public class OtherViewer extends CardListViewer {
    private static final long serialVersionUID = 1L;

    public OtherViewer(final MagicCardList cards, final GameController controller) {
        super(
            controller,
            cards,
            "Other : " + controller.getViewerInfo().getPlayerInfo(false).name,
            Theme.ICON_SMALL_HAND,
            /* showCost */ false
        );

        update();
    }
}
