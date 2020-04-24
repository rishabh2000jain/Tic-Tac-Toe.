package com.example.rishabhjain.ttc;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int gameState []={2,2,2,2,2,2,2,2,2};
    int winningPosition [][]={{0,1,2} ,{3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6}};
    boolean winner=true;

    public void fade(View view)
    {

        ImageView counter = (ImageView) view;
        int TappedCount = Integer.parseInt(counter.getTag().toString());
        //p1 o p2 x
        if(gameState[TappedCount] == 2&&winner)
        {
            gameState[TappedCount]=activePlayer;
        counter.setTranslationY(-1000f);
        if(activePlayer==0)
        {
            counter.setImageResource(R.drawable.o);
            activePlayer=1;
        }
        else
        {
            counter.setImageResource(R.drawable.x);
            activePlayer=0;
        }
       counter.animate().translationYBy(1000f).setDuration(250);
        }
        String win="X";

        for(int WinningPosition []: winningPosition)
        {
            if(gameState[WinningPosition[0]]==gameState[WinningPosition[1]]
                    &&gameState[WinningPosition[1]]==gameState[WinningPosition[2]]&&
                        gameState[WinningPosition[0]]!=2&&gameState[WinningPosition[1]]!=2&&gameState[WinningPosition[2]]!=2)
            {

                if(gameState[WinningPosition[0]]==0)
                {
                    win="O";
                }


                RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.RLayout);
                relativeLayout.animate().alpha(1);
                relativeLayout.setVisibility(view.VISIBLE);
                TextView textView=(TextView)findViewById(R.id.winnerMSG);
                textView.setText(win+" is winner");
                winner=false;
            }
           else
            {
                boolean gameIsOver=true;
                for(int gamestate:gameState)
                {
                    if(gamestate==2)
                    {
                        gameIsOver=false;
                    }
                }
                if(gameIsOver)
                {
                    RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.RLayout);
                    relativeLayout.setVisibility(view.VISIBLE);
                    TextView textView=(TextView)findViewById(R.id.winnerMSG);
                    textView.setText("Game Is Over");

                }



            }
        }
    }
    public void rePlay(View view)
    {
        activePlayer=0;
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.RLayout);
        relativeLayout.setVisibility(view.INVISIBLE);
        winner=true;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.ks);
        for(int i=0;i<9;i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView) findViewById(R.id.StartingWindow);
        imageView.animate().translationX(-500);
       imageView.animate().translationX(1000f).setDuration(2500);
    }
}
