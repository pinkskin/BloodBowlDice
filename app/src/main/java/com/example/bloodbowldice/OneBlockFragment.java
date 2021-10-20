package com.example.bloodbowldice;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;


public class OneBlockFragment extends DialogFragment {

    Dialog dialog;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.one_block_result, null);
        builder.setView(view);
        dialog = builder.create();

        ImageView block1 = (ImageView) view.findViewById(R.id.twoBlock1);
        block1.setImageDrawable(rollBlockDie());

        LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) view.findViewById(R.id.linearLayout);
        linearLayoutCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    // return single block die result
    private Drawable rollBlockDie() {
        Drawable blockDieFace;
        int randomNumber = (int) (Math.random() * 6) + 1;

        if (randomNumber == 1) {
            blockDieFace = getResources().getDrawable(R.drawable.attacker_down);
        }
        else if (randomNumber == 2) {
            blockDieFace = getResources().getDrawable(R.drawable.both_down);
        }
        else if (randomNumber == 3) {
            blockDieFace = getResources().getDrawable(R.drawable.defender_stumbles);
        }
        else if (randomNumber == 4) {
            blockDieFace = getResources().getDrawable(R.drawable.defender_down);
        }
        else {
            blockDieFace = getResources().getDrawable(R.drawable.push_back);
        }
        return blockDieFace;
    }
}
