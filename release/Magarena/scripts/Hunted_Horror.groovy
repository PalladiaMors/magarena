[
    new MagicWhenComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                MagicTargetChoice.TARGET_OPPONENT,
                this,
                "Target opponent\$ puts two 3/3 green Centaur creature tokens with protection from black onto the battlefield."
            )
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                game.doAction(new MagicPlayTokensAction(
                    it,
                    TokenCardDefinitions.get("3/3 green Centaur creature token with protection from black"),
                    2
                )); 
            })
        }
    }
]
