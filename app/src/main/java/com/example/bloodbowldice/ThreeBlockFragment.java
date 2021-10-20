package com.example.bloodbowldice;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


public class ThreeBlockFragment extends DialogFragment {

    Dialog dialog;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.three_block_result, null);
        // build dialog:
        builder.setView(view);
        dialog = builder.create();

        // to adjust imageview weight do this first:
        // NOT USED. JUST FOR TEMPLATE
        /*LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );*/

        // do stuff:
        ImageView block1, block2, block3;
        block1 = (ImageView) view.findViewById(R.id.twoBlock1);
        block2 = (ImageView) view.findViewById(R.id.threeBlock2);
        block3 = (ImageView) view.findViewById(R.id.threeBlock3);
        block1.setImageDrawable(rollBlockDie());
        block2.setImageDrawable(rollBlockDie());
        block3.setImageDrawable(rollBlockDie());

        /*block1.setAdjustViewBounds(true);
        block2.setAdjustViewBounds(true);
        block3.setAdjustViewBounds(true);*/

        // finally to adjust weight n size n all shit
        /*block1.setLayoutParams(layoutParams);
        block2.setLayoutParams(layoutParams);
        block3.setLayoutParams(layoutParams);*/

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

    // it is either use onCreateView or use OnCreateDialog. OnCreateDialog's is cuter
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.three_block_result, container, false);

        //alternative way instead of using getView()
        *//*View view = inflater.inflate(R.layout.three_block_result, container, false);
        ImageView block1, block2, block3;
        block1 = (ImageView) view.findViewById(R.id.threeBlock1);
        block2 = (ImageView) view.findViewById(R.id.threeBlock2);
        block3 = (ImageView) view.findViewById(R.id.threeBlock3);
        block1.setImageDrawable(rollBlockDie());
        block2.setImageDrawable(rollBlockDie());
        block3.setImageDrawable(rollBlockDie());
        return view;*//*
    }*/

    // no longer needed because onCreate Dialog is used. check if true hehe
    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView block1, block2, block3;
        block1 = (ImageView) getView().findViewById(R.id.threeBlock1);
        block2 = (ImageView) getView().findViewById(R.id.threeBlock2);
        block3 = (ImageView) getView().findViewById(R.id.threeBlock3);

        block1.setImageDrawable(rollBlockDie());
        block2.setImageDrawable(rollBlockDie());
        block3.setImageDrawable(rollBlockDie());
    }*/
}
