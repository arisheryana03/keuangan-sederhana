package com.aries.keuanganku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.aries.keuanganku.Database.DBHelper;
import com.aries.keuanganku.Database.MyAdapter;

public class LihatPengeluaranActivity extends AppCompatActivity {
    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;
    TextView sumPemasukkan, avgPemasukkan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pengeluaran);

        setTitle("Pemasukkanku");

        sumPemasukkan = findViewById(R.id.textViewSUMPemasukkan);
        avgPemasukkan = findViewById(R.id.textViewAVGPemasukkan);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor avg = helper.getAVGPemasukkan();
        Cursor sum = helper.getSUMPemasukkan();

        String result = "";
        String SumResult = "";

        //get column value
        if (avg.moveToNext()) {
            result = String.valueOf((int) avg.getDouble(avg.getColumnIndex("myAVG")));
        }

        if (sum.moveToNext()) {
            SumResult = String.valueOf((int) sum.getDouble(sum.getColumnIndex("mySUM")));
        }

        avgPemasukkan.setText("Rp. " + result);
        sumPemasukkan.setText("Rp. " + SumResult);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPemasukkan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, helper.getAllPemasukkanView());
        recyclerView.setAdapter(adapter);
    }
}
