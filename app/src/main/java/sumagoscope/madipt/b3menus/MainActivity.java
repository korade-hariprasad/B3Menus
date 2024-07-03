package sumagoscope.madipt.b3menus;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnAlert, btnContext, btnPopup, btnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        btnAlert = findViewById(R.id.btnAlert);
        btnCustom = findViewById(R.id.btnCustom);
        btnContext = findViewById(R.id.btnContext);
        btnPopup = findViewById(R.id.btnPopup);

        //action bar START
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //action bar END

        //context Menu START
        registerForContextMenu(btnContext);
        //context Menu END

        //popup Menu START
        PopupMenu popup = new PopupMenu(MainActivity.this, btnPopup);
        popup.inflate(R.menu.action_bar_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.action_home)
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Else", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        btnPopup.setOnClickListener(v->{
           popup.show();
        });
        //popup Menu END

        //Alert dialog START
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Exit").setMessage("Are you sure you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        btnAlert.setOnClickListener(v->dialog.show());
        //Alert dialog END

        //Custom dialog START
        Dialog d = new Dialog(MainActivity.this);
        d.setContentView(R.layout.custom_dialog);
        EditText etAddress = d.findViewById(R.id.etAddress);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d.findViewById(R.id.btnSubmit).setOnClickListener(v->{
            Toast.makeText(this, etAddress.getText().toString(), Toast.LENGTH_SHORT).show();
            d.dismiss();
        });
        btnCustom.setOnClickListener(v->{
            d.show();
        });
        //Custom dialog END
    }

    //action bar START
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_home)
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        if(item.getItemId()==android.R.id.home)
            finish();
        return true;
    }
    //action bar END


    //context Menu START
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_home)
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show();
        return true;
    }
    //context Menu END

}