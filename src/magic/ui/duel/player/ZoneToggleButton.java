package magic.ui.duel.player;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import javax.swing.JToggleButton;
import magic.data.MagicIcon;
import magic.model.MagicPlayerZone;
import magic.ui.GraphicsUtilities;
import magic.ui.IconImages;
import magic.ui.MagicStyle;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.callback.TimelineCallback;


@SuppressWarnings("serial")
public class ZoneToggleButton extends JToggleButton implements TimelineCallback {

    enum ValueStyle {
        NORMAL,
        OUTLINE
    }

    private static final Font ZONE_FONT = new Font("Dialog", Font.BOLD, 14);

    private BufferedImage zoneIconImage = null;
    private final MagicIcon magicIcon;
    private String cardCountString = "0";
    private final ValueStyle valueStyle;
    private final MagicPlayerZone playerZone;
    private Timeline timeline1;
    private int imageOffset = 0;
    private boolean animateOnChange = false;

    public int getImageOffset() {
        return imageOffset;
    }

    public void setImageOffset(int value) {
        this.imageOffset = value * 2;
        repaint();
    }
    
    // CTR
    private ZoneToggleButton(
            final MagicPlayerZone playerZone,
            final MagicIcon icon,
            final int cardCount,
            final ValueStyle valueStyle,
            final boolean isActive,
            final boolean isAnimated) {

        this.playerZone = playerZone;
        this.magicIcon = icon;
        this.valueStyle = valueStyle;
        this.animateOnChange = false;
        setEnabled(isActive);
        setNumberOfCardsInZone(cardCount);
        setMinimumSize(new Dimension(40, 60));        
    }
    // CTR
    ZoneToggleButton(
            final MagicPlayerZone playerZone,
            final MagicIcon icon,
            final int cardCount,
            final boolean isActive,
            final boolean isAnimated) {
        
        this(playerZone, icon, cardCount, ValueStyle.NORMAL, isActive, isAnimated);

    }

    public MagicPlayerZone getPlayerZone() {
        return playerZone;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final Image image = getZoneIconAsImage();
        final int x = getWidth() / 2 - image.getWidth(null) / 2;

        if (animateOnChange) {
            g.drawImage(image, x, 4, x+32, 4+32, 0+imageOffset, 0+imageOffset, 32-imageOffset, 32-imageOffset, null);
        } else {
            g.drawImage(image, x, 4, null);
        }

        drawZoneValueOverlay((Graphics2D)g, cardCountString, x, getHeight(), image);

        if (isSelected()) {
            drawSelectedBorder(g);
        }
    }

    private void drawSelectedBorder(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4.0f));
        g2d.setColor(MagicStyle.HIGHLIGHT_COLOR);
        g2d.drawRect(0, 0, getWidth(), getHeight());

    }

    private BufferedImage getZoneIconAsImage() {
        if (zoneIconImage == null) {
            zoneIconImage = GraphicsUtilities.getCompatibleBufferedImage(32, 32, Transparency.TRANSLUCENT);
            Graphics2D g2d = (Graphics2D) zoneIconImage.getGraphics();
            final Image iconImage = GraphicsUtilities.getConvertedIcon(IconImages.getIcon(magicIcon));
            g2d.drawImage(iconImage, 0, 0, this);
            g2d.dispose();
        }
        return zoneIconImage;
    }

    private void drawZoneValueOverlay(Graphics2D g2d, String text, int x, int y, final Image iconImage) {
        g2d.setFont(ZONE_FONT);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontRenderContext frc = g2d.getFontRenderContext();
        final int textHeight = Math.round(ZONE_FONT.getLineMetrics(text, frc).getHeight());
        final FontMetrics metrics = g2d.getFontMetrics(ZONE_FONT);
        int textWidth = metrics.stringWidth(text);
        int textX = x + ((iconImage.getWidth(null) / 2) - (textWidth / 2));
        int textY = y - 6;
        if (valueStyle == ValueStyle.OUTLINE) {
            GraphicsUtilities.drawStringWithOutline(g2d, text, textX, textY);
        } else {
            g2d.drawString(text, textX, textY);
        }
    }

    final void setNumberOfCardsInZone(final int cardCount) {
        final boolean isModified = !Integer.toString(cardCount).equals(cardCountString);
        cardCountString = Integer.toString(cardCount);
        if (isModified) {
            if (animateOnChange) {
                doAlertAnimation();
            } else {
                repaint();
            }
        }
    }

    public void doAlertAnimation() {
        timeline1 = new Timeline();
        timeline1.addCallback(this);
        timeline1.setDuration(200);
//        timeline1.setEase(new Spline(0.8f));
        timeline1.addPropertyToInterpolate(
                Timeline.property("imageOffset").on(this).from(0).to(4));
        timeline1.playLoop(2, Timeline.RepeatBehavior.REVERSE);
    }

    @Override
    public void onTimelineStateChanged(Timeline.TimelineState oldState, Timeline.TimelineState newState, float durationFraction, float timelinePosition) {
        // do nothing.
    }

    @Override
    public void onTimelinePulse(float durationFraction, float timelinePosition) {
        // do nothing.
    }



}