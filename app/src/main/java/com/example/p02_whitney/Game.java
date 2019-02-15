package com.example.p02_whitney;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.*;

public class Game extends AppCompatActivity
{
    //instance variables
    HashMap<String, Integer> map = null;
    HashMap<String, Integer> previousMap = null;
    GridView grid = null;
    int score = 0;
    TextView tv = null;

    //constructors
    public Game()
    {
        map = new HashMap<>();
    }
    public Game(HashMap m)
    {
        map = m;
    }

    //methods
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tv=(TextView) findViewById(R.id.Score);
        grid=(GridView)findViewById(R.id.gamebackground);
        Random random1=new Random();
        int r1 = random1.nextInt(16-1+1)+1;
        Random random2=new Random();
        int r2 = random2.nextInt(16-1+1)+1;
        if(r1==r2 && r2!=16)
        {
            r2++;
        }
        else
        {
            if(r1 == r2)
            {
                r2--;
            }
        }
        Log.i("TAG",r1+"    "+r2);
        // Initializing a new String Array
        String[] nums = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
        Random r3 = new Random();
        int[] choice={2,4};
        int ind1 = r3.nextInt(choice.length);
        int ind2 = r3.nextInt(choice.length);
        for(int i=0 ;i<nums.length;i++)
        {
            if(Integer.parseInt(nums[i])==r1)
            {
                map.put(nums[i],choice[ind1]);
            }
            if(Integer.parseInt(nums[i])==r2)
            {
                map.put(nums[i],choice[ind2]);
            }
            if(Integer.parseInt(nums[i])!=r2 && Integer.parseInt(nums[i])!=r1)
            {
                map.put(nums[i],0);
            }

        }
        // Populate a List from Array elements
        final List<String> plantsList = new ArrayList<String>(Arrays.asList(nums));
        // Data bind GridView with ArrayAdapter (String Array elements)
        gridChanged(plantsList);
        grid.setOnTouchListener(new SwipeController(Game.this)
        {
            public void onSwipeTop()
            {
                previousMap = new HashMap<>(map);
                Log.i("TAG","TOP");
                Log.i("TAG",map.toString());
                Log.i("TAG",checkTop(1) +" "+ checkTop(2) +" "+ checkTop(3) +" "+ checkTop(4));
                int k = 0;
                while(!(checkTop(1) && checkTop(2) && checkTop(3) && checkTop(4)))
                {
                    k = 1;
                    for(int i = 16; i > 4; i--)
                    {
                        int j = i;
                        if (j > 4 && map.get(j + "") == map.get((j - 4) + ""))
                        {
                            map.put((j - 4) + "", map.get(j + "") + map.get((j - 4) + ""));
                            map.put(j + "", 0);
                            String score = tv.getText().toString();
                            int scorenumber = Integer.parseInt(score);
                            scorenumber += map.get((j-4)+"");
                            tv.setText(scorenumber+"");
                        }
                        else if (j > 4 && map.get((j - 4) + "") == 0)
                        {
                            map.put((j - 4) + "", map.get(j + ""));
                            map.put(j + "", 0);
                        }
                    }
                }
                Log.i("TAG",map.toString());
                gridChanged(plantsList);
                generateRandomNumber();
                checkSwipe();
            }
            public void onSwipeRight()
            {
                previousMap = new HashMap<>(map);
                Log.i("TAG","RIGHT");
                Log.i("TAG",map.toString());
                Log.i("TAG",checkRight(4) +" "+ checkRight(8) +" "+ checkRight(12) +" "+ checkRight(16));
                int k = 0;
                int l = 0;
                while(l==0 || !(checkRight(4) && checkRight(8) && checkRight(12) && checkRight(16)))
                {
                    l = 1;
                    for (int i = 1; i < 17; i++)
                    {
                        int j = i;
                        if (k == 0 && map.get(j + "") == map.get((j + 1) + ""))
                        {
                            map.put((j + 1) + "", map.get(j + "") + map.get((j + 1) + ""));
                            map.put(j + "", 0);
                            String score=tv.getText().toString();
                            int scorenumber=Integer.parseInt(score);
                            Log.i("TAG",scorenumber+"HERE");
                            scorenumber+=map.get((j+1)+"");
                            Log.i("TAG",scorenumber+"HERE");
                            tv.setText(scorenumber+"");
                        }
                        else if (k == 0 && map.get((j + 1) + "") == 0)
                        {
                            map.put((j + 1) + "", map.get(j + ""));
                            map.put(j + "", 0);
                        }
                        if ((j / 4) != (j + 1) / 4)
                        {
                            k = 1;
                        }
                        else
                        {
                            k = 0;
                        }
                    }
                }
                Log.i("TAG",map.toString());
                gridChanged(plantsList);
                generateRandomNumber();
                checkSwipe();
            }
            public void onSwipeLeft()
            {
                previousMap = new HashMap<>(map);
                Log.i("TAG","LEFT");
                Log.i("TAG",map.toString());
                Log.i("TAG",checkLeft(1) +" "+ checkLeft(5) +" "+ checkLeft(9) +" "+ checkLeft(13));
                int k = 0;
                int l = 0;
                while(l == 0 || !(checkLeft(1) && checkLeft(5) && checkLeft(9) && checkLeft(13)))
                {
                    l = 1;
                    for (int i = 16; i > 1; i--)
                    {
                        int j = i;
                        if (j == 13 || j == 9 || j == 5)
                        {
                            k = 1;
                        }
                        else
                        {
                            k = 0;
                        }
                        if (k == 0 && map.get(j + "") == map.get((j - 1) + "")) {
                            map.put((j - 1) + "", map.get(j + "") + map.get((j - 1) + ""));
                            map.put(j + "", 0);
                            String score=tv.getText().toString();
                            int scorenumber=Integer.parseInt(score);
                            scorenumber+=map.get((j-1)+"");
                            tv.setText(scorenumber+"");
                        }
                        else if (k == 0 && map.get((j - 1) + "") == 0)
                        {
                            map.put((j - 1) + "", map.get(j + ""));
                            map.put(j + "", 0);
                        }

                    }
                }
                Log.i("TAG",map.toString());
                gridChanged(plantsList);
                generateRandomNumber();
                checkSwipe();
            }
            public void onSwipeBottom()
            {
                previousMap=new HashMap<>(map);
                Log.i("TAG", "BOTTOM");
                Log.i("TAG",map.toString());
                Log.i("TAG",checkBottom(13) +" "+ checkBottom(14) +" "+ checkBottom(15) +" "+ checkBottom(16));
                int k = 0;
                while (k == 0 || !(checkBottom(13) && checkBottom(14) && checkBottom(15) && checkBottom(16)))
                {
                    k = 1;
                    for (int i = 1; i < 13; i++)
                    {
                        int j = i;
                        if (j < 13 && map.get(j + "") == map.get((j + 4) + ""))
                        {
                            map.put((j + 4) + "", map.get(j + "") + map.get((j + 4) + ""));
                            map.put(j + "", 0);
                            String score=tv.getText().toString();
                            int scorenumber=Integer.parseInt(score);
                            scorenumber+=map.get((j+4)+"");
                            tv.setText(scorenumber+"");
                        }
                        else if (j < 13 && map.get((j + 4) + "") == 0)
                        {
                            map.put((j + 4) + "", map.get(j + ""));
                            map.put(j + "", 0);
                        }
                    }
                }
                Log.i("TAG", map.toString());
                gridChanged(plantsList);
                generateRandomNumber();
                checkSwipe();
            }
        });

    }
    public boolean checkTop(int loc)
    {
        if(map.get(loc+"")==0 && map.get((loc+4)+"")==0 && map.get(""+(loc+8))==0 && map.get(""+(loc+12))==0)
        {
            return true;
        }
        else if(map.get((loc+4)+"")==0 && map.get(""+(loc+8))==0 && map.get(""+(loc+12))==0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc+8))==0 && map.get(""+(loc+12))==0 && map.get(""+(loc+4))!=0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc+12))==0 && map.get(""+(loc+4))!=0 && map.get(""+loc)!=0 && map.get(""+(loc+8))!=0)
        {
            return true;
        }
        else if(map.get(loc+"")!=0 && map.get((loc+4)+"")!=0 && map.get(""+(loc+8))!=0 && map.get(""+(loc+12))!=0)
        {
            return true;
        }
        return false;
    }
    public boolean checkBottom(int loc)
    {
        if(map.get(loc+"")==0 && map.get((loc-4)+"")==0 && map.get(""+(loc-8))==0 && map.get(""+(loc-12))==0)
        {
            return true;
        }
        else if(map.get((loc-4)+"")==0 && map.get(""+(loc-8))==0 && map.get(""+(loc-12))==0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc-8))==0 && map.get(""+(loc-12))==0 && map.get(""+(loc-4))!=0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc-12))==0 && map.get(""+(loc-4))!=0 && map.get(""+loc)!=0 && map.get(""+(loc-8))!=0)
        {
            return true;
        }
        else if(map.get(loc+"")!=0 && map.get((loc-4)+"")!=0 && map.get(""+(loc-8))!=0 && map.get(""+(loc-12))!=0)
        {
            return true;
        }
        return false;
    }
    public boolean checkRight(int loc)
    {
        if(map.get(loc+"")==0 && map.get((loc-1)+"")==0 && map.get(""+(loc-2))==0 && map.get(""+(loc-3))==0)
        {
            return true;
        }
        else if(map.get((loc-1)+"")==0 && map.get(""+(loc-2))==0 && map.get(""+(loc-3))==0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc-2))==0 && map.get(""+(loc-3))==0 && map.get(""+(loc-1))!=0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc-3))==0 && map.get(""+(loc-1))!=0 && map.get(""+loc)!=0 && map.get(""+(loc-2))!=0)
        {
            return true;
        }
        else if(map.get(loc+"")!=0 && map.get((loc-1)+"")!=0 && map.get(""+(loc-2))!=0 && map.get(""+(loc-3))!=0)
        {
            return true;
        }
        return false;
    }
    public boolean checkLeft(int loc)
    {
        if(map.get(loc+"")==0 && map.get((loc+1)+"")==0 && map.get(""+(loc+2))==0 && map.get(""+(loc+3))==0)
        {
            return true;
        }
        else if(map.get((loc+1)+"")==0 && map.get(""+(loc+2))==0 && map.get(""+(loc+3))==0 && map.get(""+loc)!=0)
        {
            return true;
        }
        else if(map.get(""+(loc+2))==0 && map.get(""+(loc+3))==0 && map.get(""+(loc+1))!=0 && map.get(""+(loc))!=0)
        {
            return true;
        }
        else if(map.get(""+(loc+3))==0 && map.get(""+(loc+1))!=0 && map.get(""+(loc))!=0 && map.get(""+(loc+2))!=0)
        {
            return true;
        }
        else if(map.get(loc+"")!=0 && map.get((loc+1)+"")!=0 && map.get(""+(loc+2))!=0 && map.get(""+(loc+3))!=0)
        {
            return true;
        }
        return false;
    }
    public void checkSwipe()
    {
        ConstraintLayout cl=(ConstraintLayout) findViewById(R.id.constraintlayout);
        LinearLayout ll= new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.setGravity(Gravity.CENTER);
        TextView result= new TextView(this);
        for(String key : map.keySet())
        {
            if(map.get(key)==2048)
            {
                result.setText("CONGRATULATIONS! YOU WIN!");
                result.setGravity(Gravity.CENTER);
                ll.addView(result);
                cl.addView(ll);
                Intent i= new Intent(Game.this,Results.class);
                i.putExtra("Result",0);
                startActivity(i);
                return;
            }
        }
        Log.e("MAP",map.toString());
        Log.e("PREVMAP",previousMap.toString());
        for(String key : map.keySet())
        {
            if(map.get(key)!=previousMap.get(key))
            {

                return;
            }
        }

        result.setText("GAME OVER! YOU LOSE!");
        result.setGravity(Gravity.CENTER);
        ll.addView(result);
        cl.addView(ll);
        Intent i= new Intent(Game.this,Results.class);
        i.putExtra("Result",1);
        startActivity(i);

    }
    public void generateRandomNumber()
    {
        Random rand = new Random();
        int i = rand.nextInt(16)+1;
        Log.i("TAG1",map.toString());
        int flag = 0;
        for(String key: map.keySet())
        {
            if(map.get(key)!=0)
            {
                flag++;
            }
        }
        if(flag != 16)
        {
            while(map.get(i+"")!=0)
            {
                i = rand.nextInt(16)+1;
            }
            int[] choice = {2,4};
            int ind1 = rand.nextInt(choice.length);
            Log.i("TAG1",choice[ind1]+"    "+i);
            map.put(i+"",choice[ind1]);
            String[] nums = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
            Log.i("TAG1",map.toString());
            List<String> plantsList = Arrays.asList(nums);
            gridChanged(plantsList);
        }

    }
    public void gridChanged(List plantsList)
    {
        final List<String> l = plantsList;
        grid.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, l){
            public View getView(int position, View convertView, ViewGroup parent)
            {
                // Return the GridView current item as a View
                View view = super.getView(position,convertView,parent);
                // Convert the view as a TextView widget
                TextView tv = (TextView) view;
                // Set the layout parameters for TextView widget
                RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
                );
                tv.setLayoutParams(lp);
                tv.setWidth(100);
                tv.setHeight(210);
                // Display TextView text in center position
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(20);
                // Set the TextView text (GridView item text)
                if(map.get(l.get(position))!=0)
                {
                    Log.i("TAG",position+"");
                    Log.i("TAG",l.get(position));
                    tv.setText(map.get(l.get(position))+"");
                }
                else
                {
                    tv.setText("");
                }

                tv.setId(position);
                // Set the TextView background color
                tv.setBackgroundColor(Color.parseColor("#b2dfdb"));
                // Return the TextView widget as GridView item
                return tv;
            }
        });
    }
}
