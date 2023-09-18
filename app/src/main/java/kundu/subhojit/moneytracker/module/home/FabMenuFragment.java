package kundu.subhojit.moneytracker.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kundu.subhojit.moneytracker.R;

public class FabMenuFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floating_button_menu, container, false);

        // Handle clicks on Add Income and Add Expense options
        view.findViewById(R.id.text_add_income).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Add Income action
                dismiss(); // Close the dialog
            }
        });

        view.findViewById(R.id.text_add_expense).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Add Expense action
                dismiss(); // Close the dialog
            }
        });

        return view;
    }
}
