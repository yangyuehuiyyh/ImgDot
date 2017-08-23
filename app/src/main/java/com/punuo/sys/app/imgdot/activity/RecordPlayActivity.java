package com.punuo.sys.app.imgdot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.punuo.sys.app.imgdot.R;
import com.punuo.sys.app.imgdot.adapter.RecordAdapter;
import com.punuo.sys.app.imgdot.bean.Info;



public class RecordPlayActivity extends AppCompatActivity {


    private ListView listView;
    private RecordAdapter recordAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_play);

        listView = (ListView)findViewById(R.id.record_list);
        recordAdapter = new RecordAdapter(this, Info.recordList);
        listView.setAdapter(recordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RecordPlayActivity.this,PlayRecordActivity.class);
                intent.putExtra("record", position);
                startActivity(intent);
            }
        });
    }
}
