[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Destroy all creatures. They can't be regenerated. " +
                "If seven or more cards are in your graveyard, instead destroy all creatures, " + 
                "then put two 1/1 white Spirit creature tokens with flying onto the battlefield. " + 
                "Creatures destroyed this way can't be regenerated."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final Collection<MagicPermanent> targets = game.filterPermanents(MagicTargetFilterFactory.CREATURE);
            for (final MagicPermanent target : targets) {
                game.doAction(MagicChangeStateAction.Set(target,MagicPermanentState.CannotBeRegenerated));
            }
            game.doAction(new MagicDestroyAction(targets));
            if (MagicCondition.THRESHOLD_CONDITION.accept(event.getSource())) {
                game.doAction(new MagicPlayTokensAction(
                    event.getPlayer(),
                    TokenCardDefinitions.get("1/1 white Spirit creature token with flying"),
                    2
                ));
            }
        }
    }
]
