package com.example.owner.birdmemory;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Owner on 04/02/2018.
 */

public class MemoryBoard {

    MemoryImage[] imageArray = {
            new MemoryImage("blackbird", R.drawable.blackbirdfemale),
            new MemoryImage("blackbird", R.drawable.blackbirdmale),
            new MemoryImage("bullfinch", R.drawable.bullfinchfemale),
            new MemoryImage("bullfinch", R.drawable.bullfinchmale),
            new MemoryImage("housesparrow", R.drawable.housesparrowfemale),
            new MemoryImage("housesparrow", R.drawable.housesparrowmale),
            new MemoryImage("mallard", R.drawable.mallardfemale),
            new MemoryImage("mallard", R.drawable.mallardmale),
            new MemoryImage("peacock", R.drawable.peacockfemale),
            new MemoryImage("peacock", R.drawable.peacockmale),
            new MemoryImage("pheasant", R.drawable.pheasantfemale),
            new MemoryImage("pheasant", R.drawable.pheasantmale),
            new MemoryImage("robin", R.drawable.robinfemale),
            new MemoryImage("robin", R.drawable.robinmale),
            new MemoryImage("tuftedduck", R.drawable.tuftedduckfemale),
            new MemoryImage("tuftedduck", R.drawable.tuftedduckmale)
    };

    Random random = new Random();

    public void startGame() {
        //visa baksidan av korten
        //gar att losa med loop
        shuffleImages();
        shuffleImages();
        shuffleImages();

        for (int i=0; i<imageArray.length; i++) {
            //sets position in array for memoryImage to be used as tag
            imageArray[i].setPosition(i+1);
        }
    }

    private void shuffleImages() {
        for (int i=0; i<imageArray.length; i++) {
            int r = random.nextInt(16);
            MemoryImage img = imageArray[r];
            imageArray[r] = imageArray[i];
            imageArray[i] = img;
        }
    }

    // ta emot id, skicka tillbaka den bilden i int format
    public MemoryImage findImage(int id) {
        //Hamta plats [id] fran picture array
        MemoryImage img = imageArray[id];
        return img;
    }

    public ArrayList<MemoryImage> findNonPairedClickedMemoryImage() {

        ArrayList<MemoryImage> result = new ArrayList<MemoryImage>();

        for (int i=0; i<imageArray.length; i++) {
            MemoryImage img = imageArray[i];
            if (img.isClicked() == true && img.isPaired() == false) {
                result.add(img);
            }
        }
        return result;
    }

    public boolean isGameWon() {
        int clickedImages = 0;

        for (int i=0; i<imageArray.length; i++) {
            if (imageArray[i].isClicked()) {
                clickedImages++;
            }
        }

        if (clickedImages == 16) {
            return true;
        } else {
            return false;
        }
    }
}