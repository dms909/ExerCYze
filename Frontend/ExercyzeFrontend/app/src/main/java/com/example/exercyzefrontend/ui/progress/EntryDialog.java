package com.example.exercyzefrontend.ui.progress;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.exercyzefrontend.R;

public class EntryDialog extends AppCompatDialogFragment {
    private EditText weightEntryET;
    private EntryDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add Graph Entry")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Entry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String weightStr = weightEntryET.getText().toString();
                        double weightVal = Double.parseDouble(weightEntryET.getText().toString());
                        listener.applyValue(weightVal);
                    }
                });
        weightEntryET = view.findViewById(R.id.weightEntryET);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement EntryDialog Listener");
        }
    }

    public interface EntryDialogListener {
        void applyValue(double weightEntryStr);
        //add weight entry here
    }
}
