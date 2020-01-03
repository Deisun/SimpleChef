package com.example.simplechef.ui.recipe_create;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CreateRecipeAlertDialogFragment extends DialogFragment {

    public static CreateRecipeAlertDialogFragment newInstance(ArrayList<String> messageList) {
        CreateRecipeAlertDialogFragment frag = new CreateRecipeAlertDialogFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("messageList", messageList);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        ArrayList<String> messageList = getArguments().getStringArrayList("messageList");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Problem");

        StringBuilder sb = new StringBuilder();

        // iterate through all messages in the list and append to a StringBuilder
        Iterator<String> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            String message = iterator.next();
            sb.append(message);
            if (!iterator.hasNext()) {
                // do nothing
            } else {
                // only append new line if not the end of the list
               sb.append("\n");
            }
        }


        builder.setMessage(sb.toString());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });


        return builder.create();
    }
}
