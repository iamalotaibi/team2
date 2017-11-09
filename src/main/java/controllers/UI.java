package controllers;

import models.Game;

public class UI {

    public Game game;
    public Boolean card_selected;
    public Integer card_selected_rc[];
    public Integer top_card_modes[];
    public Integer top_card_rows[];

    public UI() {    }

    public void buildUI() {
        System.out.println("Building UI");
        this.game = new Game();
        game.buildGame();
        System.out.println("Finished building game");
        this.game.dealFour();

        this.card_selected = false;
        this.card_selected_rc = new Integer[2];
        this.top_card_modes = new Integer[4];
        this.top_card_rows = new Integer[4];

        for (int i = 0; i < 4; ++i) {
            this.top_card_modes[i] = 0;
            this.top_card_rows[i] = 0;
        }

        updateTopCardModes();
    }

    public void doOnDeal() {
        this.game.dealFour();
        clearCardSelection();
        updateTopCardModes();
    }

    public void doOnCardClicked(int column, int row) {
        processCardSelection(column, row);
        updateTopCardModes();
        this.game.getScore();
    }

    // This is triggered whenever user selects a card.
    public void processCardSelection(int col, int row) {
        // Validate col
        if (col < 0 || col > 3) {
            this.card_selected = false;
            System.out.println("Selected column invalid!");
            return;
        }
        // Obtain top empty row at a column
        int top_empty_row = this.game.cols.get(col).size();
        // Validate row
        if (row < top_empty_row - 1 || row > top_empty_row) {
            this.card_selected = false;
            System.out.println("Selected row invalid!");
            return;
        }
        // Validate selected card (just in case)
        if (this.card_selected && (this.card_selected_rc[1] != this.game.cols.get(this.card_selected_rc[0]).size() - 1 || !this.game.isCardMovable(this.card_selected_rc[0]))) {
            this.card_selected = false;
            System.out.println("Original selection determined invalid and cleared!");
        }

        // Performing selection
        // If a card is already selected,
        //   - the selected card could be moved to a new location
        //   - a different selection could be made
        // If there is no selection yet,
        //  - selection could be made
        // When a selection is made,
        //  - if the location contains a card
        //      - if the card can be removed, it will be removed
        //      - if the card can be moved, it will be selected
        //  - if the location is empty/invalid
        //      - current selection is cleared

        // Move card if selection is valid
        System.out.println("Card selected: " + String.valueOf(this.card_selected));
        if (this.card_selected && col != this.card_selected_rc[0] && this.game.isColumnEmpty(col) && row == 0) {
            this.game.move(this.card_selected_rc[0], col);
            this.card_selected = false;
            System.out.println("Selected card moved!");
            return;
        }
        // Otherwise perform a new selection if valid
        else if (row == top_empty_row - 1) {
            if (this.game.isCardRemovable(col)) {
                this.game.remove(col);
                this.card_selected = false;
                System.out.println("Card at location removed!");
                return;
            }
            else if (this.game.isCardMovable(col)) {
                this.card_selected_rc[0] = col;
                this.card_selected_rc[1] = row;
                this.card_selected = true;
                System.out.println("Card at location selected!");
                return;
            }
        }
        // If all fails, clear selection
        this.card_selected = false;
        System.out.println("Selection cleared/invalid!");
        return;
    }

    // Call this every time dealing, moving, or removing.
    public void clearCardSelection() {
        this.card_selected = false;
    }

    // Call this every time dealing, moving, or removing.
    public void updateTopCardModes() {
        // Modes: 0 - default; 1 - movable; 2 - removable; 3 - selected; 4 - destination
        if (this.card_selected) {
            this.top_card_modes[this.card_selected_rc[0]] = 3;
            for (int i = 0; i < 4; ++i) {
                this.top_card_rows[i] = this.game.cols.get(i).size() - 1;
                if (i != this.card_selected_rc[0]) {
                    if (this.top_card_rows[i] == -1) {
                        this.top_card_modes[i] = 4;
                        this.top_card_rows[i] = 0;
                    }
                    else if (this.game.isCardRemovable(i))
                        this.top_card_modes[i] = 2;
                    else if (this.game.isCardMovable(i))
                        this.top_card_modes[i] = 1;
                    else
                        this.top_card_modes[i] = 0;
                }
            }
        }
        else {
            for (int i = 0; i < 4; ++i) {
                this.top_card_rows[i] = this.game.cols.get(i).size() - 1;
                if (this.game.isCardRemovable(i))
                    this.top_card_modes[i] = 2;
                else if (this.game.isCardMovable(i))
                    this.top_card_modes[i] = 1;
                else
                    this.top_card_modes[i] = 0;
            }
        }
    }
}
