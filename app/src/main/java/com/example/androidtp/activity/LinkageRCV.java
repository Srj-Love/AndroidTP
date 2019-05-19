package com.example.androidtp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.androidtp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kunminx.linkage.LinkageRecyclerView;
import com.kunminx.linkage.bean.DefaultGroupedItem;

import java.util.List;

public class LinkageRCV extends AppCompatActivity {

    private static float DIALOG_HEIGHT = 400;
    private AlertDialog mDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_linkage);

        LinkageRecyclerView rcv = findViewById(R.id.linkage);

        Gson gson = new Gson();
        List<DefaultGroupedItem> items = gson.fromJson(getString(R.string.operators_json),
                new TypeToken<List<DefaultGroupedItem>>() {
                }.getType());


        rcv.init(items);

        rcv.setDefaultOnItemBindListener(
                (primaryClickView, title, position) -> {
                    Snackbar.make(primaryClickView, title, Snackbar.LENGTH_SHORT).show();
                },
                (primaryHolder, title, position) -> {
                    //TODO
                },
                (secondaryHolder, item, position) -> {
                    secondaryHolder.getView(R.id.level_2_item).setOnClickListener(v -> {
                        Snackbar.make(v, item.info.getTitle(), Snackbar.LENGTH_SHORT).show();
                    });
                },
                (headerHolder, item, position) -> {

                }
        );


      /*  View view2 = View.inflate(LinkageRCV.this, R.layout.layout_linkage_view, null);
        LinkageRecyclerView linkage = view2.findViewById(R.id.linkage);
        initLinkageDatas(linkage);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LinkageRCV.this);
        mDialog = builder.setView(linkage).show();
        linkage.setLayoutHeight(DIALOG_HEIGHT);*/
    }


    private void initLinkageDatas(LinkageRecyclerView linkage) {
        Gson gson = new Gson();
        List<DefaultGroupedItem> items = gson.fromJson(getString(R.string.operators_json),
                new TypeToken<List<DefaultGroupedItem>>() {
                }.getType());

        linkage.init(items);
        linkage.setScrollSmoothly(false);
        linkage.setDefaultOnItemBindListener(
                (primaryClickView, title, position) -> {
                    Snackbar.make(primaryClickView, title, Snackbar.LENGTH_SHORT).show();
                },
                (primaryHolder, title, position) -> {
                    //TODO
                },
                (secondaryHolder, item, position) -> {
                    secondaryHolder.getView(R.id.level_2_item).setOnClickListener(v -> {
                        if (mDialog != null && mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    });
                },
                (headerHolder, item, position) -> {
                    //TODO
                }
        );
    }
}
