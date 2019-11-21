package com.example.exercyzefrontend.ui.workoutroutine;

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

public class WorkoutNameEntryDialog extends AppCompatDialogFragment {
    private EditText workoutNameEntryET;
    private WorkoutNameEntryDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_workout_name, null);

        builder.setView(view)
                .setTitle("Add Workout Routine")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add Name", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String weightStr = weightEntryET.getText().toString();
                        String workoutName = workoutNameEntryET.getText().toString();

                        listener.applyValue(workoutName);
                    }
                });
        workoutNameEntryET = view.findViewById(R.id.nameEntryET);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (WorkoutNameEntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement WorkoutNameEntryDialog Listener");
        }
    }

    public interface WorkoutNameEntryDialogListener {
        void applyValue(String workoutNameEntryStr);
        //add weight entry her
    }
}
