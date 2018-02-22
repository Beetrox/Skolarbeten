package com.example.owner.birdmemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Memory4x4 extends AppCompatActivity {

    MemoryBoard board = new MemoryBoard();
    int openCards = 0;
    int score = 0;

    MemoryImageComparer comparer = new MemoryImageComparer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory4x4);

        board.startGame();
    }

    public void memoryCardPressed(View view) throws InterruptedException {

        if (openCards <2) {
            ImageButton imgButton = (ImageButton) view;
            int id = Integer.parseInt(imgButton.getTag().toString());
            MemoryImage image = board.findImage(id - 1);
            boolean clicked = image.isClicked();

            if (!clicked) {
                int imgResource = image.getBirdImage();
                imgButton.setImageResource(imgResource);
                image.setClicked(true);
                openCards++;

                if (openCards > 1) {

                    ArrayList<MemoryImage> clickedMemoryImages = board.findNonPairedClickedMemoryImage();

                    final MemoryImage img1 = clickedMemoryImages.get(0);
                    final MemoryImage img2 = clickedMemoryImages.get(1);

                    boolean isPair = comparer.compare(img1, img2);

                    if (isPair) {
                        img1.setPaired(true);
                        img2.setPaired(true);

                        String species = img1.getBirdType();

                        TextView tv = (TextView)findViewById(R.id.speciesTextView);
                        if (species.equals("blackbird")) {
                            tv.setText(getString(R.string.blackBird));
                        } else if (species.equals("bullfinch")) {
                            tv.setText(getString(R.string.bullFinch));
                        } else if (species.equals("housesparrow")) {
                            tv.setText(getString(R.string.houseSparrow));
                        } else if (species.equals("mallard")) {
                            tv.setText(getString(R.string.mallard));
                        }  else if (species.equals("peacock")) {
                            tv.setText(getString(R.string.peacock));
                        }  else if (species.equals("pheasant")) {
                            tv.setText(getString(R.string.pheasant));
                        }  else if (species.equals("robin")) {
                            tv.setText(getString(R.string.robin));
                        }  else if (species.equals("tuftedduck")) {
                            tv.setText(getString(R.string.tuftedDuck));
                        }

                        openCards = 0;
                    } else {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                turnBackNonPairedCards(img1, img2);
                                openCards = 0;
                            }
                        }, 1500);
                    }
                    score++;
                    updateScore();

                    if (board.isGameWon()) {
                        displayVictoryMessage();
                    }
                }
            }
        }
    }

    public void turnBackNonPairedCards(MemoryImage img1, MemoryImage img2) {

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);

        ImageButton view1 = layout.findViewWithTag(Integer.toString(img1.getPosition()));
        view1.setImageResource(R.drawable.cardback);
        img1.setClicked(false);

        ImageButton view2 = layout.findViewWithTag(Integer.toString(img2.getPosition()));
        view2.setImageResource(R.drawable.cardback);
        img2.setClicked(false);

    }
    public void updateScore() {
        String scr = String.valueOf(score);

        TextView tv = (TextView)findViewById(R.id.scoreTextView);
        tv.setText(getString(R.string.scoreTextView) +" " +scr);
    }

    public void displayVictoryMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory4x4.this);

        builder.setTitle(getString(R.string.winTitle));
        builder.setMessage(getString(R.string.winMessage) + " " + score + ".");
        builder.setNeutralButton(getString(R.string.winButton), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent mainActivity = new Intent(Memory4x4.this, MainActivity.class);
                    startActivity(mainActivity);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
