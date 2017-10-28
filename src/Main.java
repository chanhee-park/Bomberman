package com.company;

import processing.core.PApplet;

public class Main {

    public static void main(String[] args) {
        PApplet.main("Program");
    }

    @Override
    public void settings() {
        this.size(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void keyPressed() {

    }

    @Override
    public void keyReleased() {
        if (keyCode == CONTROL) {
        }
    }
}
