package com.example.exercyzefrontend.ui.workout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.exercyzefrontend.R;

public class WorkoutEntryDialog extends AppCompatDialogFragment {
    private EditText workoutItemEntryET;
    private EditText setEntryET;
    private EditText repEntryET;
    private WorkoutEntryDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_workout, null);

        builder.setView(view)
                .setTitle("Add Workout Item")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String weightStr = weightEntryET.getText().toString();
                        String workoutItem = workoutItemEntryET.getText().toString();
                        int setVal = Integer.parseInt(setEntryET.getText().toString());
                        int repVal = Integer.parseInt(repEntryET.getText().toString());
                        listener.applyValue(workoutItem, setVal, repVal);
                    }
                });
        workoutItemEntryET = view.findViewById(R.id.itemEntryET);
        setEntryET = view.findViewById(R.id.setEntryET);
        repEntryET = view.findViewById(R.id.repEntryET);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (WorkoutEntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement WorkoutEntryDialog Listener");
        }
    }

    public interface WorkoutEntryDialogListener {
        void applyValue(String workoutItemEntryStr, int setEntry, int repEntry);
    }
}
