package net.codingpark.testrolltext;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView tv = null;

    private String text = "Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text " +
            " Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text " +
            " Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text " +
            " Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text " +
            " Text Text Text Text Text Text Text Text Text Text ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        DrawView view = new DrawView(this);
        setContentView(view);*/
        ScrollTextView view = new ScrollTextView(this);
        setContentView(view);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
