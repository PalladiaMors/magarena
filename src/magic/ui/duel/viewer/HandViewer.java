package magic.ui.duel.viewer;

import magic.ui.GameController;
import magic.ui.theme.Theme;

public class HandViewer extends CardListViewer {
    private static final long serialVersionUID = 1L;

    public HandViewer(final GameController controller) {
        super(
            controller,
            controller.getViewerInfo().getPlayerInfo(false).hand,
            "Hand : " + controller.getViewerInfo().getPlayerInfo(false).name,
            Theme.ICON_SMALL_HAND,
            /* showCost */ false
        );

        update();
    }
}
