package magic.test;

import magic.model.MagicDuel;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicPlayerDefinition;
import magic.model.MagicDeckProfile;
import magic.model.phase.MagicMainPhase;

class TestUI_UserActionPanel extends TestGameBuilder {
    @Override
    public MagicGame getGame() {
        final MagicDuel duel=new MagicDuel();
        duel.setDifficulty(6);

        final MagicDeckProfile profile=new MagicDeckProfile("bgruw");
        final MagicPlayerDefinition player1=new MagicPlayerDefinition("Player",false,profile,15);
        final MagicPlayerDefinition player2=new MagicPlayerDefinition("Computer",true,profile,14);
        duel.setPlayers(new MagicPlayerDefinition[]{player1,player2});
        duel.setStartPlayer(0);

        final MagicGame game=duel.nextGame(true);
        game.setPhase(MagicMainPhase.getFirstInstance());
        final MagicPlayer player=game.getPlayer(0);
        final MagicPlayer opponent=game.getPlayer(1);

        MagicPlayer P = player;

        P.setLife(20);
        addToLibrary(P, "Forest", 20);
        createPermanent(game, P, "Thousand Winds", false, 3);
        createPermanent(game, P, "Island", false, 2);
        createPermanent(game, P, "Mountain", false, 3);
        createPermanent(game, P, "Forest", false, 2);
        addToHand(P, "Backslide", 1);
        addToHand(P, "Adder-Staff Boggart", 1);
        addToHand(P, "Joraga Warcaller", 1);
        addToHand(P, "Paperfin Rascal", 1);
        addToHand(P, "Skitter of Lizards", 1);
        addToHand(P, "Sylvan Echoes", 1);

        P = opponent;

        P.setLife(10);
        addToLibrary(P, "Forest", 20);
        addToLibrary(P, "Island", 8);
        addToLibrary(P, "Grizzly Bears", 1);
        addToHand(P, "Ludevic's Test Subject", 2);

        return game;
    }
}
