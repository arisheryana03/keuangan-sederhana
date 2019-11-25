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

public class LihatPemasukanActivity extends AppCompatActivity {

    DBHelper helper;
    MyAdapter adapter;
    SQLiteDatabase db;
    TextView avgPengeluaran, sumPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pemasukan);

        setTitle("Pengeluaranku");

        avgPengeluaran = findViewById(R.id.textViewAVGPemasukkan);
        sumPengeluaran = findViewById(R.id.textViewSUMPengeluaran);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor avg = helper.getAVGPengeluaran();
        Cursor sum = helper.getSUMPengeluaran();

        String result = "";
        String sumResult = "";

        //get column value
        if (avg.moveToNext()) {
            result = String.valueOf((int) avg.getDouble(avg.getColumnIndex("myAVG")));
        }

        if (sum.moveToNext()) {
            sumResult = String.valueOf((int) sum.getDouble(sum.getColumnIndex("mySUM")));
        }

        avgPengeluaran.setText("Rp. " + result);
        sumPengeluaran.setText("Rp. " + sumResult);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewPengeluaran);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, helper.getAllPengeluaranView());
        recyclerView.setAdapter(adapter);
    }
}
