package com.game.gui.listeners;

import com.game.GameProcess;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EnterKeyListener implements KeyListener {

    private GameProcess game;

    public EnterKeyListener(GameProcess game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            game.askQuestion();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}