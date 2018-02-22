package com.example.owner.birdmemory;

/**
 * Created by Owner on 04/02/2018.
 */

public class MemoryImage {

    private String birdType;
    private int birdImage;
    private int position;
    private boolean clicked;
    private boolean paired;

    public MemoryImage(String birdType, int birdImage) {
        this.birdType = birdType;
        this.birdImage = birdImage;
        this.clicked = false;
        this.paired = false;
    }

    public String getBirdType() {
        return birdType;
    }

    public int getBirdImage() {
        return birdImage;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isPaired() {
        return paired;
    }

    public void setPaired(boolean paired) {
        this.paired = paired;
    }

}
