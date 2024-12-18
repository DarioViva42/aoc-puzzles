package tk.vivas.adventofcode.year2024.day14;

import tk.vivas.adventofcode.AocUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.stream.IntStream;

import static java.awt.BorderLayout.*;

class Day14Visualization {

    public static final int SCALE = 5;

    private final int sizeX;
    private final int sizeY;
    private final JFrame frame;
    private final HeadquarterComponent graphic;
    private final JLabel timeIndicator;

    private int steps = 1;
    private int delay = 280;
    private boolean playing = false;

    Day14Visualization(BathroomOperation bathroomOperation) {
        sizeX = bathroomOperation.getSizeX();
        sizeY = bathroomOperation.getSizeY();

        int width = sizeX * SCALE;
        int height = sizeY * SCALE;

        frame = createFrame();

        timeIndicator = new JLabel();

        JPanel header = createHeader(timeIndicator);
        frame.add(header, NORTH);

        graphic = new HeadquarterComponent(bathroomOperation, sizeX, sizeY);
        graphic.setPreferredSize(new Dimension(width, height));

        JPanel body = new JPanel();
        body.add(graphic);
        frame.add(body, SOUTH);
    }

    public static void main(String[] args) {
        String input = AocUtils.readPuzzleInput();
        new Day14Visualization(new BathroomOperation(input, 101, 103)).start();
    }

    private void start() {
        frame.setVisible(true);

        Thread.ofPlatform().start(this::animationLoop);
    }

    private JFrame createFrame() {
        final JFrame frame;
        frame = new JFrame("Bathroom Operation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX * SCALE + 26, sizeY * SCALE + 154);
        frame.setResizable(false);
        return frame;
    }

    private JPanel createHeader(JLabel indicator) {
        JPanel header = new JPanel(new BorderLayout());
        header.add(createTimeBar(indicator), NORTH);
        header.add(createButtonBar(), SOUTH);
        return header;
    }

    private JPanel createTimeBar(JLabel indicator) {
        indicator.setText("0");
        indicator.setFont(new Font("Default", Font.BOLD, 25));

        JPanel container = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Time:");
        label.setFont(new Font("Default", Font.PLAIN, 25));
        container.add(label, WEST);
        container.add(indicator, EAST);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        return container;
    }

    private JPanel createButtonBar() {
        JPanel container = new JPanel(new BorderLayout());

        JPanel incrementButtons = createIncrementButtons();
        container.add(incrementButtons, EAST);

        JSlider speedSlider = createSpeedSlider();
        container.add(speedSlider, CENTER);

        JPanel playButton = createPlayComponent(incrementButtons, speedSlider);
        container.add(playButton, WEST);

        return container;
    }

    private JSlider createSpeedSlider() {
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL);
        speedSlider.addChangeListener(event -> delay = 530 - 5 * speedSlider.getValue());
        speedSlider.setVisible(false);
        return speedSlider;
    }

    private JPanel createIncrementButtons() {
        JPanel incrementButtons = new JPanel(new GridLayout(2, 3));
        IntStream.of(1, sizeX, sizeY,
                        -1, -sizeX, -sizeY)
                .mapToObj(this::createIncrementButton)
                .forEachOrdered(incrementButtons::add);
        return incrementButtons;
    }

    private JButton createIncrementButton(int change) {
        String name = String.valueOf(change);
        name = change > 0 ? "+" + name : name;
        JButton button = new JButton(name);
        button.addActionListener(event -> updateFrame(change));
        return button;
    }

    private JPanel createPlayComponent(JPanel incrementButtons, JSlider speedSlider) {
        JPanel container = new JPanel(new BorderLayout());

        JButton play = createPlayButton(incrementButtons, speedSlider);
        container.add(play, WEST);

        JComboBox<Integer> stepsSelection = createStepsSelection();
        container.add(stepsSelection, EAST);

        return container;
    }

    private JButton createPlayButton(JPanel incrementButtons, JSlider speedSlider) {
        JButton play = new JButton("⏵");
        play.setFont(new Font("Default", Font.PLAIN, 40));
        play.addActionListener(event -> {
            play.setText(playing ? "⏵" : "⏸");
            playing = !playing;
            incrementButtons.setVisible(!playing);
            speedSlider.setVisible(playing);
        });
        return play;
    }

    private JComboBox<Integer> createStepsSelection() {
        JComboBox<Integer> stepsSelection = new JComboBox<>();
        stepsSelection.addItem(1);
        stepsSelection.addItem(sizeX);
        stepsSelection.addItem(sizeY);
        stepsSelection.setSelectedItem(1);

        stepsSelection.addActionListener(event -> steps = (int) stepsSelection.getSelectedItem());
        return stepsSelection;
    }

    private void updateFrame(int change) {
        int time = graphic.incrementTime(change);
        timeIndicator.setText(String.valueOf(time));
    }

    private void animationLoop() {
        while (true) {
            if (playing) updateFrame(steps);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
